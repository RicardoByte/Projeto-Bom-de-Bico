package pacotes.models.pagamento;
import java.time.LocalDateTime;
import java.util.ArrayList;


public class PagamentoCartao implements MetodoPagamento {

    private double valor;
    private LocalDateTime dataPagamento;
    private String numeroCartao;
    private String cvv;
    private String metodo;

    public PagamentoCartao(double valor, String numeroCartao, String nomeTitular, String validade, String cvv, boolean debito) {
        this.valor = valor;
        this.numeroCartao = numeroCartao;
        this.cvv = cvv;
        this.dataPagamento = LocalDateTime.now();
        this.metodo = debito ? "Débito" : "Crédito" ;
    }

    @Override
    public double getValor() {
        return valor;
    }

    @Override
    public LocalDateTime getDataPagamento() {
        return dataPagamento;
    }

    @Override
    public String getMetodo() {
        return this.metodo;
    }

    @Override
    public boolean processarPagamento() {
        System.out.println("Processando pagamento via cartão...");
        // Lógica simulada de validação de cartão
        return numeroCartao != null && cvv.length() == 3;
    }

    public String[] getOpcoesParcelamento(double total) {
        ArrayList<String> opcoes = new ArrayList<>();
        opcoes.add(String.format("1x de R$ %.2f (sem juros)", total)); // 1x
        
        for (int i = 2; i <= 12; i++) {
            float juros = i * 0.01f; // Juros de 1% por parcela, como em TelaCheckout
            double totalComJuros = total * (1 + juros);
            double valorParcela = totalComJuros / i;
            opcoes.add(String.format("%dx de R$ %.2f (Total: R$ %.2f)", i, valorParcela, totalComJuros));
        }
        return opcoes.toArray(new String[0]);
    }
}
