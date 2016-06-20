/*-------------------------------------------*
 * Author     * Willian Aparecido Carvalho   *
 * Data       *                              *
 * Uso        * Projeto Agendamento Hospital *
 *-------------------------------------------*/

package Conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexao {
    
    private static final String 
        URL = "jdbc:mysql://projetohospital.mysql.uhserver.com/projetohospital",
        NOME = "senacpi2016", SENHA = "Sen@c2016";

    private static Connection conexao;
    public static Statement statement;
    public static ResultSet resultset;

    public static final int MYSQL = 0;
    private static final String MySQLDriver = "com.mysql.jdbc.Driver";

    public static Connection abrirConexao(String url, String nome, String senha,
            int banco) throws ClassNotFoundException, SQLException {

        switch (banco) {
            case MYSQL:
                Class.forName(MySQLDriver);
                break;
        }
        return DriverManager.getConnection(url, nome, senha);
    }

    public static boolean conectar() throws ClassNotFoundException {
        try {
            conexao = abrirConexao(URL, NOME, SENHA, Conexao.MYSQL);
            statement = conexao.createStatement();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static boolean desconectar() {
        try {
            statement.close();
            conexao.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
    
    public boolean executeSQL(String sql){
        try {
            statement = conexao.createStatement();
            resultset = statement.executeQuery(sql);
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
            
        }
    }   
    
     public boolean updateSQL(String sql){
        try {
            statement = conexao.createStatement();
            statement.executeUpdate(sql);
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
            
        }
    }      
}

