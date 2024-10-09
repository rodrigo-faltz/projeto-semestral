import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;

import java.util.ResourceBundle;

import javax.swing.*;
import java.awt.*;

public class TelaInicial extends JFrame implements ActionListener{
    private JButton novoJogoButton, continuarButton, comoJogarButton;
    private JPanel painel1, painel2, painel3, painel4;
    private JLabel vezDeQuem;
    Imagens imgs;

    ResourceBundle bundle = LanguageManager.getResourceBundle();

    public TelaInicial(){
        setTitle(bundle.getString("titleInicio"));
        
        imgs = new Imagens();
        Container caixa = getContentPane();
        caixa.setLayout(new GridLayout(4,1));

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


        setSize(500,800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width/2-this.getSize().width/2, 0);
    }

    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == novoJogoButton){
            dispose();
            new TelaDeSetup();
        }
        if(e.getSource() == continuarButton){
            try{
                
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/batalha_naval", "Batalha", "1234");

                DatabaseMetaData dado = conn.getMetaData();
                ResultSet tabelas = dado.getTables( "batalha_naval", null, "%", new String[] {"TABLE"});

                if(!tabelas.next()){
                }
                else{
                    dispose();
                    new RecebeDoDB();
                    new TelaDeAtaque();
                }
                    
                
            } catch (SQLException se) {
                // Handle errors for JDBC
                se.printStackTrace();
            }


    }
    if(e.getSource() == comoJogarButton){
        dispose();
        new TelaComoJogar();
    }

    }

    /* metodo de update do idioma
    
    private void updateLanguage(int languageIndex) {
        Locale selectedLocale = (languageIndex == 0) ? new Locale("pt", "BR") : new Locale("en", "US");
        LanguageManager.setCurrentLocale(selectedLocale);
        ResourceBundle messages = LanguageManager.getResourceBundle();

        loginText.setText(messages.getString("login"));
        passwordText.setText(messages.getString("password"));
        button1.setText(messages.getString("proceed"));
        button2.setText(messages.getString("exit"));
    } 
    
    chamar usando updateLanguage(languageSelector.getSelectedIndex()); no caso de um combobox
    
    para JMenuItem, fazer manualmente a troca de idioma ou criar array interno para simular um combobox e reutilizar o metodo acima

    */

}
