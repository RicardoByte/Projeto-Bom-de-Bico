package pacotes;

import javax.swing.SwingUtilities;

import pacotes.view.Home;
// import pacotes.view.PesquisaSwingSimples;

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
        SwingUtilities.invokeLater(() -> {
            // Cria o frame principal
            javax.swing.JFrame frame = new javax.swing.JFrame("Bom de Bico - Home");
            frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
            frame.setSize(1200, 800);

            // Adiciona o painel da Home ao frame
            frame.add(new Home());

            // Centraliza e torna o frame visível
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
