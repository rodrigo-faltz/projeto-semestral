

public class Player {
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
