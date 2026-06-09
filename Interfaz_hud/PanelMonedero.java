package Interfaz_hud;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class PanelMonedero extends JPanel {
    private ArrayList<VistaMoneda> monedasEnBolsillo;

    public PanelMonedero() {
        this.monedasEnBolsillo = new ArrayList<>();
        this.setBackground(new Color(230, 230, 230));
        monedasEnBolsillo.add(new VistaMoneda(10, 30, 1000, 101));
        monedasEnBolsillo.add(new VistaMoneda(80, 30, 500, 102));
        monedasEnBolsillo.add(new VistaMoneda(150, 30, 100, 103));
        monedasEnBolsillo.add(new VistaMoneda(220, 30, 100, 104));
    }

    public void agregarMoneda(VistaMoneda m) {
        this.monedasEnBolsillo.add(m);
        this.repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.drawString("Monedero del Comprador", 10, 20);
        for (VistaMoneda m : monedasEnBolsillo) {
            m.paintComponent(g);
        }
    }
}