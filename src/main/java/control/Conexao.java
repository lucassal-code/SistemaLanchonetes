package control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

//smepre lembrar de checar a conexão pois pode ter algo errado no RA ou IP
public class Conexao {
    
    public Connection obterConexao(){
        try {
            Class.forName("org.postgresql.Driver");
            String url="jdbc:postgresql://10.90.24.54/ra0081805"; //ip de casa 200.18.128.54
            String usuario="ra0081805",senha="Art2009?";
            
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
