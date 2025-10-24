package pacotes.models.pedido;

public class CarrinhoProdutos {
    private int id;
    private int carrinhoId;
    private int produtoId;
    private int quantidade;

    // Construtor completo
    public CarrinhoProdutos(int id, int carrinhoId, int produtoId, int quantidade) {
        this.id = id;
        this.carrinhoId = carrinhoId;
        this.produtoId = produtoId;
        this.quantidade = quantidade;
    }

    // Construtor sem ID (para novo item)
    public CarrinhoProdutos(int carrinhoId, int produtoId, int quantidade) {
        this.carrinhoId = carrinhoId;
        this.produtoId = produtoId;
        this.quantidade = quantidade;
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

    public int getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(int produtoId) {
        this.produtoId = produtoId;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return "CarrinhoProdutos{" +
                "id=" + id +
                ", carrinhoId=" + carrinhoId +
                ", produtoId=" + produtoId +
                ", quantidade=" + quantidade +
                '}';
    }
}