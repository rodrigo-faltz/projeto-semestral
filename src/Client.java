import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        final String HOST = "localhost";
        final int PORT = 12345;

        try (Socket socket = new Socket(HOST, PORT)) {
            System.out.println("Connected to the server");

            // Set up input and output streams
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(
                    socket.getOutputStream(), true);

            // Reader for client console input
            BufferedReader consoleReader = new BufferedReader(
                    new InputStreamReader(System.in));

            String serverMessage;

            // Read the prompt from the server
            while ((serverMessage = reader.readLine()) != null) {
                System.out.println(serverMessage);

                if (serverMessage.contains("Please enter your message:")) {
                    // Read user's message and send to server
                    String clientMessage = consoleReader.readLine();
                    writer.println(clientMessage);
                } else if (serverMessage.contains("Here is the message from the other client:")) {
                    // Read the other client's message
                    String otherClientMessage = reader.readLine();
                    System.out.println(otherClientMessage);
                    break; // Exit after receiving the message
                }
            }

            // Close the socket
            //socket.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
