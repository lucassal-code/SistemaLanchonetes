package control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Conexao {
    public Connection obterConexao(){
        try {
            String url="jdbc:postgresql://10.90.24.54/ra0081805"; //200.18.128.54 para entrar fora da escola
            String usuario="ra0081805",senha="Art2009?";
            Class.forName("org.postgresql.Driver");
            Connection c = DriverManager.getConnection(url, usuario, senha);
            JOptionPane.showConfirmDialog(null, "Conexão bem sucedida");
            return c;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro na conexão", "ERRO!", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Não Encontrado", "Houve uma Falha", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
