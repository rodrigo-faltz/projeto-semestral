package cliente;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.*;

public class TelaInicial extends JFrame implements ActionListener {
    private JButton novoJogoButton, continuarButton, comoJogarButton;
    private JPanel painel1, painel2, painel3, painel4;
    private JLabel vezDeQuem;
    Imagens imgs;
    Player player;
    private static final String ENDERECO_SERVIDOR = "localhost"; // ou IP do servidor
    private static final int PORTA = 12345;

    public TelaInicial() {
        super("Batalha Espacial - Tela de Início");
        
        imgs = new Imagens();
        Container caixa = getContentPane();
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
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == novoJogoButton) {
            
            player = new Player(); // Inicializa o player

            try (Socket socket = new Socket(ENDERECO_SERVIDOR, PORTA);
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream()))
                {
                
                
                player.setNumero((Integer) in.readObject()); // Desserializa o objeto
                System.out.println(player.getNumero());
                System.out.println("Conectado");

                // Envia um grid inicial (pode ser um grid vazio)
                Grid gridInicial = new Grid(); // Cria um grid
                out.writeObject(gridInicial); // Envia o grid para o servidor

                new TelaDeSetup(player, socket); // Abre a tela de setup
            } catch (IOException erro) {
                erro.printStackTrace();
                JOptionPane.showMessageDialog(this, "Erro ao conectar ao servidor: " + erro.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                dispose();
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }
        }

        if (e.getSource() == continuarButton) {
            // Aqui você pode implementar uma lógica para verificar se o segundo jogador está conectado
            JOptionPane.showMessageDialog(this, "Funcionalidade de continuar não implementada.", "Atenção", JOptionPane.INFORMATION_MESSAGE);
        }

        if (e.getSource() == comoJogarButton) {
            dispose();
            //new TelaComoJogar();
            try (Socket socket = new Socket(ENDERECO_SERVIDOR, PORTA);
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream()))
                {
            
            } catch (IOException erro) {
                erro.printStackTrace();
                JOptionPane.showMessageDialog(this, "Erro ao conectar ao servidor: " + erro.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                dispose();
            } 
        }
    }
}
