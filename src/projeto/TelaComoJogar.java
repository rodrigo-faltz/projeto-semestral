package projeto;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import java.awt.*;

public class TelaComoJogar extends JFrame implements ActionListener{
    private JButton proximoButton;
    private JPanel painel1, painel2;
    private JLabel imagemExplicativa;
    Imagens imgs;
    int i = 1;
    public TelaComoJogar(){
        super("Batalha Espacial - Como Jogar");

        imgs = new Imagens();
        Container caixa = getContentPane();
        caixa.setLayout(new BorderLayout());

        painel1 = new JPanel(new GridLayout(1,1)); // Ajustado para BorderLayout
        painel2 = new JPanel(new FlowLayout());

        proximoButton = new JButton(imgs.botaoProximo);
        imagemExplicativa = new JLabel(imgs.comojogar1);

        // proximoButton.setContentAreaFilled(false);
        // proximoButton.setBorderPainted(false);

        proximoButton.setPreferredSize(new Dimension(200, 50));
        //imagemExplicativa.setPreferredSize(new Dimension(500, 150));
        
        proximoButton.setContentAreaFilled(false);
        proximoButton.setBorderPainted(false);
        proximoButton.addActionListener(this);

        painel2.add(proximoButton); // Apenas adiciona o botão ao painel2
        painel1.add(imagemExplicativa); // Adiciona a imagem no topo do painel1

        // Adiciona os paineis no container principal com BorderLayout
        caixa.add(painel1, BorderLayout.CENTER);
        caixa.add(painel2, BorderLayout.SOUTH);

        painel1.setBackground(imgs.corDoFundo);
        painel2.setBackground(imgs.corDoFundo);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(dim.width, dim.height-100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
 
        // Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        // setLocation(dim.width/2-this.getSize().width/2, 0);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == proximoButton) {
            if(i == 8){
                dispose();
                new TelaInicial();
            }
            else{
                imagemExplicativa.setIcon(imgs.comojogar[i]);
                i++;
            }


        }
    }
}
