package Interfaz_hud;

import javax.swing.JPanel;
import java.awt.*;
import logica.Expendedor;
import Moneda.Moneda;
import Excepciones.*;

public class PanelComprador extends JPanel {
    private Expendedor exp;
    private PanelMonedero monedero;

    public PanelComprador(Expendedor exp) {
        this.exp = exp;
        this.setBackground(Color.WHITE);
        this.setLayout(null);
        this.monedero = new PanelMonedero();
        this.monedero.setBounds(10, 420, 450, 150);
        this.add(this.monedero);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.BLACK);
        g.drawString("Seleccione un Producto:", 40, 220);

        g.setColor(Color.RED);
        g.fillRect(40, 250, 160, 40);
        g.setColor(Color.WHITE);
        g.drawString("Comprar CocaCola ($1000)", 60, 275);

        g.setColor(Color.GREEN.darker());
        g.fillRect(40, 300, 160, 40);
        g.setColor(Color.WHITE);
        g.drawString("Comprar Sprite ($900)", 70, 325);

        g.setColor(Color.ORANGE);
        g.fillRect(40, 200, 160, 40);
        g.setColor(Color.WHITE);
        g.drawString("Comprar Fanta ($900)", 75, 225);

    }

    public void procesarClick(int x, int y) {
        if (y >= 420 && y <= 570 && x >= 10 && x <= 460) {
            monedero.procesarClick(x - 10, y - 420);
            return;
        }
        if (x >= 40 && x <= 200) {
            int idProducto = -1;
            String nombreBebida = "";

            if (y >= 200 && y <= 240) {
                    idProducto = 3;
                    nombreBebida = "Fanta";
            } else if (y >= 250 && y <= 290) {
                    idProducto = 1; // Código según tu lógica para COCA_COLA
                    nombreBebida = "CocaCola";
            } else if (y >= 300 && y <= 340) {
                    idProducto = 2; // Código según tu lógica para SPRITE
                    nombreBebida = "Sprite";
            }
            if (idProducto != -1) {
                Moneda monedaParaPago = monedero.entregarMonedaSeleccionada();
                try {
                    System.out.println("Intentando comprar " + nombreBebida + "...");

                   Productos.Producto productoComprado = exp.comprarProducto(monedaParaPago, idProducto);

                    System.out.println("¡Compra exitosa! Has obtenido: " + productoComprado.consumir());

                } catch (PagoIncorrectoException ex) {
                    System.out.println("Error de Compra: " + ex.getMessage() + " (Debes seleccionar una moneda primero).");
                } catch (NoHayProductoException ex) {
                    System.out.println("Error de Compra: " + ex.getMessage());
                } catch (PagoInsuficienteException ex) {
                    System.out.println("Error de Compra: " + ex.getMessage());
                } finally {
                    // Refrescamos el monedero por si la moneda se consumió o se devolvió
                    monedero.repaint();
                }
            }
        }
    }
}