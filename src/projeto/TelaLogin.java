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
        button1 = new JButton(bundle.getString("loginEntrar"));
        button2 = new JButton(bundle.getString("loginSair"));

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

         
        button1.addActionListener(new ActionListener() {


            @Override
            public void actionPerformed(ActionEvent e) {
                username = textField.getText();
                password = new String(passwordField.getPassword());
                message.setUsuario(username);
                //TODO: Criptografar a senha aqui 
                message.setSenha(password);
                System.out.println(username);
                System.out.println(password);
                // envia através da message a senha, o servidor recebe, acessa o banco de dados, compara e devolve
                message.setAction(Action.CONNECT);
                
            // se a senha digitada for igual a retornada pelo servidor(pelo Thread), instancia a tela de setup
            
            // service = new ClienteService();
            
            service.envia(message); // envia a mensagem para o servidor
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
                                new TelaDeSetup(player, grid, socket, service);
                                break;
                            }

                            if(action.equals(Action.LOGIN_FAIL))
                            {
                                System.out.println("Login falhou");
                                JDialog dialog = new JDialog();
                                dialog.setAlwaysOnTop(true);
                                dialog.setModal(true);
                                dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                                dialog.setLayout(new FlowLayout());
                                dialog.add(new JLabel("Login falhou"));
                                dialog.setSize(200, 100);
                                dialog.setLocationRelativeTo(null);
                                dialog.setVisible(true);
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
