package pacotes.view;

import javax.swing.*;

public class telaLogin {

    private JLabel labelUsuario = new JLabel("Usuário:");
    private JLabel labelSenha = new JLabel("Senha:");
    private JTextArea campoUsuario = new JTextArea();
    private JPasswordField senhaUsuario = new JPasswordField();
    private JButton botaoLogin = new JButton("Login");
    private JButton botaoCadastro = new JButton("Cadastrar");

    public telaLogin (){

    }

    public void janela(){
        JFrame janelaLogin = new JFrame("Login");
        janelaLogin.setLayout(null);

        janelaLogin.add(botaoLogin);
        botaoLogin.setBounds(400, 250, 180, 40);

        botaoLogin.addActionListener(e -> {
            JFrame frameHome = new JFrame("Bom de Bico - Home");
            frameHome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frameHome.setSize(1200, 800);
            frameHome.add(new TelaInicial());
            frameHome.setLocationRelativeTo(null);
            frameHome.setVisible(true);
            janelaLogin.dispose();
        });

        janelaLogin.add(botaoCadastro);
        botaoCadastro.setBounds(400, 300, 180, 40);
        botaoCadastro.addActionListener(list_botaoCadastro -> {
            telaCadastro abrirTelaCadastro = new telaCadastro();
            abrirTelaCadastro.janela();
            janelaLogin.dispose();
        });

        janelaLogin.setBounds(0, 0, 1000, 600);
        janelaLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janelaLogin.setVisible(true);

        janelaLogin.add(labelUsuario); 
        labelUsuario.setBounds(400, 80, 180, 20);
        janelaLogin.add(campoUsuario);
        campoUsuario.setBounds(400, 100, 180, 30);

        janelaLogin.add(labelSenha);
        labelSenha.setBounds(400, 130, 180, 20); 
        janelaLogin.add(senhaUsuario);
        senhaUsuario.setBounds(400, 150, 180, 30);
    }
}