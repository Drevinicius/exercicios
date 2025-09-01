package exercicio_5_3;

public class Produto {
    private String nomeProduto;
    private double precoUnitario;
    private Pedido pedido;


    public Produto(String nomeProduto, double precoUnitario){
        this.nomeProduto = nomeProduto;
        this.precoUnitario = precoUnitario;
    }

    public String getNomeProduto(){
        return nomeProduto;
    }
    public double getPrecoUnitario(){
        return precoUnitario;
    }
    public Pedido getPedido(){
        return pedido;
    }

    public void setPedido(Pedido pedido){
        this.pedido = pedido;
    }

    public void exibirInfos(){
        System.out.println("Produto: " + getNomeProduto() + 
        "\nPreço: R$ " + String.format("%.2f", getPrecoUnitario()) + " por unidade");
    }
}
