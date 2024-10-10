import java.io.IOException;

public class WAIT {

    // Static variable to hold the single instance
    private static WAIT instance;

    // Private constructor to prevent instantiation from other classes
    private WAIT(ClientConnection connection, int[][] grid) {
        try {
            String line = connection.receiveMessage();
            System.out.println("Received message: " + line);
            if ("YOUR_TURN".equals(line)) {
                new TelaDeAtaque(connection, grid);
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Optionally, handle errors such as connection issues
        }
    }

    // Public static method to provide the single instance
    public static WAIT getInstance(ClientConnection connection, int[][] grid) {
        if (instance == null) {
            instance = new WAIT(connection, grid);
        }
        return instance;
    }
}