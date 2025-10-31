package pacotes.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.MouseEvent;
import java.awt.*;
import java.awt.event.MouseAdapter;


public class TelaInicial extends JPanel {

    public TelaInicial() {        
        setLayout(new BorderLayout(10, 10));
        setBorder(new EmptyBorder(10, 10, 10, 10)); 
        add(createHeaderPanel(), BorderLayout.NORTH);
        add(createFooterPanel(), BorderLayout.SOUTH);
        JPanel mainContentPanel = new JPanel();
        mainContentPanel.setLayout(new BoxLayout(mainContentPanel, BoxLayout.Y_AXIS));

        mainContentPanel.add(createHeroPanel());
        mainContentPanel.add(createCategoriesPanel());
        mainContentPanel.add(createFeaturedProductsPanel());
        mainContentPanel.add(createPromoBannerPanel());

        JScrollPane scrollPane = new JScrollPane(mainContentPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        
        add(scrollPane, BorderLayout.CENTER);
    }

    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel(new BorderLayout(10, 0));
        headerPanel.add(new JLabel("Bom de Bico"), BorderLayout.WEST);

        JPanel navLinks = new JPanel(new FlowLayout());
        navLinks.add(new JLabel("Categorias"));
        navLinks.add(new JLabel("Promoções"));
        navLinks.add(new JLabel("Blog"));
        headerPanel.add(navLinks, BorderLayout.CENTER);

        JPanel rightPanel = new JPanel(new FlowLayout());
        rightPanel.add(new JTextField("Busca...", 15));
        
    
        JLabel userLabel = new JLabel("[Usuário]");
        userLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        userLabel.setForeground(Color.BLUE); 
        userLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Link para TelaPerfil
                TelaPerfil telaPerfil = new TelaPerfil();
                telaPerfil.setVisible(true);
                Window janelaAtual = SwingUtilities.getWindowAncestor(userLabel);
                if (janelaAtual != null) {
                    janelaAtual.dispose();
                }
            }
        });
        rightPanel.add(userLabel);
        
        rightPanel.add(new JLabel("[Favoritos]"));

    
        JLabel cartLabel = new JLabel("[Carrinho]");
        cartLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cartLabel.setForeground(Color.BLUE); 
        
        cartLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Link para TelaCarrinho
                TelaCarrinho telaCarrinho = new TelaCarrinho();
                telaCarrinho.setVisible(true);
                
                Window janelaAtual = SwingUtilities.getWindowAncestor(cartLabel);
                if (janelaAtual != null) {
                    janelaAtual.dispose();
                }
            }
        });
        rightPanel.add(cartLabel);
    
        
        headerPanel.add(rightPanel, BorderLayout.EAST);
        
        return headerPanel;
    }
    
    private JPanel createHeroPanel() {
        JPanel heroPanel = new JPanel(new BorderLayout());
        heroPanel.setBorder(BorderFactory.createTitledBorder("")); // Apenas uma borda

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        
        textPanel.add(new JLabel("Nova Linha de Rações Premium"));
        textPanel.add(new JLabel("Nutrição completa para a saúde e felicidade da sua ave."));
        
        heroPanel.add(textPanel, BorderLayout.WEST);
        heroPanel.add(new JLabel("[Imagem de Arara]"), BorderLayout.CENTER);
        return heroPanel;
    }

    private JPanel createCategoriesPanel() {
        JPanel sectionPanel = new JPanel(new BorderLayout());
        sectionPanel.setBorder(BorderFactory.createTitledBorder("Navegue por Categorias"));

        JPanel gridPanel = new JPanel(new GridLayout(1, 5, 10, 10)); 
        gridPanel.add(createCategoryItem("Rações"));
        gridPanel.add(createCategoryItem("Gaiolas"));
        gridPanel.add(createCategoryItem("Brinquedos"));
        gridPanel.add(createCategoryItem("Saúde"));
        gridPanel.add(createCategoryItem("Acessórios"));

        sectionPanel.add(gridPanel, BorderLayout.CENTER);
        return sectionPanel;
    }

    private JPanel createCategoryItem(String name) {
        JPanel itemPanel = new JPanel();
        itemPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.Y_AXIS));
        itemPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        itemPanel.setBackground(Color.WHITE);
        
        itemPanel.setPreferredSize(new Dimension(150, 100));
        itemPanel.setMaximumSize(new Dimension(200, 120));
        
        JLabel imgLabel = new JLabel("[Img " + name + "]");
        JLabel nameLabel = new JLabel(name);
        
        imgLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        itemPanel.add(Box.createVerticalGlue());
        itemPanel.add(imgLabel);
        itemPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        itemPanel.add(nameLabel);
        itemPanel.add(Box.createVerticalGlue());
        
        itemPanel.setOpaque(true);
        itemPanel.setFocusable(true);
        
        MouseAdapter mouseAdapter = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Link para TelaPesquisa
                TelaPesquisa framePesquisa = new TelaPesquisa(); 
                framePesquisa.setVisible(true);
                Window janelaAtual = SwingUtilities.getWindowAncestor(itemPanel);
                if (janelaAtual != null) {
                    janelaAtual.dispose();
                }
            }
            @Override
            public void mouseEntered(MouseEvent e) { itemPanel.setBackground(Color.LIGHT_GRAY); }
            @Override
            public void mouseExited(MouseEvent e) { itemPanel.setBackground(Color.WHITE); }
        };
        
        itemPanel.addMouseListener(mouseAdapter);
        imgLabel.addMouseListener(mouseAdapter);
        nameLabel.addMouseListener(mouseAdapter);
        
        return itemPanel;
    }

    private JPanel createFeaturedProductsPanel() {
        JPanel sectionPanel = new JPanel(new BorderLayout());
        sectionPanel.setBorder(BorderFactory.createTitledBorder("Produtos em Destaque"));

        JPanel gridPanel = new JPanel(new GridLayout(1, 4, 10, 10));
        gridPanel.add(createSimpleProductCard("Mix de Sementes", "R$ 46,90"));
        gridPanel.add(createSimpleProductCard("Gaiola Ornamental", "R$ 299,99"));
        gridPanel.add(createSimpleProductCard("Brinquedo Madeira", "R$ 78,50"));
        gridPanel.add(createSimpleProductCard("Bebedouro Clean", "R$ 36,00"));

        sectionPanel.add(gridPanel, BorderLayout.CENTER);
        return sectionPanel;
    }

    private JPanel createPromoBannerPanel() {
        JPanel bannerPanel = new JPanel(new BorderLayout(10, 0));

        bannerPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        bannerPanel.add(new JLabel("Frete Grátis em Pedidos Acima de R$199"), BorderLayout.CENTER);
        return bannerPanel;
    }

    private JPanel createFooterPanel() {
        JPanel footerPanel = new JPanel(new GridLayout(1, 4, 10, 10));
        footerPanel.setBorder(BorderFactory.createTitledBorder("Rodapé"));
        footerPanel.add(new JLabel("Bom de Bico"));
        footerPanel.add(new JLabel("Institucional"));
        footerPanel.add(new JLabel("Ajuda"));
        footerPanel.add(new JLabel("Newsletter"));
        return footerPanel;
    }
    
    private JPanel createSimpleProductCard(String name, String price) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        card.add(new JLabel("[Imagem de " + name + "]"));
        card.add(new JLabel(name));
        card.add(new JLabel(price));

        JButton adicionarCarrinho = new JButton("Adicionar ao Carrinho");
        adicionarCarrinho.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Link para TelaCarrinho
                TelaCarrinho telaCarrinho = new TelaCarrinho();
                telaCarrinho.setVisible(true);
                
                Window janelaAtual = SwingUtilities.getWindowAncestor(adicionarCarrinho);
                if (janelaAtual != null) {
                    janelaAtual.dispose();
                }
            }
        });

        card.add(adicionarCarrinho);
        return card;
    }

}