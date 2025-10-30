package pacotes.models.pedido;

public class Pedido {
    private int id;
    private int carrinhoId;
    private String status;
    private String dataPedido;

    // Construtor completo
    public Pedido(int id, int carrinhoId, String status, String dataPedido) {
        this.id = id;
        this.carrinhoId = carrinhoId;
        this.status = status;
        this.dataPedido = dataPedido;
    }

    // Construtor sem ID (para novos pedidos)
    public Pedido(int carrinhoId, String status, String dataPedido) {
        this.carrinhoId = carrinhoId;
        this.status = status;
        this.dataPedido = dataPedido;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCarrinhoId() {
        return carrinhoId;
    }

    public void setCarrinhoId(int carrinhoId) {
        this.carrinhoId = carrinhoId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(String dataPedido) {
        this.dataPedido = dataPedido;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", carrinhoId=" + carrinhoId +
                ", status='" + status + '\'' +
                ", dataPedido='" + dataPedido + '\'' +
                '}';
    }
}
