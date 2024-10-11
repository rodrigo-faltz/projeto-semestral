package projeto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class EnviaProDB {

    // Defina as informações de conexão com o banco de dados
    private static final String URL = "jdbc:mysql://localhost:3306/batalha_naval";
    private static final String USER = "Batalha";
    private static final String PASSWORD = "1234";

    // Método para conectar ao banco de dados
    public EnviaProDB(){
        String gridp1 = "INSERT INTO gridp1 (linha, coluna, valor) VALUES (?, ?, ?)";
        String acertos = "INSERT INTO acertos (player, numeroDeAcertosPlayer1, numeroDeAcertosPlayer2, AcertosNave2P1, AcertosNave2P2, AcertosNave3P1, AcertosNave3P2, AcertosNave4P1, AcertosNave4P2, AcertosNave5P1, AcertosNave5P2) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        Connection conn = null;
        PreparedStatement pstmt = null;
        Grid gridInstance = new Grid();
        int[][] valoresDoP1 = new int[10][10];
        System.arraycopy(gridInstance.getGridDoPlayer(), 0, valoresDoP1, 0, valoresDoP1.length); // ESSE EH O GRID DO P1, ONDE OS NAVIOS DO P1 ESTAO

        

        try {

            conn = DriverManager.getConnection(URL, USER, PASSWORD);

            //------ Insere o Grid do player 1 ----------
            pstmt = conn.prepareStatement(gridp1);
            for (int coluna = 0; coluna < 10; coluna++) {
                for (int linha = 0; linha < 10; linha++) {
                    pstmt.setInt(1, linha);
                    pstmt.setInt(2, coluna);
                    pstmt.setInt(3, valoresDoP1[coluna][linha]);
                    pstmt.addBatch();
                }
            }

            pstmt.executeBatch();
            

            pstmt.executeBatch();
            //------ Insere a quantidade de acertos de cada nave --------

            pstmt = conn.prepareStatement(acertos);

            
            pstmt.setInt(2, gridInstance.getNumeroDeAcertosPlayer());
            
            pstmt.setInt(4, gridInstance.getAcertosNave2());
    
            pstmt.setInt(6, gridInstance.getAcertosNave3());
    
            pstmt.setInt(8, gridInstance.getAcertosNave4());
            
            pstmt.setInt(10, gridInstance.getAcertosNave5());
            
            pstmt.addBatch();
            pstmt.executeBatch();


        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        }
    }
}
