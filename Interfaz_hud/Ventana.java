package Interfaz_hud;

import javax.swing.JFrame;
import java.awt.BorderLayout;

public class Ventana extends JFrame {
    public Ventana() {
        super("Simulador Máquina Expendedora - Grupo 9");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000, 700); // Resolución base
        this.setLocationRelativeTo(null); // Centra la ventana
        this.setResizable(false);
        this.setLayout(new BorderLayout());

        interfaz_hud.PanelPrincipal panel = new interfaz_hud.PanelPrincipal();
        this.add(panel, BorderLayout.CENTER);

        this.setVisible(true);
    }
}