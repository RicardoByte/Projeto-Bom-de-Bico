package pacotes.view;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;
import java.util.ArrayList;


// Imports corrigidos e adicionados
import pacotes.models.pagamento.MetodoPagamento;
import pacotes.models.pagamento.PagamentoBoleto;
import pacotes.models.pagamento.PagamentoCartao;
import pacotes.models.pagamento.PagamentoPix;
import pacotes.models.produtos.Produto;

public class BomDeBicoCheckout extends JPanel {
    private JTextField txtEndereco;
    private JTextField txtNome;
    private JTextField txtEmail;
    private JTextField txtTelefone;
    private JComboBox<String> comboPagamentos;
    private JComboBox<String> comboParcelas;
    private JButton btnCancelar;
    private JButton btnSalvar;
    public JPanel jpanelTitulo;
    private JLabel jlTotal;

    // Fontes e Cores (sem alteração)
    private final Font FONTE_H1 = new Font("Arial", Font.BOLD, 40);
    private final Font FONTE_H3 = new Font("Arial", Font.BOLD, 18);
    private final Font FONTE_CORPO = new Font("Roboto", Font.PLAIN, 12);
    private final Color COR_BACKGROUND = new Color(0xF2F2F2);
    private final Color COR_TEXTO_PRINCIPAL = new Color(0x030303);
    private final Color COR_BOTAO_PRIMARIO = new Color(0xF26E22);
    private final Color COR_PRECO_OFERTA = new Color(0xFF0033);

    // Campos adicionados para gerenciar o total
    private double totalBase;
    private double totalComJuros;

    public BomDeBicoCheckout(ArrayList<Produto> carrinho, double total) {
        setLayout(new BorderLayout(10, 10));
        setBackground(COR_BACKGROUND);

        // Armazena o total base e inicializa o total com juros
        this.totalBase = total;
        this.totalComJuros = total;

        jpanelTitulo = new JPanel();
        jpanelTitulo.setBackground(COR_BACKGROUND);

        JPanel painel = new JPanel(new BorderLayout(10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        painel.setBackground(COR_BACKGROUND);

        JPanel painelTitulo = new JPanel();
        painelTitulo.setBackground(COR_BACKGROUND);
        JLabel lblTitulo = new JLabel("Finalizar Compra");
        lblTitulo.setFont(FONTE_H1);
        lblTitulo.setForeground(COR_TEXTO_PRINCIPAL);
        painelTitulo.add(lblTitulo);
        painel.add(painelTitulo, BorderLayout.NORTH);

        JPanel painelInfo = new JPanel(new GridLayout(7, 2, 10, 10));
        painelInfo.setBorder(BorderFactory.createTitledBorder("Dados do Cliente"));
        painelInfo.setBackground(COR_BACKGROUND);

        // --- Campos de Texto (Nome, Email, Telefone, Endereço) ---
        JLabel lblNome = new JLabel("Nome Completo:");
        lblNome.setFont(FONTE_CORPO);
        lblNome.setForeground(COR_TEXTO_PRINCIPAL);
        painelInfo.add(lblNome);
        txtNome = new JTextField();
        txtNome.setFont(FONTE_CORPO);
        ((AbstractDocument) txtNome.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
                if (string.matches("[a-zA-Z\\s]*")) {
                    super.insertString(fb, offset, string, attr);
                }
            }
            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                if (text.matches("[a-zA-Z\\s]*")) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        });
        painelInfo.add(txtNome);

        JLabel lblEmail = new JLabel("E-mail:");
        lblEmail.setFont(FONTE_CORPO);
        lblEmail.setForeground(COR_TEXTO_PRINCIPAL);
        painelInfo.add(lblEmail);
        txtEmail = new JTextField();
        txtEmail.setFont(FONTE_CORPO);
        painelInfo.add(txtEmail);

        JLabel lblTelefone = new JLabel("Telefone:");
        lblTelefone.setFont(FONTE_CORPO);
        lblTelefone.setForeground(COR_TEXTO_PRINCIPAL);
        painelInfo.add(lblTelefone);
        txtTelefone = new JTextField();
        txtTelefone.setFont(FONTE_CORPO);
        ((AbstractDocument) txtTelefone.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
                if (string.matches("\\d*")) {
                    super.insertString(fb, offset, string, attr);
                }
            }
            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                if (text.matches("\\d*")) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        });
        painelInfo.add(txtTelefone);

        JLabel lblEndereco = new JLabel("Endereço:");
        lblEndereco.setFont(FONTE_CORPO);
        lblEndereco.setForeground(COR_TEXTO_PRINCIPAL);
        painelInfo.add(lblEndereco);
        txtEndereco = new JTextField();
        txtEndereco.setFont(FONTE_CORPO);
        painelInfo.add(txtEndereco);
        // --- Fim dos Campos de Texto ---

        JLabel lblPagamento = new JLabel("Método de Pagamento:");
        lblPagamento.setFont(FONTE_CORPO);
        lblPagamento.setForeground(COR_TEXTO_PRINCIPAL);
        painelInfo.add(lblPagamento);
        
        comboPagamentos = new JComboBox<>(new String[]{"Selecione...", "PIX", "Cartão de Débito", "Cartão de Crédito", "Boleto Bancário"});
        comboPagamentos.setFont(FONTE_CORPO);
        painelInfo.add(comboPagamentos);

        JLabel lblParcelas = new JLabel("Parcelas (só para Crédito):");
        lblParcelas.setFont(FONTE_CORPO);
        lblParcelas.setForeground(COR_TEXTO_PRINCIPAL);
        painelInfo.add(lblParcelas);
        comboParcelas = new JComboBox<>();
        comboParcelas.setEnabled(false);
        comboParcelas.setFont(FONTE_CORPO);
        painelInfo.add(comboParcelas);

        JLabel lblTotalLabel = new JLabel("Total:");
        lblTotalLabel.setFont(FONTE_CORPO);
        lblTotalLabel.setForeground(COR_TEXTO_PRINCIPAL);
        painelInfo.add(lblTotalLabel);
        
        // Usa a variável de instância
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

        // Listeners atualizados para não passar parâmetros
        comboPagamentos.addActionListener(e -> atualizarOpcoes());
        comboParcelas.addActionListener(e -> atualizarTotal());
        btnSalvar.addActionListener(e -> finalizarCompra(carrinho));
        
        btnCancelar.addActionListener(e -> {
            Container parent = getParent();
            if (parent instanceof JPanel) {
                CardLayout layout = (CardLayout) parent.getLayout();
                layout.show(parent, "carrinho");
            }
        });
        add(painel);
    }

    // --- Métodos criarBotaoPrimario e criarBotaoSecundario (sem alteração) ---
    private JButton criarBotaoPrimario(String texto) {
        JButton botao = new JButton(texto);
        botao.setFont(FONTE_CORPO);
        botao.setBackground(COR_BOTAO_PRIMARIO);
        botao.setForeground(Color.WHITE);
        botao.setFocusPainted(false);
        botao.setBorderPainted(false);
        botao.setPreferredSize(new Dimension(150, 40));
        botao.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
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
    // --- Fim dos Métodos de Botão ---


    private void atualizarOpcoes() {
        String metodo = (String) comboPagamentos.getSelectedItem();
        comboParcelas.removeAllItems();
        comboParcelas.setEnabled(false);
        
        if ("Cartão de Crédito".equals(metodo)) {
            comboParcelas.setEnabled(true);
            // Cria um PagamentoCartao temporário (false para crédito)
            PagamentoCartao cartao = new PagamentoCartao(totalBase, null, null, null, null, false);
            
            // Chama o novo método getOpcoesParcelamento
            for (String opcao : cartao.getOpcoesParcelamento(totalBase)) {
                comboParcelas.addItem(opcao);
            }
        }
        // Atualiza o total (com juros) ao mudar a opção de pagamento
        atualizarTotal();
    }


    private void atualizarTotal() {
        String metodo = (String) comboPagamentos.getSelectedItem();
        double novoTotal = this.totalBase; // Começa com o total base
        
        // A lógica de juros foi mantida, mas agora usa 'totalBase'
        if ("PIX".equals(metodo)) {
            novoTotal *= 1.005f;
        } else if ("Cartão de Débito".equals(metodo)) {
            novoTotal *= 1.01f;
        } else if ("Boleto Bancário".equals(metodo)) {
            novoTotal *= 1.02f;
        } else if ("Cartão de Crédito".equals(metodo) && comboParcelas.getSelectedIndex() >= 0) {
            int parcelas = comboParcelas.getSelectedIndex() + 1;
            float juros = parcelas * 0.01f;
            novoTotal *= (1 + juros);
        }
        
        // Armazena o total final e atualiza o label
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
        double valorFinal = this.totalComJuros; // Usa o total com juros calculado

        // Dados de pagamento simulados (em um app real, viriam de campos de texto)
        String numCartaoSimulado = "1234567890123456";
        String validadeSimulada = "12/25";
        String cvvSimulado = "123";
        String chavePixSimulada = "chave-pix-simulada@banco.com";
        // O código de boleto precisa ter 47 dígitos para passar na validação simulada
        String boletoSimulado = "34191790010104351004791020150008191000000012345";

        
        // Instancia a classe de pagamento correta
        if ("PIX".equals(metodo)) {
            pagamento = new PagamentoPix(valorFinal, chavePixSimulada);
            
        } else if ("Cartão de Débito".equals(metodo)) {
            // 'true' para débito
            pagamento = new PagamentoCartao(valorFinal, numCartaoSimulado, nome, validadeSimulada, cvvSimulado, true);
            
        } else if ("Cartão de Crédito".equals(metodo)) {
            // 'false' para crédito
            pagamento = new PagamentoCartao(valorFinal, numCartaoSimulado, nome, validadeSimulada, cvvSimulado, false);
            
        } else if ("Boleto Bancário".equals(metodo)) {
            pagamento = new PagamentoBoleto(valorFinal, boletoSimulado);
        }

        // Processa o pagamento usando o método da interface
        if (pagamento != null && pagamento.processarPagamento()) {
            // Usa pagamento.getMetodo() para uma mensagem dinâmica
            JOptionPane.showMessageDialog(this, "Compra finalizada com " + pagamento.getMetodo() + "!");
            
            Container parent = getParent();
            if (parent instanceof JPanel) {
                CardLayout layout = (CardLayout) parent.getLayout();
                layout.show(parent, "carrinho");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Erro no processamento do pagamento!");
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Tela de Checkout");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 700);
        frame.add(new BomDeBicoCheckout(new ArrayList<>(), 100.00));
        frame.setVisible(true);
    }   
}