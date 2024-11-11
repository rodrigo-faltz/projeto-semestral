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
    

    public TelaLogin(Player player, Grid grid, ClienteService service, Message message) {
        super("Login");

        this.player = player;
        this.grid = grid;
        this.service = service;
        this.message = message;
        this.socket = service.getSocket();

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
                    message.setUsuario(username);
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

                new Thread(new ListenerSocket(service.getSocket())).start(); // escuta a mensagem recebida do servidor
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
                    while (true)
                        {

                            message = (Message) input.readObject();

                            Action action = message.getAction();
                            System.out.println("Action received: " + action); // Debug statement

                            if(action.equals(Action.ENVIA_PLAYER))
                            {
                                player.setNumero(message.getNumeroDoPlayer());
                                System.out.println("Recebeu o player: "+message.getNumeroDoPlayer());
                                new TelaAposLogin(grid, socket, service, player);
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
                            JButton exitButton = new JButton("Close");
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
                            new TelaLogin(player, grid, service, message);
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
}
