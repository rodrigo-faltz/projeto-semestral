package projeto;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.*;


public class ClienteService 
{
    private Socket socket;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    


    public Socket connect()
    {
        try {
            this.socket = new Socket("localhost", 12345);
            this.output = new ObjectOutputStream(socket.getOutputStream());
        } catch (UnknownHostException e) {
            Logger.getLogger(ServidorService.class.getName()).log(Level.SEVERE, null, e);
        }
        catch(IOException e)
        {
            Logger.getLogger(ServidorService.class.getName()).log(Level.SEVERE, null, e);
        }
        return socket;
    }

    public void envia(Message message)
    { //TODO: envia a mensagem
        try
        {
            output.writeObject(message);
            
        } 
        catch (IOException e)
        {
            Logger.getLogger(ServidorService.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void recebe(Message message)
    {
        try {
            //TODO: lógica do receba
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
    
}
