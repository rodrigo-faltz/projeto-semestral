package cliente;

import java.io.*;
import java.net.*;

public class Cliente {
    private static final String ENDERECO_SERVIDOR = "localhost"; // ou IP do servidor
    private static final int PORTA = 12345;

    public static void main(String[] args) {
        try (Socket socket = new Socket(ENDERECO_SERVIDOR, PORTA);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in))) {
             
            String mensagem;
            while (true) {
                System.out.print("Digite uma mensagem: ");
                mensagem = stdin.readLine();
                if (mensagem.equalsIgnoreCase("sair")) {
                    break;
                }
                out.println(mensagem);
                System.out.println("Resposta do servidor: " + in.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

