import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Insatanciamento dos objetos
        ProcessadorDePagamentos processador = new ProcessadorDePagamentos();
        Double valor;
        int operacao;
        try (Scanner teclado = new Scanner(System.in)) {
            System.out.print("1 - Pix\n2 - Cartao de credito\n3 - Boleto\nEscolha a de pagamento: ");
            operacao = teclado.nextInt();

            System.out.print("Digite o valor: ");
            valor = teclado.nextDouble();

            switch (operacao) {
                case 1:
                    // Adicionando um novo tipo de pagamento sem modificar o ProcessadorDePagamentos
                    Pagamento pix = new PagamentoPix();
                    processador.processar(pix, valor);
                    break;
                case 2:
                    // Processa um pagamento com Cartão de Crédito
                    Pagamento cartao = new PagamentoCartaoCredito();
                    processador.processar(cartao, valor);
                    break;
                case 3:
                    // Processa um pagamento com Boleto
                    Pagamento boleto = new PagamentoBoleto();
                    processador.processar(boleto, valor);
                    break;
                default:
                    System.out.println("Operacao invalida");
                    break;
            }
        }catch (Exception e){
            System.out.println("Erro: " + e);
        }
    }
}
