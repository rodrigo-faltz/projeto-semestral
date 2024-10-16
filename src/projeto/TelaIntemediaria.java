package projeto;


import javax.swing.*;
import projeto.Message.Action;
import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ResourceBundle;

public class TelaIntemediaria {

    JFrame frame;
    Player player;
    Grid grid;
    Socket socket;
    ClienteService service;

    ResourceBundle bundle = LanguageManager.getResourceBundle();

    public TelaIntemediaria(Grid grid, Player player, ClienteService service, Socket socket) {
        Imagens imgs = new Imagens();
        frame = new JFrame(bundle.getString("titleIntermediaria"));
        JLabel titulo = new JLabel();
        JPanel painel = new JPanel(new BorderLayout());
        JLabel imagem = new JLabel();
        String msgplayer;
        this.service = service;
        this.player = player;
        this.grid = grid;
        this.socket = socket;

        
        

        
        if(player.getNumero() == 2){
            msgplayer = bundle.getString("messageIntermediariaImp");
        }
        else{
            msgplayer = bundle.getString("messageIntermediariaRep");
        }
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
        titulo.setText(msgplayer);
        titulo.setPreferredSize(new Dimension(600, 50));

        // Configurações da imagem
        if(player.getNumero()==1){
            imagem.setIcon(imgs.atkImp);
        }
        else{
            imagem.setIcon(imgs.atkRep);
        }
        imagem.setHorizontalAlignment(SwingConstants.CENTER);
        imagem.setPreferredSize(new Dimension(600, 250));

        // Configurações do botão next





        
        
        // Adicionando componentes ao painel principal
        painel.add(titulo, BorderLayout.NORTH);
        painel.add(imagem, BorderLayout.CENTER);

        // Adicionando painel ao frame
        frame.add(painel);
        frame.pack();
        frame.setVisible(true);
        System.out.println("Teste 0.4");
        new Thread(new ListenerSocket(service.getSocket())).start();

        
    }

    private class ListenerSocket implements Runnable
    {
        private ObjectInputStream input;

        public ListenerSocket(Socket socket)
        {
            
                this.input = service.getInput();
            
        }

        @Override
        public void run()
        {
            Message message = null;
            try
                {
                    System.out.println("Teste 1");
                    while (true)
                        {
                            message = (Message) input.readObject();
                            System.out.println("Teste 2");
                            Action action = message.getAction();
                            System.out.println(action);
                            if(action.equals(Action.ENVIA_PLAYER))
                            {
                                player.setNumero(message.getNumeroDoPlayer());
                                System.out.println("Recebeu o player: "+message.getNumeroDoPlayer());
                                break;
                            }

                            

                            if(action.equals(Action.ENVIA_VITORIA))
                            {
                                
                                System.out.println("Perdeu");
                                new TelaDerrota(message.getNumeroDoPlayer()); // Criar tela de derrota
                                frame.dispose();
                                break;
                            }

                            if(action.equals(Action.VEZ_DO_PLAYER))
                            {   
                                System.out.println(message.getAction());
                                new TelaDeAtaque(grid, player, service, socket);
                                frame.dispose();
                                break;
                                
                            }

                        }

                }
            catch(IOException e)
            {
                Logger.getLogger(ServidorService.class.getName()).log(Level.SEVERE, null, e);
            }
            catch(ClassNotFoundException e)
            {
                Logger.getLogger(ServidorService.class.getName()).log(Level.SEVERE, null, e);
            }

        }
    }


}
