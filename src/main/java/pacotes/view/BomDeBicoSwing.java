package pacotes.view;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Recriação estrutural da homepage "Bom de Bico" usando Java Swing.
 * Foca no layout e na organização dos componentes.
 */
public class BomDeBicoSwing extends JFrame {

    // Define cores principais para consistência
    private static final Color COLOR_LIGHT_GREEN_BG = new Color(245, 250, 245);
    private static final Color COLOR_BUTTON_GREEN = new Color(139, 195, 74); // Verde do botão "Confira"
    private static final Color COLOR_LIGHT_GREEN_BUTTON = new Color(232, 245, 233); // Verde claro do botão "Adicionar"
    private static final Color COLOR_DARK_TEXT = new Color(33, 33, 33);
    private static final Color COLOR_PRICE = new Color(67, 160, 71);

    public BomDeBicoSwing() {
        setTitle("Bom de Bico");
        setSize(1200, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // 1. Cabeçalho (NORTH)
        add(createHeaderPanel(), BorderLayout.NORTH);

        // 2. Rodapé (SOUTH)
        add(createFooterPanel(), BorderLayout.SOUTH);

        // 3. Conteúdo Principal (CENTER)
        // Painel que conterá todas as seções empilhadas
        JPanel mainContentPanel = new JPanel();
        mainContentPanel.setLayout(new BoxLayout(mainContentPanel, BoxLayout.Y_AXIS));
        mainContentPanel.setBackground(Color.WHITE);

        // Adiciona todas as seções da página
        mainContentPanel.add(createHeroPanel());
        mainContentPanel.add(Box.createRigidArea(new Dimension(0, 30))); // Espaçador
        mainContentPanel.add(createCategoriesPanel());
        mainContentPanel.add(Box.createRigidArea(new Dimension(0, 30))); // Espaçador
        mainContentPanel.add(createFeaturedProductsPanel());
        mainContentPanel.add(Box.createRigidArea(new Dimension(0, 30))); // Espaçador
        mainContentPanel.add(createPromoBannerPanel());
        mainContentPanel.add(Box.createRigidArea(new Dimension(0, 30))); // Espaçador

        // Coloca o painel principal dentro de um JScrollPane
        JScrollPane scrollPane = new JScrollPane(mainContentPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(null); // Remove a borda do scrollpane
        scrollPane.getVerticalScrollBar().setUnitIncrement(16); // Melhora a velocidade da rolagem

        add(scrollPane, BorderLayout.CENTER);
    }

    /**
     * Cria o painel do cabeçalho (Logo, Links, Busca, Ícones)
     */
    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel(new BorderLayout(20, 0));
        headerPanel.setBorder(new EmptyBorder(10, 20, 10, 20)); // Padding
        headerPanel.setBackground(Color.WHITE);

        // Esquerda: Logo
        JLabel logo = new JLabel("Bom de Bico");
        logo.setFont(new Font("Arial", Font.BOLD, 24));
        logo.setForeground(COLOR_BUTTON_GREEN);
        headerPanel.add(logo, BorderLayout.WEST);

        // Centro: Links de Navegação
        JPanel navLinks = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        navLinks.setBackground(Color.WHITE);
        navLinks.add(new JLabel("Categorias"));
        navLinks.add(new JLabel("Promoções"));
        navLinks.add(new JLabel("Blog"));
        headerPanel.add(navLinks, BorderLayout.CENTER);

        // Direita: Busca e Ícones
        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 0));
        rightPanel.setBackground(Color.WHITE);
        rightPanel.add(new JTextField("Busca...", 15));
        rightPanel.add(new JLabel("[Ícone Usuário]"));
        rightPanel.add(new JLabel("[Ícone Favoritos]"));
        rightPanel.add(new JLabel("[Ícone Carrinho]"));
        headerPanel.add(rightPanel, BorderLayout.EAST);
        
        // Adiciona uma linha sutil abaixo do header
        headerPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));

        return headerPanel;
    }

    /**
     * Cria o painel "Hero" (Banner principal da Arara)
     */
    private JPanel createHeroPanel() {
        JPanel heroPanel = new JPanel(new BorderLayout());
        heroPanel.setBackground(new Color(30, 30, 30)); // Fundo escuro
        heroPanel.setPreferredSize(new Dimension(1200, 350));

        // Placeholder para a imagem da Arara
        JLabel heroImage = new JLabel("[Imagem de uma Arara Azul]");
        heroImage.setForeground(Color.WHITE);
        heroImage.setFont(new Font("Arial", Font.ITALIC, 20));
        heroImage.setHorizontalAlignment(SwingConstants.CENTER);
        heroPanel.add(heroImage, BorderLayout.CENTER);

        // Painel de Texto e Botão à esquerda
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setOpaque(false); // Transparente
        textPanel.setBorder(new EmptyBorder(60, 60, 60, 60));

        JLabel title = new JLabel("Nova Linha de Rações Premium");
        title.setFont(new Font("Arial", Font.BOLD, 40));
        title.setForeground(Color.WHITE);
        textPanel.add(title);

        textPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JLabel subtitle = new JLabel("Nutrição completa para a saúde e felicidade da sua ave.");
        subtitle.setFont(new Font("Arial", Font.PLAIN, 18));
        subtitle.setForeground(Color.WHITE);
        textPanel.add(subtitle);

        textPanel.add(Box.createRigidArea(new Dimension(0, 25)));

        JButton ctaButton = new JButton("Confira Agora");
        ctaButton.setBackground(COLOR_BUTTON_GREEN);
        ctaButton.setForeground(Color.WHITE);
        ctaButton.setFont(new Font("Arial", Font.BOLD, 16));
        ctaButton.setBorder(new EmptyBorder(12, 25, 12, 25));
        ctaButton.setFocusPainted(false);
        // Alinha o botão à esquerda
        ctaButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        textPanel.add(ctaButton);

        heroPanel.add(textPanel, BorderLayout.WEST);
        return heroPanel;
    }

    /**
     * Cria a seção "Navegue por Categorias"
     */
    private JPanel createCategoriesPanel() {
        JPanel sectionPanel = new JPanel();
        sectionPanel.setLayout(new BoxLayout(sectionPanel, BoxLayout.Y_AXIS));
        sectionPanel.setBackground(Color.WHITE);
        sectionPanel.setBorder(new EmptyBorder(20, 40, 20, 40)); // Padding

        // Título da Seção
        JLabel title = new JLabel("Navegue por Categorias");
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setAlignmentX(Component.LEFT_ALIGNMENT);
        title.setBorder(new EmptyBorder(0, 0, 15, 0));
        sectionPanel.add(title);

        // Grade de Categorias
        JPanel gridPanel = new JPanel(new GridLayout(1, 5, 20, 20)); // 1 linha, 5 colunas, espaçamento
        gridPanel.setBackground(Color.WHITE);

        gridPanel.add(createCategoryItem("Rações", "As melhores marcas"));
        gridPanel.add(createCategoryItem("Gaiolas", "Conforto e segurança"));
        gridPanel.add(createCategoryItem("Brinquedos", "Diversão garantida"));
        gridPanel.add(createCategoryItem("Saúde", "Vitaminas e cuidados"));
        gridPanel.add(createCategoryItem("Acessórios", "Tudo para o bem-estar"));

        sectionPanel.add(gridPanel);
        return sectionPanel;
    }

    /**
     * Helper para criar um item de categoria (placeholder)
     * NOTA: Imagens circulares exigiriam custom painting (Graphics.fillOval)
     */
    private JPanel createCategoryItem(String name, String description) {
        JPanel itemPanel = new JPanel();
        itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.Y_AXIS));
        itemPanel.setBackground(Color.WHITE);
        itemPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Placeholder para a imagem circular
        JLabel imgPlaceholder = new JLabel("[Imagem " + name + "]");
        imgPlaceholder.setFont(new Font("Arial", Font.ITALIC, 12));
        imgPlaceholder.setPreferredSize(new Dimension(150, 150));
        imgPlaceholder.setMinimumSize(new Dimension(150, 150));
        imgPlaceholder.setMaximumSize(new Dimension(150, 150));
        imgPlaceholder.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        imgPlaceholder.setHorizontalAlignment(SwingConstants.CENTER);
        imgPlaceholder.setAlignmentX(Component.CENTER_ALIGNMENT);
        itemPanel.add(imgPlaceholder);
        
        itemPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JLabel nameLabel = new JLabel(name);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        itemPanel.add(nameLabel);

        JLabel descLabel = new JLabel(description);
        descLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        descLabel.setForeground(Color.GRAY);
        descLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        itemPanel.add(descLabel);

        return itemPanel;
    }

    /**
     * Cria a seção "Produtos em Destaque"
     */
    private JPanel createFeaturedProductsPanel() {
        JPanel sectionPanel = new JPanel();
        sectionPanel.setLayout(new BoxLayout(sectionPanel, BoxLayout.Y_AXIS));
        sectionPanel.setBackground(Color.WHITE);
        sectionPanel.setBorder(new EmptyBorder(20, 40, 20, 40)); // Padding

        // Título da Seção
        JLabel title = new JLabel("Produtos em Destaque");
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setAlignmentX(Component.LEFT_ALIGNMENT);
        title.setBorder(new EmptyBorder(0, 0, 15, 0));
        sectionPanel.add(title);

        // Grade de Produtos
        JPanel gridPanel = new JPanel(new GridLayout(1, 4, 20, 20)); // 1 linha, 4 colunas
        gridPanel.setBackground(Color.WHITE);

        gridPanel.add(createProductCard("Mix de Sementes Premium", "★★★★★", "R$ 46,90"));
        gridPanel.add(createProductCard("Gaiola Espaçosa Ornamental", "★★★★★", "R$ 299,99"));
        gridPanel.add(createProductCard("Brinquedo Interativo de Madeira", "★★★★☆", "R$ 78,50"));
        gridPanel.add(createProductCard("Bebedouro Automático Clean", "★★★★☆", "R$ 36,00"));

        sectionPanel.add(gridPanel);
        return sectionPanel;
    }

    /**
     * Helper para criar um "card" de produto
     */
    private JPanel createProductCard(String name, String rating, String price) {
        JPanel cardPanel = new JPanel(new BorderLayout(0, 10));
        cardPanel.setBackground(Color.WHITE);
        cardPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        // Placeholder para a imagem do produto
        JLabel imgPlaceholder = new JLabel("[Imagem " + name + "]");
        imgPlaceholder.setFont(new Font("Arial", Font.ITALIC, 14));
        imgPlaceholder.setPreferredSize(new Dimension(250, 250));
        imgPlaceholder.setHorizontalAlignment(SwingConstants.CENTER);
        imgPlaceholder.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));
        cardPanel.add(imgPlaceholder, BorderLayout.NORTH);

        // Painel de detalhes (título, preço, botão)
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
        detailsPanel.setBackground(Color.WHITE);
        detailsPanel.setBorder(new EmptyBorder(10, 15, 15, 15)); // Padding

        JLabel nameLabel = new JLabel(name);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        nameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        detailsPanel.add(nameLabel);

        JLabel ratingLabel = new JLabel(rating);
        ratingLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        ratingLabel.setForeground(Color.ORANGE);
        ratingLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        detailsPanel.add(ratingLabel);
        
        detailsPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JLabel priceLabel = new JLabel(price);
        priceLabel.setFont(new Font("Arial", Font.BOLD, 20));
        priceLabel.setForeground(COLOR_PRICE);
        priceLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        detailsPanel.add(priceLabel);

        detailsPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        JButton addButton = new JButton("Adicionar ao Carrinho");
        addButton.setBackground(COLOR_LIGHT_GREEN_BUTTON);
        addButton.setForeground(COLOR_PRICE);
        addButton.setFont(new Font("Arial", Font.BOLD, 14));
        addButton.setFocusPainted(false);
        addButton.setBorder(null); // Tira a borda padrão
        addButton.setPreferredSize(new Dimension(200, 40));
        addButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        detailsPanel.add(addButton);

        cardPanel.add(detailsPanel, BorderLayout.CENTER);
        return cardPanel;
    }

    /**
     * Cria o banner de "Frete Grátis"
     */
    private JPanel createPromoBannerPanel() {
        JPanel bannerPanel = new JPanel(new BorderLayout(20, 0));
        bannerPanel.setBackground(COLOR_LIGHT_GREEN_BG); // Verde bem claro
        bannerPanel.setBorder(new EmptyBorder(40, 50, 40, 50)); // Padding
        bannerPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 200)); // Limita altura

        JLabel title = new JLabel("Frete Grátis em Pedidos Acima de R$199");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(COLOR_DARK_TEXT);
        bannerPanel.add(title, BorderLayout.CENTER);

        JButton viewProductsButton = new JButton("Ver Produtos");
        viewProductsButton.setBackground(COLOR_BUTTON_GREEN);
        viewProductsButton.setForeground(Color.WHITE);
        viewProductsButton.setFont(new Font("Arial", Font.BOLD, 16));
        viewProductsButton.setBorder(new EmptyBorder(12, 25, 12, 25));
        viewProductsButton.setFocusPainted(false);
        bannerPanel.add(viewProductsButton, BorderLayout.EAST);

        return bannerPanel;
    }

    /**
     * Cria o painel do rodapé
     */
    private JPanel createFooterPanel() {
        JPanel footerPanel = new JPanel(new GridLayout(1, 4, 20, 20)); // 1 linha, 4 colunas
        footerPanel.setBackground(COLOR_DARK_TEXT);
        footerPanel.setBorder(new EmptyBorder(40, 40, 40, 40));

        footerPanel.add(createFooterColumn("Bom de Bico", "Tudo o que seu pássaro precisa..."));
        footerPanel.add(createFooterColumn("Institucional", "Sobre nós", "Contato", "Blog"));
        footerPanel.add(createFooterColumn("Ajuda", "FAQ", "Política de Envios"));
        
        // Coluna da Newsletter
        JPanel newsletterPanel = new JPanel();
        newsletterPanel.setLayout(new BoxLayout(newsletterPanel, BoxLayout.Y_AXIS));
        newsletterPanel.setOpaque(false);

        JLabel title = new JLabel("Newsletter");
        title.setFont(new Font("Arial", Font.BOLD, 18));
        title.setForeground(Color.WHITE);
        title.setAlignmentX(Component.LEFT_ALIGNMENT);
        newsletterPanel.add(title);
        
        JLabel desc = new JLabel("Inscreva-se e ganhe 10% OFF...");
        desc.setFont(new Font("Arial", Font.PLAIN, 12));
        desc.setForeground(Color.LIGHT_GRAY);
        desc.setAlignmentX(Component.LEFT_ALIGNMENT);
        newsletterPanel.add(desc);
        
        newsletterPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        
        // Input e Botão
        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.add(new JTextField("Seu e-mail"), BorderLayout.CENTER);
        inputPanel.add(new JButton(">"), BorderLayout.EAST);
        inputPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        inputPanel.setMaximumSize(new Dimension(300, 40));
        newsletterPanel.add(inputPanel);
        
        footerPanel.add(newsletterPanel);

        return footerPanel;
    }

    /**
     * Helper para criar uma coluna de texto simples para o rodapé
     */
    private JPanel createFooterColumn(String title, String... links) {
        JPanel columnPanel = new JPanel();
        columnPanel.setLayout(new BoxLayout(columnPanel, BoxLayout.Y_AXIS));
        columnPanel.setOpaque(false); // Transparente

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        columnPanel.add(titleLabel);

        columnPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        for (String link : links) {
            JLabel linkLabel = new JLabel(link);
            linkLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            linkLabel.setForeground(Color.LIGHT_GRAY);
            linkLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            columnPanel.add(linkLabel);
            columnPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        }

        return columnPanel;
    }

    /**
     * Método Main
     */
    public static void main(String[] args) {
        // Garante que a GUI rode na Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(() -> {
            new BomDeBicoSwing().setVisible(true);
        });
    }
}