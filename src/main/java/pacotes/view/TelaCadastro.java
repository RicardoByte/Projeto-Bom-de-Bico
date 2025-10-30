package pacotes.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Tela de Cadastro de novo usuário.
 */
public class TelaCadastro extends JFrame {

    // Componentes do formulário
    private JTextField txtNome;
    private JTextField txtEmail;
    private JPasswordField txtSenha;
    private JPasswordField txtConfirmarSenha;
    
    private JButton btnCadastrar;
    private JButton btnCancelar;

    public TelaCadastro() {
        inicializarJanela();
        inicializarComponentes();
        montarInterface();
        configurarEventos();
    }

    private void inicializarJanela() {
        setTitle("Bom de Bico - Novo Cadastro");
        // DISPOSE_ON_CLOSE: Fecha apenas esta janela
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
    }

    private void inicializarComponentes() {
        txtNome = new JTextField(25);
        txtEmail = new JTextField(25);
        txtSenha = new JPasswordField(25);
        txtConfirmarSenha = new JPasswordField(25);
        btnCadastrar = new JButton("Finalizar Cadastro");
        btnCancelar = new JButton("Cancelar");
    }

    private void montarInterface() {
        add(criarPainelTitulo(), BorderLayout.NORTH);
        add(criarPainelFormulario(), BorderLayout.CENTER);
        add(criarPainelBotoes(), BorderLayout.SOUTH);
    }

    private JPanel criarPainelTitulo() {
        JPanel painel = new JPanel();
        JLabel lblTitulo = new JLabel("Criar Nova Conta");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        painel.add(lblTitulo);
        return painel;
    }

    private JPanel criarPainelFormulario() {
        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        painel.add(criarLinhaFormulario("Nome Completo:", txtNome));
        painel.add(criarLinhaFormulario("Email:", txtEmail));
        painel.add(criarLinhaFormulario("Senha (mín. 6):", txtSenha));
        painel.add(criarLinhaFormulario("Confirmar Senha:", txtConfirmarSenha));

        return painel;
    }

    private JPanel criarLinhaFormulario(String labelText, JComponent campo) {
        JPanel linha = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        linha.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel label = new JLabel(labelText);
        label.setPreferredSize(new Dimension(120, 25));
        linha.add(label);
        linha.add(campo);
        return linha;
    }

    private JPanel criarPainelBotoes() {
        JPanel painel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        painel.add(btnCadastrar);
        painel.add(btnCancelar);
        return painel;
    }

    private void configurarEventos() {
        btnCadastrar.addActionListener(this::acaoCadastrar);
        btnCancelar.addActionListener(e -> dispose()); // Apenas fecha a janela
    }

    private void acaoCadastrar(ActionEvent e) {
        if (!validarFormulario()) {
            return;
        }
        // Lógica de cadastro (simulada)
        System.out.println("Novo usuário cadastrado: " + txtEmail.getText());
        
        JOptionPane.showMessageDialog(this, "Cadastro realizado com sucesso!");
        dispose(); // Fecha a janela após cadastrar
    }

    private boolean validarFormulario() {
        if (txtNome.getText().trim().isEmpty() || txtEmail.getText().trim().isEmpty()) {
            exibirErro("Nome e Email são obrigatórios!");
            return false;
        }
        String senha = new String(txtSenha.getPassword());
        String confirmarSenha = new String(txtConfirmarSenha.getPassword());

        if (senha.length() < 6) {
            exibirErro("A senha deve ter no mínimo 6 caracteres!");
            return false;
        }
        if (!senha.equals(confirmarSenha)) {
            exibirErro("As senhas não conferem!");
            return false;
        }
        return true;
    }

    private void exibirErro(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem, "Erro de Validação", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(() -> {
            new TelaCadastro().setVisible(true);
        });
    }
}