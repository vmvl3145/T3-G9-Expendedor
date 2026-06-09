package Interfaz_hud;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import logica.Expendedor;

public class PanelComprador extends JPanel {
    private Expendedor exp;

    public PanelComprador(Expendedor exp) {
        this.exp = exp;
        this.setBackground(Color.WHITE); // Color del fondo derecho
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.BLACK);
        g.drawString("Mesa del Comprador", 150, 50);

        // Representación de una moneda de $1000
        g.setColor(Color.ORANGE);
        g.fillOval(100, 100, 80, 80);
        g.setColor(Color.BLACK);
        g.drawString("$1000", 125, 145);

        // Botón virtual simulado para comprar Coca-Cola
        g.setColor(Color.RED);
        g.fillRect(100, 250, 150, 50);
        g.setColor(Color.WHITE);
        g.drawString("Comprar CocaCola", 120, 280);
    }
}