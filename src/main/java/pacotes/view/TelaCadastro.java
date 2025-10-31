package pacotes.view;

import javax.swing.*;

public class TelaCadastro {
    private JLabel labelNome = new JLabel("Nome:");
    private JLabel labelCPF = new JLabel("CPF:");
    private JLabel labelCNPJ = new JLabel("CNPJ:");
    private JLabel labelEmail = new JLabel("Email:");
    private JLabel labelSenha = new JLabel("Senha:");
    private JLabel labelConfirmaSenha = new JLabel("Confirmar Senha:");
    private JTextField campoUsuario_Email = new JTextField();
    private JTextField campoUsuario_Nome = new JTextField();
    private JPasswordField senhaUsuario_Confirma = new JPasswordField();
    private JPasswordField senhaUsuario = new JPasswordField();
    private JTextField campoUsuario_CPF = new JTextField(11);
    private JTextField campoUsuario_CNPJ = new JTextField(14);
    private JButton botaoCadastrar = new JButton("Cadastrar");
    private JRadioButton botaoPessoaJuridica = new JRadioButton("Pessoa Jurídica");

    public TelaCadastro() {
    }

    public void janela() {
        JFrame janelaCadastro = new JFrame("Cadastro");
        janelaCadastro.setLayout(null);
        janelaCadastro.setVisible(true);
        janelaCadastro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janelaCadastro.setBounds(0, 0, 1000, 600);

        janelaCadastro.add(botaoCadastrar);
        botaoCadastrar.setBounds(400, 400, 180, 40);
        botaoCadastrar.addActionListener(list_botaoCadastrar -> {
            TelaLogin abrirTela = new TelaLogin();
            abrirTela.janela();
            janelaCadastro.dispose();
        });

        janelaCadastro.add(botaoPessoaJuridica);
        botaoPessoaJuridica.setBounds(400, 360, 180, 25);
        botaoPessoaJuridica.addActionListener(list_botaoPessoaJuridica -> {
            boolean selecionado = botaoPessoaJuridica.isSelected();
            labelCPF.setVisible(!selecionado);
            campoUsuario_CPF.setVisible(!selecionado);
            labelCNPJ.setVisible(selecionado);
            campoUsuario_CNPJ.setVisible(selecionado);
        });

        janelaCadastro.add(labelNome);
        labelNome.setBounds(400, 80, 180, 20);
        janelaCadastro.add(campoUsuario_Nome);
        campoUsuario_Nome.setBounds(400, 100, 180, 30);

        janelaCadastro.add(labelCPF);
        labelCPF.setBounds(400, 135, 180, 20);
        janelaCadastro.add(campoUsuario_CPF);
        campoUsuario_CPF.setBounds(400, 155, 180, 30);

        janelaCadastro.add(labelCNPJ);
        labelCNPJ.setBounds(400, 135, 180, 20);
        labelCNPJ.setVisible(false);
        janelaCadastro.add(campoUsuario_CNPJ);
        campoUsuario_CNPJ.setBounds(400, 155, 180, 30);
        campoUsuario_CNPJ.setVisible(false);

        janelaCadastro.add(labelEmail);
        labelEmail.setBounds(400, 190, 180, 20);
        janelaCadastro.add(campoUsuario_Email);
        campoUsuario_Email.setBounds(400, 210, 180, 30);

        janelaCadastro.add(labelSenha);
        labelSenha.setBounds(400, 245, 180, 20);
        janelaCadastro.add(senhaUsuario);
        senhaUsuario.setBounds(400, 265, 180, 30);

        janelaCadastro.add(labelConfirmaSenha);
        labelConfirmaSenha.setBounds(400, 300, 180, 30);
        janelaCadastro.add(senhaUsuario_Confirma);
        senhaUsuario_Confirma.setBounds(400, 330, 180, 30);
    }
}