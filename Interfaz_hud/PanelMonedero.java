package Interfaz_hud;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import Moneda.*;
import logica.*;

public class PanelMonedero extends JPanel {
    private ArrayList<VistaMoneda> monedasEnBolsillo;
    private Moneda monedaSeleccionada;

    public PanelMonedero() {
        this.monedasEnBolsillo = new ArrayList<>();
        this.setBackground(new Color(230, 230, 230));
        monedasEnBolsillo.add(new VistaMoneda(10, 30, 1000, 101));
        monedasEnBolsillo.add(new VistaMoneda(80, 30, 500, 102));
        monedasEnBolsillo.add(new VistaMoneda(150, 30, 100, 103));
        monedasEnBolsillo.add(new VistaMoneda(220, 30, 100, 104));
        this.monedaSeleccionada = null;
    }

    public void agregarMoneda(VistaMoneda m) {
        this.monedasEnBolsillo.add(m);
        this.repaint();
    }

    public Moneda entregarMonedaSeleccionada() {
        Moneda m = this.monedaSeleccionada;
        this.monedaSeleccionada = null;
        return m;
    }

    public Moneda verMonedaSeleccionada() {
        return this.monedaSeleccionada;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.drawString("Monedero del Comprador (Click para seleccionarla)", 10, 20);
        for (VistaMoneda m : monedasEnBolsillo) {
            m.paintComponent(g);
        }
        g.setColor(Color.BLUE);
        if (monedaSeleccionada != null) {
            g.drawString("Moneda seleccionada: $" + monedaSeleccionada.getValor(), 10, 140);
        } else {
            g.drawString("Ninguna moneda seleccionada", 10, 140);
        }
    }

    public void procesarClick(int clickX, int clickY) {
        VistaMoneda monedaClickeada = null;

        for (VistaMoneda m : monedasEnBolsillo) {
            if (clickX >= m.getX() && clickX <= m.getX() + 60 &&
                    clickY >= m.getY() && clickY <= m.getY() + 90) {
                monedaClickeada = m;
                break;
            }
        }

        if (monedaClickeada != null) {
            int valor = monedaClickeada.getValor();
            int serieAleatoria = (int) (Math.random() * 900) + 100;

            if (valor == 1000) this.monedaSeleccionada = new Moneda1000(serieAleatoria);
            else if (valor == 500) this.monedaSeleccionada = new Moneda500(serieAleatoria);
            else if (valor == 100) this.monedaSeleccionada = new Moneda100(serieAleatoria);

            monedasEnBolsillo.remove(monedaClickeada);
            System.out.println("Moneda de $" + valor + " (Serie: " + serieAleatoria + ") cargada.");
            this.repaint();
        }
    }
}