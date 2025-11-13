package model;

import control.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ServicosLanchonete {
    
    //cadastro de funcionarios para o banco de dados
    public int cadastrarFuncionarios(DadosFuncionarios dados){
        try {
            Conexao c = new Conexao();
            Connection con = c.obterConexao();
            String SQL = "INSERT INTO sistemalanchonete.funcionarios (nome, cpf, telefone, endereco, cargo, salario, email) VALUES (?,?,?,?,?,?,?) RETURNING id";
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
            Logger.getLogger(ServicosLanchonete.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    //listagem de funcionarios
    public List<DadosFuncionarios> listaFuncionarios(){
        try {
            List<DadosFuncionarios> retorno = new ArrayList<>();
            Conexao c = new Conexao();
            Connection con = c.obterConexao();
            String SQL = "SELECT * FROM sistemalanchonete.funcionarios ORDER BY id DESC";
            PreparedStatement p = con.prepareStatement(SQL);
            
            ResultSet r = p.executeQuery();
            while (r.next()) {
                    DadosFuncionarios dados = new DadosFuncionarios();
                    dados.setNome(r.getString("nome"));
                    dados.setCpf(r.getString("cpf"));
                    dados.setTelefone(r.getString("telefone"));
                    dados.setEndereco(r.getString("endereco"));
                    dados.setCargo(r.getString("cargo"));
                    dados.setSalario(r.getDouble("salario"));
                    dados.setEmail(r.getString("email"));
                    retorno.add(dados);
            }
            con.close();
            return retorno;
            
        } catch (SQLException ex) {
            System.getLogger(ServicosLanchonete.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return null;
    }
    
    //método para excluir funcionarios
    public void deletarFuncionario(int idFunc){
        if(idFunc > 0){
            try {
                Conexao c = new Conexao();
                Connection con = c.obterConexao();
                String SQL = "DELETE FROM sistemalanchonete.funcionarios WHERE id=?";
                PreparedStatement p = con.prepareStatement(SQL);
                
                p.setInt(1, idFunc);
                p.execute();
                con.close();
                
            } catch (SQLException ex) {
                System.getLogger(ServicosLanchonete.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
        }
    }
    
    //permite pesquisar com base no Id do funcionario
    public DadosFuncionarios consultarPorId(int idFunc) {
        try {
            DadosFuncionarios retorno = new DadosFuncionarios();
            Conexao c = new Conexao();
            Connection con = c.obterConexao();
            String SQL = "SELECT * FROM sistemalanchonete.funcionarios WHERE id = ?";
            PreparedStatement p = con.prepareStatement(SQL);
            
            p.setInt(1, idFunc);
            ResultSet r = p.executeQuery();
            
            if (r.next()) {
                retorno.setId(r.getInt("id"));
                retorno.setNome(r.getString("nome"));
                retorno.setCpf(r.getString("cpf"));
                retorno.setTelefone(r.getString("telefone"));
                retorno.setEndereco(r.getString("endereco"));
                retorno.setCargo(r.getString("cargo"));
                retorno.setSalario(r.getDouble("salario"));
                retorno.setEmail(r.getString("email"));
            }
            
            con.close();
            return retorno;
        } catch (SQLException ex) {
            Logger.getLogger(ServicosLanchonete.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    //arrumar o comando sql e o preparedstatement
    public void atualizarDadosFunc(DadosFuncionarios dados) {
        try {
            Conexao c = new Conexao();
            Connection con = c.obterConexao();
            String SQL = "UPDATE sistemalanchonete.funcionarios "
                    + "SET nome = ?, cpf = ?, telefone = ?, "
                    + "endereco = ?, cargo = ?, salario = ?, "
                    + "email = ? WHERE id = ?";
            PreparedStatement p = con.prepareStatement(SQL);

            p.setString(1, dados.getNome());
            p.setString(2, dados.getCpf());
            p.setString(3, dados.getTelefone());
            p.setString(4, dados.getEndereco());
            p.setString(5, dados.getCargo());
            p.setDouble(6, dados.getSalario());
            p.setString(7, dados.getEmail());
            p.setInt(8, dados.getId());

            p.executeUpdate();
            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(ServicosLanchonete.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
