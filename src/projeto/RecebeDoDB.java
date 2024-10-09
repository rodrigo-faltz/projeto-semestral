package projeto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class RecebeDoDB {

    // Defina as informações de conexão com o banco de dados
    private static final String URL = "jdbc:mysql://localhost:3306/batalha_naval";
    private static final String USER = "Batalha";
    private static final String PASSWORD = "1234";

    // Método para conectar ao banco de dados
    public RecebeDoDB(){
        String gridp1 = "SELECT linha, coluna, valor FROM gridp1";
        String gridp2 = "SELECT linha, coluna, valor FROM gridp2";
        String acertos = "SELECT player, numeroDeAcertosPlayer1, numeroDeAcertosPlayer2, AcertosNave2P1, AcertosNave2P2, AcertosNave3P1, AcertosNave3P2, AcertosNave4P1, AcertosNave4P2, AcertosNave5P1, AcertosNave5P2 FROM acertos";

        Connection conn = null;
        PreparedStatement pstmt = null;
        Grid gridInstance = new Grid();
        int[][] valoresDoP1 = new int[10][10];
        //System.arraycopy(gridInstance.getGridDoPlayer1(), 0, valoresDoP1, 0, valoresDoP1.length); // ESSE EH O GRID DO P1, ONDE OS NAVIOS DO P1 ESTAO

        int[][] valoresDoP2 = new int[10][10];
        //System.arraycopy(gridInstance.getGridDoPlayer2(), 0, valoresDoP2, 0, valoresDoP2.length); // ESSE EH O GRID DO P1, ONDE OS NAVIOS DO P1 ESTAO

        try {

            conn = DriverManager.getConnection(URL, USER, PASSWORD);

            //------ Recebe o Grid do player 1 ----------
            pstmt = conn.prepareStatement(gridp1);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int linha = rs.getInt(1);
                int coluna = rs.getInt(2);
                int valor = rs.getInt(3);

                valoresDoP1[coluna][linha] = valor;
            }

            //gridInstance.setGridDoPlayer1(valoresDoP1);
            System.arraycopy(valoresDoP1, 0, gridInstance.getGridDoPlayer(), 0, valoresDoP1.length);
            //------ Insere o Grid do player 2 ----------
            pstmt = conn.prepareStatement(gridp2);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                int linha = rs.getInt(1);
                int coluna = rs.getInt(2);
                int valor = rs.getInt(3);

                valoresDoP2[coluna][linha] = valor;
            }
            //gridInstance.setGridDoPlayer2(valoresDoP2);
           

            //------ Insere a quantidade de acertos de cada nave --------
            pstmt = conn.prepareStatement(acertos);
            rs = pstmt.executeQuery();
            rs.next();
            
            gridInstance.setNumeroDeAcertosPlayer(rs.getInt(2));
            
            gridInstance.setAcertosNave2(rs.getInt(4));
            
            gridInstance.setAcertosNave3(rs.getInt(6));
            
            gridInstance.setAcertosNave4(rs.getInt(8));
            
            gridInstance.setAcertosNave5(rs.getInt(10));
            
        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        }
    }
}
