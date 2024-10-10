package projeto;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.*;

import projeto.Message.Action;


public class ClienteService {
    private Socket socket;
    private ObjectOutputStream output;
    private ObjectInputStream input;

    public Socket connect() {
        try {
            this.socket = new Socket("localhost", 12345);
            this.output = new ObjectOutputStream(socket.getOutputStream());
            this.input = new ObjectInputStream(socket.getInputStream());
            System.out.println("Conexão estabelecida com sucesso!");
        } catch (UnknownHostException e) {
            Logger.getLogger(ServidorService.class.getName()).log(Level.SEVERE, "Host desconhecido", e);
            return null; // Retorna null em caso de falha
        } catch (IOException e) {
            Logger.getLogger(ServidorService.class.getName()).log(Level.SEVERE, "Erro de I/O durante a conexão", e);
            return null; // Retorna null em caso de falha
        }
        return socket;
    }

    public void envia(Message message) {
        if (output != null) {
            try {
                output.writeObject(message);
                output.flush(); // Garante que o objeto foi enviado
            } catch (IOException e) {
                Logger.getLogger(ServidorService.class.getName()).log(Level.SEVERE, "Erro ao enviar mensagem", e);
            }
        } else {
            System.out.println("O output stream não foi inicializado.");
        }
    }

    public ObjectInputStream getInput() {
        if (input == null) {
            System.out.println("InputStream não inicializado.");
        }
        return input;
    }

    public ObjectOutputStream getOutput() {
        if (output == null) {
            System.out.println("OutputStream não inicializado.");
        }
        return output;
    }

    public Socket getSocket() {
        if (socket == null) {
            System.out.println("Socket não inicializado.");
        }
        return socket;
    }
}

