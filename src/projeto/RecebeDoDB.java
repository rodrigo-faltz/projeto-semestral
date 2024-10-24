package projeto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class RecebeDoDB {

    // Defina as informações de conexão com o banco de dados
    private static final String URL = "jdbc:mysql://localhost:3306/batalha_naval";
    private static final String USER = "login";
    private static final String PASSWORD = "1234";
    

    // Método para conectar ao banco de dados
    
    public RecebeDoDB() {
        // Initialization code here
    }
    
    

    public boolean checaLogin(String loginUsuario, String senhaUsuario){

        String selectSQL = "SELECT * FROM Users WHERE User = ? AND Password = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Estabelece a conexão com o banco de dados
            conn = DriverManager.getConnection(URL, USER, PASSWORD);

            // Prepara a consulta de seleção
            pstmt = conn.prepareStatement(selectSQL);

            // Define os parâmetros da consulta
            pstmt.setString(1, loginUsuario);
            pstmt.setString(2, senhaUsuario);

            // Executa a consulta
            rs = pstmt.executeQuery();

            // Verifica se o usuário existe
            if (rs.next()) {
                return true;
            }

        } catch (SQLException se) {
            se.printStackTrace();




            return false;
        } finally {
            // Close resources in the finally block to ensure they are closed even if an exception occurs
            if (rs != null) try { rs.close(); } catch (SQLException se) { se.printStackTrace(); }
            if (pstmt != null) try { pstmt.close(); } catch (SQLException se) { se.printStackTrace(); }
            if (conn != null) try { conn.close(); } catch (SQLException se) { se.printStackTrace(); }
        }

        return false;
    }

    public int getWins(String loginUsuario)
    {
        String selectSQL = "SELECT Wins FROM Users WHERE User = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Estabelece a conexão com o banco de dados
            conn = DriverManager.getConnection(URL, USER, PASSWORD);

            // Prepara a consulta de seleção
            pstmt = conn.prepareStatement(selectSQL);

            // Define os parâmetros da consulta
            pstmt.setString(1, loginUsuario);

            // Executa a consulta
            rs = pstmt.executeQuery();

            // Verifica se o usuário existe
            if (rs.next()) {
                return rs.getInt("Wins");
            }

        } catch (SQLException se) {
            se.printStackTrace();

            return -1;
        } finally {
            // Close resources in the finally block to ensure they are closed even if an exception occurs
            if (rs != null) try { rs.close(); } catch (SQLException se) { se.printStackTrace(); }
            if (pstmt != null) try { pstmt.close(); } catch (SQLException se) { se.printStackTrace(); }
            if (conn != null) try { conn.close(); } catch (SQLException se) { se.printStackTrace(); }
        }

        return -1;
    
    }

    public String[][] leaderboard(boolean derrotaouVitoria, String intervaloDeTempo) {
        // Intervalo de tempo dever ser: "day", "week", "month", "year"
        String[][] leaderboard = new String[10][2];
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int i = 0;

        String selectLeadersSQL = "SELECT jogador, COUNT(*) AS total_vitorias " +
        "FROM resultados_partidas " +
        "WHERE vitoria = ? " +
        "AND data_jogo >= DATE_FORMAT(CURDATE(), '%Y-%m-01') " +
        "AND data_jogo < CURDATE() " +
        "GROUP BY jogador " +
        "ORDER BY total_vitorias DESC " +
        "LIMIT 10;";

        if (intervaloDeTempo.equalsIgnoreCase("WEEK")) {
        selectLeadersSQL = "SELECT jogador, COUNT(*) AS total_vitorias " +
            "FROM resultados_partidas " +
            "WHERE vitoria = ? " +
            "AND WEEK(data_jogo) = WEEK(CURDATE()) " +
            "AND MONTH(data_jogo) = MONTH(CURDATE()) " +
            "AND YEAR(data_jogo) = YEAR(CURDATE()) " +
            "GROUP BY jogador " +
            "ORDER BY total_vitorias DESC " +
            "LIMIT 10;";
        } else if (intervaloDeTempo.equalsIgnoreCase("MONTH")) {
        selectLeadersSQL = "SELECT jogador, COUNT(*) AS total_vitorias " +
            "FROM resultados_partidas " +
            "WHERE vitoria = ? " +
            "AND MONTH(data_jogo) = MONTH(CURDATE()) " +
            "AND YEAR(data_jogo) = YEAR(CURDATE()) " +
            "GROUP BY jogador " +
            "ORDER BY total_vitorias DESC " +
            "LIMIT 10;";
        } else if (intervaloDeTempo.equalsIgnoreCase("YEAR")) {
        selectLeadersSQL = "SELECT jogador, COUNT(*) AS total_vitorias " +
            "FROM resultados_partidas " +
            "WHERE vitoria = ? " +
            "AND YEAR(data_jogo) = YEAR(CURDATE()) " +
            "GROUP BY jogador " +
            "ORDER BY total_vitorias DESC " +
            "LIMIT 10;";
        }

        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            pstmt = conn.prepareStatement(selectLeadersSQL);
            pstmt.setBoolean(1, derrotaouVitoria);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                leaderboard[i][0] = rs.getString("jogador");
                leaderboard[i][1] = rs.getString("total_vitorias");
                i++;
            }

        } catch (SQLException se) {
            se.printStackTrace();
        }
        return leaderboard;
    }
}
