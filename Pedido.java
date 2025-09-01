package exercicio_5_3;

import java.util.List;
import java.util.ArrayList;

public class Pedido {
    private int numPedido;
    private String dataPedido;
    private Cliente cliente;
    private List<ItemPedido> itemPedido;

    public Pedido(int numPedido, String dataPedido, Cliente cliente){
        this.numPedido = numPedido;
        this.dataPedido = dataPedido;
        this.cliente = cliente;
        this.itemPedido = new ArrayList<>();
    }

    // Todos os metodos GETTER
    public int getNumPedido(){ return numPedido;}
    public String getDataPedido(){ return dataPedido;}
    public Cliente getCliente(){ return cliente;}
    public List<ItemPedido> getItemPedido(){return itemPedido;}

    // Todos os metodos solicitado
    public void adicionarItemPedido(Produto produto, int quantidade){ // Recebe o produto e sua quantidade
        ItemPedido novoItem = new ItemPedido(produto, quantidade); // Cria uma nova lista de itens de pedido
        this.itemPedido.add(novoItem); // passa o novo objeto para a lista
        produto.setPedido(this); // Faz a classe produto tambem saiba da adicao 
    }
    public double calculaTotal(ItemPedido itemPedido){
        double somaTotal = 0;
        for(ItemPedido novoItem: getItemPedido()){
            somaTotal += novoItem.calcularSubTotal();
        }
        return somaTotal;
    }
    public void exibirDetalhesPedido(){
        double somaTotal = 0;
        System.out.println("Num. do Pedido: " + getNumPedido() + 
        "\nData de emissao: " + getDataPedido() + 
        "\nCliente: " + getCliente().getNome());
        System.out.println("\nCarregando itens do pedido: ");

        for(ItemPedido novoItem: getItemPedido()){
            System.out.println("- " + novoItem.getProdutoPedido().getNomeProduto() + " - Subtotal: " + 
            String.format("%.2f", novoItem.calcularSubTotal()));
            somaTotal += (novoItem.getProdutoPedido().getPrecoUnitario() * novoItem.getQuantidade());
        }
        System.out.println("Valor total do pedido: " + String.format("%.2f", somaTotal));
    }
    
    public static void main(String[] args) {
        Cliente cliente1 = new Cliente("Andre", "anvcn@gmail.com"); // Criando um cliente

        Produto xSalada = new Produto("X-Salada", 5); // Criando os produtos
        Produto refri = new Produto("Coca Gelada 2lt", 8);
        Produto pizza = new Produto("Pizza Mussarela 8 fts", 50);

        Pedido pedidoNovo = new Pedido(1, "14/08/2025", cliente1); // Criado o pedido

        pedidoNovo.adicionarItemPedido(xSalada, 5); // Adicionando ao pedido
        pedidoNovo.adicionarItemPedido(refri, 3);
        pedidoNovo.adicionarItemPedido(pizza, 2);

        pedidoNovo.exibirDetalhesPedido(); // Exibindo os detalhes


       
    }
}
