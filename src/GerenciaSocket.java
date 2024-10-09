

import java.io.IOException;
import java.net.Socket;

public class GerenciaSocket {
    private static final String ENDERECO_SERVIDOR = "localhost"; // ou IP do servidor
    private static final int PORTA = 12345;
    private static GerenciaSocket instancia;
    private Socket socket;
    
        private GerenciaSocket() {
            try {
                // Inicialize o socket uma única vez
                socket = new Socket(ENDERECO_SERVIDOR, PORTA);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    
        public static GerenciaSocket getInstance() {
            if (instancia == null) {
                instancia = new GerenciaSocket();
            }
            return instancia;
        }
    
        public Socket getSocket() {
            return socket;
        }
    }