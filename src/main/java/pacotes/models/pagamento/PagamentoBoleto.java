package pacotes.models.pagamento;

import java.time.LocalDateTime;

public class PagamentoBoleto implements MetodoPagamento {

    private double valor;
    private LocalDateTime dataPagamento;
    private String codigoBoleto;

    public PagamentoBoleto(double valor, String codigoBoleto) {
        this.valor = valor;
        this.codigoBoleto = codigoBoleto;
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
        return "Boleto Bancário";
    }

    @Override
    public boolean processarPagamento() {
        System.out.println("Processando pagamento via boleto...");
        // Simula a validação de código de boleto
        return codigoBoleto != null && codigoBoleto.length() == 47;
    }
}