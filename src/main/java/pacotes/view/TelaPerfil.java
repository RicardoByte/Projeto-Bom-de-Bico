package pacotes.view; 

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener;

public class TelaPerfil extends JFrame {

    private JTextField txtNome;
    private JTextField txtEmail;
    private JTextField txtTelefone;
    private JPasswordField txtSenhaAtual;
    private JPasswordField txtNovaSenha;
    
   
    private JButton btnSalvar;
    private JButton btnCancelar;

    public TelaPerfil() {
        setTitle("Bom de Bico - Meu Perfil");
        setSize(1024, 768); 
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        ((JPanel) getContentPane()).setBorder(new EmptyBorder(10, 10, 10, 10));
        add(createHeaderPanel(), BorderLayout.NORTH);
        add(createFooterPanel(), BorderLayout.SOUTH);
        add(createProfileFormPanel(), BorderLayout.CENTER);
    }

    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel(new BorderLayout(10, 0));
        
        JButton homeButton = new JButton("Bom de Bico"); 
        homeButton.setFont(new Font("Arial", Font.BOLD, 16)); 
        homeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        homeButton.addActionListener(new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent e) {
                // Link para TelaInicial (troca o painel)
                JFrame frameAtual = (JFrame) SwingUtilities.getWindowAncestor(homeButton);
                frameAtual.getContentPane().removeAll();
                frameAtual.add(new TelaInicial());
                frameAtual.revalidate();
                frameAtual.repaint();
                frameAtual.setTitle("Bom de Bico - Home");
            }
        });
        
        headerPanel.add(homeButton, BorderLayout.WEST); 
       

        JPanel navLinks = new JPanel(new FlowLayout());
        navLinks.add(new JLabel("Categorias"));
        navLinks.add(new JLabel("Promoções"));
        navLinks.add(new JLabel("Blog"));
        headerPanel.add(navLinks, BorderLayout.CENTER);

        JPanel rightPanel = new JPanel(new FlowLayout());
        rightPanel.add(new JTextField("Busca...", 15));
        
        
        JLabel pesquisaLabel = new JLabel("Pesquisa");
        pesquisaLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        pesquisaLabel.setForeground(Color.BLUE);
        pesquisaLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                TelaPesquisa telaPesquisa = new TelaPesquisa();
                telaPesquisa.setVisible(true);
                Window janelaAtual = SwingUtilities.getWindowAncestor(pesquisaLabel);
                janelaAtual.dispose();
            }
        });
        rightPanel.add(pesquisaLabel);
        
        
        rightPanel.add(new JLabel("[Favoritos]")); 
        
        
        JLabel carrinhoLabel = new JLabel("[Carrinho]");
        carrinhoLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        carrinhoLabel.setForeground(Color.BLUE);
        
        carrinhoLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                TelaCarrinho telaCarrinho = new TelaCarrinho();
                telaCarrinho.setVisible(true);
                Window janelaAtual = SwingUtilities.getWindowAncestor(carrinhoLabel);
                if (janelaAtual != null) {
                    janelaAtual.dispose();
                }
            }
        });
        rightPanel.add(carrinhoLabel);
     
        
        headerPanel.add(rightPanel, BorderLayout.EAST);
        
        return headerPanel;
    }

    
    private JPanel createProfileFormPanel() {
        JPanel formPanel = new JPanel(new BorderLayout(10, 10));
        formPanel.setBorder(BorderFactory.createTitledBorder("Meu Perfil"));
        
        JPanel fieldsPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        fieldsPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        txtNome = new JTextField("João da Silva", 20);
        txtEmail = new JTextField("joao.silva@email.com", 20);
        txtEmail.setEditable(false);
        txtEmail.setBackground(Color.LIGHT_GRAY);
        txtTelefone = new JTextField("(11) 98765-4321", 20);
        txtSenhaAtual = new JPasswordField(20);
        txtNovaSenha = new JPasswordField(20);
        
        fieldsPanel.add(new JLabel("Nome Completo:"));
        fieldsPanel.add(txtNome);
        
        fieldsPanel.add(new JLabel("Email (não editável):"));
        fieldsPanel.add(txtEmail);
        
        fieldsPanel.add(new JLabel("Telefone:"));
        fieldsPanel.add(txtTelefone);
        
        fieldsPanel.add(new JSeparator());
        fieldsPanel.add(new JSeparator());

        fieldsPanel.add(new JLabel("Senha Atual:"));
        fieldsPanel.add(txtSenhaAtual);
        
        fieldsPanel.add(new JLabel("Nova Senha:"));
        fieldsPanel.add(txtNovaSenha);
        
        formPanel.add(fieldsPanel, BorderLayout.CENTER);
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnSalvar = new JButton("Salvar Alterações");
        btnCancelar = new JButton("Cancelar");
        
        buttonPanel.add(btnCancelar);
        buttonPanel.add(btnSalvar);
        
        formPanel.add(buttonPanel, BorderLayout.SOUTH);

       
        btnCancelar.addActionListener(e -> {
           
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(btnCancelar);
            frame.getContentPane().removeAll();
            frame.add(new TelaInicial());
            frame.revalidate();
            frame.repaint();
            frame.setTitle("Bom de Bico - Home");
        });
        
        btnSalvar.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, 
                "Dados salvos com sucesso!", 
                "Perfil Atualizado", 
                JOptionPane.INFORMATION_MESSAGE);
        });

        return formPanel;
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

}