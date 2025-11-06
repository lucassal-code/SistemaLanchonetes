package model;

//a implementação do banco de dados vai ser para clientes, enquanto as comidas e ingredientes ficam aqui

import control.Conexao;
import java.sql.Connection;
import java.util.ArrayList;


public class ServicosLanchonete {
    //fazer banco de dados para cadastro de clientes no Individual do pgAdmin amanhã
    public int cadastrarFuncionarios(DadosFuncionarios dados){
        Conexao c = new Conexao();
        Connection con = c.obterConexao();
        String SQL = "INSERT INTO sistemalanchonete.funcionarios (nome, cpf, telefone, email, endereco, cargo, salario, email) VALUES (?,?,?,?,?,?,?,?) RETURNING id ";
    }
}
