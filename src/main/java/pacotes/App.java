package pacotes;

import pacotes.view.telaCadastro;
import pacotes.view.telaLogin;

/**
 * Hello world!
 */
public final class App {
    private App() {
    }

    /**
     * Says hello to the world.
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        telaLogin janelaLogin = new telaLogin();
        telaCadastro janelaCadastro = new telaCadastro();
        janelaLogin.janela();
    }
}
