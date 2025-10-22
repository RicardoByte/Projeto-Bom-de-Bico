package pacotes.models.pagamento;
import java.time.LocalDateTime;

public interface Pagamento {
    double getValor();
    LocalDateTime getDataPagamento();
    String getMetodo();
    boolean processarPagamento();
}
