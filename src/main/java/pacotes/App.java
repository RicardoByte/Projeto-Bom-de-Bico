package pacotes;

import pacotes.view.TelaInicial;
import javax.swing.*;

public final class App {
    private App() {
    }
   public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            
            javax.swing.JFrame frame = new javax.swing.JFrame("Bom de Bico - Home");
            frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
            frame.setSize(1200, 800);

            
            frame.add(new TelaInicial());

            
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
