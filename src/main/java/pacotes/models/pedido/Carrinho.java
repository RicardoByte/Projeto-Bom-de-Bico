package pacotes.models.pedido;

import java.util.ArrayList;
import java.util.List;

import pacotes.models.produtos.Produto;

public class Carrinho {
    private List<Produto> itens;
    private double valorTotal;

    public Carrinho() {
        itens = new ArrayList<>();
        valorTotal = 0;
    }

    public void adicionarItem(Produto produto, int quantidade) {
        if (quantidade > produto.getQuantidadeEstoque()) {
            throw new IllegalArgumentException("Quantidade solicitada excede o estoque disponível.");
        }
        itens.add(produto);
        calcularTotal();
    }

    public void removerItem(Produto item) {
        itens.remove(item);
        calcularTotal();
    }

    public float calcularTotal() {
        valorTotal = 0;
        for (Produto item : itens) {
            valorTotal += item.calcularPrecoTotal(1);
        }
        return valorTotal;
    }

    public void limparCarrinho() {
        itens.clear();
        valorTotal = 0;
    }

    public List<Produto> getItens() {
        return itens; }
    public float getValorTotal() {
        return valorTotal; }
}
