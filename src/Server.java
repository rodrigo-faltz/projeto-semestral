import java.io.*;
import java.net.*;
import java.util.concurrent.atomic.AtomicInteger;
import javax.swing.JOptionPane;

public class Server {
    private static final int PORT = 12345;
    private static ClientHandler client1 = null;
    private static ClientHandler client2 = null;
    private static final Object lock = new Object();

    private static int[][] client1Grid = null;
    private static int[][] client2Grid = null;
    private static AtomicInteger gridsReceived = new AtomicInteger(0);
    private static boolean gameStarted = false;

    private enum GameState {
        WAITING_FOR_PLAYERS,
        SETUP_PHASE,
        WAITING_FOR_GRIDS,
        ATTACK_PHASE
    }

    private static GameState gameState = GameState.WAITING_FOR_PLAYERS;

    public static void main(String[] args) {
        System.out.println("Server is listening on port " + PORT);

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            // Wait for first client
            Socket socket1 = serverSocket.accept();
            client1 = new ClientHandler(socket1, 1); // Assign number 1
            System.out.println("Client1 connected");
    
            // Wait for second client
            Socket socket2 = serverSocket.accept();
            client2 = new ClientHandler(socket2, 2); // Assign number 2
            System.out.println("Client2 connected");
    
            // Start client handler threads
            Thread t1 = new Thread(client1);
            Thread t2 = new Thread(client2);
            t1.start();
            t2.start();
    
            // Wait for both threads to finish
            t1.join();
            t2.join();
    
            System.out.println("Server shutting down");
    
        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    // Inner class to handle client communication
    static class ClientHandler implements Runnable {
        private Socket socket;
        private BufferedReader reader;
        private PrintWriter writer;
        private String clientName;
        private String message;
        private static AtomicInteger messagesReceived = new AtomicInteger(0);
        private int clientNumber;
        private ObjectInputStream objIn;
        private ObjectOutputStream objOut;
        int i = 0;


        public ClientHandler(Socket socket, int clientNumber) {
            this.socket = socket;
            this.clientNumber = clientNumber;

            try {

                objOut = new ObjectOutputStream(socket.getOutputStream());
                objOut.flush(); // Important to flush the header
                // Then create ObjectInputStream
                objIn = new ObjectInputStream(socket.getInputStream());

                
                reader = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
                writer = new PrintWriter(
                        socket.getOutputStream(), true);
        
                // Send the client number to the client
                writer.println("CLIENT_NUMBER:" + clientNumber);
        
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

                @Override
        public void run() {
            try {
                // Wait until both clients are connected
                synchronized (lock) {
                    if (client1 != null && client2 != null && !gameStarted) {
                        gameStarted = true;
                        client1.writer.println("START_SETUP");
                        client2.writer.println("START_SETUP");
                    } else {
                        while (!gameStarted) {
                            lock.wait();
                        }
                    }
                }

                // Receive grids from clients
                //writer.println("SEND_GRID");
                ObjectInputStream objIn = new ObjectInputStream(socket.getInputStream());
                int[][] grid = (int[][]) objIn.readObject();

                // Store the grid
                synchronized (lock) {
                    if (this == client1) {
                        client1Grid = grid;
                    } else {
                        client2Grid = grid;
                    }
                    gridsReceived.incrementAndGet();
                    if (gridsReceived.get() == 2) {
                        client1.writer.println("START_GAME");
                        try {
                            Thread.sleep(400);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        client2.writer.println("START_GAME");

                        try {
                            Thread.sleep(400);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        client1.objOut.writeObject(client2Grid);
                        client1.objOut.flush();

                        try {
                            Thread.sleep(400);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        client2.objOut.writeObject(client1Grid);
                        client2.objOut.flush();

                        try {
                            Thread.sleep(400);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        client1.objOut.writeObject(client2Grid);
                        client1.objOut.flush();



                        
                        lock.notifyAll();
                    } else {
                        while (gridsReceived.get() < 2) {
                            lock.wait();
                        }
                    }
                    
                    
                    while (true) {
                        if(i == 0){
                            
                            try {
                                String message = client1.reader.readLine();
                                // Process the message
                                System.out.println("Received1: " + message);
                                if (message.equals("TROCAR_TURNO")) {
                                    synchronized (lock) {
                                        System.out.println("Trocar turno");
                                        client2.writer.println("YOUR_TURN");
                                        i = 1;
                                        // Further handling based on game state
                                    }
                                }
                            } catch (SocketTimeoutException e) {
                                // Handle the timeout (e.g., retry, log, etc.)
                                System.out.println("Read timed out, no data received.");
                            }

                        }
                        else{
                            try {
                                String message2 = client2.reader.readLine();
                                System.out.println("Received2: " + message2);
                                if (message2.equals("TROCAR_TURNO")) {
                                    synchronized (lock) {
                                        System.out.println("Trocar turno");
                                        client1.writer.println("YOUR_TURN");
                                        i = 0;
                                        // Further handling based on game state
                                    }
                                }
                            } catch (SocketTimeoutException e) {
                                // Handle the timeout (e.g., retry, log, etc.)
                                System.out.println("Read timed out, no data received.");
                            }

                        }
                    }
                }

                // Proceed to attack phase
                // Implement game logic for attack phase here...

            } catch (IOException | ClassNotFoundException | InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}
