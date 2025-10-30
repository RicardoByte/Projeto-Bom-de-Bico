package pacotes.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import pacotes.models.produtos.Produto;
import pacotes.models.pedido.ItemCarrinho;

/**
 * Tela de Carrinho de Compras.
 * Esta classe agora é um JFrame que gerencia 
 * a lista de itens e o painel de checkout usando CardLayout.
 */
public class TelaCarrinho extends JFrame {

    private static final int LARGURA_JANELA = 800;
    private static final int ALTURA_JANELA = 600;
    private static final int ESPACAMENTO = 10;

    // --- GERENCIAMENTO DE ESTADO (CARRINHO) ---
    // Lista estática para ser acessível por outras telas (ex: TelaPesquisa)
    private static List<ItemCarrinho> itensDoCarrinho = new ArrayList<>();

    // Componentes da interface
    private JTable tabelaCarrinho;
    private DefaultTableModel modeloTabela;
    private JLabel lblTotal;
    private JLabel lblQuantidadeItens;
    private JButton btnFinalizar;
    private JButton btnContinuar;
    private JButton btnRemover;
    private JButton btnLimpar;

    // Componentes do CardLayout
    private CardLayout cardLayout;
    private JPanel painelPrincipal; // Painel que contém os cards
    private PainelCheckout painelCheckout; // O painel de checkout

    public TelaCarrinho() {
        inicializarJanela();
        inicializarComponentes();
        montarInterface(); // Monta a estrutura com CardLayout
        configurarEventos();
        
        // Carrega os itens da lista estática na tabela
        atualizarTabela();
    }

    /**
     * Configura as propriedades básicas da janela
     */
    private void inicializarJanela() {
        setTitle("Bom de Bico - Carrinho de Compras");
        // DISPOSE_ON_CLOSE: Fecha apenas esta janela
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(LARGURA_JANELA, ALTURA_JANELA);
        setLocationRelativeTo(null);
        
        // Layout principal da Janela (para o CardLayout)
        cardLayout = new CardLayout();
        painelPrincipal = new JPanel(cardLayout);
        add(painelPrincipal);
    }

    /**
     * Instancia todos os componentes (separados do layout)
     */
    private void inicializarComponentes() {
        String[] colunas = {"ID", "Produto", "Preço Unit.", "Quantidade", "Subtotal"};
        modeloTabela = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 3; // Apenas quantidade é editável
            }
        };

        tabelaCarrinho = new JTable(modeloTabela);
        tabelaCarrinho.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabelaCarrinho.setRowHeight(30);

        lblQuantidadeItens = new JLabel("Itens no carrinho: 0");
        lblTotal = new JLabel("Total: R$ 0,00");

        btnRemover = new JButton("Remover Item");
        btnLimpar = new JButton("Limpar Carrinho");
        btnContinuar = new JButton("Continuar Comprando");
        btnFinalizar = new JButton("Finalizar Compra");
    }

    /**
     * Monta a estrutura da interface com CardLayout
     */
    private void montarInterface() {
        // 1. Criar o Painel da Lista do Carrinho
        JPanel painelListaCarrinho = new JPanel(new BorderLayout(ESPACAMENTO, ESPACAMENTO));
        painelListaCarrinho.add(criarPainelTopo(), BorderLayout.NORTH);
        painelListaCarrinho.add(criarPainelTabela(), BorderLayout.CENTER);
        painelListaCarrinho.add(criarPainelRodape(), BorderLayout.SOUTH);

        // 2. Criar o Painel de Checkout (inicialmente)
        // Passamos o CardLayout e o painelPai para o checkout poder "voltar"
        painelCheckout = new PainelCheckout(new ArrayList<>(), 0.0, cardLayout, painelPrincipal);

        // 3. Adicionar os painéis ao CardLayout
        painelPrincipal.add(painelListaCarrinho, "CARRINHO");
        painelPrincipal.add(painelCheckout, "CHECKOUT");
        
        // Inicia mostrando o carrinho
        cardLayout.show(painelPrincipal, "CARRINHO");
    }

    // --- Métodos de montagem da tela de Lista (antigos) ---
    private JPanel criarPainelTopo() {
        JPanel painel = new JPanel(new BorderLayout());
        painel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JLabel lblTitulo = new JLabel("Meu Carrinho de Compras");
        painel.add(lblTitulo, BorderLayout.WEST);
        painel.add(lblQuantidadeItens, BorderLayout.EAST);
        return painel;
    }

    private JPanel criarPainelTabela() {
        JPanel painel = new JPanel(new BorderLayout(ESPACAMENTO, ESPACAMENTO));
        painel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        JScrollPane scrollPane = new JScrollPane(tabelaCarrinho);
        painel.add(scrollPane, BorderLayout.CENTER);

        JPanel painelBotoesLateral = new JPanel();
        painelBotoesLateral.setLayout(new BoxLayout(painelBotoesLateral, BoxLayout.Y_AXIS));
        btnRemover.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnLimpar.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelBotoesLateral.add(btnRemover);
        painelBotoesLateral.add(Box.createRigidArea(new Dimension(0, 10)));
        painelBotoesLateral.add(btnLimpar);
        painel.add(painelBotoesLateral, BorderLayout.EAST);
        return painel;
    }

    private JPanel criarPainelRodape() {
        JPanel painel = new JPanel(new BorderLayout(ESPACAMENTO, ESPACAMENTO));
        painel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JPanel painelTotal = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        painelTotal.add(lblTotal);
        painel.add(painelTotal, BorderLayout.NORTH);
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        painelBotoes.add(btnContinuar);
        painelBotoes.add(btnFinalizar);
        painel.add(painelBotoes, BorderLayout.SOUTH);
        return painel;
    }
    // --- Fim dos métodos de montagem ---

    /**
     * Configura os eventos dos componentes
     */
    private void configurarEventos() {
        btnRemover.addActionListener(this::acaoRemoverItem);
        btnLimpar.addActionListener(this::acaoLimparCarrinho);
        btnContinuar.addActionListener(this::acaoContinuarComprando);
        btnFinalizar.addActionListener(this::acaoFinalizarCompra);

        modeloTabela.addTableModelListener(e -> {
            if (e.getColumn() == 3) { // Coluna de quantidade
                acaoAtualizarQuantidade(e.getFirstRow());
            }
        });
    }

    // --- Métodos de Ação ---

    private void acaoRemoverItem(ActionEvent e) {
        int linhaSelecionada = tabelaCarrinho.getSelectedRow();
        if (linhaSelecionada < 0) {
            exibirAviso("Selecione um item para remover!");
            return;
        }
        itensDoCarrinho.remove(linhaSelecionada);
        atualizarTabela();
    }

    private void acaoLimparCarrinho(ActionEvent e) {
        if (itensDoCarrinho.isEmpty()) return;
        int resposta = JOptionPane.showConfirmDialog(this, "Limpar todo o carrinho?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (resposta == JOptionPane.YES_OPTION) {
            itensDoCarrinho.clear();
            atualizarTabela();
        }
    }

    private void acaoContinuarComprando(ActionEvent e) {
        // Abre a tela de pesquisa
        TelaPesquisa telaPesquisa = new TelaPesquisa();
        telaPesquisa.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        telaPesquisa.setVisible(true);
        // Fecha a tela do carrinho
        dispose();
    }

    /**
     * Ação de Finalizar Compra: AGORA MUDA PARA O CARD DE CHECKOUT
     */
    private void acaoFinalizarCompra(ActionEvent e) {
        if (itensDoCarrinho.isEmpty()) {
            exibirAviso("O carrinho está vazio!");
            return;
        }

        // 1. Calcular o total e extrair a lista de produtos
        double total = calcularTotalDouble();
        ArrayList<Produto> produtosParaCheckout = extrairProdutosDoCarrinho();

        // 2. Criar um NOVO painel de checkout com os dados atualizados
        //    (Passando o layout e o painel pai para ele poder voltar)
        painelCheckout = new PainelCheckout(produtosParaCheckout, total, cardLayout, painelPrincipal);
        
        // 3. Remover o painel de checkout antigo e adicionar o novo
        painelPrincipal.remove(1); // Remove o componente na posição 1 (o checkout antigo)
        painelPrincipal.add(painelCheckout, "CHECKOUT"); // Adiciona o novo

        // 4. Mudar para a tela de checkout
        cardLayout.show(painelPrincipal, "CHECKOUT");
    }

    private void acaoAtualizarQuantidade(int linha) {
        try {
            int novaQuantidade = (Integer) modeloTabela.getValueAt(linha, 3);
            ItemCarrinho item = itensDoCarrinho.get(linha);

            if (novaQuantidade <= 0) {
                exibirAviso("Quantidade deve ser maior que zero!");
                atualizarTabela(); // Reverte para o valor antigo
                return;
            }
            if (novaQuantidade > item.getProduto().getQuantidadeEstoque()) {
                exibirAviso("Quantidade excede o estoque disponível!");
                atualizarTabela(); // Reverte
                return;
            }
            item.setQuantidade(novaQuantidade);
            atualizarTabela(); // Atualiza subtotais e total

        } catch (Exception e) {
            exibirErro("Erro ao atualizar quantidade.");
            atualizarTabela();
        }
    }

    // --- Métodos de Gerenciamento Estático do Carrinho ---

    /**
     * Método estático para adicionar produtos de outras telas.
     */
    public static void adicionarProdutoAoCarrinho(Produto produto, int quantidade) {
        if (produto == null || quantidade <= 0) return;
        if (quantidade > produto.getQuantidadeEstoque()) {
            // Idealmente, mostraria um JOptionPane, mas métodos estáticos não devem
            // criar GUI. Vamos apenas impedir a adição.
            System.err.println("Estoque insuficiente!");
            return;
        }

        // Verifica se o produto já está no carrinho
        for (ItemCarrinho item : itensDoCarrinho) {
            if (item.getProduto().getId() == produto.getId()) {
                int novaQuantidade = item.getQuantidade() + quantidade;
                if (novaQuantidade > produto.getQuantidadeEstoque()) {
                    item.setQuantidade(produto.getQuantidadeEstoque()); // Limita ao estoque
                } else {
                    item.setQuantidade(novaQuantidade);
                }
                return; // Encontrou e atualizou
            }
        }
        // Se saiu do loop, é um produto novo
        itensDoCarrinho.add(new ItemCarrinho(produto, quantidade));
    }
    
    // Limpa o carrinho (chamado pelo checkout após sucesso)
    public static void limparCarrinhoEstatico() {
        itensDoCarrinho.clear();
    }


    // --- Métodos Utilitários Internos ---

    private void atualizarTabela() {
        modeloTabela.setRowCount(0); // Limpa a tabela
        for (ItemCarrinho item : itensDoCarrinho) { // Lê da lista estática
            Produto p = item.getProduto();
            double subtotal = p.getPreco() * item.getQuantidade();
            modeloTabela.addRow(new Object[]{
                p.getId(),
                p.getNome(),
                String.format("R$ %.2f", p.getPreco()),
                item.getQuantidade(),
                String.format("R$ %.2f", subtotal)
            });
        }
        atualizarTotais();
    }

    private void atualizarTotais() {
        lblQuantidadeItens.setText("Itens no carrinho: " + itensDoCarrinho.size());
        lblTotal.setText(String.format("Total: R$ %.2f", calcularTotalDouble()));
    }

    private double calcularTotalDouble() {
        double valorTotal = 0.0;
        for (ItemCarrinho item : itensDoCarrinho) {
            valorTotal += item.getProduto().getPreco() * item.getQuantidade();
        }
        return valorTotal;
    }
    
    private ArrayList<Produto> extrairProdutosDoCarrinho() {
        ArrayList<Produto> lista = new ArrayList<>();
        for (ItemCarrinho item : itensDoCarrinho) {
            lista.add(item.getProduto());
        }
        return lista;
    }

    private void exibirErro(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem, "Erro", JOptionPane.ERROR_MESSAGE);
    }

    private void exibirAviso(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem, "Aviso", JOptionPane.WARNING_MESSAGE);
    }

    /**
     * Método main para executar a tela (teste)
     */
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(() -> {
            new TelaCarrinho().setVisible(true);
        });
    }
}