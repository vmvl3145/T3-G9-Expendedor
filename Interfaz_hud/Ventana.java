package Interfaz_hud;

import javax.swing.JFrame;
import java.awt.BorderLayout;

public class Ventana extends JFrame {
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