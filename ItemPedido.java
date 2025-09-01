package exercicio_5_3;

public class ItemPedido {   
    private Produto produto;
    private int quantidade;

    public ItemPedido(Produto produto, int quantidade){
        this.produto = produto;
        this.quantidade = quantidade;
    }
    
    public Produto getProdutoPedido(){
        return produto;
    }
    public int getQuantidade(){
        return quantidade;
    }

    public double calcularSubTotal(){
        return quantidade * produto.getPrecoUnitario();
    }
}
