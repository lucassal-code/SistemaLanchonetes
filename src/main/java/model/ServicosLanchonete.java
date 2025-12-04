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

/*
ALTER TABLE sua_tabela
ALTER COLUMN sua_coluna
ADD GENERATED ALWAYS AS IDENTITY;
*/

public class ServicosLanchonete {
    
    //Sessão de Pedidos
    
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
            
            int exeUpdate = p.executeUpdate();      //  ResultSet r = p.executeQuery();
            con.close();                            //  if (r.next()) {
            return exeUpdate;                       //      return r.getInt("id");
                                                    //  }
                                                    //  con.close();
                                                    //  return 0;
        } catch (SQLException ex) {                 //} catch (SQLException ex) {
            System.err.println("Erro na conexão");  //  System.err.println("Erro na conexão");
            System.getLogger(ServicosLanchonete.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
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
                    
                    //tava faltando somente esse setId pra armazenar o id os funcionários
                    dados.setId(r.getInt("id"));
                    
                    retorno.add(dados);
            }
            con.close();
            return retorno;
            
        } catch (SQLException ex) {
            System.err.println("Erro na conexão");
            System.getLogger(ServicosLanchonete.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return null;
    }
    
    //método para excluir funcionarios
    public void deletarFuncionario(int idFunc){
        try {//if e o try tavam invertidos
            if(idFunc > 0){
                Conexao c = new Conexao();
                Connection con = c.obterConexao();
                String SQL = "DELETE FROM sistemalanchonete.funcionarios WHERE id=?";
                PreparedStatement p = con.prepareStatement(SQL);
                
                p.setInt(1, idFunc);
                p.executeUpdate();
                con.close();
            } 
        } catch (SQLException ex) {
            System.err.println("Erro na conexão");
            System.getLogger(ServicosLanchonete.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
    
    //arrumar o comando sql e o preparedstatement
    public void atualizarDadosFunc(DadosFuncionarios dados) {
        try {
            Conexao c = new Conexao();
            Connection con = c.obterConexao();
            String SQL = "UPDATE sistemalanchonete.funcionarios "
                    + " set nome = ?, cpf = ?, telefone = ?,"
                    + " endereço = ?, cargo = ?, salario = ?,"
                    + " email = ? WHERE id = ?";
            PreparedStatement p = con.prepareStatement(SQL);
            
            p.setString(1, dados.getNome());
            p.setString(2, dados.getCpf());
            p.setString(3, dados.getTelefone());
            p.setString(4, dados.getEndereco());
            p.setString(5, dados.getCargo());
            p.setDouble(6, dados.getSalario());
            p.setString(7, dados.getEmail());
            
            p.setInt(9, dados.getId());
            
            ResultSet r = p.executeQuery();
            con.close();
            
        } catch (SQLException ex) {
            System.err.println("Erro na conexão");
            Logger.getLogger(ServicosLanchonete.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    //Sessão de Pedidos
    
    //controle de pedidos criados
    public int criarPedidos(DadosPedidos dados){
        try {
            Conexao c = new Conexao();
            Connection con = c.obterConexao();
            String SQL = "INSERT INTO sistemalanchonete.pedidos (nomecliente, valor, infoadicionais) VALUES (?,?,?) RETURNING numpedido";
            PreparedStatement p = con.prepareStatement(SQL);
            
            p.setString(1, dados.getNomeCliente());
            p.setDouble(2, dados.getValorTotal());
            p.setString(3, dados.getInfoAdd());
            
            int exeUpdate = p.executeUpdate();
            con.close();
            return exeUpdate;
            
        } catch (SQLException ex) {
            System.err.println("Erro na conexão");
            System.getLogger(ServicosLanchonete.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }                                           
        return 0;
    }
    
    //listagem de pedidos
    public List<DadosPedidos> listaPedidos(){
        try {
            List<DadosPedidos> retorno = new ArrayList<>();
            Conexao c = new Conexao();
            Connection con = c.obterConexao();
            String SQL = "SELECT * FROM sistemalanchonete.pedidos ORDER BY numpedido DESC";
            PreparedStatement p = con.prepareStatement(SQL);
            
            ResultSet r = p.executeQuery();
            while (r.next()) {
                    DadosPedidos dados = new DadosPedidos();
                    
                    dados.setNumPedido(r.getInt("numpedido"));
                    dados.setNomeCliente(r.getString("nomecliente"));
                    dados.setValorTotal(r.getDouble("valor"));
                    dados.setInfoAdd(r.getString("infoadicionais"));
                    
                    retorno.add(dados);
            }
            con.close();
            return retorno;
            
        } catch (SQLException ex) {
            System.err.println("Erro na conexão");
            System.getLogger(ServicosLanchonete.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return null;
    }
    
    //atualização das informações de um pedido
    public void atualizarDadosPedido(DadosPedidos dados) {
        try {
            Conexao c = new Conexao();
            Connection con = c.obterConexao();
            String SQL = "UPDATE sistemalanchonete.pedidos set nomecliente = ?, valor = ?, infoadicionais = ?, WHERE numpedido = ?";
            
            PreparedStatement p = con.prepareStatement(SQL);
            
            p.setString(1, dados.getNomeCliente());
            p.setDouble(2, dados.getValorTotal());
            p.setString(3, dados.getInfoAdd());    
            
            p.setInt(9, dados.getNumPedido());
            
            ResultSet r = p.executeQuery();
            con.close();
            
        } catch (SQLException ex) {
            System.err.println("Erro na conexão");
            Logger.getLogger(ServicosLanchonete.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //método para deletar pedidos
    public void deletarPedido(int nP){
        try {
            if(nP > 0){
                Conexao c = new Conexao();
                Connection con = c.obterConexao();
                String SQL = "DELETE FROM sistemalanchonete.pedidos WHERE numpedido=?";
                PreparedStatement p = con.prepareStatement(SQL);
                
                p.setInt(1, nP);
                p.executeUpdate();
                con.close();
            } 
        } catch (SQLException ex) {
            System.err.println("Erro na conexão");
            System.getLogger(ServicosLanchonete.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
    
    
    
    //Sessão do Cardapio
    
    //exibição do cardápio
    public List<ItemCardapio> listagemCardapio(){
        try {
            List<ItemCardapio> retorno = new ArrayList<>();
            Conexao c = new Conexao();
            Connection con = c.obterConexao();
            String SQL = "SELECT * FROM sistemalanchonete.cardapio";
            PreparedStatement p = con.prepareStatement(SQL);
            
            ResultSet r = p.executeQuery();
            while (r.next()) {
                    ItemCardapio dados = new ItemCardapio();
                    
                    dados.setnItem(r.getInt("numeroitem"));
                    dados.setNome(r.getString("nome"));
                    dados.setPreco(r.getDouble("preco"));
                    dados.setInfoAdd(r.getString("infoadicionais"));
                    dados.setTipo(r.getString("tipo"));
                    
                    retorno.add(dados);
            }
            con.close();
            return retorno;
            
        } catch (SQLException ex) {
            System.err.println("Erro na conexão");
            System.getLogger(ServicosLanchonete.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return null;
    }
}
