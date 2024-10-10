import javax.swing.Timer;

public class TelaInter3 {

    private TelaInter2 telaInter2;
    private Timer timer;

    public TelaInter3(ClientConnection connection, int[][] grid) {
        telaInter2 = new TelaInter2(connection, grid);

        // Delay the execution of WAIT to allow TelaInter2 to be fully rendered
        timer = new Timer(500, e -> {
            // Stop the timer so WAIT runs only once
            timer.stop();

            // Run the WAIT logic using the singleton instance
            WAIT.getInstance(connection, grid);
            telaInter2.disposeFrame();
        });
        timer.setRepeats(false);
        timer.start();
    }
}