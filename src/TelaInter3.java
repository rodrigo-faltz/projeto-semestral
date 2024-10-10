public class TelaInter3 {

    private TelaInter2 telaInter2;

    public TelaInter3(ClientConnection connection, int[][] grid) {
        telaInter2 = new TelaInter2(connection, grid);
        new WAIT(connection, grid);
        telaInter2.disposeFrame();
    }
}