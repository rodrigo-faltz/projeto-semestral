package projeto;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.concurrent.atomic.AtomicInteger;
import java.io.EOFException;

import projeto.Message.Action;

import java.io.ObjectInputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServidorService {
    private ServerSocket serverSocket;
    private AtomicInteger playerNumber = new AtomicInteger(1);
    private static ExecutorService pool = Executors.newFixedThreadPool(2); // Usando ExecutorService
    private static ListenerSocket cliente1 = null;
    private static ListenerSocket cliente2 = null;
    private Grid grid1;
    private Grid grid2;

    public ServidorService() {
        System.out.println("Tesatando");
        new CriaDBs();
        

        try {
            serverSocket = new ServerSocket(12345);
            System.out.println("Aguardando a conexão do cliente");

            while (playerNumber.get() <= 2) { // Permite até 2 jogadores
                Socket socket = serverSocket.accept();
                System.out.println("Cliente conectado: " + socket.getInetAddress());

                // Cria o ListenerSocket e adiciona ao pool
                int currentPlayerNumber = playerNumber.getAndIncrement();
                ListenerSocket handler = new ListenerSocket(socket, currentPlayerNumber);
                pool.execute(handler); // Executa o handler no ExecutorService
                
                // Atribui o handler ao cliente correspondente
                if (currentPlayerNumber == 1) {
                    cliente1 = handler;
                } else {
                    cliente2 = handler;
                }
            }


        } catch (IOException e) {
            Logger.getLogger(ServidorService.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            pool.shutdown(); // Desliga o pool de threads quando não for mais necessário
        }
    }

    private class ListenerSocket implements Runnable {
        private ObjectOutputStream output;
        private ObjectInputStream input;
        private int currentPlayerNumber;

        public ListenerSocket(Socket socket, int playerNumber) { // Recebe o número do jogador
            this.currentPlayerNumber = playerNumber; // Atribui o número do jogador
            try {
                this.output = new ObjectOutputStream(socket.getOutputStream());
                this.input = new ObjectInputStream(socket.getInputStream());
                 // Envia a mensagem de conexão
            } catch (IOException e) {
                Logger.getLogger(ServidorService.class.getName()).log(Level.SEVERE, null, e);
            }
        }

        private void connect() {
            try {
                Message message = new Message();
                message.setAction(Action.ENVIA_PLAYER);
                message.setNumeroDoPlayer(currentPlayerNumber); // Usa o número do jogador já definido
                output.writeObject(message);
                output.flush();
                System.out.println("Jogador " + currentPlayerNumber + " conectado.");
            } catch (IOException e) {
                Logger.getLogger(ServidorService.class.getName()).log(Level.SEVERE, null, e);
            }
        }

        @Override
        public void run() {
            try {
                RecebeDoDB receba = null;
                
                while (true) {
                    Message message = (Message) input.readObject();
                    Action action = message.getAction();
                    System.out.println("Mensagem recebida: " + action);
                    System.err.println("Player: " + message.getNumeroDoPlayer());
        
                    if (action.equals(Action.CONNECT)) {
                        receba = new RecebeDoDB();
                        System.out.println("Usuario: " + message.getUsuario());
                        System.out.println("Senha: " + message.getSenha());
                        boolean teste = receba.checaLogin(message.getUsuario(), message.getSenha());
                        System.out.println("Teste login: " + teste);
        
                        if (teste) {
                            connect();
                            message.setAction(Action.ENVIA_PLAYER);
                            message.setNumeroDoPlayer(currentPlayerNumber);
                            output.writeObject(message);
                            output.flush();
                        } else {
                            message.setAction(Action.LOGIN_FAIL);
                            output.writeObject(message);
                            output.flush();
                        }
                    } else if (action.equals(Action.DISCONNECT)) {
                        // Lógica para desconectar, se necessário
                    } else if (action.equals(Action.ENVIA_GRID)) {
                        

                        if(currentPlayerNumber == 1)
                        {
                            grid1 = message.getGrid();
                            System.out.println("Grid 1 recebido");
                            enviarMensagemParaCliente(2, message);
                            System.out.println("Grid 1 enviado");
                        }

                        if(currentPlayerNumber == 2)
                        {
                            grid2 = message.getGrid();
                            System.out.println("Grid 2 recebido");
                            enviarMensagemParaCliente(1, message);
                            System.out.println("Grid 2 enviado");
                        }

                        if (grid1 != null && grid2 != null) {
                            
                            System.out.println("Começar jogo...");
                            message.setAction(Action.COMECAR_JOGO);
                            enviaComecar(message);
                            
                            // Envia a mensagem de início do jogo para ambos os clientes
                            
                            
                        }
                    } else if (action.equals(Action.ENVIA_PLAYER)) {
                        // Lógica para enviar jogador
                    } else if (action.equals(Action.ENVIA_VITORIA)) {
                        int numDoPlayer = message.getNumeroDoPlayer();

                        System.out.println("Mensagem recebida: " + action);
                        System.err.println("Player: "+ numDoPlayer);

                        if(numDoPlayer == 1)
                        {

                            message.setNumeroDoPlayer(2);
                            enviarMensagemParaCliente(2, message);
                        }
                        else if(numDoPlayer == 2)
                        {
                            message.setNumeroDoPlayer(1);
                            enviarMensagemParaCliente(1, message);
                        }
                        
                    } else if (action.equals(Action.VEZ_DO_PLAYER)){
                        int numDoPlayer = message.getNumeroDoPlayer();
                        if(numDoPlayer == 1)
                        {
                            message.setNumeroDoPlayer(2);
                            enviarMensagemParaCliente(2, message);
                        }
                        else if(numDoPlayer == 2)
                        {
                            message.setNumeroDoPlayer(1);
                            enviarMensagemParaCliente(1, message);
                        }
                    }
                    
                }
            } catch (EOFException e) {
                System.out.println("Cliente " + currentPlayerNumber + " desconectado.");
            } catch (IOException | ClassNotFoundException e) {
                Logger.getLogger(ServidorService.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }

    private void mandaGrid(Message message, ObjectOutputStream output) {
        try {
            message.setAction(Action.ENVIA_GRID);
            output.writeObject(message);
            output.flush();
        } catch (Exception e) {
            // Tratar exceções
        }
    }

    private void mandaPlayer(Message message, ObjectOutputStream output) {
        // Implementação se necessário
    }

    private void enviaVitoria(Message message, ObjectOutputStream output) {
        // Implementação se necessário
    }

    private void enviaComecar(Message message) {
        message.setAction(Action.COMECAR_JOGO);
                
                // Enviar para ambos os clientes
        enviarMensagemParaCliente(1, message);
        System.out.println("Enviando começar jogo para Cliente 1");

        enviarMensagemParaCliente(2, message);
        System.out.println("Enviando começar jogo para Cliente2");

        
    }

    public void enviarMensagemParaCliente(int clienteId, Message message) {
        try {
            if (clienteId == 1 && cliente1 != null) {
                cliente1.output.writeObject(message);
                cliente1.output.flush();
            } else if (clienteId == 2 && cliente2 != null) {
                cliente2.output.writeObject(message);
                cliente2.output.flush();
            } else {
                System.out.println("Cliente " + clienteId + " não está conectado.");
            }
        } catch (IOException e) {
            Logger.getLogger(ServidorService.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
}
