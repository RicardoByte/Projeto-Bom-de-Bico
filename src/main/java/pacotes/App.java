package pacotes;

import pacotes.view.TelaLogin;


public final class App {
    private App() {
    }

    public static void main(String[] args) {
        TelaLogin janelaLogin = new TelaLogin();
        janelaLogin.janela();
    }
}