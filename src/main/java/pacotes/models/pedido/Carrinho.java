package pacotes.models.pedido;

import java.util.ArrayList;
import java.util.List;
import pacotes.models.produtos.Produto;

public class Carrinho {
    private int id;
    private int clienteId;
    private List<ItemCarrinho> itens;
    private double valorTotal;

    // Construtor completo
    public Carrinho(int id, int clienteId) {
        this.id = id;
        this.clienteId = clienteId;
        this.itens = new ArrayList<>();
        this.valorTotal = 0;
    }

    // Construtor sem ID (para novo carrinho)
    public Carrinho(int clienteId) {
        this.clienteId = clienteId;
        this.itens = new ArrayList<>();
        this.valorTotal = 0;
    }

    // Construtor vazio
    public Carrinho() {
        this.itens = new ArrayList<>();
        this.valorTotal = 0;
    }

    // Adicionar item ao carrinho
    public void adicionarItem(Produto produto, int quantidade) {
        if (produto == null) {
            throw new IllegalArgumentException("Produto não pode ser nulo.");
        }
        
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser maior que zero.");
        }
        
        if (quantidade > produto.getQuantidadeEstoque()) {
            throw new IllegalArgumentException("Quantidade solicitada excede o estoque disponível.");
        }
        
        // Verifica se o produto já está no carrinho
        ItemCarrinho itemExistente = buscarItem(produto.getId());
        if (itemExistente != null) {
            int novaQuantidade = itemExistente.getQuantidade() + quantidade;
            if (novaQuantidade > produto.getQuantidadeEstoque()) {
                throw new IllegalArgumentException("Quantidade total excede o estoque disponível.");
            }
            itemExistente.setQuantidade(novaQuantidade);
        } else {
            ItemCarrinho novoItem = new ItemCarrinho(produto, quantidade);
            itens.add(novoItem);
        }
        
        calcularTotal();
    }

    // Remover item do carrinho
    public void removerItem(int produtoId) {
        itens.removeIf(item -> item.getProduto().getId() == produtoId);
        calcularTotal();
    }

    // Atualizar quantidade de um item
    public void atualizarQuantidade(int produtoId, int novaQuantidade) {
        ItemCarrinho item = buscarItem(produtoId);
        if (item != null) {
            if (novaQuantidade > item.getProduto().getQuantidadeEstoque()) {
                throw new IllegalArgumentException("Quantidade solicitada excede o estoque disponível.");
            }
            item.setQuantidade(novaQuantidade);
            calcularTotal();
        }
    }

    // Buscar item no carrinho
    private ItemCarrinho buscarItem(int produtoId) {
        for (ItemCarrinho item : itens) {
            if (item.getProduto().getId() == produtoId) {
                return item;
            }
        }
        return null;
    }

    // Calcular total do carrinho
    public double calcularTotal() {
        valorTotal = 0;
        for (ItemCarrinho item : itens) {
            valorTotal += item.getSubtotal();
        }
        return valorTotal;
    }

    // Limpar carrinho
    public void limparCarrinho() {
        itens.clear();
        valorTotal = 0;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public List<ItemCarrinho> getItens() {
        return itens;
    }

    public void setItens(List<ItemCarrinho> itens) {
        this.itens = itens;
        calcularTotal();
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public int getTotalItens() {
        int total = 0;
        for (ItemCarrinho item : itens) {
            total += item.getQuantidade();
        }
        return total;
    }

    public boolean estaVazio() {
        return itens.isEmpty();
    }

    @Override
    public String toString() {
        return "Carrinho{" +
                "id=" + id +
                ", clienteId=" + clienteId +
                ", totalItens=" + getTotalItens() +
                ", valorTotal=" + String.format("%.2f", valorTotal) +
                '}';
    }
}