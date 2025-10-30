package pacotes.view;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;
import java.util.ArrayList;

import pacotes.models.pagamento.MetodoPagamento;
import pacotes.models.pagamento.PagamentoBoleto;
import pacotes.models.pagamento.PagamentoCartao;
import pacotes.models.pagamento.PagamentoPix;
import pacotes.models.produtos.Produto;


public class PainelCheckout extends JPanel {
    
  
    private JTextField txtEndereco;
    private JTextField txtNome;
    private JTextField txtEmail;
    private JTextField txtTelefone;
    private JComboBox<String> comboPagamentos;
    private JComboBox<String> comboParcelas;
    private JButton btnCancelar;
    private JButton btnSalvar;
    private JLabel jlTotal;

   
    private final Font FONTE_H1 = new Font("Arial", Font.BOLD, 40);
    private final Font FONTE_H3 = new Font("Arial", Font.BOLD, 18);
    private final Font FONTE_CORPO = new Font("Roboto", Font.PLAIN, 12);
    private final Color COR_BACKGROUND = new Color(0xF2F2F2);
    private final Color COR_TEXTO_PRINCIPAL = new Color(0x030303);
    private final Color COR_BOTAO_PRIMARIO = new Color(0xF26E22);
    private final Color COR_PRECO_OFERTA = new Color(0xFF0033);

    
    private double totalBase;
    private double totalComJuros;
    
    
    private CardLayout cardLayoutPai;
    private JPanel painelPai;

    
    public PainelCheckout(ArrayList<Produto> carrinho, double total, CardLayout layout, JPanel painelPai) {
        this.totalBase = total;
        this.totalComJuros = total;
        this.cardLayoutPai = layout;
        this.painelPai = painelPai;
        
        setLayout(new BorderLayout(10, 10));
        setBackground(COR_BACKGROUND);
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Painel principal
        JPanel painel = new JPanel(new BorderLayout(10, 10));
        painel.setBackground(COR_BACKGROUND);

        // --- Título ---
        JPanel painelTitulo = new JPanel();
        painelTitulo.setBackground(COR_BACKGROUND);
        JLabel lblTitulo = new JLabel("Finalizar Compra");
        lblTitulo.setFont(FONTE_H1);
        lblTitulo.setForeground(COR_TEXTO_PRINCIPAL);
        painelTitulo.add(lblTitulo);
        painel.add(painelTitulo, BorderLayout.NORTH);

        // --- Formulário de Informações ---
        JPanel painelInfo = new JPanel(new GridLayout(7, 2, 10, 10));
        painelInfo.setBorder(BorderFactory.createTitledBorder("Dados do Cliente"));
        painelInfo.setBackground(COR_BACKGROUND);

        painelInfo.add(new JLabel("Nome Completo:"));
        txtNome = new JTextField();
        aplicarFiltroTexto(txtNome); // Filtro para aceitar apenas letras
        painelInfo.add(txtNome);

        painelInfo.add(new JLabel("E-mail:"));
        txtEmail = new JTextField();
        painelInfo.add(txtEmail);

        painelInfo.add(new JLabel("Telefone:"));
        txtTelefone = new JTextField();
        aplicarFiltroNumerico(txtTelefone); 
        painelInfo.add(txtTelefone);

        painelInfo.add(new JLabel("Endereço:"));
        txtEndereco = new JTextField();
        painelInfo.add(txtEndereco);

        painelInfo.add(new JLabel("Método de Pagamento:"));
        comboPagamentos = new JComboBox<>(new String[]{"Selecione...", "PIX", "Cartão de Débito", "Cartão de Crédito", "Boleto Bancário"});
        painelInfo.add(comboPagamentos);

        painelInfo.add(new JLabel("Parcelas (só para Crédito):"));
        comboParcelas = new JComboBox<>();
        comboParcelas.setEnabled(false);
        painelInfo.add(comboParcelas);

        painelInfo.add(new JLabel("Total:"));
        jlTotal = new JLabel("R$ " + String.format("%.2f", this.totalBase));
        jlTotal.setFont(FONTE_H3);
        jlTotal.setForeground(COR_PRECO_OFERTA);
        painelInfo.add(jlTotal);

        painel.add(painelInfo, BorderLayout.CENTER);

        
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        painelBotoes.setBackground(COR_BACKGROUND);
        btnSalvar = criarBotaoPrimario("Finalizar Compra");
        btnCancelar = criarBotaoSecundario("Cancelar");
        painelBotoes.add(btnSalvar);
        painelBotoes.add(btnCancelar);
        painel.add(painelBotoes, BorderLayout.SOUTH);

        // --- Listeners ---
        comboPagamentos.addActionListener(e -> atualizarOpcoesPagamento());
        comboParcelas.addActionListener(e -> atualizarTotalComJuros());
        btnSalvar.addActionListener(e -> finalizarCompra(carrinho));
        
        
        btnCancelar.addActionListener(e -> {
            if (cardLayoutPai != null && painelPai != null) {
                
                cardLayoutPai.show(painelPai, "CARRINHO");
            }
        });
        
        add(painel, BorderLayout.CENTER);
    }

    // --- Métodos de UI (Botões e Filtros) ---

    private JButton criarBotaoPrimario(String texto) {
        JButton botao = new JButton(texto);
        botao.setFont(FONTE_CORPO);
        botao.setBackground(COR_BOTAO_PRIMARIO);
        botao.setForeground(Color.WHITE);
        botao.setFocusPainted(false);
        botao.setBorderPainted(false);
        botao.setPreferredSize(new Dimension(150, 40));
        return botao;
    }

    private JButton criarBotaoSecundario(String texto) {
        JButton botao = new JButton(texto);
        botao.setFont(FONTE_CORPO);
        botao.setBackground(Color.WHITE);
        botao.setForeground(COR_BOTAO_PRIMARIO);
        botao.setBorder(BorderFactory.createLineBorder(COR_BOTAO_PRIMARIO));
        botao.setFocusPainted(false);
        botao.setPreferredSize(new Dimension(100, 35));
        return botao;
    }

    private void aplicarFiltroTexto(JTextField campo) {
        ((AbstractDocument) campo.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
                if (string.matches("[a-zA-Z\\s]*")) super.insertString(fb, offset, string, attr);
            }
            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                if (text.matches("[a-zA-Z\\s]*")) super.replace(fb, offset, length, text, attrs);
            }
        });
    }
    
    private void aplicarFiltroNumerico(JTextField campo) {
         ((AbstractDocument) campo.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
                if (string.matches("\\d*")) super.insertString(fb, offset, string, attr);
            }
            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                if (text.matches("\\d*")) super.replace(fb, offset, length, text, attrs);
            }
        });
    }


    private void atualizarOpcoesPagamento() {
        String metodo = (String) comboPagamentos.getSelectedItem();
        comboParcelas.removeAllItems();
        comboParcelas.setEnabled(false);
        
        if ("Cartão de Crédito".equals(metodo)) {
            comboParcelas.setEnabled(true);
            PagamentoCartao cartao = new PagamentoCartao(totalBase, null, null, null, null, false);
            for (String opcao : cartao.getOpcoesParcelamento(totalBase)) {
                comboParcelas.addItem(opcao);
            }
        }
        atualizarTotalComJuros();
    }

    private void atualizarTotalComJuros() {
        String metodo = (String) comboPagamentos.getSelectedItem();
        double novoTotal = this.totalBase; // Começa com o total base
        
        if ("PIX".equals(metodo)) novoTotal *= 1.005f;
        else if ("Cartão de Débito".equals(metodo)) novoTotal *= 1.01f;
        else if ("Boleto Bancário".equals(metodo)) novoTotal *= 1.02f;
        else if ("Cartão de Crédito".equals(metodo) && comboParcelas.getSelectedIndex() >= 0) {
            int parcelas = comboParcelas.getSelectedIndex() + 1;
            float juros = parcelas * 0.01f;
            novoTotal *= (1 + juros);
        }
        
        this.totalComJuros = novoTotal;
        jlTotal.setText("R$ " + String.format("%.2f", this.totalComJuros));
    }
    
    private void finalizarCompra(ArrayList<Produto> carrinho) {
        String nome = txtNome.getText().trim();
        String endereco = txtEndereco.getText().trim();
        String email = txtEmail.getText().trim();
        String telefone = txtTelefone.getText().trim();
        String metodo = (String) comboPagamentos.getSelectedItem();
        
        if (nome.isEmpty() || endereco.isEmpty() || email.isEmpty() || telefone.isEmpty() || "Selecione...".equals(metodo)) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos!");
            return;
        }
        if (carrinho == null || carrinho.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Carrinho vazio!");
            return;
        }

        MetodoPagamento pagamento = null;
        double valorFinal = this.totalComJuros;

        // Dados de pagamento simulados
        String numCartaoSimulado = "1234567890123456";
        String validadeSimulada = "12/25";
        String cvvSimulado = "123";
        String chavePixSimulada = "chave-pix-simulada@banco.com";
        String boletoSimulado = "34191790010104351004791020150008191000000012345"; // 47 dígitos

        if ("PIX".equals(metodo)) {
            pagamento = new PagamentoPix(valorFinal, chavePixSimulada);
        } else if ("Cartão de Débito".equals(metodo)) {
            pagamento = new PagamentoCartao(valorFinal, numCartaoSimulado, nome, validadeSimulada, cvvSimulado, true);
        } else if ("Cartão de Crédito".equals(metodo)) {
            pagamento = new PagamentoCartao(valorFinal, numCartaoSimulado, nome, validadeSimulada, cvvSimulado, false);
        } else if ("Boleto Bancário".equals(metodo)) {
            pagamento = new PagamentoBoleto(valorFinal, boletoSimulado);
        }

        if (pagamento != null && pagamento.processarPagamento()) {
            JOptionPane.showMessageDialog(this, "Compra finalizada com " + pagamento.getMetodo() + "!");
            
            
            TelaCarrinho.limparCarrinhoEstatico();
            
            
            if (cardLayoutPai != null && painelPai != null) {
                cardLayoutPai.show(painelPai, "CARRINHO");
                
                Container topLevelContainer = this.getTopLevelAncestor();
                if (topLevelContainer instanceof JFrame) {
                    ((JFrame) topLevelContainer).dispose();
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Erro no processamento do pagamento!");
        }
    }

    
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Teste do Painel de Checkout");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 700);
            
            // Para testar, passamos null para os controles de navegação
            PainelCheckout painel = new PainelCheckout(new ArrayList<>(), 100.00, null, null);
            
            frame.add(painel);
            frame.setVisible(true);
        });
    }   
}