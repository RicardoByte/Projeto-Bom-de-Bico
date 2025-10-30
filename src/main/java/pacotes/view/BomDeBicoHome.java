package pacotes.view; // Certifique-se que o pacote está correto

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;


public class BomDeBicoHome extends JFrame {

    // Define fontes padrão para hierarquia visual
    private static final Font FONTE_TITULO = new Font("Arial", Font.BOLD, 22);
    private static final Font FONTE_SUBTITULO = new Font("Arial", Font.BOLD, 16);
    private static final Font FONTE_PADRAO = new Font("Arial", Font.PLAIN, 12);

    public BomDeBicoHome() {
        setTitle("Bom de Bico - Home");
        setSize(1024, 768); // Um tamanho de tela mais moderno
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Layout principal da janela
        setLayout(new BorderLayout(0, 10)); // Espaçamento vertical

        // 1. Cabeçalho (NORTH)
        add(createHeaderPanel(), BorderLayout.NORTH);

        // 2. Rodapé (SOUTH)
        add(createFooterPanel(), BorderLayout.SOUTH);

        // 3. Conteúdo Principal (CENTER)
        JPanel mainContentPanel = new JPanel();
        mainContentPanel.setLayout(new BoxLayout(mainContentPanel, BoxLayout.Y_AXIS));
        mainContentPanel.setBackground(Color.WHITE);
        // Adiciona um preenchimento (padding) geral em volta do conteúdo
        mainContentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Adiciona todas as seções da página
        mainContentPanel.add(createHeroPanel());
        mainContentPanel.add(Box.createRigidArea(new Dimension(0, 25))); // Espaçador
        mainContentPanel.add(createCategoriesPanel());
        mainContentPanel.add(Box.createRigidArea(new Dimension(0, 25))); // Espaçador
        mainContentPanel.add(createFeaturedProductsPanel());
        mainContentPanel.add(Box.createRigidArea(new Dimension(0, 25))); // Espaçador
        mainContentPanel.add(createPromoBannerPanel());

        // Coloca o painel principal dentro de um JScrollPane
        JScrollPane scrollPane = new JScrollPane(mainContentPanel);
        scrollPane.setBorder(null); // Remove a borda do scrollpane
        scrollPane.getVerticalScrollBar().setUnitIncrement(12); // Rolagem mais suave

        add(scrollPane, BorderLayout.CENTER);
    }

    //Cria o painel do cabeçalho
    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel(new BorderLayout(20, 0));
        // Adiciona preenchimento (padding)
        headerPanel.setBorder(new EmptyBorder(10, 20, 10, 20));
        // Borda inferior sutil para separar do conteúdo
        headerPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));

        // Esquerda: Logo
        JLabel logo = new JLabel("Bom de Bico");
        logo.setFont(new Font("Arial", Font.BOLD, 20));
        headerPanel.add(logo, BorderLayout.WEST);

        // Centro: Navegação
        JPanel navPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        JButton btnCategorias = new JButton("Categorias/Pesquisa");
        btnCategorias.addActionListener(this::abrirPesquisa);
        navPanel.add(btnCategorias);
        navPanel.add(new JLabel("Blog")); // Placeholder
        headerPanel.add(navPanel, BorderLayout.CENTER);

        // Direita: Ações do Usuário
        JPanel userPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 0));
        JButton btnPerfil = new JButton("Meu Perfil");
        btnPerfil.addActionListener(this::abrirPerfil);
        userPanel.add(btnPerfil);

        JButton btnCarrinho = new JButton("Carrinho");
        btnCarrinho.addActionListener(this::abrirCarrinho);
        userPanel.add(btnCarrinho);
        
        headerPanel.add(userPanel, BorderLayout.EAST);
        return headerPanel;
    }

    
    // Cria o painel "Hero" (Banner principal da Arara)
    private JPanel createHeroPanel() {
        JPanel heroPanel = new JPanel(new BorderLayout(10, 10));
        heroPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        heroPanel.setBackground(new Color(245, 245, 245)); // Fundo cinza claro

        JLabel imgLabel = new JLabel("[Imagem de uma Arara]", SwingConstants.CENTER);
        imgLabel.setFont(new Font("Arial", Font.ITALIC, 18));
        imgLabel.setPreferredSize(new Dimension(0, 200));
        heroPanel.add(imgLabel, BorderLayout.CENTER);

        // Painel sul para texto e botão
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setOpaque(false); // Transparente

        JLabel title = new JLabel("Nova Linha de Rações Premium");
        title.setFont(FONTE_TITULO);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        textPanel.add(title);
        
        textPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JButton ctaButton = new JButton("Confira Agora");
        ctaButton.addActionListener(this::abrirPesquisa); // Link para Pesquisa
        ctaButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        textPanel.add(ctaButton);
        
        heroPanel.add(textPanel, BorderLayout.SOUTH);

        return heroPanel;
    }

    
     // Cria a seção "Navegue por Categorias"
    private JPanel createCategoriesPanel() {
        // Painel principal da seção
        JPanel sectionPanel = new JPanel(new BorderLayout(0, 15));
        
        JLabel title = new JLabel("Navegue por Categorias");
        title.setFont(FONTE_SUBTITULO);
        sectionPanel.add(title, BorderLayout.NORTH);

        // Painel da grade de categorias
        JPanel gridPanel = new JPanel(new GridLayout(1, 0, 15, 15)); // 1 linha, colunas dinâmicas, 15px de gap
        
        JButton btnRacoes = new JButton("Rações");
        btnRacoes.addActionListener(this::abrirPesquisa);
        gridPanel.add(btnRacoes);

        JButton btnGaiolas = new JButton("Gaiolas");
        btnGaiolas.addActionListener(this::abrirPesquisa);
        gridPanel.add(btnGaiolas);

        JButton btnBrinquedos = new JButton("Brinquedos");
        btnBrinquedos.addActionListener(this::abrirPesquisa);
        gridPanel.add(btnBrinquedos);

        JButton btnSaude = new JButton("Saúde");
        btnSaude.addActionListener(this::abrirPesquisa);
        gridPanel.add(btnSaude);

        sectionPanel.add(gridPanel, BorderLayout.CENTER);
        return sectionPanel;
    }

    
     // Cria a seção "Produtos em Destaque"
    private JPanel createFeaturedProductsPanel() {
        JPanel sectionPanel = new JPanel(new BorderLayout(0, 15));
        
        JLabel title = new JLabel("Produtos em Destaque");
        title.setFont(FONTE_SUBTITULO);
        sectionPanel.add(title, BorderLayout.NORTH);

        // Grade de produtos
        JPanel gridPanel = new JPanel(new GridLayout(1, 0, 15, 15)); // 1 linha, 15px de gap

        // Adiciona "cards" de produto simplificados
        gridPanel.add(createSimpleProductCard("Mix de Sementes"));
        gridPanel.add(createSimpleProductCard("Gaiola Ornamental"));
        gridPanel.add(createSimpleProductCard("Brinquedo Interativo"));
        gridPanel.add(createSimpleProductCard("Bebedouro Automático"));

        sectionPanel.add(gridPanel, BorderLayout.CENTER);
        return sectionPanel;
    }

    
     // Helper para criar um "card" de produto simples e agradável
    private JPanel createSimpleProductCard(String name) {
        JPanel card = new JPanel();
        // Empilha os componentes verticalmente
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createEtchedBorder()); // Borda sutil
        card.setBackground(Color.WHITE);

        // Placeholder da Imagem
        JLabel img = new JLabel("[Imagem: " + name + "]");
        img.setFont(FONTE_PADRAO);
        img.setAlignmentX(Component.CENTER_ALIGNMENT);
        img.setBorder(new EmptyBorder(30, 10, 30, 10)); // Padding para simular altura
        card.add(img);

        card.add(Box.createRigidArea(new Dimension(0, 10)));

        // Nome do Produto
        JLabel nameLabel = new JLabel(name);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(nameLabel);

        card.add(Box.createRigidArea(new Dimension(0, 5)));

        // Preço (Placeholder)
        JLabel priceLabel = new JLabel("R$ 49,90");
        priceLabel.setFont(FONTE_PADRAO);
        priceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(priceLabel);

        card.add(Box.createRigidArea(new Dimension(0, 10)));

        // Botão
        JButton addButton = new JButton("Adicionar");
        addButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(addButton);

        card.add(Box.createRigidArea(new Dimension(0, 10))); // Espaço inferior
        return card;
    }

    /**
     * Cria o banner de "Frete Grátis"
     */
    private JPanel createPromoBannerPanel() {
        JPanel bannerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        bannerPanel.setBackground(new Color(232, 245, 233)); // Verde claro (do seu código original)
        bannerPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        JLabel title = new JLabel("Frete Grátis em Pedidos Acima de R$199");
        title.setFont(new Font("Arial", Font.BOLD, 16));
        bannerPanel.add(title);
        
        JButton viewProductsButton = new JButton("Ver Produtos");
        viewProductsButton.addActionListener(this::abrirPesquisa); // Link para Pesquisa
        bannerPanel.add(viewProductsButton);

        return bannerPanel;
    }

    /**
     * Cria o painel do rodapé
     */
    private JPanel createFooterPanel() {
        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 10));
        // Borda superior para separar do conteúdo
        footerPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.LIGHT_GRAY));
        
        footerPanel.add(new JLabel("Sobre Nós"));
        footerPanel.add(new JLabel("Contato"));
        footerPanel.add(new JLabel("FAQ"));
        footerPanel.add(new JLabel("© 2024 Bom de Bico"));

        return footerPanel;
    }


    // --- MÉTODOS DE AÇÃO PARA NAVEGAÇÃO ---

    private void abrirPesquisa(ActionEvent e) {
        BomDeBicoPesquisa pesquisa = new BomDeBicoPesquisa();
        pesquisa.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Fecha só esta janela
        pesquisa.setVisible(true);
    }

    private void abrirCarrinho(ActionEvent e) {
        BomDeBicoCarrinho carrinho = new BomDeBicoCarrinho();
        carrinho.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        carrinho.setVisible(true);
    }

    private void abrirPerfil(ActionEvent e) {
        BomDeBicoPerfil perfil = new BomDeBicoPerfil();
        perfil.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        perfil.setVisible(true);
    }

    /**
     * Método Main
     */
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            new BomDeBicoHome().setVisible(true);
        });
    }
}