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

    public ServidorService() {
        try {
            serverSocket = new ServerSocket(12345);

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
                connect(); // Envia a mensagem de conexão
            } catch (IOException e) {
                Logger.getLogger(ServidorService.class.getName()).log(Level.SEVERE, null, e);
            }
        }

        private void connect() {
            try {
                Message message = new Message();
                System.out.println(message.getUsuario());
                System.out.println(message.getSenha());
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
                while (true) {
                    Message message = (Message) input.readObject();
                    Action action = message.getAction();

                    if (action.equals(Action.CONNECT)) {
                        connect(); // Reconecta se necessário
                    } else if (action.equals(Action.DISCONNECT)) {
                        // Lógica para desconectar, se necessário
                    } else if (action.equals(Action.ENVIA_GRID)) {
                        System.out.println("Grid recebido");

                        if(message.getPlayer().getNumero() == 1)
                        {
                            enviarMensagemParaCliente(2, message);
                        }

                        if(message.getPlayer().getNumero() == 2)
                        {
                            enviarMensagemParaCliente(1, message);
                        }
                    } else if (action.equals(Action.ENVIA_PLAYER)) {
                        // Lógica para enviar jogador
                    } else if (action.equals(Action.ENVIA_VITÓRIA)) {
                        // Lógica para enviar vitória
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

    public void enviarMensagemParaCliente(int clienteId, Message message) {
        try {
            if (clienteId == 1 && cliente1 != null) {
                cliente1.output.writeObject(message);
                cliente1.output.flush();
            } else if (clienteId == 2 && cliente2 != null) {
                cliente2.output.writeObject(message);
                cliente2.output.flush();
            }
        } catch (IOException e) {
            Logger.getLogger(ServidorService.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
