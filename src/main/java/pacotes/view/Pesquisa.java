package pacotes.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import pacotes.view.Home;

public class Pesquisa extends JFrame {

    public Pesquisa() {
        setTitle("Pesquisa de Produtos - Bom de Bico");
        setSize(1100, 700); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        setLayout(new BorderLayout(10, 10)); 
        add(createHeaderPanel(), BorderLayout.NORTH);
        add(createFilterPanel(), BorderLayout.WEST);
        add(createMainContentPanel(), BorderLayout.CENTER);
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(10, 10, 10, 10));
    }

    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel(new BorderLayout(10, 0));
        
        JButton homeButton = new JButton("Bom de Bico");
        homeButton.addActionListener(e -> {
            this.getContentPane().removeAll();
            this.add(new Home());
            this.revalidate();
            this.repaint();  
        });

        headerPanel.add(homeButton, BorderLayout.WEST);
        headerPanel.add(new JTextField("Buscar por produto ou marca..."), BorderLayout.CENTER);

        JPanel iconPanel = new JPanel(new FlowLayout());
        iconPanel.add(new JLabel("Usuário"));
        iconPanel.add(new JLabel("Favoritos"));
        iconPanel.add(new JLabel("Carrinho"));
        
        headerPanel.add(iconPanel, BorderLayout.EAST);
        return headerPanel;
    }

    private JPanel createFilterPanel() {
        JPanel filterPanel = new JPanel();
        filterPanel.setLayout(new BoxLayout(filterPanel, BoxLayout.Y_AXIS));
        filterPanel.setBorder(BorderFactory.createTitledBorder("Filtros")); 
        
        JPanel categoriesPanel = new JPanel();
        categoriesPanel.setLayout(new BoxLayout(categoriesPanel, BoxLayout.Y_AXIS));
        categoriesPanel.setBorder(BorderFactory.createTitledBorder("Categorias"));
        
        categoriesPanel.add(new JCheckBox("Rações"));
        categoriesPanel.add(new JCheckBox("Gaiolas"));
        categoriesPanel.add(new JCheckBox("Brinquedos"));
        categoriesPanel.add(new JCheckBox("Acessórios"));
        filterPanel.add(categoriesPanel);

        JPanel pricePanel = new JPanel(new BorderLayout(5, 5));
        pricePanel.setBorder(BorderFactory.createTitledBorder("Faixa de Preço"));
        
        JSlider priceSlider = new JSlider(50, 500); 
        pricePanel.add(new JLabel("R$ 50"), BorderLayout.WEST);
        pricePanel.add(priceSlider, BorderLayout.CENTER);
        pricePanel.add(new JLabel("R$ 500"), BorderLayout.EAST);
        filterPanel.add(pricePanel);

        JPanel brandPanel = new JPanel(new BorderLayout());
        brandPanel.setBorder(BorderFactory.createTitledBorder("Marcas"));
        brandPanel.add(new JTextField("Buscar por marca"), BorderLayout.CENTER);
        filterPanel.add(brandPanel);

        JPanel birdTypePanel = new JPanel();
        birdTypePanel.setLayout(new BoxLayout(birdTypePanel, BoxLayout.Y_AXIS));
        birdTypePanel.setBorder(BorderFactory.createTitledBorder("Tipo de Pássaro"));
        
        birdTypePanel.add(new JCheckBox("Canário"));
        birdTypePanel.add(new JCheckBox("Calopsita"));
        birdTypePanel.add(new JCheckBox("Periquito"));
        filterPanel.add(birdTypePanel);

        filterPanel.add(Box.createVerticalGlue()); 
        filterPanel.add(new JButton("Aplicar Filtros"));
        filterPanel.add(Box.createRigidArea(new Dimension(0, 5))); 
        filterPanel.add(new JButton("Limpar Tudo"));

        return filterPanel;
    }

    private JPanel createMainContentPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));

        JPanel contentHeader = new JPanel(new BorderLayout());
        contentHeader.add(new JLabel("Todos os Produtos"), BorderLayout.WEST);
        
        String[] sortOptions = {"Mais Relevantes", "Menor Preço", "Maior Preço"};
        contentHeader.add(new JComboBox<>(sortOptions), BorderLayout.EAST);
        
        mainPanel.add(contentHeader, BorderLayout.NORTH);

        JPanel productGrid = new JPanel(new GridLayout(2, 4, 10, 10)); 
        
        productGrid.add(createSimpleProductCard("Ração Premium", "R$ 45,50"));
        productGrid.add(createSimpleProductCard("Gaiola Ornamental", "R$ 189,90"));
        productGrid.add(createSimpleProductCard("Kit Brinquedos", "R$ 29,99"));
        productGrid.add(createSimpleProductCard("Bebedouro Auto.", "R$ 12,00"));
        productGrid.add(createSimpleProductCard("Mix de Sementes", "R$ 22,75"));
        productGrid.add(createSimpleProductCard("Gaiola Transporte", "R$ 75,00"));
        productGrid.add(createSimpleProductCard("Espelho com Sino", "R$ 15,90"));
        productGrid.add(createSimpleProductCard("Ninho Fibra", "R$ 18,50"));
        
        mainPanel.add(productGrid, BorderLayout.CENTER);

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

    private JPanel createSimpleProductCard(String name, String price) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createLineBorder(Color.GRAY)); 

        JLabel imgPlaceholder = new JLabel("[Imagem de " + name + "]");
        imgPlaceholder.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(imgPlaceholder);
        
        JLabel nameLabel = new JLabel(name);
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(nameLabel);
        
        JLabel priceLabel = new JLabel(price);
        priceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(priceLabel);
        
        JButton addButton = new JButton("Adicionar ao Carrinho");
        addButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(addButton);
        
        return card;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Pesquisa().setVisible(true);
        });
    }
}