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

public class LoginWindow extends JFrame {
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

    public LoginWindow(Player player, Grid grid, ClienteService service, Message message) {
        super("Login");

        this.player = player;
        this.grid = grid;
        // this.socket = socket;
        this.service = service;

        loginText = new JLabel("Login:");
        passwordText = new JLabel("Senha:");
        textField = new JTextField(15);
        passwordField = new JPasswordField(15);
        button1 = new JButton("Prosseguir");
        button2 = new JButton("Sair");

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
                message.setSenha(password);
                System.out.println(username);
                System.out.println(password);
                // envia através da message a senha, o servidor recebe, acessa o banco de dados, compara e devolve
            
            //service.envia(message);
            // se a senha digitada for igual a retornada pelo servidor(pelo Thread), instancia a tela de setup
            message.setAction(Action.CONNECT);
            // service = new ClienteService();
            socket = service.connect(); 
            new Thread(new ListenerSocket(socket)).start(); // escuta a mensagem recebida do servidor
            System.out.println(player.getNumero());
            new TelaDeSetup(player, grid, socket, service);
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
            try {
                this.input = new ObjectInputStream(socket.getInputStream());
            } catch (IOException e) {
                Logger.getLogger(ServidorService.class.getName()).log(Level.SEVERE, null, e);
            }
        }

        @Override
        public void run()
        {
            Message message = null;
            try
                {
                    while ((message = (Message) input.readObject())!=null)
                        {
                            Action action = message.getAction();

                            if(action.equals(Action.ENVIA_PLAYER))
                            {
                                player.setNumero(message.getNumeroDoPlayer());
                                System.out.println("Recebeu o player: "+message.getNumeroDoPlayer());
                            }

                            if(action.equals(Action.ENVIA_GRID))
                            {
                                System.out.println("Recebeu o grid, ta errado");
                            }

                            if(action.equals(Action.ENVIA_VITÓRIA))
                            {
                                System.out.println("Como que recebe vitória se nem começou?");
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
