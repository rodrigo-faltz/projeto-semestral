

import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class Servidor {
    private static final int PORTA = 12345;
    private static ExecutorService pool = Executors.newFixedThreadPool(2);
    private static int playerCounter = 1; // Controla o número do player
    private static ClienteHandler cliente1 = null;
    private static ClienteHandler cliente2 = null;
    private static int vez = 1; // Controla de quem é a vez
    private static boolean jogoFinalizado = false;
    private static Grid gridCliente1 = null;
    private static Grid gridCliente2 = null;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORTA)) {
            System.out.println("Servidor aguardando conexões...");

            while (playerCounter <= 2) { // Aceita apenas dois jogadores
                Socket clienteSocket = serverSocket.accept();
                System.out.println("Cliente conectado: " + clienteSocket.getInetAddress());

                // Atribuir um número de player ao cliente
                int playerNumber = playerCounter;
                ClienteHandler handler = new ClienteHandler(clienteSocket, playerNumber);
                if (playerNumber == 1) {
                    cliente1 = handler;
                } else {
                    cliente2 = handler;
                }
                playerCounter++; // Incrementa para o próximo cliente

                pool.execute(handler); // Inicia o cliente em uma nova thread
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método sincronizado para verificar se o jogo pode começar
    public static synchronized void verificarIniciarJogo() {
        if (cliente1 != null && cliente2 != null && gridCliente1 != null && gridCliente2 != null) {
            // Ambos os clientes enviaram seus grids, iniciar o jogo
            enviarParaTodosClientes("COMECAR_JOGO", null);
            mudarVez(); // Inicia o jogo definindo de quem é a vez
        }
    }

    // Método para verificar a vitória
    public static synchronized void verificarVitoria(int playerNumber) {
        if (!jogoFinalizado) {
            jogoFinalizado = true;
            enviarParaTodosClientes("FIM", "Player " + playerNumber + " venceu!");
        }
    }

    // Método para alternar a vez entre os jogadores
    public static synchronized void mudarVez() {
        vez = (vez == 1) ? 2 : 1; // Alterna entre Player 1 e Player 2
        enviarParaTodosClientes("VEZ", vez); // Envia a vez para ambos os jogadores
    }

    // Método para enviar mensagem para ambos os clientes
    public static synchronized void enviarParaTodosClientes(String tipo, Object dado) {
        try {
            if (cliente1 != null) {
                cliente1.enviarDado(tipo, dado);
            }
            if (cliente2 != null) {
                cliente2.enviarDado(tipo, dado);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método para enviar dados de um cliente ao outro
    public static synchronized void enviarParaOutroCliente(Object dado, int playerNumber) {
        try {
            if (playerNumber == 1 && cliente2 != null) {
                cliente2.enviarDado("GRID", dado);  // Repasse do Cliente 1 para o Cliente 2
            } else if (playerNumber == 2 && cliente1 != null) {
                cliente1.enviarDado("GRID", dado);  // Repasse do Cliente 2 para o Cliente 1
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Classe interna ClienteHandler
    static class ClienteHandler implements Runnable {
        private Socket socket;
        private int playerNumber;
        private ObjectOutputStream out;
        private ObjectInputStream in;

        public ClienteHandler(Socket socket, int playerNumber) {
            this.socket = socket;
            this.playerNumber = playerNumber;
        }

        @Override
        public void run() {
            try {
                // Ordem de criação dos streams: primeiro Output, depois Input
                out = new ObjectOutputStream(socket.getOutputStream());
                in = new ObjectInputStream(socket.getInputStream());

                // Envia o número do jogador ao cliente
                out.writeObject(playerNumber);
                out.flush();

                System.out.println("Player " + playerNumber + " está conectado.");

                while (!socket.isClosed()) {
                    try {
                        Object recebido = in.readObject();
                        System.out.println(recebido);
                        processarRecebido(recebido);
                    } catch (EOFException e) {
                        System.out.println("Conexão fechada pelo cliente: " + playerNumber);
                        break; // Sai do loop
                    } catch (IOException e) {
                        System.out.println("Erro de I/O: " + e.getMessage());
                        break; // Sai do loop
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                fecharConexoes();
            }
        }

        // Método para enviar dados ao cliente
        public void enviarDado(String tipo, Object dado) {
            try {
                out.writeObject(tipo); // Envia o tipo da mensagem
                out.writeObject(dado); // Envia o dado em si (vez ou grid)
                out.flush();
            } catch (IOException e) {
                System.err.println("Conexão perdida com o cliente " + playerNumber);
                e.printStackTrace();
            }
        }

        private void processarRecebido(Object recebido) {
            if (recebido instanceof String) {
                String mensagem = (String) recebido;
                if ("VITORIA".equals(mensagem)) {
                    Servidor.verificarVitoria(playerNumber);
                } else if ("TROCA_TURNO".equals(mensagem)) {
                    Servidor.mudarVez();
                } else if ("GRID".equals(mensagem)) {
                    try {
                        Grid gridRecebido = (Grid) in.readObject();
                        if (playerNumber == 1) {
                            Servidor.gridCliente1 = gridRecebido;
                        } else if (playerNumber == 2) {
                            Servidor.gridCliente2 = gridRecebido;
                        }
                        System.out.println("Grid recebido do Player " + playerNumber);
                        Servidor.verificarIniciarJogo();
                    } catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                } else if ("JOGADA".equals(mensagem)) {
                    try {
                        int[] coordenadas = (int[]) in.readObject();
                        // Processar a jogada
                        // Aqui você deve implementar a lógica para atualizar o grid do oponente
                        // e verificar se houve acerto ou erro, e então enviar o resultado aos clientes

                        // Exemplo simplificado:
                        // Enviar resultado para o jogador que fez a jogada
                        enviarDado("RESULTADO", "Acertou"); // ou "Errou"

                        // Enviar atualização para o outro jogador
                        Servidor.enviarParaOutroCliente(coordenadas, playerNumber);

                        // Alternar a vez
                        Servidor.mudarVez();
                    } catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("Mensagem desconhecida: " + mensagem);
                }
            } else {
                System.out.println("Objeto não esperado do tipo: " + recebido.getClass().getName());
            }
        }

        private void fecharConexoes() {
            try {
                if (in != null) in.close();
                if (out != null) out.close();
                if (socket != null) socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
