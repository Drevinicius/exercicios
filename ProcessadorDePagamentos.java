package ExerSOLID.exer_5;

class ProcessadorDePagamentos {
    public void processar(Pagamento tipoPagamento, double valor) {
        tipoPagamento.processarPagamento(valor);
    }
}