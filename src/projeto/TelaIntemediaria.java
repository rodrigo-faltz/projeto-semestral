package projeto;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import java.awt.*;
import java.io.*;
import java.net.*;

public class TelaIntemediaria {

    JFrame frame;
    Player player;
    Grid grid;
    private ObjectInputStream in; // Stream de entrada para receber dados do servidor
    private ObjectOutputStream out; // Stream de saída para enviar dados ao servidor
    Socket socket;

    public TelaIntemediaria(Grid grid) {
        Imagens imgs = new Imagens();
        frame = new JFrame("Batalha Espacial - Transição");
        JLabel titulo = new JLabel();
        JPanel painel = new JPanel(new BorderLayout());
        JButton next = new JButton();
        JLabel imagem = new JLabel();
        String msgplayer;

        iniciarThreadRecepcao();
        

        
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
                new TelaDeAtaque(grid);
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

    private void iniciarThreadRecepcao() {
        new Thread(() -> {

            try {
                this.in = new ObjectInputStream(socket.getInputStream());
                this.out = new ObjectOutputStream(socket.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                // Loop que fica ouvindo as mensagens do servidor
                while (true) {
                    String tipo = (String) in.readObject();

                    if (tipo.equals("VEZ")) {
                        int vez = (int) in.readObject();
                        System.out.println("Vez do jogador: " + vez);

                        if (vez == player.getNumero()) {
                            new TelaDeAtaque(grid);
                            break;
                        } else {
                            //habilitarControlesDoJogador(false); //ideia boa
                        }
                    } 
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }).start();
    }

    


}
