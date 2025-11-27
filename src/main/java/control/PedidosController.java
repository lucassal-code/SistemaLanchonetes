package control;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;
import model.DadosPedidos;
import model.ItemCardapio;
import model.ServicosLanchonete;

@Named
@SessionScoped
public class PedidosController implements Serializable{
    private String mensagem = "Bem vindo ao Controle de Pedidos!";
    
    private List<DadosPedidos> listaPedidos;
    private DadosPedidos dadosPed = new DadosPedidos();
    
    private List<ItemCardapio> cardapio;
    private ItemCardapio itemC = new ItemCardapio();
    
    public PedidosController() {
        ServicosLanchonete s = new ServicosLanchonete();
        listaPedidos = s.listaPedidos();
        cardapio = s.listagemCardapio();
    }

    public void criarPedido() {
        ServicosLanchonete s = new ServicosLanchonete();
        int nP;

        if(this.dadosPed.getNumPedido()!= 0) {
            s.atualizarDadosPedido(dadosPed);
            nP = dadosPed.getNumPedido();
            this.mensagem = "pedido " + nP + " atualizado com SUCESSO!";
        }else{
            nP = s.criarPedidos(dadosPed); //provavel q posso so deletar tudo isso
            this.mensagem = (nP > 0)
                ? "Pedido " + nP + " criado com SUCESSO!"
                : "Erro ao elaborar pedido.";
        }

        this.listaPedidos = s.listaPedidos();
    }

    public String getMensagem() {
        return mensagem;
    }
    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    
    public List<DadosPedidos> getListaPedidos() {
        return listaPedidos;
    }
    public void setListaPedidos(List<DadosPedidos> listaPedidos) {
        this.listaPedidos = listaPedidos;
    }

    
    public DadosPedidos getDadosPed() {
        return dadosPed;
    }
    public void setDadosPed(DadosPedidos dadosPed) {
        this.dadosPed = dadosPed;
    }

    
    public List<ItemCardapio> getCardapio() {
        return cardapio;
    }
    public void setCardapio(List<ItemCardapio> cardapio) {
        this.cardapio = cardapio;
    }

    
    public ItemCardapio getItemC() {
        return itemC;
    }
    public void setItemC(ItemCardapio itemC) {
        this.itemC = itemC;
    }
}
