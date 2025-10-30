package pacotes.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import pacotes.models.produtos.Produto;
import pacotes.repository.ProdutoDb;
import pacotes.models.pedido.ItemCarrinho;

public class BomDeBicoCarrinho extends JFrame {

    // Constantes de UI
    private static final int LARGURA_JANELA = 800;
    private static final int ALTURA_JANELA = 600;
    private static final int ESPACAMENTO = 10;

    // Componentes da interface
    private JTable tabelaCarrinho;
    private DefaultTableModel modeloTabela;
    private JLabel lblTotal;
    private JLabel lblQuantidadeItens;
    private JButton btnFinalizar;
    private JButton btnContinuar;
    private JButton btnRemover;
    private JButton btnLimpar;

    // Lista de itens do carrinho (Produto e quantidade)
    private List<ItemCarrinho> itensCarrinho;

    public BomDeBicoCarrinho() {
        itensCarrinho = new ArrayList<>();
        inicializarJanela();
        inicializarComponentes();
        montarInterface();
        configurarEventos();
        carregarProdutosExemplo();
    }

    /**
     * Configura as propriedades básicas da janela
     */
    private void inicializarJanela() {
        setTitle("Bom de Bico - Carrinho de Compras");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(LARGURA_JANELA, ALTURA_JANELA);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(ESPACAMENTO, ESPACAMENTO));
    }

    /**
     * Instancia todos os componentes da interface
     */
    private void inicializarComponentes() {
        // Configuração da tabela
        String[] colunas = {"ID", "Produto", "Preço Unit.", "Quantidade", "Subtotal"};
        modeloTabela = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 3; // Apenas a coluna quantidade é editável
            }

                };

        tabelaCarrinho = new JTable(modeloTabela);
        tabelaCarrinho.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabelaCarrinho.setRowHeight(30);

        // Labels de informação
        lblQuantidadeItens = new JLabel("Itens no carrinho: 0");
        lblTotal = new JLabel("Total: R$ 0,00");

        // Botões
        btnRemover = new JButton("Remover Item");
        btnLimpar = new JButton("Limpar Carrinho");
        btnContinuar = new JButton("Continuar Comprando");
        btnFinalizar = new JButton("Finalizar Compra");
    }

    /**
     * Monta a estrutura completa da interface
     */
    private void montarInterface() {
        add(criarPainelTopo(), BorderLayout.NORTH);
        add(criarPainelTabela(), BorderLayout.CENTER);
        add(criarPainelRodape(), BorderLayout.SOUTH);
    }

    /**
     * Cria o painel do topo com título
     */
    private JPanel criarPainelTopo() {
        JPanel painel = new JPanel(new BorderLayout());
        painel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel lblTitulo = new JLabel("Meu Carrinho de Compras");

        painel.add(lblTitulo, BorderLayout.WEST);
        painel.add(lblQuantidadeItens, BorderLayout.EAST);

        return painel;
    }

    /**
     * Cria o painel com a tabela de produtos
     */
    private JPanel criarPainelTabela() {
        JPanel painel = new JPanel(new BorderLayout(ESPACAMENTO, ESPACAMENTO));
        painel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));

        JScrollPane scrollPane = new JScrollPane(tabelaCarrinho);
        painel.add(scrollPane, BorderLayout.CENTER);

        // Painel de botões laterais
        JPanel painelBotoesLateral = new JPanel();
        painelBotoesLateral.setLayout(new BoxLayout(painelBotoesLateral, BoxLayout.Y_AXIS));
        painelBotoesLateral.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));

        btnRemover.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnLimpar.setAlignmentX(Component.CENTER_ALIGNMENT);

        painelBotoesLateral.add(btnRemover);
        painelBotoesLateral.add(Box.createRigidArea(new Dimension(0, 10)));
        painelBotoesLateral.add(btnLimpar);

        painel.add(painelBotoesLateral, BorderLayout.EAST);

        return painel;
    }

    /**
     * Cria o painel do rodapé com total e botões de ação
     */
    private JPanel criarPainelRodape() {
        JPanel painel = new JPanel(new BorderLayout(ESPACAMENTO, ESPACAMENTO));
        painel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Painel do total
        JPanel painelTotal = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        painelTotal.add(lblTotal);
        painel.add(painelTotal, BorderLayout.NORTH);

        // Painel de botões
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        painelBotoes.add(btnContinuar);
        painelBotoes.add(btnFinalizar);
        painel.add(painelBotoes, BorderLayout.SOUTH);

        return painel;
    }

    /**
     * Configura os eventos dos componentes
     */
    private void configurarEventos() {
        btnRemover.addActionListener(this::acaoRemoverItem);
        btnLimpar.addActionListener(this::acaoLimparCarrinho);
        btnContinuar.addActionListener(this::acaoContinuarComprando);
        btnFinalizar.addActionListener(this::acaoFinalizarCompra);

        // Listener para alteração de quantidade na tabela
        modeloTabela.addTableModelListener(e -> {
            if (e.getColumn() == 3) { // Coluna de quantidade
                atualizarQuantidadeItem(e.getFirstRow());
            }
        });
    }

    /**
     * Adiciona um produto ao carrinho
     */
    public void adicionarProduto(Produto produto, int quantidade) {
        if (produto == null || quantidade <= 0) {
            exibirErro("Produto ou quantidade inválidos!");
            return;
        }

        if (quantidade > produto.getQuantidadeEstoque()) {
            exibirErro("Quantidade solicitada não disponível em estoque!");
            return;
        }

        // Verifica se o produto já está no carrinho
        for (ItemCarrinho item : itensCarrinho) {
            if (item.getProduto().getId() == produto.getId()) {
                int novaQuantidade = item.getQuantidade() + quantidade;
                if (novaQuantidade > produto.getQuantidadeEstoque()) {
                    exibirErro("Quantidade total excede o estoque disponível!");
                    return;
                }
                item.setQuantidade(novaQuantidade);
                atualizarTabela();
                return;
            }
        }

        // Adiciona novo item
        itensCarrinho.add(new ItemCarrinho(produto, quantidade));
        atualizarTabela();
    }

    /**
     * Atualiza a tabela com os itens do carrinho
     */
    private void atualizarTabela() {
        modeloTabela.setRowCount(0);

        for (ItemCarrinho item : itensCarrinho) {
            Produto produto = item.getProduto();
            int quantidade = item.getQuantidade();
            double subtotal = produto.getPreco() * quantidade;

            modeloTabela.addRow(new Object[]{
                produto.getId(),
                produto.getNome(),
                String.format("R$ %.2f", produto.getPreco()),
                quantidade,
                String.format("R$ %.2f", subtotal)
            });
        }

        atualizarTotais();
    }

    /**
     * Atualiza os totais do carrinho
     */
    private void atualizarTotais() {
        int totalItens = itensCarrinho.size();
        double valorTotal = 0.0;

        for (ItemCarrinho item : itensCarrinho) {
            valorTotal += item.getProduto().getPreco() * item.getQuantidade();
        }

        lblQuantidadeItens.setText("Itens no carrinho: " + totalItens);
        lblTotal.setText(String.format("Total: R$ %.2f", valorTotal));
    }

    /**
     * Atualiza a quantidade de um item específico
     */
    private void atualizarQuantidadeItem(int row) {
        try {
            int novaQuantidade = (Integer) modeloTabela.getValueAt(row, 3);
            ItemCarrinho item = itensCarrinho.get(row);

            if (novaQuantidade <= 0) {
                exibirErro("Quantidade deve ser maior que zero!");
                atualizarTabela();
                return;
            }

            if (novaQuantidade > item.getProduto().getQuantidadeEstoque()) {
                exibirErro("Quantidade excede o estoque disponível!");
                atualizarTabela();
                return;
            }

            item.setQuantidade(novaQuantidade);
            atualizarTabela();

        } catch (Exception e) {
            exibirErro("Erro ao atualizar quantidade: " + e.getMessage());
            atualizarTabela();
        }
    }

    /**
     * Remove o item selecionado do carrinho
     */
    private void acaoRemoverItem(ActionEvent e) {
        int linhaSelecionada = tabelaCarrinho.getSelectedRow();

        if (linhaSelecionada < 0) {
            exibirAviso("Selecione um item para remover!");
            return;
        }

        int resposta = JOptionPane.showConfirmDialog(
            this,
            "Deseja realmente remover este item do carrinho?",
            "Confirmar Remoção",
            JOptionPane.YES_NO_OPTION
        );

        if (resposta == JOptionPane.YES_OPTION) {
            itensCarrinho.remove(linhaSelecionada);
            atualizarTabela();
        }
    }

    /**
     * Limpa todos os itens do carrinho
     */
    private void acaoLimparCarrinho(ActionEvent e) {
        if (itensCarrinho.isEmpty()) {
            exibirAviso("O carrinho já está vazio!");
            return;
        }

        int resposta = JOptionPane.showConfirmDialog(
            this,
            "Deseja realmente limpar todo o carrinho?",
            "Confirmar Limpeza",
            JOptionPane.YES_NO_OPTION
        );

        if (resposta == JOptionPane.YES_OPTION) {
            itensCarrinho.clear();
            atualizarTabela();
        }
    }

    /**
     * Volta para a tela de produtos
     */
    private void acaoContinuarComprando(ActionEvent e) {
        System.out.println("Continuando comprando...");
        // Aqui você pode abrir a tela de listagem de produtos
        // new BomDeBicoProdutos().setVisible(true);
        dispose();
    }

    /**
     * Finaliza a compra
     */
    private void acaoFinalizarCompra(ActionEvent e) {
        if (itensCarrinho.isEmpty()) {
            exibirAviso("O carrinho está vazio!");
            return;
        }

        StringBuilder resumo = new StringBuilder();
        resumo.append("Resumo da Compra:\n\n");

        double total = 0.0;
        for (ItemCarrinho item : itensCarrinho) {
            Produto produto = item.getProduto();
            int quantidade = item.getQuantidade();
            double subtotal = produto.getPreco() * quantidade;
            total += subtotal;

            resumo.append(String.format("%s - %d x R$ %.2f = R$ %.2f\n",
                produto.getNome(), quantidade, produto.getPreco(), subtotal));
        }

        resumo.append(String.format("\nTotal: R$ %.2f", total));

        int resposta = JOptionPane.showConfirmDialog(
            this,
            resumo.toString(),
            "Confirmar Compra",
            JOptionPane.YES_NO_OPTION
        );

        if (resposta == JOptionPane.YES_OPTION) {
            if (processarCompra()) {
                JOptionPane.showMessageDialog(
                    this,
                    "Compra realizada com sucesso!",
                    "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE
                );
                itensCarrinho.clear();
                atualizarTabela();
            }
        }
    }

    /**
     * Processa a compra (aqui você pode integrar com banco de dados)
     */
    private boolean processarCompra() {
        try {
            System.out.println("=== Processando Compra ===");
            for (ItemCarrinho item : itensCarrinho) {
                Produto produto = item.getProduto();
                int quantidade = item.getQuantidade();
                
                System.out.println(String.format("Produto: %s | Qtd: %d | Total: R$ %.2f",
                    produto.getNome(), quantidade, produto.getPreco() * quantidade));
                
                // Aqui você pode atualizar o estoque no banco
                // int novoEstoque = produto.getQuantidadeEstoque() - quantidade;
                // ProdutoDb.atualizarProduto(...);
            }
            System.out.println("======================");
            return true;
        } catch (Exception e) {
            exibirErro("Erro ao processar compra: " + e.getMessage());
            return false;
        }
    }

    /**
     * Carrega produtos de exemplo do banco de dados
     */
    private void carregarProdutosExemplo() {
        // Busca produtos do banco
        List<Produto> produtosDisponiveis = ProdutoDb.listarProdutos();

        // Adiciona alguns produtos ao carrinho como exemplo
        if (produtosDisponiveis != null && !produtosDisponiveis.isEmpty()) {
            if (produtosDisponiveis.size() > 0) {
                adicionarProduto(produtosDisponiveis.get(0), 2);
            }
            if (produtosDisponiveis.size() > 1) {
                adicionarProduto(produtosDisponiveis.get(1), 1);
            }
        }
    }

    /**
     * Exibe mensagem de erro
     */
    private void exibirErro(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem, "Erro", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Exibe mensagem de aviso
     */
    private void exibirAviso(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem, "Aviso", JOptionPane.WARNING_MESSAGE);
    }

    /**
     * Método main para executar a tela
     */
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            BomDeBicoCarrinho frame = new BomDeBicoCarrinho();
            frame.setVisible(true);
        });
    }
}