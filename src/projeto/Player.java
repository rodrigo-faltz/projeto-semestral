package projeto;
import java.io.Serializable;
public class Player implements Serializable{
    private static final long serialVersionUID = 1L;
    private static  Player instance;
    private int numero;

    public Player(int numero)
    {
        this.numero = numero;
    }

    public Player()
    {

    }
    public static synchronized Player getInstance() {
        if (instance == null) {
            instance = new Player();
        }
        return instance;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getNumero() {
        return numero;
    }
    
}
