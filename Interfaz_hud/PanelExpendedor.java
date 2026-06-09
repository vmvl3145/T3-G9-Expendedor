package Interfaz_hud;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import logica.Expendedor;

public class PanelExpendedor extends JPanel {
    private Expendedor exp;

    public PanelExpendedor(Expendedor exp) {
        this.exp = exp;
        this.setBackground(Color.LIGHT_GRAY); // Color del fondo izquierdo
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Dibujo base de la máquina expendedora
        g.setColor(Color.DARK_GRAY);
        g.fillRect(50, 50, 400, 550); // El chasis principal

        g.setColor(new Color(173, 216, 230)); // Celeste para el vidrio
        g.fillRect(80, 80, 250, 350); // Vitrina de productos

        g.setColor(Color.BLACK);
        g.fillRect(360, 150, 10, 40); // Ranura de monedas

        g.fillRect(150, 480, 120, 80); // Cajetín para retirar bebida
    }
}