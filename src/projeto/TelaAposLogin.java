package projeto;

import javax.swing.*;
// Replace 'your.package.name' with the actual package name where Grid is located

import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.*;
import projeto.Message.Action;
import java.util.ResourceBundle;

public class TelaAposLogin {
    private ObjectInputStream in; // Stream de entrada para receber dados do servidor
    private ObjectOutputStream out; // Stream de saída para enviar dados ao servidor
    private Grid grid;
    private String tipo;
    private Socket socket;
    private Player player;
    private ClienteService service;
    
    
    ResourceBundle bundle = LanguageManager.getResourceBundle();
    JFrame frame = new JFrame(bundle.getString("titleApos"));

    public TelaAposLogin(Grid grid, Socket socket, ClienteService service, Player player) {
        
        JLabel titulo = new JLabel();
        JPanel painel = new JPanel(new BorderLayout());
        this.grid = grid;
        JLabel imagem = new JLabel();
        this.socket = socket;
        this.service = service;
        this.player = player;
        tipo = "";

        // Configurações do frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(600, 300));
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        // Configurações do painel
        painel.setBackground(new Color(7, 8, 28));

        // Configurações do título
        titulo.setFont(new Font("Dialog", Font.BOLD, 18));
        titulo.setForeground(new Color(255, 235, 15));
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setText(bundle.getString("messageApos"));
        titulo.setPreferredSize(new Dimension(600, 50));

        // Configurações da imagem
        painel.add(titulo, BorderLayout.NORTH);
        painel.add(imagem, BorderLayout.CENTER);

        // Adicionando painel ao frame
        frame.add(painel);
        frame.pack();
        frame.setVisible(true);
        System.out.println(player.getNumero());
        
        new Thread(new ListenerSocket(this.socket)).start();

       
        
        

        if (this.grid == null) {
            System.out.println("Grid recebido está vazio.");
        } else {
            System.out.println("Grid recebido: ");
        }
        

        System.out.println("Player TelaAposSetup: "+player.getNumero());
    }

    private class ListenerSocket implements Runnable {
        private ObjectInputStream input;
        
    
        public ListenerSocket(Socket socket) {
            
            
                if (socket != null && socket.isConnected() && !socket.isClosed()) {
                    System.out.println("Criando ObjectInputStream...");
                    this.input = service.getInput();
                    System.out.println("ObjectInputStream criado com sucesso.");
                } else {
                    System.out.println("Socket está nulo, não conectado ou fechado.");
                }
        }
        
            
        @Override
        public void run() {
            if (this.input == null) {
                System.out.println("InputStream está nulo. Não pode continuar.");
                return; // Impede que o código continue se o input não for inicializado
            }
            

            
            Message message = null;



            try {
                while ((message = (Message) input.readObject()) != null) {
                    System.out.println(service.getSocket().isClosed());
                    System.out.println(player.getNumero());
                    //message = (Message) input.readObject();
                    
                    Action action = message.getAction();
                    System.out.println("Mensagem recebida: " + action);
    
                    if (action.equals(Action.ENVIA_PLAYER)) {
                        
                        System.out.println("Testando: " + message.getNumeroDoPlayer());
                        message.setAction(Action.ENVIA_PLAYER);
                        service.envia(message);
                        
                    }
                    if (action.equals(Action.TELA_APOS_LOGIN)) {
                        System.out.println("Received TELA_APOS_LOGIN");
                        new TelaDeSetup(player, grid, socket, service);
                        frame.dispose();
                        // SwingUtilities.invokeLater(() -> {
                        //     new TelaDeSetup(player, grid, socket, service);
                        //     frame.dispose();
                        // });
                        break;
                    }
                }
            } catch (IOException e) {
                System.out.println("Erro na leitura de objeto: " + e.getMessage());
                Logger.getLogger(TelaAposLogin.class.getName()).log(Level.SEVERE, "Erro na leitura de objeto", e);
            } catch (ClassNotFoundException e) {
                System.out.println("Classe não encontrada: " + e.getMessage());
                Logger.getLogger(TelaAposLogin.class.getName()).log(Level.SEVERE, "Classe não encontrada", e);
            }
        }
    }
    
    

   


}
