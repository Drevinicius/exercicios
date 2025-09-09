package ExerSOLID.exer_5;

// Lógica específica para cartão de crédito
class PagamentoCartaoCredito extends Pagamento {
    public void processarPagamento(double valor) {
        Double novoValor = valor + (valor * 0.1); // Via cartao a um acressimo
        System.out.println("Processando pagamento com Cartão de Credito no valor de R$" + String.format("%.2f", valor)
        + "\nValor em cartao tem acressimo na transacao, novo valor R$" + String.format("%.2f", novoValor));
    }
}