package pacotes;

import javax.swing.SwingUtilities;

import pacotes.view.BomDeBicoSwing;

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
            new BomDeBicoSwing().setVisible(true);
        });
    }
}
