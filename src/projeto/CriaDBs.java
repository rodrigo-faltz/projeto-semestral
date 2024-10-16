package projeto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;

//import com.mysql.cj.jdbc.DatabaseMetaData;

public class CriaDBs {

    // Defina as informações de conexão com o banco de dados
    private static final String URL = "jdbc:mysql://localhost:3306/batalha_naval";
    private static final String USER = "login";
    private static final String PASSWORD = "1234";
    

    // Método para conectar ao banco de dados
    public CriaDBs(){
        
        String criaTabelaDeLogin = "CREATE TABLE IF NOT EXISTS Users ("
            + "User VARCHAR(50) NOT NULL UNIQUE, "
            + "Password VARCHAR(250) NOT NULL, "
            + "Wins INT"
            + ");";

        Connection conn = null;
        PreparedStatement pstmt = null;
        System.out.println("User = "+USER+" Password = "+PASSWORD);
        try {  

            conn = DriverManager.getConnection(URL, USER, PASSWORD);

            DatabaseMetaData dado = conn.getMetaData();
            ResultSet tabelas = dado.getTables("batalha_naval", null, "%", new String[] {"TABLE"});
                    
            while (tabelas.next()) {
                String nomeTabela = tabelas.getString("TABLE_NAME");
                if (nomeTabela.equals("Users")) {
                    return;
                }
            }
            
            if (criaTabelaDeLogin == null || criaTabelaDeLogin.trim().isEmpty()) {
                throw new SQLException("The SQL statement for creating the login table is null or empty.");
            }
            
            System.out.println("Executing SQL: " + criaTabelaDeLogin);
            
            pstmt = conn.prepareStatement(criaTabelaDeLogin);
            pstmt.executeUpdate();
        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } finally {
            // Close resources
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
}
