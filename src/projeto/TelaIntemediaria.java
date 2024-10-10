package projeto;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import projeto.Message.Action;
import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TelaIntemediaria {

    JFrame frame;
    Player player;
    Grid grid;
    Socket socket;
    ClienteService service;

    public TelaIntemediaria(Grid grid, Player player, ClienteService service, Socket socket) {
        Imagens imgs = new Imagens();
        frame = new JFrame("Batalha Espacial - Transição");
        JLabel titulo = new JLabel();
        JPanel painel = new JPanel(new BorderLayout());
        JButton next = new JButton();
        JLabel imagem = new JLabel();
        String msgplayer;

        
        

        
        if(player.getNumero() == 1){
            msgplayer = "2";
        }
        else{
            msgplayer = "1";
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
        titulo.setText("Agora é a vez do player " + msgplayer);
        titulo.setPreferredSize(new Dimension(600, 50));

        // Configurações da imagem
        if(player.getNumero()==1){
            imagem.setIcon(imgs.republica);
        }
        else{
            imagem.setIcon(imgs.imperio);
        }
        imagem.setHorizontalAlignment(SwingConstants.CENTER);
        imagem.setPreferredSize(new Dimension(600, 250));

        // Configurações do botão next
        next.setIcon(imgs.botaoProsseguir);
        next.setPreferredSize(new Dimension(170, 50));
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setBackground(new Color(7, 8, 28)); // Definindo a cor de fundo do painel do botão
        buttonPanel.add(next); // Adicionando o botão ao painel do botão



        next.setContentAreaFilled(false);
        next.setBorderPainted(false);
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                frame.dispose();
                new TelaDeAtaque(grid, player, service, socket);
            }
        });
        // Adicionando componentes ao painel principal
        painel.add(titulo, BorderLayout.NORTH);
        painel.add(imagem, BorderLayout.CENTER);
        painel.add(buttonPanel, BorderLayout.SOUTH);

        // Adicionando painel ao frame
        frame.add(painel);
        frame.pack();
        frame.setVisible(true);

        
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
                    while ((message = (Message) input.readObject())!=null)
                        {
                            Action action = message.getAction();

                            if(action.equals(Action.ENVIA_PLAYER))
                            {
                                player.setNumero(message.getNumeroDoPlayer());
                                System.out.println("Recebeu o player: "+message.getNumeroDoPlayer());
                                break;
                            }

                            

                            if(action.equals(Action.ENVIA_VITÓRIA))
                            {
                                System.out.println("Perdeu");
                                break;
                            }

                            if(action.equals(Action.VEZ_DO_PLAYER))
                            {
                                new TelaDeAtaque(grid, player, service, socket);
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
