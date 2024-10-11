package projeto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EnviaProDB {

    // Defina as informações de conexão com o banco de dados
    private static final String URL = "jdbc:mysql://localhost:3306/batalha_naval";
    private static final String USER = "root";
    private static final String PASSWORD = "password";

    // Método para conectar ao banco de dados
    public EnviaProDB() {
        String insertSQL = "INSERT INTO Users (User, Password) VALUES (?, ?)";

        Connection conn = null;
        PreparedStatement pstmt = null;
        Grid gridInstance = new Grid();
        int[][] valoresDoP1 = new int[10][10];
        System.arraycopy(gridInstance.getGridDoPlayer(), 0, valoresDoP1, 0, valoresDoP1.length); // ESSE EH O GRID DO P1, ONDE OS NAVIOS DO P1 ESTAO

        try {
            // Estabelece a conexão com o banco de dados
            conn = DriverManager.getConnection(URL, USER, PASSWORD);

            // Prepara a consulta de inserção
            pstmt = conn.prepareStatement(insertSQL);

            // Primeira inserção
            pstmt.setString(1, "ottock");   // Índice 1 para o primeiro parâmetro (Login)
            pstmt.setString(2, "sapo");     // Índice 2 para o segundo parâmetro (Password)
            pstmt.addBatch();               // Adiciona ao batch

            // Segunda inserção
            pstmt.setString(1, "lafare");   // Índice 1 para o primeiro parâmetro (Login)
            pstmt.setString(2, "betinha");  // Índice 2 para o segundo parâmetro (Password)
            pstmt.addBatch();               // Adiciona ao batch

            // Executa as inserções em lote
            pstmt.executeBatch();

        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            // Fecha os recursos
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
}
