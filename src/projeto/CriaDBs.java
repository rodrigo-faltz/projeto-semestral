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
    private static final String USER = "Batalha";
    private static final String PASSWORD = "1234";

    // Método para conectar ao banco de dados
    public CriaDBs(){
        String criaTabelaDeAcertos = "CREATE TABLE Acertos ("
        + "player INT, "
        + "numeroDeAcertosPlayer1 INT, "
        + "numeroDeAcertosPlayer2 INT, "
        + "AcertosNave2P1 INT, "
        + "AcertosNave2P2 INT, "
        + "AcertosNave3P1 INT, "
        + "AcertosNave3P2 INT, "
        + "AcertosNave4P1 INT, "
        + "AcertosNave4P2 INT, "
        + "AcertosNave5P1 INT, "
        + "AcertosNave5P2 INT)";

        String criaGridP1 = "CREATE TABLE GridP1 ( linha INT, coluna INT, valor INT)";
        String criaGridP2 = "CREATE TABLE GridP2 ( linha INT, coluna INT, valor INT)";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {

            conn = DriverManager.getConnection(URL, USER, PASSWORD);

            DatabaseMetaData dado = conn.getMetaData();
            ResultSet tabelas = dado.getTables("batalha_naval", null, "%", new String[] {"TABLE"});

            if(tabelas.next()){
                new LimpaTabelasDoDB();
            }


            pstmt = conn.prepareStatement(criaTabelaDeAcertos);
            pstmt.executeUpdate();

            pstmt = conn.prepareStatement(criaGridP1);
            pstmt.executeUpdate();

            pstmt = conn.prepareStatement(criaGridP2);
            pstmt.executeUpdate();


 
        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        }
    }
}
