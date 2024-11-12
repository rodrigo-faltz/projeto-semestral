package projeto;
import java.io.Serializable;


public class Message implements Serializable {
    private static final long serialVersionUID = 1L;
    private Grid grid;
    private Player player;
    private Action action;
    private int numeroDoPlayer = 0;
    private String usuario;
    private String senha;
    private String[][] leaderboard = new String[10][2];

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
        ENVIA_GRID, ENVIA_PLAYER, ENVIA_VITORIA, CONNECT, 
        DISCONNECT, COMECAR_JOGO, VEZ_DO_PLAYER, ERROU, 
        LOGIN_FAIL, TELA_APOS_LOGIN, TELA_LEADERBOARD ,
        TELA_LEADERBOARD_ANO_VITORIA, TELA_LEADERBOARD_MES_VITORIA,
        TELA_LEADERBOARD_SEMANA_VITORIA, TELA_LEADERBOARD_ANO_DERROTA,
        TELA_LEADERBOARD_MES_DERROTA, TELA_LEADERBOARD_SEMANA_DERROTA,
        SAIU_LEADERBOARD,
    }

   public void setUsuario(String usuario) {
       this.usuario = usuario;
   }

   public void setSenha(String senha) {
       this.senha = senha;
   }
   public void setLeaderboard(String[][] leaderboard) {
       this.leaderboard = leaderboard;
   }    

   public String getUsuario() {
       return usuario;
   }

   public String getSenha() {
       return senha;
   }
   public String[][] getLeaderboard() {
       return leaderboard;
   }



   

    
}
