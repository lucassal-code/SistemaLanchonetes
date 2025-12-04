package control;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;
import model.DadosFuncionarios;
import model.ServicosLanchonete;

@Named
@SessionScoped
public class Cadastro implements Serializable{
    
    private String mensagem = "Bem vindo ao Cadastro de Funcionários!";
    private DadosFuncionarios dadosFunc = new DadosFuncionarios();
    private List<DadosFuncionarios> listaFunc;
    
    //se esse construtor tivesse algum parametro, não seria identificado como bean para ser usado no web
    public Cadastro() {                                     //public Cadastro(List<DadosFuncionarios> listaFunc) {
        ServicosLanchonete s = new ServicosLanchonete();    //  this.listaFunc = listaFunc;
        this.listaFunc = s.listaFuncionarios();             //}
    }
    
    //serve para atualizar se tiver mesmo id ou adicionar novo funcionario
    public void cadastrarFuncionario() {
        ServicosLanchonete s = new ServicosLanchonete();
        int id;

        if(this.dadosFunc.getId() != 0) {
            s.atualizarDadosFunc(dadosFunc);
            id = dadosFunc.getId();
            this.mensagem = "Funcionário " + id + " atualizado com SUCESSO!";
        }else{
            id = s.cadastrarFuncionarios(dadosFunc);
            this.mensagem = (id > 0)
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
        this.mensagem="Funcionario "+porId+" apagado com sucesso!";
    }

    //mensagem
    public String getMensagem() {
        return mensagem;
    }
    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    //dados funcionário
    public DadosFuncionarios getDadosFunc() {
        return dadosFunc;
    }
    public void setDadosFunc(DadosFuncionarios dadosFunc) {
        this.dadosFunc = dadosFunc;
    }

    //lista de funcionários
    public List<DadosFuncionarios> getListaFunc() {
        return listaFunc;
    }
    public void setListaFunc(List<DadosFuncionarios> listaFunc) {
        this.listaFunc = listaFunc;
    }
}
