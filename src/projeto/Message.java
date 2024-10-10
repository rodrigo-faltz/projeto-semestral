package projeto;
import java.io.Serializable;


public class Message implements Serializable {
    private static final long serialVersionUID = 1L;
    private Grid grid;
    private Player player;
    private Action action;
    private int numeroDoPlayer = 0;

    public void setNumeroDoPlayer(int numeroDoPlayer) {
        this.numeroDoPlayer = numeroDoPlayer;
    }

    public int getNumeroDoPlayer() {
        return numeroDoPlayer;
    }


    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Grid getGrid() {
        return grid;
    }

    public Player getPlayer() {
        return player;
    }

    public enum Action {
        ENVIA_GRID, ENVIA_PLAYER, ENVIA_VITÓRIA, CONNECT, DISCONNECT, COMECAR_JOGO
    }

    
}
