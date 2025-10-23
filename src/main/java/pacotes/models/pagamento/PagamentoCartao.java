package pacotes.models.pagamento;
import java.time.LocalDateTime;


public class PagamentoCartao implements MetodoPagamento {

    private double valor;
    private LocalDateTime dataPagamento;
    private String numeroCartao;
    private String nomeTitular;
    private String validade;
    private String cvv;

    public PagamentoCartao(double valor, String numeroCartao, String nomeTitular, String validade, String cvv) {
        this.valor = valor;
        this.numeroCartao = numeroCartao;
        this.nomeTitular = nomeTitular;
        this.validade = validade;
        this.cvv = cvv;
        this.dataPagamento = LocalDateTime.now();
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
        return "Cartão de Crédito/Débito";
    }

    @Override
    public boolean processarPagamento() {
        System.out.println("Processando pagamento via cartão...");
        // Lógica simulada de validação de cartão
        return numeroCartao != null && cvv.length() == 3;
    }
}
