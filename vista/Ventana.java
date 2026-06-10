package vista;

import javax.swing.JFrame;
import java.awt.BorderLayout;

/**
 * Ventana principal de la aplicación
 * Configura las dimensiones de la pantalla, la posición inicial, el cierre de la aplicación y monta el panel principal que orquesta el simulador. */

public class Ventana extends JFrame {
    /** Constructor de la ventana. Configura las propiedades esenciales del JFrame e inicializa el flujo visual montando el PanelPrincipal en el centro. */
    public Ventana() {
        super("Expendedor");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000, 700);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        PanelPrincipal panel = new PanelPrincipal();
        this.add(panel, BorderLayout.CENTER);
        this.setVisible(true);
    }
}