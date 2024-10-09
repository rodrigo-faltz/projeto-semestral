
import javax.swing.*;
// Replace 'your.package.name' with the actual package name where Grid is located

import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class TelaAposSetup {
    private ObjectInputStream in; // Stream de entrada para receber dados do servidor
    private ObjectOutputStream out; // Stream de saída para enviar dados ao servidor
    private Grid grid;
    private String tipo;
    private GerenciaSocket gerenciaSocket;

    public TelaAposSetup(Grid grid) {
        JFrame frame = new JFrame("Batalha Espacial - Aguarde");
        JLabel titulo = new JLabel();
        JPanel painel = new JPanel(new BorderLayout());
        this.grid = grid;
        JLabel imagem = new JLabel();
        gerenciaSocket = GerenciaSocket.getInstance();
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
        
        // Initialize streams
        initializeStreams();
        iniciarThreadRecepcao();
    }

    private void initializeStreams() {
        try {
            Socket socket = gerenciaSocket.getSocket();
            if (socket != null && !socket.isClosed()) {
                out = new ObjectOutputStream(socket.getOutputStream());
                System.out.println("ObjectOutputStream created.");
            } else {
                System.err.println("Socket is null or closed.");
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to create ObjectOutputStream.");
        }
    }

    private void iniciarThreadRecepcao() {
        new Thread(() -> {
            try {
                in = new ObjectInputStream(gerenciaSocket.getSocket().getInputStream());

                try {
                    // Loop que fica ouvindo as mensagens do servidor
                    while (true) {
                        if (in.available() > 0) {
                            tipo = (String) in.readObject();
                        }

                        if (tipo.equals("COMECAR_JOGO")) {
                            new TelaDeAtaque(grid);
                        } 
                    }
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            } catch (IOException error) {
                error.printStackTrace();
            }
        }).start();
    }
}
