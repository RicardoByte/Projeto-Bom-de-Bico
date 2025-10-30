package pacotes.models.logistica;

public class Transporte {

    private String tipo;
    private double custo;
    private double tempoEstimado;

    public Transporte() {
    }

    public Transporte(String tipo, double custo, double tempoEstimado) {
        this.tipo = tipo;
        this.custo = custo;
        this.tempoEstimado = tempoEstimado;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getCusto() {
        return custo;
    }

    public void setCusto(double custo) {
        this.custo = custo;
    }

    public double getTempoEstimado() {
        return tempoEstimado;
    }

    public void setTempoEstimado(double tempoEstimado) {
        this.tempoEstimado = tempoEstimado;
    }

    @Override
    public String toString() {
        return tipo + " - R$" + custo + " | Tempo estimado: " + tempoEstimado + " dias";
    }
}
