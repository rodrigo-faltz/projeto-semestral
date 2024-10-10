package projeto;



import java.io.Serializable;

public class Grid implements Serializable{
    
    private static final long serialVersionUID = 1L;
    private int grid[][],numeroDeAcertosPlayer, 
     AcertosNave2, AcertosNave3, AcertosNave4,
     AcertosNave5;

    

    public Grid(){
        grid= new int[10][10];
        
        numeroDeAcertosPlayer = 0;
        
        AcertosNave2 = 2;
        
        AcertosNave3 = 3;
        
        AcertosNave4 = 4;
        
        AcertosNave5 = 5;
    
    }

    public void setGridDoPlayer(int[][] gridDoPlayer) {
        System.arraycopy(gridDoPlayer, 0, this.grid, 0, gridDoPlayer.length);
    }
    
    public int[][] getGridDoPlayer() {
        return grid;
    }  

    public void setAcertosNave2(int acertosNave2) {
        AcertosNave2 = acertosNave2;
    }
    
    public void setAcertosNave3(int acertosNave3) {
        AcertosNave3 = acertosNave3;
    }
    
    public void setAcertosNave4(int acertosNave4) {
        AcertosNave4 = acertosNave4;
    }
    
    public void setAcertosNave5(int acertosNave5) {
        AcertosNave5 = acertosNave5;
    }
    
    public void setNumeroDeAcertosPlayer(int numeroDeAcertosPlayer) {
        this.numeroDeAcertosPlayer = numeroDeAcertosPlayer;
    }
    
    public void calculaAcertosNave2() {
        AcertosNave2--;
    }
    
    public void calculaAcertosNave3() {
        AcertosNave3--;
    }
    
    public void calculaAcertosNave4() {
        AcertosNave4--;
    }
    
    public void calculaAcertosNave5() {
        AcertosNave5--;
    }
    
    public void calculaNumeroDeAcertosPlayer() {
        this.numeroDeAcertosPlayer = numeroDeAcertosPlayer+1;
    }
    
    public int getNumeroDeAcertosPlayer() {
        return numeroDeAcertosPlayer;
    }
    
    public int getAcertosNave2() {
        return AcertosNave2;
    }
    
    public int getAcertosNave3() {
        return AcertosNave3;
    }
    
    public int getAcertosNave4() {
        return AcertosNave4;
    }
    
    public int getAcertosNave5() {
        return AcertosNave5;
    }
    
}