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
    private boolean checaLogin;

    // Método para conectar ao banco de dados
    public RecebeDoDB(String loginUsuario, String senhaUsuario){
        String Login = "SELECT Users, Password FROM Login WHERE Login = ? && WHERE Password = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {

            conn = DriverManager.getConnection(URL, USER, PASSWORD);

            
            pstmt = conn.prepareStatement(Login);
            pstmt.setString(1, loginUsuario);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                String login = rs.getString("Login");
                String password = rs.getString("Password");
                System.out.println("Login: " + login + ", Password: " + password);
                setChecaLogin(true);
            } else {
                System.out.println("Nenhum resultado encontrado.");
                setChecaLogin(false);
            }

        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            // Fecha os recursos
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        
    }

    public void setChecaLogin(boolean checaLogin) {
        this.checaLogin = checaLogin;
    }

    public boolean getChecaLogin()
    {
        return checaLogin;
    }
    
    }

