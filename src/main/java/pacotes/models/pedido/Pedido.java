package pacotes.models.pedido;

import java.time.LocalDateTime;
import java.util.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import pacotes.models.pagamento.MetodoPagamento;
import pacotes.models.usuario.Cliente;
import pacotes.models.produtos.Produto;

public class Pedido {
    private String idPedido;
    private Date dataCriacao;
    private String status;
    private List<Produto> itensDoPedido;
    private float totalPedido;
    private MetodoPagamento metodoPagamento;
    private Cliente cliente;
    private int parcelas;

    public Pedido(String idPedido, Cliente cliente, List<Produto> itens, MetodoPagamento metodoPagamento) {
        this.idPedido = idPedido;
        this.dataCriacao = new Date();
        this.status = "Pendente";
        this.itensDoPedido = new ArrayList<>(itens);
        this.metodoPagamento = metodoPagamento;
        this.cliente = cliente;
        this.parcelas = 1;
        calcularTotal();
    }
    private void calcularTotal() {
        totalPedido = 0;
        for (Produto item : itensDoPedido) {
            totalPedido += item.calcularPrecoTotal();
        }
    }

    public void confirmarPedido() {
        if (metodoPagamento.processarPagamento(totalPedido, parcelas)) {
            status = "Pago";
            System.out.println("Pedido confirmado para " + cliente.getNome());
        } else {
            System.out.println("Falha no pagamento.");
        }
    }

    public void cancelarPedido() {
        status = "Cancelado";
        System.out.println("Pedido cancelado.");
    }

    public float getTotalPedido() {
        return totalPedido;
    }
}
