import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.Socket;

public class TelaIntemediaria {

    JFrame frame;
    ClientConnection connection;
    int[][] grid;
    private Timer timer;
    private BufferedReader reader;
    private BufferedReader in;
    private Socket socket;

    public TelaIntemediaria(ClientConnection connection, int[][] grid) {
        try {
            reader = new BufferedReader(new InputStreamReader(connection.getSocket().getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Connection error.");
            System.exit(0);
        }
        Imagens imgs = new Imagens();
        frame = new JFrame("Batalha Espacial - Transição");
        JLabel titulo = new JLabel();
        JPanel painel = new JPanel(new BorderLayout());
        JButton next = new JButton();
        JLabel imagem = new JLabel();
        Grid gridInstance = Grid.getInstance();
        this.connection = connection;

        this.grid = grid;
        this.socket = connection.getSocket();

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
        titulo.setText("Agora é a vez do player " + gridInstance.getPlayer());
        titulo.setPreferredSize(new Dimension(600, 50));

        // Configurações da imagem
        if(gridInstance.getPlayer()==1){
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
                new TelaDeAtaque(connection, grid);
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
        waitForMessage();
    }


    private void waitForMessage() {
        try {
            String line = connection.receiveMessage();
            System.out.println("Received message: " + line);
            if ("YOUR_TURN".equals(line)) {
                frame.dispose();
                new TelaDeAtaque(connection, grid);
            }


        } catch (IOException e) {
            e.printStackTrace();
            // Optionally, handle errors such as connection issues
        }
    }



}
