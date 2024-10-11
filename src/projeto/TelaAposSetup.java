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

public class TelaAposSetup {
    private ObjectInputStream in; // Stream de entrada para receber dados do servidor
    private ObjectOutputStream out; // Stream de saída para enviar dados ao servidor
    private Grid grid;
    private String tipo;
    private Socket socket;
    

    public TelaAposSetup(Grid grid, Socket socket) {
        JFrame frame = new JFrame("Batalha Espacial - Aguarde");
        JLabel titulo = new JLabel();
        JPanel painel = new JPanel(new BorderLayout());
        this.grid = grid;
        JLabel imagem = new JLabel();
        this.socket = socket;
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
