// Lógica específica para boleto
class PagamentoBoleto extends Pagamento {
    public void processarPagamento(double valor) {
        System.out.println("Processando pagamento com Boleto sem acressimo no valor de R$" + valor);
    }
}