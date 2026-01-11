package control;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import model.DadosPedidos;
import model.ItemCardapio;
import model.ServicosLanchonete;

@Named
@SessionScoped
public class PedidosController implements Serializable {

    private String mensagem = "Bem vindo ao Controle de Pedidos!";
    
    //criação de pedidos
    private List<DadosPedidos> listaPedidos;
    private DadosPedidos dadosPed = new DadosPedidos();
    
    //cardapio
    private List<ItemCardapio> cardapio;
    private ItemCardapio itemC = new ItemCardapio();
    
    //lista dos itens selecionados
    private List<ItemCardapio> itensSelecionados;
    private double valorTotal = 0;

    public PedidosController() {
        ServicosLanchonete s = new ServicosLanchonete();
        listaPedidos = s.listaPedidos();
        cardapio = s.listagemCardapio();
    }

    public void criarPedido() {
        ServicosLanchonete s = new ServicosLanchonete();
        int nP;

        if (this.dadosPed.getNumPedido() != 0) {
            s.atualizarDadosPedido(dadosPed);
            nP = dadosPed.getNumPedido();
            this.mensagem = "pedido " + nP + " atualizado com SUCESSO!";
        } else {
            nP = s.criarPedidos(dadosPed);
            this.mensagem = (nP > 0)
                ? "Pedido " + nP + " criado com SUCESSO!"
                : "Erro ao elaborar pedido.";
        }

        this.listaPedidos = s.listaPedidos();

        //limpa os dados após a criação do pedido
        this.dadosPed = new DadosPedidos();

        //desmarca os itens marcados no último pedido
        for (ItemCardapio item : cardapio) {
            item.setSelecionado(false);
        }

        //por fim, zera o valor total
        this.valorTotal = 0;
    }

    //Novo método para coletar os itens selecionados no cardápio
    public void finalizarPedido() {
        List<ItemCardapio> itensSelecionadosTemp = new ArrayList<>();
        double valTemp = 0;
        
        //adicionando função para adicionar automaticamente o valor total da compra
        for(ItemCardapio c : this.cardapio){
            if(c.isSelecionado()){
                itensSelecionadosTemp.add(c);
                
                valTemp += c.getPreco();
                this.dadosPed.setValorTotal(valTemp);//supostamente adiciona o valor corretamente
                
                //valTemp = 0;
            }
        }
        
        /*this.itensSelecionados = this.cardapio.stream()
                .filter(ItemCardapio::isSelecionado)
                .collect(Collectors.toList());*/
        
        //mensagem caso esteja vazio ou cheio
        this.mensagem = itensSelecionadosTemp.isEmpty()
                ? "Nenhum item selecionado!"
                : itensSelecionadosTemp.size() + " itens selecionados no pedido.";
        
    }
    
    //método para apagar pedidos
    public void apagarPedido(int n){
        ServicosLanchonete s = new ServicosLanchonete();
        s.deletarPedido(n);
        this.listaPedidos = s.listaPedidos();
        this.mensagem="Pedido "+n+" removido";
    }
    
    public void alterarPed(int porId){
        ServicosLanchonete s = new ServicosLanchonete();
        this.dadosPed = s.consultarPorId_Ped(porId);
        this.listaPedidos = s.listaPedidos();
    }    

    //mensagem
    public String getMensagem() {
        return mensagem;
    }
    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    //lista de pedidos
    public List<DadosPedidos> getListaPedidos() {
        return listaPedidos;
    }
    public void setListaPedidos(List<DadosPedidos> listaPedidos) {
        this.listaPedidos = listaPedidos;
    }

    //dados pedido
    public DadosPedidos getDadosPed() {
        return dadosPed;
    }
    public void setDadosPed(DadosPedidos dadosPed) {
        this.dadosPed = dadosPed;
    }

    //listagem do cardápio
    public List<ItemCardapio> getCardapio() {
        return cardapio;
    }
    public void setCardapio(List<ItemCardapio> cardapio) {
        this.cardapio = cardapio;
    }
    
    //item do cardépio
    public ItemCardapio getItemC() {
        return itemC;
    }
    public void setItemC(ItemCardapio itemC) {
        this.itemC = itemC;
    }

    //se o valor ta selecionado
    public List<ItemCardapio> getItensSelecionados() {
        return itensSelecionados;
    }

    //valor total do pedido
    public double getValorTotal() {
        return valorTotal;
    }
    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }
}