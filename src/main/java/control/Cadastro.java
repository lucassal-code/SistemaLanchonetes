package control;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;
import model.DadosCliente;
import model.DadosFuncionarios;
import model.ServicosLanchonete;

@Named
@SessionScoped
public class Cadastro implements Serializable{
    
    private String mensagem1 = "Bem vindo ao Cadastro de Funcionários!";
    private DadosFuncionarios dadosFunc = new DadosFuncionarios();
    private List<DadosFuncionarios> listaFunc;
    
    private String mensagem2 = "Bem vindo ao Cadastro de Clientes!";
    private DadosCliente dadoscli = new DadosCliente();
    private List<DadosCliente> listaClientes;
    
    //se esse construtor tivesse algum parametro, não seria identificado como bean para ser usado no web
    public Cadastro() {                                     //public Cadastro(List<DadosFuncionarios> listaFunc) {
        ServicosLanchonete s = new ServicosLanchonete();    //  this.listaFunc = listaFunc;
        this.listaFunc = s.listaFuncionarios();             //}
        
        this.listaClientes = s.listaClientes();
    }
    
    //serve para atualizar se tiver mesmo id ou adicionar novo funcionario
    public void cadastrarFuncionario() {
        ServicosLanchonete s = new ServicosLanchonete();
        int id;

        if(this.dadosFunc.getId() != 0) {
            s.atualizarDadosFunc(dadosFunc);
            id = dadosFunc.getId();
            this.mensagem1 = "Funcionário " + id + " atualizado com SUCESSO!";
        }else{
            id = s.cadastrarFuncionarios(dadosFunc);
            this.mensagem1 = (id > 0)
                ? "Funcionário " + id + " cadastrado com SUCESSO!"
                : "Erro ao cadastrar funcionário.";
        }

        this.listaFunc = s.listaFuncionarios();
    }
    
    //apaga o funcionario da tabela
    public void apagarDados(int porId){
        ServicosLanchonete s = new ServicosLanchonete();
        s.deletarFuncionario(porId);
        this.listaFunc = s.listaFuncionarios();
        this.mensagem1 = "Funcionario "+porId+" apagado com sucesso!";
    }
    
    public void alterarFunc(int porId){
        ServicosLanchonete s = new ServicosLanchonete();
        this.dadosFunc = s.consultarPorId_Func(porId);
        this.listaFunc=s.listaFuncionarios();
    }    
    
    //cadastro de clientes
    public void cadastrarCliente() {
        ServicosLanchonete s = new ServicosLanchonete();
        int id;

        if(this.dadoscli.getId() != 0) {
            s.atualizarDadosCliente(dadoscli);
            id = dadoscli.getId();
            this.mensagem2 = "Cliente " + id + " atualizado com SUCESSO!";
        }else{
            id = s.cadastrarCliente(dadoscli);
            this.mensagem2 = (id > 0)
                ? "Cliente " + id + " cadastrado com SUCESSO!"
                : "Erro ao cadastrar cliente.";
        }

        this.listaClientes = s.listaClientes();
        
        //só pra limpar ao executar
        this.dadoscli = new DadosCliente();
    }
    
    //apaga o cliente
    public void apagarDadosCliente(int porId){
        ServicosLanchonete s = new ServicosLanchonete();
        s.deletarCliente(porId);
        this.listaClientes = s.listaClientes();
        this.mensagem2 = "Cliente "+porId+" apagado com sucesso!";
    }
    
    public void alterarCli(int porId){
        ServicosLanchonete s = new ServicosLanchonete();
        this.dadoscli = s.consultarPorId_Cliente(porId);
        this.listaClientes = s.listaClientes();
    } 

    //mensagem
    public String getMensagem1() {
        return mensagem1;
    }
    public void setMensagem1(String mensagem1) {
        this.mensagem1 = mensagem1;
    }

    public String getMensagem2() {
        return mensagem2;
    }
    public void setMensagem2(String mensagem2) {
        this.mensagem2 = mensagem2;
    }
    
    //dados funcionário
    public DadosFuncionarios getDadosFunc() {
        return dadosFunc;
    }
    public void setDadosFunc(DadosFuncionarios dadosFunc) {
        this.dadosFunc = dadosFunc;
    }

    //dados cliente
    public DadosCliente getDadoscli() {
        return dadoscli;
    }
    public void setDadoscli(DadosCliente dadoscli) {
        this.dadoscli = dadoscli;
    }

    //lista de funcionários
    public List<DadosFuncionarios> getListaFunc() {
        return listaFunc;
    }
    public void setListaFunc(List<DadosFuncionarios> listaFunc) {
        this.listaFunc = listaFunc;
    }

    //listagem de clientes
    public List<DadosCliente> getListaClientes() {
        return listaClientes;
    }
    public void setListaClientes(List<DadosCliente> listaClientes) {
        this.listaClientes = listaClientes;
    }
}
