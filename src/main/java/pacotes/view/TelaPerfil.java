package pacotes.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Tela de Perfil do Usuário.
 */
public class TelaPerfil extends JFrame {

    // Componentes do formulário
    private JTextField txtNome;
    private JTextField txtEmail;
    private JTextField txtTelefone;
    private JPasswordField txtNovaSenha;
    private JPasswordField txtConfirmarSenha;
    
    private JButton btnSalvar;
    private JButton btnCancelar;

    public TelaPerfil() {
        inicializarJanela();
        inicializarComponentes();
        montarInterface();
        configurarEventos();
    }

    private void inicializarJanela() {
        setTitle("Bom de Bico - Detalhes da Conta");
        // DISPOSE_ON_CLOSE: Fecha apenas esta janela
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 350);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
    }

    private void inicializarComponentes() {
        txtNome = new JTextField(25);
        txtEmail = new JTextField(25);
        txtTelefone = new JTextField(25);
        txtNovaSenha = new JPasswordField(25);
        txtConfirmarSenha = new JPasswordField(25);
        btnSalvar = new JButton("Salvar Alterações");
        btnCancelar = new JButton("Fechar"); // Alterado de "Cancelar"

        carregarDadosUsuario();
    }

    private void carregarDadosUsuario() {
        // Dados de exemplo
        txtNome.setText("João Silva");
        txtEmail.setText("joao.silva@email.com");
        txtTelefone.setText("(11) 98765-4321");
    }

    private void montarInterface() {
        add(criarPainelTitulo(), BorderLayout.NORTH);
        add(criarPainelFormulario(), BorderLayout.CENTER);
        add(criarPainelBotoes(), BorderLayout.SOUTH);
    }

    private JPanel criarPainelTitulo() {
        JPanel painel = new JPanel();
        JLabel lblTitulo = new JLabel("Detalhes da Conta");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        painel.add(lblTitulo);
        return painel;
    }

    private JPanel criarPainelFormulario() {
        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        painel.add(criarLabelSecao("Dados Pessoais"));
        painel.add(criarLinhaFormulario("Nome:", txtNome));
        painel.add(criarLinhaFormulario("Email:", txtEmail));
        painel.add(criarLinhaFormulario("Telefone:", txtTelefone));

        painel.add(Box.createRigidArea(new Dimension(0, 20)));

        painel.add(criarLabelSecao("Alterar Senha"));
        painel.add(criarLinhaFormulario("Nova Senha:", txtNovaSenha));
        painel.add(criarLinhaFormulario("Confirmar Senha:", txtConfirmarSenha));

        return painel;
    }

    private JLabel criarLabelSecao(String texto) {
        JLabel label = new JLabel(texto);
        label.setFont(new Font("Arial", Font.BOLD, 12));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        return label;
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
        painel.add(btnSalvar);
        painel.add(btnCancelar);
        return painel;
    }

    private void configurarEventos() {
        btnSalvar.addActionListener(this::acaoSalvar);
        btnCancelar.addActionListener(e -> dispose());
    }

    private void acaoSalvar(ActionEvent e) {
        if (!validarFormulario()) {
            return;
        }
        // Lógica de salvar (simulada)
        System.out.println("Nome: " + txtNome.getText());
        System.out.println("Email: " + txtEmail.getText());
        
        JOptionPane.showMessageDialog(this, "Perfil atualizado com sucesso!");
        dispose(); // Fecha a janela após salvar
    }

    private boolean validarFormulario() {
        if (txtNome.getText().trim().isEmpty() || txtEmail.getText().trim().isEmpty()) {
            exibirErro("Nome e Email são obrigatórios!");
            return false;
        }
        String novaSenha = new String(txtNovaSenha.getPassword());
        String confirmarSenha = new String(txtConfirmarSenha.getPassword());
        if (!novaSenha.isEmpty() && !novaSenha.equals(confirmarSenha)) {
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
            new TelaPerfil().setVisible(true);
        });
    }
}