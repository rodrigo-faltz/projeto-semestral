package projeto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


//import com.mysql.cj.jdbc.DatabaseMetaData;

public class LimpaTabelasDoDB {

    // Defina as informações de conexão com o banco de dados
    private static final String URL = "jdbc:mysql://localhost:3306/batalha_naval";
    private static final String USER = "Batalha";
    private static final String PASSWORD = "1234";

    // Método para conectar ao banco de dados
    public LimpaTabelasDoDB(){
        String droppaAcertos = "drop Table acertos";

        String droppaGridP1 = "drop TABLE GridP1";
        String droppaGridP2 = "drop TABLE GridP2";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {

            conn = DriverManager.getConnection(URL, USER, PASSWORD);

            pstmt = conn.prepareStatement(droppaAcertos);
            pstmt.executeUpdate();

            pstmt = conn.prepareStatement(droppaGridP1);
            pstmt.executeUpdate();

            pstmt = conn.prepareStatement(droppaGridP2);
            pstmt.executeUpdate();


        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        }
    }
}
