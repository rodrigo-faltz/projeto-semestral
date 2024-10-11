package projeto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


//import com.mysql.cj.jdbc.DatabaseMetaData;

public class LimpaTabelasDoDB {

    // Defina as informações de conexão com o banco de dados
    private static final String URL = "jdbc:mysql://localhost:3306/batalha_naval";
    private static final String USER = "root";
    private static final String PASSWORD = "password";

    // Método para conectar ao banco de dados
    public LimpaTabelasDoDB(){
        String dropaLogin = "drop Table Users";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {

            conn = DriverManager.getConnection(URL, USER, PASSWORD);

            pstmt = conn.prepareStatement(dropaLogin);
            pstmt.executeUpdate();

            


        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        }
    }
}
