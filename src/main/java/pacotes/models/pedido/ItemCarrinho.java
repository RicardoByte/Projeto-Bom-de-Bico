package pacotes.models.pedido;

import pacotes.models.produtos.Produto;

public class ItemCarrinho {
    private int id;
    private int carrinhoId;
    private Produto produto;
    private int quantidade;

    // Construtor completo
    public ItemCarrinho(int id, int carrinhoId, Produto produto, int quantidade) {
        this.id = id;
        this.carrinhoId = carrinhoId;
        this.produto = produto;
        this.quantidade = quantidade;
    }

    // Construtor sem IDs (para novos itens)
    public ItemCarrinho(Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
    }

    // Construtor vazio
    public ItemCarrinho() {
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

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    // Método para calcular o subtotal do item
    public double getSubtotal() {
        return produto.getPreco() * quantidade;
    }

    @Override
    public String toString() {
        return "ItemCarrinho{" +
                "id=" + id +
                ", produto=" + (produto != null ? produto.getNome() : "null") +
                ", quantidade=" + quantidade +
                ", subtotal=" + String.format("%.2f", getSubtotal()) +
                '}';
    }
}