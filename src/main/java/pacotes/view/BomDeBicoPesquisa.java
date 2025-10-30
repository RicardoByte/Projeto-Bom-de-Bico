package pacotes.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * Recriação estrutural da página "Pesquisa de Produtos - Bom de Bico"
 * usando Java Swing.
 * Foca no layout e na organização dos componentes (Filtros, Grade de Produtos).
 */
public class BomDeBicoPesquisa extends JFrame {

    public BomDeBicoPesquisa() {
        setTitle("Pesquisa de Produtos - Bom de Bico");
        setSize(1280, 800); // Um tamanho maior para acomodar os filtros e a grade
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // O layout principal é um BorderLayout.
        // WEST: Painel de Filtros
        // CENTER: Painel de Conteúdo (Produtos)
        // NORTH: Cabeçalho de busca
        setLayout(new BorderLayout(10, 10)); // 10px de gap

        // 1. Cabeçalho (NORTH)
        add(createHeaderPanel(), BorderLayout.NORTH);

        // 2. Painel de Filtros (WEST)
        add(createFilterPanel(), BorderLayout.WEST);

        // 3. Conteúdo Principal (CENTER)
        add(createMainContentPanel(), BorderLayout.CENTER);
    }

    /**
     * Cria o painel do cabeçalho (Logo, Busca, Ícones de Usuário)
     */
    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel(new BorderLayout(20, 0));
        headerPanel.setBorder(new EmptyBorder(10, 15, 10, 15)); // Padding

        // Esquerda: Logo
        JLabel logo = new JLabel("Bom de Bico");
        logo.setFont(new Font("Arial", Font.BOLD, 20));
        headerPanel.add(logo, BorderLayout.WEST);

        // Centro: Campo de Busca
        JTextField searchField = new JTextField("Buscar por produto ou marca...");
        headerPanel.add(searchField, BorderLayout.CENTER);

        // Direita: Links de Usuário
        JPanel userPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 0));
        userPanel.add(new JLabel("Usuário"));
        userPanel.add(new JLabel("Favoritos"));
        userPanel.add(new JLabel("Carrinho"));
        headerPanel.add(userPanel, BorderLayout.EAST);

        return headerPanel;
    }

    /**
     * Cria o painel de filtros da lateral esquerda.
     * Este painel usa um BoxLayout vertical para empilhar as seções.
     */
    private JPanel createFilterPanel() {
        JPanel filterPanel = new JPanel();
        filterPanel.setLayout(new BoxLayout(filterPanel, BoxLayout.Y_AXIS));
        filterPanel.setBorder(new EmptyBorder(10, 10, 10, 10)); // Padding
        filterPanel.setPreferredSize(new Dimension(250, 0)); // Largura preferida

        JLabel title = new JLabel("Filtros");
        title.setFont(new Font("Arial", Font.BOLD, 18));
        title.setAlignmentX(Component.LEFT_ALIGNMENT);
        filterPanel.add(title);

        filterPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        // --- Seção Categorias ---
        JPanel categoriesPanel = new JPanel();
        categoriesPanel.setLayout(new BoxLayout(categoriesPanel, BoxLayout.Y_AXIS));
        categoriesPanel.setBorder(new TitledBorder("Categorias"));
        categoriesPanel.add(new JCheckBox("Rações"));
        categoriesPanel.add(new JCheckBox("Gaiolas"));
        categoriesPanel.add(new JCheckBox("Brinquedos"));
        categoriesPanel.add(new JCheckBox("Acessórios"));
        categoriesPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        filterPanel.add(categoriesPanel);

        filterPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        // --- Seção Faixa de Preço ---
        JPanel pricePanel = new JPanel(new BorderLayout(5, 0));
        pricePanel.setBorder(new TitledBorder("Faixa de Preço"));
        pricePanel.add(new JLabel("R$ 50"), BorderLayout.WEST);
        pricePanel.add(new JSlider(50, 500), BorderLayout.CENTER);
        pricePanel.add(new JLabel("R$ 500"), BorderLayout.EAST);
        pricePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        // Limita a altura máxima para o slider não esticar
        pricePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, pricePanel.getPreferredSize().height));
        filterPanel.add(pricePanel);

        filterPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        // --- Seção Marcas ---
        JPanel brandPanel = new JPanel(new BorderLayout(0, 5));
        brandPanel.setBorder(new TitledBorder("Marcas"));
        brandPanel.add(new JTextField("Buscar por marca"), BorderLayout.NORTH);
        // Lista de Marcas (placeholder)
        String[] dummyBrands = { "Marca A", "Marca B", "Marca C", "Marca D", "Marca E" };
        JList<String> brandList = new JList<>(dummyBrands);
        JScrollPane brandScrollPane = new JScrollPane(brandList);
        brandScrollPane.setPreferredSize(new Dimension(0, 100)); // Altura fixa
        brandPanel.add(brandScrollPane, BorderLayout.CENTER);
        brandPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        filterPanel.add(brandPanel);

        filterPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        // --- Seção Tipo de Pássaro ---
        JPanel birdTypePanel = new JPanel();
        birdTypePanel.setLayout(new BoxLayout(birdTypePanel, BoxLayout.Y_AXIS));
        birdTypePanel.setBorder(new TitledBorder("Tipo de Pássaro"));
        birdTypePanel.add(new JCheckBox("Canário"));
        birdTypePanel.add(new JCheckBox("Calopsita"));
        birdTypePanel.add(new JCheckBox("Periquito"));
        birdTypePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        filterPanel.add(birdTypePanel);

        // Adiciona um "espaçador" flexível para empurrar os botões para baixo
        filterPanel.add(Box.createVerticalGlue());

        // --- Botões de Ação ---
        JButton applyButton = new JButton("Aplicar Filtros");
        applyButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        filterPanel.add(applyButton);

        filterPanel.add(Box.createRigidArea(new Dimension(0, 5)));

        JButton clearButton = new JButton("Limpar Tudo");
        clearButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        filterPanel.add(clearButton);

        return filterPanel;
    }

    /**
     * Cria o painel de conteúdo principal (Cabeçalho, Grade e Paginação).
     * Este painel usa BorderLayout.
     */
    private JPanel createMainContentPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout(0, 10)); // 10px de gap vertical
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10)); // Padding

        // 1. Cabeçalho do Conteúdo (Título e Ordenação)
        JPanel contentHeader = new JPanel(new BorderLayout());
        contentHeader.add(new JLabel("Todos os Produtos"), BorderLayout.WEST);
        String[] sortOptions = { "Mais Relevantes", "Menor Preço", "Maior Preço" };
        contentHeader.add(new JComboBox<>(sortOptions), BorderLayout.EAST);
        mainPanel.add(contentHeader, BorderLayout.NORTH);

        // 2. Grade de Produtos (CENTER)
        // Usamos GridLayout para criar a grade 4x2
        JPanel gridPanel = new JPanel(new GridLayout(2, 4, 10, 10)); // 2 linhas, 4 colunas, 10px de gap
        
        // Adiciona 8 produtos placeholder
        gridPanel.add(createProductCard("Ração Premium", "R$ 45,50"));
        gridPanel.add(createProductCard("Gaiola Ornamental", "R$ 189,90"));
        gridPanel.add(createProductCard("Kit Brinquedos", "R$ 29,99"));
        gridPanel.add(createProductCard("Bebedouro Auto.", "R$ 12,00"));
        gridPanel.add(createProductCard("Mix de Sementes", "R$ 22,75"));
        gridPanel.add(createProductCard("Gaiola Transporte", "R$ 75,00"));
        gridPanel.add(createProductCard("Espelho com Sino", "R$ 15,90"));
        gridPanel.add(createProductCard("Ninho Fibra", "R$ 18,50"));

        mainPanel.add(gridPanel, BorderLayout.CENTER);

        // 3. Paginação (SOUTH)
        JPanel paginationPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        paginationPanel.add(new JButton("<"));
        paginationPanel.add(new JButton("1"));
        paginationPanel.add(new JButton("2"));
        paginationPanel.add(new JButton("3"));
        paginationPanel.add(new JLabel("..."));
        paginationPanel.add(new JButton("10"));
        paginationPanel.add(new JButton(">"));
        mainPanel.add(paginationPanel, BorderLayout.SOUTH);

        return mainPanel;
    }

    /**
     * Helper para criar um "card" de produto individual.
     * Usa BoxLayout vertical para empilhar os elementos.
     */
    private JPanel createProductCard(String name, String price) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        card.setBackground(Color.WHITE);

        // 1. Imagem (Placeholder)
        JLabel imgPlaceholder = new JLabel("[Imagem de " + name + "]");
        imgPlaceholder.setFont(new Font("Arial", Font.ITALIC, 12));
        imgPlaceholder.setAlignmentX(Component.CENTER_ALIGNMENT);
        imgPlaceholder.setBorder(new EmptyBorder(40, 20, 40, 20)); // Padding para simular tamanho
        card.add(imgPlaceholder);

        // 2. Nome
        JLabel nameLabel = new JLabel(name);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(nameLabel);

        // 3. Preço
        JLabel priceLabel = new JLabel(price);
        priceLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        priceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(priceLabel);

        card.add(Box.createRigidArea(new Dimension(0, 10)));

        // 4. Botão
        JButton addButton = new JButton("Adicionar ao Carrinho");
        addButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(addButton);

        card.add(Box.createRigidArea(new Dimension(0, 10)));

        return card;
    }

    /**
     * Método Main
     */
    public static void main(String[] args) {
        // Garante que a GUI rode na Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(() -> {
            new BomDeBicoPesquisa().setVisible(true);
        });
    }
}