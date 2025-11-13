package control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexao {
    public Connection obterConexao(){
        try {
            
            Class.forName("org.postgresql.Driver");
            String url="jdbc:postgresql://10.90.24.54/ra0081805"; //200.18.128.54 para entrar fora da escola
            String usuario="ra0081805",senha="Art2009?";
            
            //Conexão bem sucedida
            Connection c = DriverManager.getConnection(url, usuario, senha);
            return c;
            
        } catch (SQLException | ClassNotFoundException ex) {
            //houve um erro na conexão ou não foi encontrado
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
