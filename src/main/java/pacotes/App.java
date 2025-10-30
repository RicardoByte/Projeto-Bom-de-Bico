package pacotes;

import pacotes.view.TelaInicial;
import javax.swing.*;

public final class App {
    private App() {
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TelaInicial home = new TelaInicial();
            home.setVisible(true);
        });
    }
}
