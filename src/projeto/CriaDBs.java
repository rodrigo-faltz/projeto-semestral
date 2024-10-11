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
    private static final String USER = "root";
    private static final String PASSWORD = "password";

    // Método para conectar ao banco de dados
    public CriaDBs(){
        String criaTabelaDeLogin = "CREATE TABLE Users ("
        + "User VARCHAR(50) NOT NULL UNIQUE, "
        + "Password VARCHAR(250) NOT NULL)";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {

            conn = DriverManager.getConnection(URL, USER, PASSWORD);

            DatabaseMetaData dado = conn.getMetaData();
            ResultSet tabelas = dado.getTables("batalha_naval", null, "%", new String[] {"TABLE"});

            if(tabelas.next()){
                new LimpaTabelasDoDB();
            }


            pstmt = conn.prepareStatement(criaTabelaDeLogin);
            pstmt.executeUpdate();

            


 
        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        }
    }
}
