package model;

//a implementação do banco de dados vai ser para clientes, enquanto as comidas e ingredientes ficam aqui

import control.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class ServicosLanchonete {
    //fazer banco de dados para cadastro de clientes no Individual do pgAdmin amanhã
    public int cadastrarFuncionarios(DadosFuncionarios dados){
        try {
            Conexao c = new Conexao();
            Connection con = c.obterConexao();
            String SQL = "INSERT INTO sistemalanchonete.funcionarios (nome, cpf, telefone, endereco, cargo, salario, email) VALUES (?,?,?,?,?,?,?,?) RETURNING id";
            PreparedStatement p = con.prepareStatement(SQL);
            
            p.setString(1, dados.getNome());
            p.setString(2, dados.getCpf());
            p.setString(3, dados.getTelefone());
            p.setString(4, dados.getEndereco());
            p.setString(5, dados.getCargo());
            p.setDouble(6, dados.getSalario());
            p.setString(7, dados.getEmail());
            
            ResultSet r = p.executeQuery();
            if (r.next()) {
                return r.getInt("id");
            }
            con.close();
            return 0;
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro na conexão", "ERRO!", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(ServicosLanchonete.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    public List<DadosFuncionarios> listaFuncionarios(){
        List<DadosFuncionarios> retorno;
        Conexao c = new Conexao();
        Connection con = c.obterConexao();
        String SQL = "SELECT * FROM sistemalanchonete.alunos ORDER BY id DESC";
    }
}
