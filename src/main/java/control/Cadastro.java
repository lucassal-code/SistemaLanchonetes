package control;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;
import model.DadosFuncionarios;
import model.ServicosLanchonete;

@Named
@SessionScoped //arrumar o bean do cadastro
public class Cadastro implements Serializable{
    private String mensagem = "Bem vindo ao Cadastro de Funcionários!";
    private DadosFuncionarios dadosFunc = new DadosFuncionarios();
    private List<DadosFuncionarios> listaFunc;

    public Cadastro(List<DadosFuncionarios> listaFunc) {
        ServicosLanchonete s = new ServicosLanchonete();
        this.listaFunc = s.listaFuncionarios();
    }
    
    public void cadastrarFuncionario(){
        ServicosLanchonete s = new ServicosLanchonete();
        
        /*descobrir depois o que fazer com o endereço, se será mais formulado ou so assim mesmo
        //dadosFunc.setEndereco(dadosCEP.getEndereco()); dadosFunc.setCidade(dadosCEP.getCidade());
        dadosFunc.setEstado(dadosCEP.getEstado());*/
        
        int id = this.dadosFunc.getId();
        
        if(this.dadosFunc.getId()!= 0){
            s.atualizarDadosFunc(dadosFunc);
        }else{
            id = s.cadastrarFuncionarios(dadosFunc);
        }
        
        if(id > 0){
            this.mensagem = "Funcionário "+id+" cadastrado com SUCESSO!";
        }else{
            this.mensagem = "Erro ao cadastrar funcionário";
        }
        this.listaFunc = s.listaFuncionarios();
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public DadosFuncionarios getDadosFunc() {
        return dadosFunc;
    }

    public void setDadosFunc(DadosFuncionarios dadosFunc) {
        this.dadosFunc = dadosFunc;
    }

    public List<DadosFuncionarios> getListaFunc() {
        return listaFunc;
    }

    public void setListaFunc(List<DadosFuncionarios> listaFunc) {
        this.listaFunc = listaFunc;
    }
}
