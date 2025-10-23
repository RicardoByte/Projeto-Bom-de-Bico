package pacotes.models.pagamento;

import java.time.LocalDateTime;

public class PagamentoPix implements MetodoPagamento {

    private double valor;
    private LocalDateTime dataPagamento;
    private String chavePix;

    public PagamentoPix(double valor, String chavePix) {
        this.valor = valor;
        this.chavePix = chavePix;
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
        return "PIX";
    }

    @Override
    public boolean processarPagamento() {
        System.out.println("Processando pagamento via PIX...");
        // Simula o sucesso se a chave estiver presente
        return chavePix != null && !chavePix.isEmpty();
    }
}