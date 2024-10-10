import java.io.*;
import java.net.*;

public class ClientConnection {
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;
    private int clientNumber; // Add this field
    private ObjectInputStream objIn;
    private ObjectOutputStream objOut;

    public ClientConnection(String host, int port) throws IOException {
        this.socket = new Socket(host, port);
        this.objOut = new ObjectOutputStream(socket.getOutputStream());
        objOut = new ObjectOutputStream(socket.getOutputStream());
        objOut.flush();
        // Then create ObjectInputStream
        objIn = new ObjectInputStream(socket.getInputStream());

        this.reader = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
        this.writer = new PrintWriter(
                socket.getOutputStream(), true);


        String message = reader.readLine();
        if (message.startsWith("CLIENT_NUMBER:")) {
            String[] parts = message.split(":");
            clientNumber = Integer.parseInt(parts[1]);
            System.out.println("Received client number: " + clientNumber);
        } else {
            throw new IOException("Failed to receive client number from server.");
        }
    }

    public void sendMessage(String message) {
        writer.println(message);
    }

    public String receiveMessage() throws IOException {
        return reader.readLine();
    }

    // public void close() throws IOException {
    //     socket.close();
    // }

    public void sendGrid(int[][] grid) throws IOException {
        objOut.writeObject(grid);
        objOut.flush();
    }
    public void sendMsg(String Msg) throws IOException {
        objOut.writeObject("TROCA_TURNO");
        objOut.flush();
    }
    public int getClientNumber() {
        return clientNumber;
    }
    public boolean testSocketConnetion() {
        return socket.isConnected();
    }
    public int[][] receiveGrid() throws IOException, ClassNotFoundException {
        return (int[][]) objIn.readObject();
    }

    public Socket getSocket() {
        return socket;
    }
}
