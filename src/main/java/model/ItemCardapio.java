package model;

public class ItemCardapio {
    private int nItem;
    private String nome, infoAdd, tipo;
    private double preco;
    private boolean selecionado; //testando função de selecionar no cardápio
    
    //N° Item
    public int getnItem() {
        return nItem;
    }

    public void setnItem(int nItem) {
        this.nItem = nItem;
    }

    //Nome Item
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    //Info Adicionais do Item
    public String getInfoAdd() {
        return infoAdd;
    }

    public void setInfoAdd(String infoAdd) {
        this.infoAdd = infoAdd;
    }

    //Tipo do Item
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    //Preço do Item
    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public boolean isSelecionado() {
        return selecionado;
    }

    public void setSelecionado(boolean selecionado) {
        this.selecionado = selecionado;
    }
}
