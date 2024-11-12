package projeto;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import projeto.Message.Action;
import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.logging.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.util.Locale;
import java.util.ResourceBundle;


public class TelaLogin extends JFrame {
    private JLabel loginText, passwordText;
    private JTextField textField;
    private JPasswordField passwordField;
    private JButton button1, button2;
    private String username;
    private String password;
    Player player;
    private Socket socket;
    private ClienteService service;
    private Message message;
    private int controlador = 0;
    private Grid grid; 
    ResourceBundle bundle = LanguageManager.getResourceBundle();
    private boolean running = true;
    
    JMenuItem pt, en, es, ja, de;
    JMenu idioma;
    JMenuBar menuBar;

    public TelaLogin(ClienteService service, Socket socket) {
        setTitle(bundle.getString("titleInicio"));

        grid = new Grid();
        message = new Message();
        player = new Player();
        this.service = service;
        this.socket = socket;

        // Initialize the menu bar and language menu
        menuBar = new JMenuBar();
        menuBar.setBackground(new Color(7, 8, 28));                // Dark background for menu bar
        menuBar.setBorder(BorderFactory.createLineBorder(new Color(44, 46, 95))); // Border to match theme

        idioma = new JMenu(bundle.getString("selectLanguage"));
        idioma.setForeground(Color.WHITE);                         // White text color for menu
        idioma.setBackground(new Color(7, 8, 28));                 // Dark background for menu

        // Portuguese Menu Item
        pt = new JMenuItem("Português");
        pt.setBackground(new Color(7, 8, 28));                     // Dark background for menu item
        pt.setForeground(Color.WHITE);                             // White text color
        pt.setFont(new Font("SansSerif", Font.PLAIN, 14));         // Font style and size
        pt.setBorder(BorderFactory.createLineBorder(new Color(44, 46, 95))); // Matching border
        pt.setOpaque(true);
        pt.addChangeListener(e -> pt.setBackground(pt.isArmed() ? new Color(44, 46, 95) : new Color(7, 8, 28))); // Highlight color

        // English Menu Item
        en = new JMenuItem("English");
        en.setBackground(new Color(7, 8, 28));                     
        en.setForeground(Color.WHITE);                             
        en.setFont(new Font("SansSerif", Font.PLAIN, 14));         
        en.setBorder(BorderFactory.createLineBorder(new Color(44, 46, 95))); 
        en.setOpaque(true);
        en.addChangeListener(e -> en.setBackground(en.isArmed() ? new Color(44, 46, 95) : new Color(7, 8, 28)));

        // Spanish Menu Item
        es = new JMenuItem("Español");
        es.setBackground(new Color(7, 8, 28));                     
        es.setForeground(Color.WHITE);                             
        es.setFont(new Font("SansSerif", Font.PLAIN, 14));         
        es.setBorder(BorderFactory.createLineBorder(new Color(44, 46, 95))); 
        es.setOpaque(true);
        es.addChangeListener(e -> es.setBackground(es.isArmed() ? new Color(44, 46, 95) : new Color(7, 8, 28)));

        // Japanese Menu Item
        ja = new JMenuItem("日本語");
        ja.setBackground(new Color(7, 8, 28));                     
        ja.setForeground(Color.WHITE);                             
        ja.setFont(new Font("SansSerif", Font.PLAIN, 14));         
        ja.setBorder(BorderFactory.createLineBorder(new Color(44, 46, 95))); 
        ja.setOpaque(true);
        ja.addChangeListener(e -> ja.setBackground(ja.isArmed() ? new Color(44, 46, 95) : new Color(7, 8, 28)));

        // German Menu Item
        de = new JMenuItem("Deutsch");
        de.setBackground(new Color(7, 8, 28));                     
        de.setForeground(Color.WHITE);                             
        de.setFont(new Font("SansSerif", Font.PLAIN, 14));         
        de.setBorder(BorderFactory.createLineBorder(new Color(44, 46, 95))); 
        de.setOpaque(true);
        de.addChangeListener(e -> de.setBackground(de.isArmed() ? new Color(44, 46, 95) : new Color(7, 8, 28)));

        // Add each menu item to the language menu
        idioma.add(pt);
        idioma.add(en);
        idioma.add(es);
        idioma.add(ja);
        idioma.add(de);

        // Add the language menu to the menu bar
        menuBar.add(idioma);
        setJMenuBar(menuBar);


        pt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateLanguage(0);
            }
        });

        en.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateLanguage(1);
            }
        });

        es.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateLanguage(2);
            }
        });

        ja.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateLanguage(3);
            }
        });

        de.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateLanguage(4);
            }
        });

        idioma.add(pt);
        idioma.add(en);
        idioma.add(es);
        idioma.add(ja);
        idioma.add(de);

        menuBar.add(idioma);
        setJMenuBar(menuBar);

        loginText = new JLabel(bundle.getString("loginUsuario"));
        passwordText = new JLabel(bundle.getString("loginSenha"));
        textField = new JTextField(15);
        passwordField = new JPasswordField(15);
        loginText.setForeground(Color.YELLOW);
        passwordText.setForeground(Color.YELLOW);  // Set text color to red
        button1 = new JButton(bundle.getString("loginEntrar"));
        button2 = new JButton(bundle.getString("loginSair"));

        button1.setBackground(new Color(44, 46, 95));  // Dark red background color
        button1.setForeground(Color.WHITE);  // White text color for contrast
        button1.setFocusPainted(false);

        button2.setBackground(new Color(44, 46, 95));  // Dark red background color
        button2.setForeground(Color.WHITE);  // White text color for contrast
        button2.setFocusPainted(false);

        getContentPane().setBackground(new Color(7,8,28));

        setLayout(new GridLayout(3, 2, 10, 10));
        add(loginText);
        add(textField);
        add(passwordText);
        add(passwordField);
        add(button1);
        add(button2);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);   
        setLocationRelativeTo(null);  
        setResizable(false);
        button1.addActionListener(new ActionListener() {


            @Override
            public void actionPerformed(ActionEvent e) {
                username = textField.getText();
                password = new String(passwordField.getPassword());
                // message.setSenha(password);
                try {
                    String usuarioCriptografado = AESUtil.encrypt(username);
                    System.out.println("Usuário criptografada: " + usuarioCriptografado);
                    message.setUsuario(usuarioCriptografado); //Seta o usuário criptografado
                    String senhaCriptografada = AESUtil.encrypt(password);
                    System.out.println("Senha criptografada: " + senhaCriptografada);
                    message.setSenha(senhaCriptografada); // Seta a senha criptografada na mensagem
                    message.setAction(Action.CONNECT);
                    service.envia(message); // Envia a mensagem com a senha criptografada para o servidor
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                System.out.println(username);
                System.out.println(password);

                Thread thread = new Thread(new ListenerSocket(service.getSocket()));
                thread.start(); // escuta a mensagem recebida do servidor
                System.out.println(player.getNumero());
                dispose();
            }
        }); 

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        }); // Botão de sair
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getControlador() {
        return controlador;
    }

    public void setControlador(int contador) {
        this.controlador = contador;
    }

    private class ListenerSocket implements Runnable
    {
        private ObjectInputStream input;

        public ListenerSocket(Socket socket)
        {
            
            this.input = service.getInput();
            
        }

        @Override
        public void run()
        {
            Message message = null;
            try
                {
                    while (running)
                        {

                            message = (Message) input.readObject();

                            Action action = message.getAction();
                            System.out.println("Action received: " + action); // Debug statement

                            if(action.equals(Action.ENVIA_PLAYER))
                            {
                                player.setNumero(message.getNumeroDoPlayer());
                                System.out.println("Recebeu o player: "+message.getNumeroDoPlayer());
                                new TelaInicial(grid, player, service, socket);
                                running = false;
                                break;
                            }

                            if(action.equals(Action.LOGIN_FAIL))
                            {
                                
                            System.out.println(bundle.getString("loginEntrar"));

                            // Create and configure the error dialog
                            JDialog dialog = new JDialog();
                            dialog.setTitle("Login Error");  // Set the title
                            dialog.setAlwaysOnTop(true);
                            dialog.setModal(true);
                            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                            dialog.setLayout(new GridLayout(2, 1));

                            // Create label with padding and custom text color
                            JLabel messageLabel = new JLabel(bundle.getString("loginMessageError"));
                            messageLabel.setForeground(Color.YELLOW);  // Set text color to red
                            messageLabel.setBorder(new EmptyBorder(10, 20, 10, 20));  // Top, left, bottom, right padding

                            // Set the background color of the dialog content
                            dialog.getContentPane().setBackground(new Color(7,8,28));  // Light gray background

                            // Create an exit button
                            JButton exitButton = new JButton(bundle.getString("voltar"));
                            exitButton.setBackground(new Color(44, 46, 95));  // Dark red background color
                            exitButton.setForeground(Color.WHITE);  // White text color for contrast
                            exitButton.setFocusPainted(false);
                            exitButton.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    dialog.dispose();  // Close the dialog
                                }
                            });

                            // Add the label and button to the dialog
                            dialog.add(messageLabel);
                            dialog.add(exitButton);

                            // Adjust dialog size to fit its content and padding
                            dialog.pack();
                            dialog.setLocationRelativeTo(null);  // Center the dialog on the screen

                            // Show the dialog
                            dialog.setVisible(true);

                            // Proceed to the login screen
                            new TelaLogin(service, socket);
                            break;




                        }
                        
                        
                    }

                }
            catch(IOException e)
            {
                Logger.getLogger(ServidorService.class.getName()).log(Level.SEVERE, null, e);
            }
            catch(ClassNotFoundException e)
            {
                Logger.getLogger(ServidorService.class.getName()).log(Level.SEVERE, null, e);
            }

        }
    }

        private void updateLanguage(int languageIndex) {
        Locale selectedLocale = LanguageManager.getSupportedLocales()[languageIndex];
        LanguageManager.setCurrentLocale(selectedLocale);
        ResourceBundle messages = LanguageManager.getResourceBundle();

        setTitle(messages.getString("titleInicio"));
        idioma.setText(messages.getString("selectLanguage"));
        button1.setText(messages.getString("loginEntrar"));
        button2.setText(messages.getString("loginSair"));
        loginText.setText(messages.getString("loginUsuario"));
        passwordText.setText(messages.getString("loginSenha"));


        repaint(); // Repinta a janela para refletir as mudanças visuais
    } 
}
