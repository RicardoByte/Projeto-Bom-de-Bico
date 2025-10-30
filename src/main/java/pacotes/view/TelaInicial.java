package pacotes.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Tela principal "Home" da aplicação.
 */
public class TelaInicial extends JFrame {

    // Define fontes padrão para hierarquia visual
    private static final Font FONTE_TITULO = new Font("Arial", Font.BOLD, 22);
    private static final Font FONTE_SUBTITULO = new Font("Arial", Font.BOLD, 16);
    private static final Font FONTE_PADRAO = new Font("Arial", Font.PLAIN, 12);

    public TelaInicial() {
        setTitle("Bom de Bico - Home");
        setSize(1024, 768);
        // Define o fechamento padrão para SAIR da aplicação
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(0, 10));

        // 1. Cabeçalho (NORTH)
        add(criarPainelCabecalho(), BorderLayout.NORTH);

        // 2. Rodapé (SOUTH)
        add(criarPainelRodape(), BorderLayout.SOUTH);

        // 3. Conteúdo Principal (CENTER)
        JPanel painelConteudoPrincipal = new JPanel();
        painelConteudoPrincipal.setLayout(new BoxLayout(painelConteudoPrincipal, BoxLayout.Y_AXIS));
        painelConteudoPrincipal.setBackground(Color.WHITE);
        painelConteudoPrincipal.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Adiciona todas as seções da página
        painelConteudoPrincipal.add(criarPainelDestaque());
        painelConteudoPrincipal.add(Box.createRigidArea(new Dimension(0, 25))); // Espaçador
        painelConteudoPrincipal.add(criarPainelCategorias());
        painelConteudoPrincipal.add(Box.createRigidArea(new Dimension(0, 25))); // Espaçador
        painelConteudoPrincipal.add(criarPainelProdutosDestaque());
        painelConteudoPrincipal.add(Box.createRigidArea(new Dimension(0, 25))); // Espaçador
        painelConteudoPrincipal.add(criarPainelPromocional());

        // Coloca o painel principal dentro de um JScrollPane
        JScrollPane scrollPane = new JScrollPane(painelConteudoPrincipal);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(12);

        add(scrollPane, BorderLayout.CENTER);
    }

    /**
     * Cria o painel do cabeçalho
     */
    private JPanel criarPainelCabecalho() {
        JPanel painelCabecalho = new JPanel(new BorderLayout(20, 0));
        painelCabecalho.setBorder(new EmptyBorder(10, 20, 10, 20));
        painelCabecalho.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));

        // Esquerda: Logo
        JLabel logo = new JLabel("Bom de Bico");
        logo.setFont(new Font("Arial", Font.BOLD, 20));
        painelCabecalho.add(logo, BorderLayout.WEST);

        // Centro: Navegação
        JPanel painelNavegacao = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        JButton btnCategorias = new JButton("Categorias/Pesquisa");
        btnCategorias.addActionListener(this::abrirTelaPesquisa);
        painelNavegacao.add(btnCategorias);
        painelNavegacao.add(new JLabel("Blog")); // Placeholder
        painelCabecalho.add(painelNavegacao, BorderLayout.CENTER);

        // Direita: Ações do Usuário
        JPanel painelUsuario = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 0));
        
        JButton btnCadastro = new JButton("Cadastrar");
        btnCadastro.addActionListener(this::abrirTelaCadastro);
        painelUsuario.add(btnCadastro);
        
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
     * Cria o painel "Hero" (Banner principal da Arara)
     */
    private JPanel criarPainelDestaque() {
        JPanel painelDestaque = new JPanel(new BorderLayout(10, 10));
        painelDestaque.setBorder(new EmptyBorder(20, 20, 20, 20));
        painelDestaque.setBackground(new Color(245, 245, 245)); // Fundo cinza claro

        JLabel imgLabel = new JLabel("[Imagem de uma Arara]", SwingConstants.CENTER);
        imgLabel.setFont(new Font("Arial", Font.ITALIC, 18));
        imgLabel.setPreferredSize(new Dimension(0, 200));
        painelDestaque.add(imgLabel, BorderLayout.CENTER);

        JPanel painelTexto = new JPanel();
        painelTexto.setLayout(new BoxLayout(painelTexto, BoxLayout.Y_AXIS));
        painelTexto.setOpaque(false); // Transparente

        JLabel titulo = new JLabel("Nova Linha de Rações Premium");
        titulo.setFont(FONTE_TITULO);
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelTexto.add(titulo);
        
        painelTexto.add(Box.createRigidArea(new Dimension(0, 10)));

        JButton btnAcao = new JButton("Confira Agora");
        btnAcao.addActionListener(this::abrirTelaPesquisa); // Link para Pesquisa
        btnAcao.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelTexto.add(btnAcao);
        
        painelDestaque.add(painelTexto, BorderLayout.SOUTH);
        return painelDestaque;
    }

    /**
     * Cria a seção "Navegue por Categorias"
     */
    private JPanel criarPainelCategorias() {
        JPanel painelSecao = new JPanel(new BorderLayout(0, 15));
        
        JLabel titulo = new JLabel("Navegue por Categorias");
        titulo.setFont(FONTE_SUBTITULO);
        painelSecao.add(titulo, BorderLayout.NORTH);

        JPanel painelGrade = new JPanel(new GridLayout(1, 0, 15, 15));
        
        JButton btnRacoes = new JButton("Rações");
        btnRacoes.addActionListener(this::abrirTelaPesquisa);
        painelGrade.add(btnRacoes);

        JButton btnGaiolas = new JButton("Gaiolas");
        btnGaiolas.addActionListener(this::abrirTelaPesquisa);
        painelGrade.add(btnGaiolas);

        JButton btnBrinquedos = new JButton("Brinquedos");
        btnBrinquedos.addActionListener(this::abrirTelaPesquisa);
        painelGrade.add(btnBrinquedos);

        JButton btnSaude = new JButton("Saúde");
        btnSaude.addActionListener(this::abrirTelaPesquisa);
        painelGrade.add(btnSaude);

        painelSecao.add(painelGrade, BorderLayout.CENTER);
        return painelSecao;
    }

    /**
     * Cria a seção "Produtos em Destaque"
     */
    private JPanel criarPainelProdutosDestaque() {
        JPanel painelSecao = new JPanel(new BorderLayout(0, 15));
        
        JLabel titulo = new JLabel("Produtos em Destaque");
        titulo.setFont(FONTE_SUBTITULO);
        painelSecao.add(titulo, BorderLayout.NORTH);

        JPanel painelGrade = new JPanel(new GridLayout(1, 0, 15, 15));

        // Adiciona "cards" de produto simplificados
        painelGrade.add(criarCardProdutoSimples("Mix de Sementes"));
        painelGrade.add(criarCardProdutoSimples("Gaiola Ornamental"));
        painelGrade.add(criarCardProdutoSimples("Brinquedo Interativo"));
        painelGrade.add(criarCardProdutoSimples("Bebedouro Automático"));

        painelSecao.add(painelGrade, BorderLayout.CENTER);
        return painelSecao;
    }

    /**
     * Helper para criar um "card" de produto simples e agradável
     */
    private JPanel criarCardProdutoSimples(String nome) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createEtchedBorder());
        card.setBackground(Color.WHITE);

        JLabel img = new JLabel("[Imagem: " + nome + "]");
        img.setFont(FONTE_PADRAO);
        img.setAlignmentX(Component.CENTER_ALIGNMENT);
        img.setBorder(new EmptyBorder(30, 10, 30, 10));
        card.add(img);

        card.add(Box.createRigidArea(new Dimension(0, 10)));

        JLabel nomeLabel = new JLabel(nome);
        nomeLabel.setFont(new Font("Arial", Font.BOLD, 14));
        nomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(nomeLabel);

        card.add(Box.createRigidArea(new Dimension(0, 5)));

        JLabel precoLabel = new JLabel("R$ 49,90");
        precoLabel.setFont(FONTE_PADRAO);
        precoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(precoLabel);

        card.add(Box.createRigidArea(new Dimension(0, 10)));

        // Botão "Adicionar" que abre a tela de pesquisa
        JButton btnAdicionar = new JButton("Ver Detalhes");
        btnAdicionar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnAdicionar.addActionListener(this::abrirTelaPesquisa);
        card.add(btnAdicionar);

        card.add(Box.createRigidArea(new Dimension(0, 10)));
        return card;
    }

    /**
     * Cria o banner de "Frete Grátis"
     */
    private JPanel criarPainelPromocional() {
        JPanel painelBanner = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        painelBanner.setBackground(new Color(232, 245, 233));
        painelBanner.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        JLabel titulo = new JLabel("Frete Grátis em Pedidos Acima de R$199");
        titulo.setFont(new Font("Arial", Font.BOLD, 16));
        painelBanner.add(titulo);
        
        JButton btnVerProdutos = new JButton("Ver Produtos");
        btnVerProdutos.addActionListener(this::abrirTelaPesquisa); // Link para Pesquisa
        painelBanner.add(btnVerProdutos);

        return painelBanner;
    }

    /**
     * Cria o painel do rodapé
     */
    private JPanel criarPainelRodape() {
        JPanel painelRodape = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 10));
        painelRodape.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.LIGHT_GRAY));
        
        painelRodape.add(new JLabel("Sobre Nós"));
        painelRodape.add(new JLabel("Contato"));
        painelRodape.add(new JLabel("FAQ"));
        painelRodape.add(new JLabel("© 2024 Bom de Bico"));

        return painelRodape;
    }

    // --- MÉTODOS DE AÇÃO PARA NAVEGAÇÃO ---

    private void abrirTelaPesquisa(ActionEvent e) {
        TelaPesquisa telaPesquisa = new TelaPesquisa();
        // DISPOSE_ON_CLOSE: Fecha apenas esta janela, não a aplicação inteira
        telaPesquisa.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        telaPesquisa.setVisible(true);
    }

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
    
    private void abrirTelaCadastro(ActionEvent e) {
        TelaCadastro telaCadastro = new TelaCadastro();
        telaCadastro.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        telaCadastro.setVisible(true);
    }

    /**
     * Método Main
     */
    public static void main(String[] args) {
        try {
            // Usa a aparência do sistema operacional
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            new TelaInicial().setVisible(true);
        });
    }
}