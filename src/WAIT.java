import java.io.IOException;

public class WAIT {

    public WAIT (ClientConnection connection, int[][] grid) { 
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
}
