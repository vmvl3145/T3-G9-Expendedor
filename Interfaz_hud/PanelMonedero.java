package Interfaz_hud;

import java.util.concurrent.ThreadLocalRandom;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;
import java.util.ArrayList;
import Moneda.*;
import logica.*;

public class PanelMonedero extends JPanel {
    private ArrayList<VistaMoneda> monedasEnBolsillo;
    private Moneda monedaSeleccionada;

    public PanelMonedero() {
        this.monedasEnBolsillo = new ArrayList<>();
        this.setBackground(new Color(230, 230, 230));

        // Monedas iniciales con series aleatorias
        monedasEnBolsillo.add(new VistaMoneda(10, 30, 1000, ThreadLocalRandom.current().nextInt(100, 1000)));
        monedasEnBolsillo.add(new VistaMoneda(80, 30, 500, ThreadLocalRandom.current().nextInt(100, 1000)));
        monedasEnBolsillo.add(new VistaMoneda(150, 30, 100, ThreadLocalRandom.current().nextInt(100, 1000)));
        monedasEnBolsillo.add(new VistaMoneda(220, 30, 100, ThreadLocalRandom.current().nextInt(100, 1000)));
        this.monedaSeleccionada = null;
    }

    public void agregarMoneda(VistaMoneda m) {
        this.monedasEnBolsillo.add(m);
        this.repaint();
    }

    public Moneda entregarMonedaSeleccionada() {
        Moneda m = this.monedaSeleccionada;
        this.monedaSeleccionada = null; // Se consume al entregarla
        this.repaint();
        return m;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Título
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 12));
        g.drawString("Monedero del Comprador (Click para seleccionar)", 8, 20);

        // Billetera Infinita
        g.setColor(new Color(34, 139, 34));
        g.fillRoundRect(300, 4, 120, 24, 8, 8);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 11));
        g.drawString("Sacar de Billetera", 310, 20);

        // Dibujar las monedas en el bolsillo
        for (VistaMoneda m : monedasEnBolsillo) {
            m.paintComponent(g);
        }
        g.setFont(new Font("Arial", Font.BOLD, 12));
        if (monedaSeleccionada != null) {
            g.setColor(new Color(0, 102, 204));
            g.drawString("Moneda lista para pagar: $" + monedaSeleccionada.getValor(), 10, 140);
        } else {
            g.setColor(Color.GRAY);
            g.drawString("Ninguna moneda seleccionada", 10, 140);
        }
    }

    public void procesarClick(int clickX, int clickY) {
        if (clickX >= 290 && clickX <= 440 && clickY >= 4 && clickY <= 28) {
            int[] valores = {100, 500, 1000};
            int valorAleatorio = valores[ThreadLocalRandom.current().nextInt(0, valores.length)];
            this.recibirVuelto(valorAleatorio);
            System.out.println("Sacaste una moneda de $" + valorAleatorio + " desde la billetera infinita.");
            return;
        }
        VistaMoneda monedaClickeada = null;
        for (VistaMoneda m : monedasEnBolsillo) {
            if (clickX >= m.getX() && clickX <= m.getX() + 60 &&
                    clickY >= m.getY() && clickY <= m.getY() + 90) {
                monedaClickeada = m;
                break;
            }
        }

        if (monedaClickeada != null) {
            if (this.monedaSeleccionada != null) {
                this.recibirVuelto(this.monedaSeleccionada.getValor());
            }
            int valor = monedaClickeada.getValor();
            int serieAleatoria = ThreadLocalRandom.current().nextInt(100, 1000);
            if (valor == 1000) this.monedaSeleccionada = new Moneda1000(serieAleatoria);
            else if (valor == 500) this.monedaSeleccionada = new Moneda500(serieAleatoria);
            else if (valor == 100) this.monedaSeleccionada = new Moneda100(serieAleatoria);
            monedasEnBolsillo.remove(monedaClickeada);
            this.repaint();
        }
    }

    /** Recibe una moneda específica conservando su número de serie original.*/
    public void recibirVuelto(int valor, int serieExacta) {
        int maxX = -60;
        for (VistaMoneda m : monedasEnBolsillo) {
            if (m.getX() > maxX) {
                maxX = m.getX();
            }
        }
        int nuevaX = maxX + 65;
        if (nuevaX > 380) {
            nuevaX = 10;
        }
        monedasEnBolsillo.add(new VistaMoneda(nuevaX, 30, valor, serieExacta));
        this.repaint();
    }

    /**
     * Método sobrecargado para el botón "Sacar de Billetera" o deselección.
     * Inventa una serie aleatoria automáticamente.
     */
    public void recibirVuelto(int valor) {
        int serieAleatoria = java.util.concurrent.ThreadLocalRandom.current().nextInt(100, 1000);
        this.recibirVuelto(valor, serieAleatoria);
    }
}