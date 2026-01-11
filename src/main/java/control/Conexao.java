package control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexao {

    public Connection obterConexao() {
        try {
            Class.forName("org.postgresql.Driver");

            String usuario = "ra0081805";
            String senha = "Art2009?";

            // Lista de url para tentativas
            String[] urls = {
                "jdbc:postgresql://10.90.24.54/ra0081805",//ip da escola
                "jdbc:postgresql://200.18.128.54/ra0081805"//ip de casa
            };

            for (String url : urls) {
                try {
                    Connection c = DriverManager.getConnection(url, usuario, senha);
                    System.out.println("Conectado com sucesso em: " + url);
                    return c;
                } catch (SQLException e) {
                    // Loga o erro e tenta o próximo IP
                    Logger.getLogger(Conexao.class.getName())
                          .log(Level.WARNING, "Falha ao conectar em: " + url, e);
                }
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Se nenhum ip funcionar
        return null;
    }
}