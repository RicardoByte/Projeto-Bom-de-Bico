package pacotes.models.logistica;
import java.time.LocalDateTime;

public class Entrega {

    private String codigoRastreio;
    private Endereco enderecoEntrega;
    private Transporte transporte;
    private LocalDateTime dataEnvio;
    private LocalDateTime dataEntregaPrevista;
    private boolean entregue;

    public Entrega(String codigoRastreio, Endereco enderecoEntrega, Transporte transporte) {
        this.codigoRastreio = codigoRastreio;
        this.enderecoEntrega = enderecoEntrega;
        this.transporte = transporte;
        this.dataEnvio = LocalDateTime.now();
        this.dataEntregaPrevista = dataEnvio.plusDays((long) transporte.getTempoEstimado());
        this.entregue = false;
    }

    public String getCodigoRastreio() {
        return codigoRastreio;
    }

    public Endereco getEnderecoEntrega() {
        return enderecoEntrega;
    }

    public Transporte getTransporte() {
        return transporte;
    }

    public LocalDateTime getDataEnvio() {
        return dataEnvio;
    }

    public LocalDateTime getDataEntregaPrevista() {
        return dataEntregaPrevista;
    }

    public boolean isEntregue() {
        return entregue;
    }

    public void marcarComoEntregue() {
        this.entregue = true;
        System.out.println("Entrega marcada como concluída!");
    }

    @Override
    public String toString() {
        return "Código: " + codigoRastreio +
                "\nEndereço: " + enderecoEntrega +
                "\nTransporte: " + transporte +
                "\nEnviado em: " + dataEnvio +
                "\nEntrega prevista: " + dataEntregaPrevista +
                "\nStatus: " + (entregue ? "Entregue" : "Em transporte");
    }
}