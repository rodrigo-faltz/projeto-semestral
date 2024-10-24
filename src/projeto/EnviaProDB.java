package projeto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EnviaProDB {

    // Defina as informações de conexão com o banco de dados
    private static final String URL = "jdbc:mysql://localhost:3306/batalha_naval";
    private static final String USER = "login";
    private static final String PASSWORD = "1234";

    // Método para conectar ao banco de dados
    public EnviaProDB(){

    }

    // public void enviaVitoria(String loginUsuario) {

    //     String updateSQL = "UPDATE Users SET Wins = Wins + 1 WHERE User = ?";
    //     Connection conn = null;
    //     PreparedStatement pstmt = null;

    //     try {
    //         // Estabelece a conexão com o banco de dados
    //         conn = DriverManager.getConnection(URL, USER, PASSWORD);

    //         // Prepara a consulta de atualização
    //         pstmt = conn.prepareStatement(updateSQL);

    //         // Define os parâmetros da consulta
    //         pstmt.setString(1, loginUsuario);

    //         // Executa a consulta
    //         pstmt.executeUpdate();

    //     } catch (SQLException se) {
    //         se.printStackTrace();
    //     }
    // }

    public void enviaCredencial(String loginUsuario, String senhaUsuario) {

        String insertSQL = "INSERT INTO Users (User, Password, Wins) VALUES (?, ?, 0)";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            // Estabelece a conexão com o banco de dados
            conn = DriverManager.getConnection(URL, USER, PASSWORD);

            // Prepara a consulta de inserção
            pstmt = conn.prepareStatement(insertSQL);

            // Define os parâmetros da consulta
            pstmt.setString(1, loginUsuario);
            pstmt.setString(2, senhaUsuario);

            // Executa a consulta
            pstmt.executeUpdate();

        } catch (SQLException se) {
            se.printStackTrace();

        }
    }

    public void enviaPartida(boolean Vitoria, String Username, String Data_Jogo) {

        String insertPartidaSQL = "INSERT INTO resultados_partidas (Vitoria, Jogador, Data_Jogo) VALUES (?, ?, ?)";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            // Estabelece a conexão com o banco de dados
            conn = DriverManager.getConnection(URL, USER, PASSWORD);

            // Prepara a consulta de atualização
            pstmt = conn.prepareStatement(insertPartidaSQL);

            // Define os parâmetros da consulta
            pstmt.setBoolean(1, Vitoria);
            pstmt.setString(2, Username);
            pstmt.setString(3, Data_Jogo);

            // Executa a consulta
            pstmt.executeUpdate();

        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
}