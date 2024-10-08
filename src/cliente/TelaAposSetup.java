package cliente;
import javax.swing.*;


import java.awt.*;

public class TelaAposSetup {

    public TelaAposSetup() {
        //ImageIcon image = new ImageIcon("pitoes.png"); // Exemplo de imagem principal
        //ImageIcon nextIcon = new ImageIcon("portugal.png"); // Exemplo de ícone do botão próximo
        JFrame frame = new JFrame("Batalha Espacial - Aguarde");
        JLabel titulo = new JLabel();
        JPanel painel = new JPanel(new BorderLayout());
        
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
        titulo.setText("Aguarde o outro player colocar as naves" );
        titulo.setPreferredSize(new Dimension(600, 50));

        // Configurações da imagem
        painel.add(titulo, BorderLayout.NORTH);
        painel.add(imagem, BorderLayout.CENTER);
        

        // Adicionando painel ao frame
        frame.add(painel);
        frame.pack();
        frame.setVisible(true);
    }

    
}
