package projeto;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import projeto.Message.Action;

import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.logging.*;

public class TelaInicial extends JFrame implements ActionListener {
    private JButton novoJogoButton, continuarButton, comoJogarButton;
    private JPanel painel1, painel2, painel3, painel4;
    private JLabel vezDeQuem;
    Imagens imgs;
    private Player player;
    private Socket socket;
    private Message message;
    private ClienteService service; 
    private Grid grid;



    public TelaInicial() {
        super("Batalha Espacial - Tela de Início");
        
        imgs = new Imagens();
        Container caixa = getContentPane();
        grid = new Grid();
        message = new Message();
        player = new Player();
        service = new ClienteService();


        

        caixa.setLayout(new GridLayout(4, 1));

        painel1 = new JPanel(new FlowLayout());
        painel2 = new JPanel(new FlowLayout());
        painel3 = new JPanel(new FlowLayout());
        painel4 = new JPanel(new FlowLayout());

        novoJogoButton = new JButton(imgs.botaoNovoJogo);
        continuarButton = new JButton(imgs.botaoCarregar);
        comoJogarButton = new JButton(imgs.botaoComoJogar);
        vezDeQuem = new JLabel(imgs.batalha);

        novoJogoButton.setContentAreaFilled(false);
        novoJogoButton.setBorderPainted(false);
        continuarButton.setContentAreaFilled(false);
        continuarButton.setBorderPainted(false);
        comoJogarButton.setContentAreaFilled(false);
        comoJogarButton.setBorderPainted(false);

        novoJogoButton.setPreferredSize(new Dimension(334, 109));
        continuarButton.setPreferredSize(new Dimension(334, 109));
        comoJogarButton.setPreferredSize(new Dimension(334, 109));
        vezDeQuem.setPreferredSize(new Dimension(500, 150));
        
        novoJogoButton.addActionListener(this);
        continuarButton.addActionListener(this);
        comoJogarButton.addActionListener(this);

        painel1.add(novoJogoButton);
        painel2.add(continuarButton);
        painel3.add(vezDeQuem);
        painel4.add(comoJogarButton);
        
        caixa.add(painel3);
        caixa.add(painel1);
        caixa.add(painel2);
        caixa.add(painel4);

        painel1.setBackground(imgs.corDoFundo);
        painel2.setBackground(imgs.corDoFundo);
        painel3.setBackground(imgs.corDoFundo);
        painel4.setBackground(imgs.corDoFundo);

        setSize(500, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - this.getSize().width / 2, 0);

        System.out.println("Player TelaInicial: "+player.getNumero());
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == novoJogoButton) {
            
            new LoginWindow(player, grid, service, this.message);
            dispose();
        }

        if (e.getSource() == continuarButton) {
            //TODO: ;
        }

        if (e.getSource() == comoJogarButton) {
            
            //TODO: ;
        
        }
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

                            if(action.equals(Action.ENVIA_GRID))
                            {
                                System.out.println("Recebeu o grid, ta errado");
                                break;
                            }

                            if(action.equals(Action.ENVIA_VITORIA))
                            {
                                System.out.println("Como que recebe vitória se nem começou?");
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
