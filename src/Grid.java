
public class Grid {
    private static Grid instance;
    private int gridDoPlayer1[][], gridDoPlayer2[][], player, numeroDeAcertosPlayer1, 
    numeroDeAcertosPlayer2, AcertosNave2P1, AcertosNave2P2, AcertosNave3P1, AcertosNave3P2, AcertosNave4P1,
    AcertosNave4P2, AcertosNave5P1, AcertosNave5P2;

    public static Grid getInstance() {
        if (instance == null) {
            instance = new Grid();
        }
        return instance;
    }

    public Grid(){
        gridDoPlayer1 = new int[10][10];
        gridDoPlayer2 = new int[10][10];
        player = 1;
        numeroDeAcertosPlayer1 = 0;
        numeroDeAcertosPlayer2 = 0;

        AcertosNave2P1 = 2;
        AcertosNave2P2 = 2;

        AcertosNave3P1 = 3;
        AcertosNave3P2 = 3;

        AcertosNave4P1 = 4;
        AcertosNave4P2 = 4;

        AcertosNave5P1 = 5;
        AcertosNave5P2 = 5;


    }

    public void setGridDoPlayer1(int[][] gridDoPlayer1) {
        System.arraycopy(gridDoPlayer1, 0, this.gridDoPlayer1, 0, gridDoPlayer1.length);
    }
    public void setGridDoPlayer2(int[][] gridDoPlayer2) {
        System.arraycopy(gridDoPlayer2, 0, this.gridDoPlayer2, 0, gridDoPlayer2.length);
    }
    public int[][] getGridDoPlayer1() {
        return gridDoPlayer1;
    }
    public int[][] getGridDoPlayer2() {
        return gridDoPlayer2;
    }
    public void setPlayer(int player) {
        this.player = player;
    }
    public int getPlayer() {
        return player;
    }

    public void setAcertosNave2P1(int acertosNave2P1) {
        AcertosNave2P1 = acertosNave2P1;
    }
    public void setAcertosNave2P2(int acertosNave2P2) {
        AcertosNave2P2 = acertosNave2P2;
    }
    public void setAcertosNave3P1(int acertosNave3P1) {
        AcertosNave3P1 = acertosNave3P1;
    }
    public void setAcertosNave3P2(int acertosNave3P2) {
        AcertosNave3P2 = acertosNave3P2;
    }
    public void setAcertosNave4P1(int acertosNave4P1) {
        AcertosNave4P1 = acertosNave4P1;
    }
    public void setAcertosNave4P2(int acertosNave4P2) {
        AcertosNave4P2 = acertosNave4P2;
    }
    public void setAcertosNave5P1(int acertosNave5P1) {
        AcertosNave5P1 = acertosNave5P1;
    }
    public void setAcertosNave5P2(int acertosNave5P2) {
        AcertosNave5P2 = acertosNave5P2;
    }
    public void setNumeroDeAcertosPlayer1(int numeroDeAcertosPlayer1) {
        this.numeroDeAcertosPlayer1 = numeroDeAcertosPlayer1;
    }
    public void setNumeroDeAcertosPlayer2(int numeroDeAcertosPlayer2) {
        this.numeroDeAcertosPlayer2 = numeroDeAcertosPlayer2;
    }
    public void calculaAcertosNave2P1() {
        AcertosNave2P1--;
    }
    public void calculaAcertosNave2P2() {
        AcertosNave2P2--;
    }
    public void calculaAcertosNave3P1() {
        AcertosNave3P1--;
    }
    public void calculaAcertosNave3P2() {
        AcertosNave3P2--;
    }
    public void calculaAcertosNave4P1() {
        AcertosNave4P1--;
    }
    public void calculaAcertosNave4P2() {
        AcertosNave4P2--;
    }
    public void calculaAcertosNave5P1() {
        AcertosNave5P1--;
    }
    public void calculaAcertosNave5P2() {
        AcertosNave5P2--;
    }
    public void calculaNumeroDeAcertosPlayer1() {
        this.numeroDeAcertosPlayer1 = numeroDeAcertosPlayer1+1;
    }
    public void calculaNumeroDeAcertosPlayer2() {
        this.numeroDeAcertosPlayer2 = numeroDeAcertosPlayer2+1;
    }
    public int getNumeroDeAcertosPlayer1() {
        return numeroDeAcertosPlayer1;
    }
    public int getNumeroDeAcertosPlayer2() {
        return numeroDeAcertosPlayer2;
    }
    public int getAcertosNave2P1() {
        return AcertosNave2P1;
    }
    public int getAcertosNave2P2() {
        return AcertosNave2P2;
    }
    public int getAcertosNave3P1() {
        return AcertosNave3P1;
    }
    public int getAcertosNave3P2() {
        return AcertosNave3P2;
    }
    public int getAcertosNave4P1() {
        return AcertosNave4P1;
    }
    public int getAcertosNave4P2() {
        return AcertosNave4P2;
    }
    public int getAcertosNave5P1() {
        return AcertosNave5P1;
    }
    public int getAcertosNave5P2() {
        return AcertosNave5P2;
    }
}