import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

public class TelaAposSetup {

    public TelaAposSetup() {
        //ImageIcon image = new ImageIcon("pitoes.png"); // Exemplo de imagem principal
        //ImageIcon nextIcon = new ImageIcon("portugal.png"); // Exemplo de ícone do botão próximo
        Imagens imgs = new Imagens();
        JFrame frame = new JFrame("Batalha Espacial - Transição");
        JLabel titulo = new JLabel();
        JPanel painel = new JPanel(new BorderLayout());
        JButton next = new JButton();
        JLabel imagem = new JLabel();

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
        titulo.setText("Agora é a vez do player 2 de colocar as naves" );
        titulo.setPreferredSize(new Dimension(600, 50));

        // Configurações da imagem
        imagem.setIcon(imgs.imperio); 
        imagem.setHorizontalAlignment(SwingConstants.CENTER);
        imagem.setPreferredSize(new Dimension(600, 200));

        // Configurações do botão next
        next.setIcon(imgs.botaoProsseguir);
        next.setPreferredSize(new Dimension(170, 50));

        next.setContentAreaFilled(false);
        next.setBorderPainted(false);

        
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                frame.dispose();
                new TelaDeSetup();
            }
        });

        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setBackground(new Color(7, 8, 28)); // Definindo a cor de fundo do painel do botão
        buttonPanel.add(next); // Adicionando o botão ao painel do botão

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
