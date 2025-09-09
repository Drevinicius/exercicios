package ExerSOLID.exer_5;

// Lógica específica para Pix
class PagamentoPix extends Pagamento {
    public void processarPagamento(double valor) {
        Double novoValor = valor - (valor * 0.1); // Pix a vista recebe 10% de desconto
        System.out.println("Processando pagamento com Pix no valor de R$" + String.format("%.2f", valor) +
        "\nPagamento via Pix com desconto, novo valor R$ " + String.format("%.2f", novoValor));
    }
}