import javax.swing.*;

public class telaCadastro {

    private JTextField campoUsuario_Email = new JTextField();
    private JTextArea campoUsuario_Nome = new JTextArea();
    private JPasswordField senhaUsuario_Confirma = new JPasswordField();
    private JPasswordField senhaUsuario = new JPasswordField();
    private JTextField campoUsuario_CPF = new JTextField(11);
    private JTextField campoUsuario_CNPJ = new JTextField(14);
    private JButton botaoCadastrar = new JButton("Cadastrar");
    private JRadioButton botaoPessoaJuridica = new JRadioButton("Pessoa Jurídica");

    public telaCadastro(){

    }

    public void janela(){
        JFrame janelaCadastro = new JFrame("Cadastro");
        janelaCadastro.setLayout(null);
        janelaCadastro.setVisible(true);
        janelaCadastro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janelaCadastro.setBounds(0, 0, 1000, 600);


        janelaCadastro.add(botaoCadastrar);
        botaoCadastrar.setBounds(400, 400, 180, 40);
        botaoCadastrar.addActionListener(list_botaoCadastrar -> {
            telaLogin abrirTela = new telaLogin();
            abrirTela.janela();
            janelaCadastro.dispose();
        });
        janelaCadastro.add(botaoPessoaJuridica);
        botaoPessoaJuridica.setBounds(400, 340, 180, 25);
        botaoPessoaJuridica.addActionListener(list_botaoPessoaJuridica -> {
           if(botaoPessoaJuridica.isSelected()){
                janelaCadastro.remove(campoUsuario_CPF);
                janelaCadastro.add(campoUsuario_CNPJ);
                campoUsuario_CNPJ.setBounds(400, 135, 180, 30);
           }
           else if(!botaoPessoaJuridica.isSelected()){
            janelaCadastro.remove(campoUsuario_CNPJ);
            janelaCadastro.add(campoUsuario_CPF);
           }
        });

        janelaCadastro.add(campoUsuario_Nome);
        campoUsuario_Nome.setBounds(400, 100, 180, 30);

        janelaCadastro.add(campoUsuario_CPF);
        campoUsuario_CPF.setBounds(400, 135, 180, 30);

        janelaCadastro.add(campoUsuario_Email);
        campoUsuario_Email.setBounds(400, 170, 180, 30);

        janelaCadastro.add(senhaUsuario);
        senhaUsuario.setBounds(400, 205, 180, 30);
        janelaCadastro.add(senhaUsuario_Confirma);
        senhaUsuario_Confirma.setBounds(400, 235, 180, 30);
        
        
    }
}