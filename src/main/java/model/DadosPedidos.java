package model;

public class DadosPedidos {
    private int numPedido;
    private String nomeCliente, infoAdd;
    private double valorTotal;
    
    //N° Pedido
    public int getNumPedido() {
        return numPedido;
    }

    public void setNumPedido(int numPedido) {
        this.numPedido = numPedido;
    }
    
    //Nome do Cliente
    public String getNomeCliente(String string) {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }
    
    //Informações Adicionais
    public String getInfoAdd(String string) {
        return infoAdd;
    }

    public void setInfoAdd(String infoAdd) {
        this.infoAdd = infoAdd;
    }
    
    //Valor Total
    public double getValorTotal(double aDouble) {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }
    
}
