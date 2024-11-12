package projeto;

import javax.swing.*;
import java.net.Socket;

public class Teste {
    
    private static ClienteService service;
    private static Socket socket;
        public static void main(String[] args) {
            
            SwingUtilities.invokeLater(new Runnable(){
                public void run() {
                    System.setProperty("sun.java2d.uiScale", "1.0");
                    service = new ClienteService();
                    socket = service.connect();
                    new TelaLogin(service, socket);
                }
            });
        }
    }