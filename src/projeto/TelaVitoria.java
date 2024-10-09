package projeto;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;




public class TelaVitoria {
    TelaVitoria()
    {
            Imagens imgs = new Imagens();
            JFrame frame = new JFrame("Batalha Espacial - Vitória!");
            JLabel titulo = new JLabel();
            JPanel painel = new JPanel(new BorderLayout());
            JButton exit = new JButton();
            JLabel imagem = new JLabel();
            int player = 0;
            
    
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
            
            titulo.setPreferredSize(new Dimension(600, 50));
    
            // Configurações da imagem
            if(player == 1){
                titulo.setText("Parabéns! Você eliminou o Império" );
                imagem.setIcon(imgs.vitoria1); 
            }
            else{
                titulo.setText("Parabéns! Você eliminou os Rebeldes" );
                imagem.setIcon(imgs.vitoria2); 
            }
            
            imagem.setHorizontalAlignment(SwingConstants.CENTER);
            imagem.setPreferredSize(new Dimension(600, 250));
    
            // Configurações do botão exit
            exit.setIcon(imgs.botaoSair);
            exit.setPreferredSize(new Dimension(170, 50));
            
            exit.setContentAreaFilled(false);
            exit.setBorderPainted(false);
            exit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.exit(-1);
                    
                }
            });
            JPanel buttonPanel = new JPanel(new GridBagLayout());
            buttonPanel.setBackground(new Color(7, 8, 28)); // Definindo a cor de fundo do painel do botão
            buttonPanel.add(exit); // Adicionando o botão ao painel do botão
    
            // Adicionando componentes ao painel principal
            painel.add(titulo, BorderLayout.NORTH);
            painel.add(imagem, BorderLayout.CENTER);
            painel.add(buttonPanel, BorderLayout.SOUTH);
    
            // Adicionando painel ao frame
            frame.add(painel);
            frame.pack();
            frame.setVisible(true);
        
    }
}
