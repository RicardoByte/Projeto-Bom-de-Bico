package pacotes.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class BomDeBicoPerfil extends JFrame {

    // Constantes de UI
    private static final int LARGURA_JANELA = 500;
    private static final int ALTURA_JANELA = 350;
    private static final int LARGURA_CAMPO = 25;
    private static final int LARGURA_LABEL = 120;
    private static final int ESPACAMENTO_COMPONENTES = 10;

    // Componentes do formulário
    private JTextField txtNome;
    private JTextField txtEmail;
    private JTextField txtTelefone;
    private JPasswordField txtNovaSenha;
    private JPasswordField txtConfirmarSenha;
    
    // Botões
    private JButton btnSalvar;
    private JButton btnCancelar;

    public BomDeBicoPerfil() {
        inicializarJanela();
        inicializarComponentes();
        montarInterface();
        configurarEventos();
    }

    /**
     * Configura as propriedades básicas da janela
     */
    private void inicializarJanela() {
        setTitle("Bom de Bico - Detalhes da Conta");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(LARGURA_JANELA, ALTURA_JANELA);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(ESPACAMENTO_COMPONENTES, ESPACAMENTO_COMPONENTES));
    }

    /**
     * Instancia todos os componentes da interface
     */
    private void inicializarComponentes() {
        // Campos de texto
        txtNome = new JTextField(LARGURA_CAMPO);
        txtEmail = new JTextField(LARGURA_CAMPO);
        txtTelefone = new JTextField(LARGURA_CAMPO);
        txtNovaSenha = new JPasswordField(LARGURA_CAMPO);
        txtConfirmarSenha = new JPasswordField(LARGURA_CAMPO);

        // Botões
        btnSalvar = new JButton("Salvar Alterações");
        btnCancelar = new JButton("Cancelar");

        // Pré-preenche dados de exemplo
        carregarDadosUsuario();
    }

    /**
     * Carrega os dados do usuário nos campos
     */
    private void carregarDadosUsuario() {
        txtNome.setText("João Silva");
        txtEmail.setText("joao.silva@email.com");
        txtTelefone.setText("");
    }

    /**
     * Monta a estrutura completa da interface
     */
    private void montarInterface() {
        add(criarPainelTitulo(), BorderLayout.NORTH);
        add(criarPainelFormulario(), BorderLayout.CENTER);
        add(criarPainelBotoes(), BorderLayout.SOUTH);
    }

    /**
     * Cria o painel do título
     */
    private JPanel criarPainelTitulo() {
        JPanel painel = new JPanel();
        JLabel lblTitulo = new JLabel("Detalhes da Conta");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        painel.add(lblTitulo);
        return painel;
    }

    /**
     * Cria o painel com o formulário de dados
     */
    private JPanel criarPainelFormulario() {
        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        // Seção: Dados Pessoais
        painel.add(criarLabelSecao("Dados Pessoais"));
        painel.add(criarLinhaFormulario("Nome:", txtNome));
        painel.add(criarLinhaFormulario("Email:", txtEmail));
        painel.add(criarLinhaFormulario("Telefone:", txtTelefone));

        // Espaçamento entre seções
        painel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Seção: Segurança
        painel.add(criarLabelSecao("Alterar Senha"));
        painel.add(criarLinhaFormulario("Nova Senha:", txtNovaSenha));
        painel.add(criarLinhaFormulario("Confirmar Senha:", txtConfirmarSenha));

        return painel;
    }

    /**
     * Cria um label de seção
     */
    private JLabel criarLabelSecao(String texto) {
        JLabel label = new JLabel(texto);
        label.setFont(new Font("Arial", Font.BOLD, 12));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        return label;
    }

    /**
     * Cria uma linha do formulário com label e campo
     */
    private JPanel criarLinhaFormulario(String labelText, JComponent campo) {
        JPanel linha = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        linha.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel label = new JLabel(labelText);
        label.setPreferredSize(new Dimension(LARGURA_LABEL, 25));

        linha.add(label);
        linha.add(campo);

        return linha;
    }

    /**
     * Cria o painel com os botões de ação
     */
    private JPanel criarPainelBotoes() {
        JPanel painel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        painel.add(btnSalvar);
        painel.add(btnCancelar);
        return painel;
    }

    /**
     * Configura os eventos dos botões
     */
    private void configurarEventos() {
        btnSalvar.addActionListener(this::acaoSalvar);
        btnCancelar.addActionListener(this::acaoCancelar);
    }

    /**
     * Ação executada ao clicar em Salvar
     */
    private void acaoSalvar(ActionEvent e) {
        if (!validarFormulario()) {
            return;
        }

        salvarAlteracoes();
        
        JOptionPane.showMessageDialog(
            this,
            "Perfil atualizado com sucesso!",
            "Sucesso",
            JOptionPane.INFORMATION_MESSAGE
        );
    }

    /**
     * Valida os dados do formulário
     */
    private boolean validarFormulario() {
        // Valida campos obrigatórios
        if (txtNome.getText().trim().isEmpty()) {
            exibirErro("O nome é obrigatório!");
            return false;
        }

        if (txtEmail.getText().trim().isEmpty()) {
            exibirErro("O email é obrigatório!");
            return false;
        }

        // Valida senhas se foram preenchidas
        String novaSenha = new String(txtNovaSenha.getPassword());
        String confirmarSenha = new String(txtConfirmarSenha.getPassword());

        if (!novaSenha.isEmpty() || !confirmarSenha.isEmpty()) {
            if (!novaSenha.equals(confirmarSenha)) {
                exibirErro("As senhas não conferem!");
                return false;
            }

            if (novaSenha.length() < 6) {
                exibirErro("A senha deve ter no mínimo 6 caracteres!");
                return false;
            }
        }

        return true;
    }

    /**
     * Salva as alterações do perfil
     */
    private void salvarAlteracoes() {
        String nome = txtNome.getText().trim();
        String email = txtEmail.getText().trim();
        String telefone = txtTelefone.getText().trim();
        String novaSenha = new String(txtNovaSenha.getPassword());

        System.out.println("=== Salvando Perfil ===");
        System.out.println("Nome: " + nome);
        System.out.println("Email: " + email);
        System.out.println("Telefone: " + telefone);
        
        if (!novaSenha.isEmpty()) {
            System.out.println("Senha alterada");
        }
        
        System.out.println("=====================");

        // Limpa os campos de senha após salvar
        txtNovaSenha.setText("");
        txtConfirmarSenha.setText("");
    }

    /**
     * Ação executada ao clicar em Cancelar
     */
    private void acaoCancelar(ActionEvent e) {
        int resposta = JOptionPane.showConfirmDialog(
            this,
            "Deseja realmente cancelar? As alterações não serão salvas.",
            "Confirmar Cancelamento",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE
        );

        if (resposta == JOptionPane.YES_OPTION) {
            System.out.println("Operação cancelada pelo usuário");
            dispose();
        }
    }

    /**
     * Exibe uma mensagem de erro
     */
    private void exibirErro(String mensagem) {
        JOptionPane.showMessageDialog(
            this,
            mensagem,
            "Erro de Validação",
            JOptionPane.ERROR_MESSAGE
        );
    }

    /**
     * Método main para executar a aplicação
     */
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            BomDeBicoPerfil frame = new BomDeBicoPerfil();
            frame.setVisible(true);
        });
    }
}