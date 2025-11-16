package control;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author janio.silva
 */
public class Conexao {
    
    public Connection obterConexao(){
        try {
            Class.forName("org.postgresql.Driver");
            String url="jdbc:postgresql://10.90.24.54/ra0081998";
            String usuario="ra0081998",senha="Art2009?";
            
            Connection c = DriverManager.getConnection(url, usuario, senha);
            return c;
        } catch (SQLException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
