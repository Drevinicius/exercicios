class ProcessadorDePagamentos {
    public void processar(Pagamento tipoPagamento, double valor) {
        tipoPagamento.processarPagamento(valor);
    }
}