package control;

import java.io.Serializable;
import java.util.List;
import model.DadosFuncionarios;

public class Cadastro implements Serializable{
    private String mensagem = "Bem vindo ao Cadastro de Funcionários!";
    private DadosFuncionarios dadosFunc = new DadosFuncionarios();
    private List<DadosFuncionarios> listaFunc;

    public Cadastro(List<DadosFuncionarios> listaFunc) {
        this.listaFunc = listaFunc;
    }
    
    
}
