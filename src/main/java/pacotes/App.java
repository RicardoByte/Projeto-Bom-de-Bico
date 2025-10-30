package pacotes;

import pacotes.view.BomDeBicoHome;
import javax.swing.*;

public final class App {
    private App() {
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BomDeBicoHome home = new BomDeBicoHome();
            home.setVisible(true);
        });
    }
}
