package pacotes;

import pacotes.view.telaCadastro;
import pacotes.view.telaLogin;


public final class App {
    private App() {
    }

    public static void main(String[] args) {
        telaLogin janelaLogin = new telaLogin();
        telaCadastro janelaCadastro = new telaCadastro();
        janelaLogin.janela();
    }
}
