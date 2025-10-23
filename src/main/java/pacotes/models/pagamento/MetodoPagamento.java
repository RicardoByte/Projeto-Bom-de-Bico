package pacotes.models.pagamento;
import java.time.LocalDateTime;

public interface MetodoPagamento {
    double getValor();
    LocalDateTime getDataPagamento();
    String getMetodo();
    boolean processarPagamento();
}
