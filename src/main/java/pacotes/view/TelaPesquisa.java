package pacotes.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

import pacotes.repository.ProdutoDb; 
import pacotes.models.produtos.Produto; 

/**
 * Tela de Pesquisa de Produtos.
 */
public class TelaPesquisa extends JFrame {

    public TelaPesquisa() {
        setTitle("Pesquisa de Produtos - Bom de Bico");
        setSize(1280, 800);
        // DISPOSE_ON_CLOSE: Fecha apenas esta janela
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        add(criarPainelCabecalho(), BorderLayout.NORTH);
        add(criarPainelFiltros(), BorderLayout.WEST);
        add(criarPainelConteudoPrincipal(), BorderLayout.CENTER);
    }

    /**
     * Cria o painel do cabeçalho
     */
    private JPanel criarPainelCabecalho() {
        JPanel painelCabecalho = new JPanel(new BorderLayout(20, 0));
        painelCabecalho.setBorder(new EmptyBorder(10, 15, 10, 15));

        JLabel logo = new JLabel("Bom de Bico");
        logo.setFont(new Font("Arial", Font.BOLD, 20));
        painelCabecalho.add(logo, BorderLayout.WEST);

        JTextField campoBusca = new JTextField("Buscar por produto ou marca...");
        painelCabecalho.add(campoBusca, BorderLayout.CENTER);

        // Direita: Links de Usuário (agora são botões)
        JPanel painelUsuario = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 0));
        
        JButton btnPerfil = new JButton("Meu Perfil");
        btnPerfil.addActionListener(this::abrirTelaPerfil);
        painelUsuario.add(btnPerfil);

        JButton btnCarrinho = new JButton("Carrinho");
        btnCarrinho.addActionListener(this::abrirTelaCarrinho);
        painelUsuario.add(btnCarrinho);
        
        painelCabecalho.add(painelUsuario, BorderLayout.EAST);
        return painelCabecalho;
    }

    /**
     * Cria o painel de filtros da lateral esquerda.
     */
    private JPanel criarPainelFiltros() {
        JPanel painelFiltros = new JPanel();
        painelFiltros.setLayout(new BoxLayout(painelFiltros, BoxLayout.Y_AXIS));
        painelFiltros.setBorder(new EmptyBorder(10, 10, 10, 10));
        painelFiltros.setPreferredSize(new Dimension(250, 0));

        JLabel titulo = new JLabel("Filtros");
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        titulo.setAlignmentX(Component.LEFT_ALIGNMENT);
        painelFiltros.add(titulo);

        painelFiltros.add(Box.createRigidArea(new Dimension(0, 15)));

        // --- Seção Categorias ---
        JPanel painelCategorias = new JPanel();
        painelCategorias.setLayout(new BoxLayout(painelCategorias, BoxLayout.Y_AXIS));
        painelCategorias.setBorder(new TitledBorder("Categorias"));
        painelCategorias.add(new JCheckBox("Rações"));
        painelCategorias.add(new JCheckBox("Gaiolas"));
        painelCategorias.add(new JCheckBox("Brinquedos"));
        painelCategorias.add(new JCheckBox("Acessórios"));
        painelCategorias.setAlignmentX(Component.LEFT_ALIGNMENT);
        painelFiltros.add(painelCategorias);

        painelFiltros.add(Box.createRigidArea(new Dimension(0, 15)));

        // --- Seção Faixa de Preço ---
        JPanel painelPreco = new JPanel(new BorderLayout(5, 0));
        painelPreco.setBorder(new TitledBorder("Faixa de Preço"));
        painelPreco.add(new JLabel("R$ 50"), BorderLayout.WEST);
        painelPreco.add(new JSlider(50, 500), BorderLayout.CENTER);
        painelPreco.add(new JLabel("R$ 500"), BorderLayout.EAST);
        painelPreco.setAlignmentX(Component.LEFT_ALIGNMENT);
        painelPreco.setMaximumSize(new Dimension(Integer.MAX_VALUE, painelPreco.getPreferredSize().height));
        painelFiltros.add(painelPreco);
        
        // ... (Outros filtros como Marcas, Tipo de Pássaro) ...

        painelFiltros.add(Box.createVerticalGlue()); // Empurra botões para baixo

        // --- Botões de Ação ---
        JButton btnAplicar = new JButton("Aplicar Filtros");
        btnAplicar.setAlignmentX(Component.LEFT_ALIGNMENT);
        painelFiltros.add(btnAplicar);

        painelFiltros.add(Box.createRigidArea(new Dimension(0, 5)));

        JButton btnLimpar = new JButton("Limpar Tudo");
        btnLimpar.setAlignmentX(Component.LEFT_ALIGNMENT);
        painelFiltros.add(btnLimpar);

        return painelFiltros;
    }

    /**
     * Cria o painel de conteúdo principal (Cabeçalho, Grade e Paginação).
     */
    private JPanel criarPainelConteudoPrincipal() {
        JPanel painelPrincipal = new JPanel(new BorderLayout(0, 10));
        painelPrincipal.setBorder(new EmptyBorder(10, 10, 10, 10));

        // 1. Cabeçalho do Conteúdo (Título e Ordenação)
        JPanel cabecalhoConteudo = new JPanel(new BorderLayout());
        cabecalhoConteudo.add(new JLabel("Todos os Produtos"), BorderLayout.WEST);
        String[] opcoesOrdenacao = { "Mais Relevantes", "Menor Preço", "Maior Preço" };
        cabecalhoConteudo.add(new JComboBox<>(opcoesOrdenacao), BorderLayout.EAST);
        painelPrincipal.add(cabecalhoConteudo, BorderLayout.NORTH);

        // 2. Grade de Produtos (CENTER)
        JPanel painelGrade = new JPanel(new GridLayout(0, 4, 10, 10)); 
        
        // Carrega produtos reais do "banco"
        List<Produto> produtos = ProdutoDb.listarProdutos();
        if (produtos != null && !produtos.isEmpty()) {
            for (Produto p : produtos) {
                painelGrade.add(criarCardProduto(p));
            }
        } else {
            painelGrade.add(new JLabel("Nenhum produto encontrado."));
        }

        painelPrincipal.add(new JScrollPane(painelGrade), BorderLayout.CENTER);

        // 3. Paginação (SOUTH)
        JPanel painelPaginacao = new JPanel(new FlowLayout(FlowLayout.CENTER));
        painelPaginacao.add(new JButton("<"));
        painelPaginacao.add(new JButton("1"));
        painelPaginacao.add(new JButton("2"));
        painelPaginacao.add(new JButton("..."));
        painelPaginacao.add(new JButton(">"));
        painelPrincipal.add(painelPaginacao, BorderLayout.SOUTH);

        return painelPrincipal;
    }

    /**
     * Helper para criar um "card" de produto individual.
     */
    private JPanel criarCardProduto(Produto produto) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        card.setBackground(Color.WHITE);

        JLabel imgPlaceholder = new JLabel("[Imagem de " + produto.getNome() + "]");
        imgPlaceholder.setFont(new Font("Arial", Font.ITALIC, 12));
        imgPlaceholder.setAlignmentX(Component.CENTER_ALIGNMENT);
        imgPlaceholder.setBorder(new EmptyBorder(40, 20, 40, 20));
        card.add(imgPlaceholder);

        JLabel nomeLabel = new JLabel(produto.getNome());
        nomeLabel.setFont(new Font("Arial", Font.BOLD, 14));
        nomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(nomeLabel);

        JLabel precoLabel = new JLabel(String.format("R$ %.2f", produto.getPreco()));
        precoLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        precoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(precoLabel);

        card.add(Box.createRigidArea(new Dimension(0, 10)));

        JButton btnAdicionar = new JButton("Adicionar ao Carrinho");
        btnAdicionar.setAlignmentX(Component.CENTER_ALIGNMENT);
        // AÇÃO DE ADICIONAR AO CARRINHO
        btnAdicionar.addActionListener(e -> {
            // Chama o método estático da TelaCarrinho
            TelaCarrinho.adicionarProdutoAoCarrinho(produto, 1);
            JOptionPane.showMessageDialog(this, 
                produto.getNome() + " adicionado ao carrinho!", 
                "Sucesso", 
                JOptionPane.INFORMATION_MESSAGE);
        });
        card.add(btnAdicionar);

        card.add(Box.createRigidArea(new Dimension(0, 10)));
        return card;
    }
    
    // --- MÉTODOS DE NAVEGAÇÃO (para o cabeçalho) ---

    private void abrirTelaCarrinho(ActionEvent e) {
        TelaCarrinho telaCarrinho = new TelaCarrinho();
        telaCarrinho.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        telaCarrinho.setVisible(true);
    }

    private void abrirTelaPerfil(ActionEvent e) {
        TelaPerfil telaPerfil = new TelaPerfil();
        telaPerfil.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        telaPerfil.setVisible(true);
    }

    /**
     * Método Main (para teste)
     */
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(() -> {
            new TelaPesquisa().setVisible(true);
        });
    }
}