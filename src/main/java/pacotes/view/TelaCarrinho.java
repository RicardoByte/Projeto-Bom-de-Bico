package pacotes.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import pacotes.models.produtos.Produto;
import pacotes.models.pedido.ItemCarrinho;

public class TelaCarrinho extends JFrame {

    private static final int LARGURA_JANELA = 1024;
    private static final int ALTURA_JANELA = 768;
    private static final int ESPACAMENTO = 10;
    private static List<ItemCarrinho> itensDoCarrinho = new ArrayList<>();
    private JTable tabelaCarrinho;
    private DefaultTableModel modeloTabela;
    private JLabel lblTotal;
    private JLabel lblQuantidadeItens;
    private JButton btnFinalizar;
    private JButton btnContinuar;
    private JButton btnRemover;
    private JButton btnLimpar;
    private CardLayout cardLayout;
    private JPanel painelPrincipal;
    private PainelCheckout painelCheckout;


    public TelaCarrinho() {
        inicializarJanela();
        inicializarComponentes();
        montarInterface();
        configurarEventos();
        atualizarTabela();
    }

    private void inicializarJanela() {
        setTitle("Bom de Bico - Carrinho de Compras");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(LARGURA_JANELA, ALTURA_JANELA);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(0, 10));
    }
    
    private void montarInterface() {
        add(criarPainelCabecalhoPadrao(), BorderLayout.NORTH);
        add(criarPainelRodapePadrao(), BorderLayout.SOUTH);

        cardLayout = new CardLayout();
        painelPrincipal = new JPanel(cardLayout);
        
        JPanel painelListaCarrinho = new JPanel(new BorderLayout(ESPACAMENTO, ESPACAMENTO));
        painelListaCarrinho.setBorder(new EmptyBorder(20, 20, 20, 20));
        painelListaCarrinho.add(criarPainelTopo(), BorderLayout.NORTH);
        painelListaCarrinho.add(criarPainelTabela(), BorderLayout.CENTER);
        painelListaCarrinho.add(criarPainelRodapeBotoes(), BorderLayout.SOUTH);

        painelCheckout = new PainelCheckout(new ArrayList<>(), 0.0, cardLayout, painelPrincipal); 

        painelPrincipal.add(painelListaCarrinho, "CARRINHO");
        painelPrincipal.add(painelCheckout, "CHECKOUT");
        
        add(painelPrincipal, BorderLayout.CENTER);
        cardLayout.show(painelPrincipal, "CARRINHO");
    }

    private void inicializarComponentes() {
        String[] colunas = {"ID", "Produto", "Preço Unit.", "Quantidade", "Subtotal"};
        modeloTabela = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return column == 3; }
        };
        tabelaCarrinho = new JTable(modeloTabela);
        tabelaCarrinho.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabelaCarrinho.setRowHeight(30);
        lblQuantidadeItens = new JLabel("Itens no carrinho: 0");
        lblTotal = new JLabel("Total: R$ 0,00");
        lblTotal.setFont(new Font("Arial", Font.BOLD, 16));
        btnRemover = new JButton("Remover Item");
        btnLimpar = new JButton("Limpar Carrinho");
        btnContinuar = new JButton("Continuar Comprando");
        btnFinalizar = new JButton("Finalizar Compra");
    }
    
    
    
    private JPanel criarPainelTopo() {
       
        JPanel painel = new JPanel(new BorderLayout());
        painel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        JLabel lblTitulo = new JLabel("Meu Carrinho de Compras");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));
        painel.add(lblTitulo, BorderLayout.WEST);
        painel.add(lblQuantidadeItens, BorderLayout.EAST);
        return painel;
    }

    private JPanel criarPainelTabela() {
       
        JPanel painel = new JPanel(new BorderLayout(ESPACAMENTO, ESPACAMENTO));
        JScrollPane scrollPane = new JScrollPane(tabelaCarrinho);
        painel.add(scrollPane, BorderLayout.CENTER);
        JPanel painelBotoesLateral = new JPanel();
        painelBotoesLateral.setLayout(new BoxLayout(painelBotoesLateral, BoxLayout.Y_AXIS));
        painelBotoesLateral.setBorder(new EmptyBorder(0, 10, 0, 0));
        btnRemover.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnLimpar.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelBotoesLateral.add(btnRemover);
        painelBotoesLateral.add(Box.createRigidArea(new Dimension(0, 10)));
        painelBotoesLateral.add(btnLimpar);
        painel.add(painelBotoesLateral, BorderLayout.EAST);
        return painel;
    }

    private JPanel criarPainelRodapeBotoes() {
       
        JPanel painel = new JPanel(new BorderLayout(ESPACAMENTO, ESPACAMENTO));
        painel.setBorder(new EmptyBorder(10, 0, 0, 0));
        JPanel painelTotal = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        painelTotal.add(lblTotal);
        painel.add(painelTotal, BorderLayout.NORTH);
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        painelBotoes.add(btnContinuar);
        painelBotoes.add(btnFinalizar);
        painel.add(painelBotoes, BorderLayout.SOUTH);
        return painel;
    }

    
    
    private JPanel criarPainelCabecalhoPadrao() {
        JPanel painelCabecalho = new JPanel(new BorderLayout(20, 0));
        painelCabecalho.setBorder(new EmptyBorder(10, 20, 10, 20));
        painelCabecalho.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));

       
        JButton homeButton = new JButton("Bom de Bico");
        homeButton.setFont(new Font("Arial", Font.BOLD, 20));
        homeButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); 
        
        homeButton.addActionListener(new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frameAtual = (JFrame) SwingUtilities.getWindowAncestor(homeButton);
                
                frameAtual.getContentPane().removeAll();
                frameAtual.add(new TelaInicial()); 
                frameAtual.revalidate();
                frameAtual.repaint();
                frameAtual.setTitle("Bom de Bico - Home");
            }
        });
        painelCabecalho.add(homeButton, BorderLayout.WEST);
      

        JPanel painelUsuario = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 0));
        
        JButton btnPesquisa = new JButton("Pesquisa");
        btnPesquisa.addActionListener(this::acaoContinuarComprando);
        painelUsuario.add(btnPesquisa);
        
        JButton btnPerfil = new JButton("Meu Perfil");
        btnPerfil.addActionListener(this::abrirTelaPerfil);
        painelUsuario.add(btnPerfil);
        
        painelCabecalho.add(painelUsuario, BorderLayout.EAST);
        return painelCabecalho;
    }
    
    private JPanel criarPainelRodapePadrao() {
        JPanel painelRodape = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 10));
        painelRodape.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.LIGHT_GRAY));
        painelRodape.add(new JLabel("© 2024 Bom de Bico"));
        return painelRodape;
    }


    private void configurarEventos() {
        
        btnRemover.addActionListener(this::acaoRemoverItem);
        btnLimpar.addActionListener(this::acaoLimparCarrinho);
        btnContinuar.addActionListener(this::acaoContinuarComprando);
        btnFinalizar.addActionListener(this::acaoFinalizarCompra);
        modeloTabela.addTableModelListener(e -> {
            if (e.getColumn() == 3) { acaoAtualizarQuantidade(e.getFirstRow()); }
        });
    }

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
        
        TelaPesquisa telaPesquisa = new TelaPesquisa();
        telaPesquisa.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        telaPesquisa.setVisible(true);
        dispose();
    }

    private void acaoFinalizarCompra(ActionEvent e) {
       /*     
        if (itensDoCarrinho.isEmpty()) {
            exibirAviso("O carrinho está vazio!");
            return;
        }*/
        double total = calcularTotalDouble();
        ArrayList<Produto> produtosParaCheckout = extrairProdutosDoCarrinho();
        painelCheckout = new PainelCheckout(produtosParaCheckout, total, cardLayout, painelPrincipal);
        painelPrincipal.remove(1);
        painelPrincipal.add(painelCheckout, "CHECKOUT");
        cardLayout.show(painelPrincipal, "CHECKOUT");
    }

    private void acaoAtualizarQuantidade(int linha) {
        
        try {
            int novaQuantidade = (Integer) modeloTabela.getValueAt(linha, 3);
            ItemCarrinho item = itensDoCarrinho.get(linha);
            if (novaQuantidade <= 0) {
                exibirAviso("Quantidade deve ser maior que zero!");
                atualizarTabela(); 
                return;
            }
            if (novaQuantidade > item.getProduto().getQuantidadeEstoque()) {
                exibirAviso("Quantidade excede o estoque disponível!");
                atualizarTabela(); 
                return;
            }
            item.setQuantidade(novaQuantidade);
            atualizarTabela();
        } catch (Exception e) {
            exibirErro("Erro ao atualizar quantidade.");
            atualizarTabela();
        }
    }
    
    private void abrirTelaPerfil(ActionEvent e) {
       
        TelaPerfil telaPerfil = new TelaPerfil();
        telaPerfil.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        telaPerfil.setVisible(true);
    }
    
   
    public static void adicionarProdutoAoCarrinho(Produto produto, int quantidade) {
        
        if (produto == null || quantidade <= 0) return;
        if (quantidade > produto.getQuantidadeEstoque()) {
            System.err.println("Estoque insuficiente!");
            return;
        }
        for (ItemCarrinho item : itensDoCarrinho) {
            if (item.getProduto().getId() == produto.getId()) {
                int novaQuantidade = item.getQuantidade() + quantidade;
                if (novaQuantidade > produto.getQuantidadeEstoque()) {
                    item.setQuantidade(produto.getQuantidadeEstoque());
                } else {
                    item.setQuantidade(novaQuantidade);
                }
                return;
            }
        }
        itensDoCarrinho.add(new ItemCarrinho(produto, quantidade));
    }
    
    public static void limparCarrinhoEstatico() {
        itensDoCarrinho.clear();
    }

    
    private void atualizarTabela() {
        
        modeloTabela.setRowCount(0);
        for (ItemCarrinho item : itensDoCarrinho) {
            Produto p = item.getProduto();
            double subtotal = p.getPreco() * item.getQuantidade();
            modeloTabela.addRow(new Object[]{
                p.getId(), p.getNome(), String.format("R$ %.2f", p.getPreco()),
                item.getQuantidade(), String.format("R$ %.2f", subtotal)
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
}