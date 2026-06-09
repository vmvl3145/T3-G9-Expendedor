package Interfaz_hud;

import javax.swing.JPanel;
import java.awt.*;
import java.util.ArrayList;

import Productos.Producto;
import logica.Expendedor;
import Moneda.Moneda;
import Excepciones.*;

public class PanelComprador extends JPanel {
    private Expendedor exp;
    private PanelMonedero monedero;
    private String mensajeEstado;
    private Color colorMensaje;
    private int idUltimoProductoComprado = -1;
    private String nombreUltimoProducto = "";
    private ArrayList<VistaMoneda> vueltoVisual = new ArrayList<>();

    public PanelComprador(Expendedor exp) {
        this.exp = exp;
        this.setBackground(Color.WHITE);
        this.setLayout(null);
        this.monedero = new PanelMonedero();
        this.monedero.setBounds(10, 420, 450, 150);
        this.add(this.monedero);
        this.mensajeEstado = "Esperando interacción...";
        this.colorMensaje = Color.GRAY;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 14));
        g.drawString("Zona de Recolección (Comprador)", 30, 30);

        if (idUltimoProductoComprado != -1) {
            g.setFont(new Font("Arial", Font.PLAIN, 12));
            g.drawString("Acabas de retirar:", 40, 60);
            switch (idUltimoProductoComprado) {
                // CocaCola
                case 1:
                    g.setColor(Color.RED);
                break;
                // Sprite
                case 2:
                    g.setColor(Color.GREEN.darker());
                break;
                // Fanta
                case 3:
                    g.setColor(Color.ORANGE);
                break;
                // Snickers
                case 4:
                    g.setColor(new Color(101, 67, 33));
                break;
                // Super8
                case 5: g.setColor(Color.YELLOW.darker());
                break;
            }
            g.fillRect(40, 70, 50, 50);
            g.setColor(Color.BLACK);
            g.drawRect(40, 70, 50, 50);
            g.drawString(nombreUltimoProducto, 100, 100);
        }

        if (vueltoVisual != null && !vueltoVisual.isEmpty()) {
            g.setColor(Color.BLACK);
            g.drawString("Tu Vuelto:", 40, 150);
            for (VistaMoneda m : vueltoVisual) {
                if (m != null) {
                    m.paintComponent(g);
                }
            }
        }

        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(30, 310, 400, 80);
        g.setColor(Color.BLACK);
        g.drawRect(30, 310, 400, 80);
        g.setFont(new Font("Arial", Font.BOLD, 12));
        g.drawString("ESTADO DE LA COMPRA:", 45, 330);
        g.setColor(colorMensaje);
        g.setFont(new Font("Arial", Font.PLAIN, 12));
        g.drawString(mensajeEstado, 45, 355);
    }
    public void efectuarCompra(int idProducto) {
        Moneda monedaParaPago = monedero.entregarMonedaSeleccionada();

        try {
            exp.comprarProducto(monedaParaPago, idProducto);
            Productos.Producto prod = exp.getProducto();
            // Compra exitosa
            this.idUltimoProductoComprado = idProducto;
            this.nombreUltimoProducto = prod.consumir();
            this.mensajeEstado = "¡Éxito! Retira tu " + this.nombreUltimoProducto;
            this.colorMensaje = new Color(0, 128, 0);

            // Extraer vuelto
            vueltoVisual.clear();
            Moneda m;
            int coordX = 40;
            int coordY = 160;

            while ((m = exp.getVuelto()) != null) {
                vueltoVisual.add(new VistaMoneda(coordX, coordY, m.getValor(), 1000 + (int)(Math.random()*100)));
                coordX += 65; // Mover a la derecha para la próxima moneda
                // salto de línea si son muchas monedas
                if (coordX > 350) {
                    coordX = 40;
                    coordY += 70;
                }
            }

        } catch (PagoIncorrectoException e) {
            this.mensajeEstado = "Error: " + e.getMessage() + " (Selecciona monedas abajo).";
            this.colorMensaje = Color.RED;
        } catch (PagoInsuficienteException e) {
            this.mensajeEstado = "Error: Dinero insuficiente para este item.";
            this.colorMensaje = Color.RED;
        } catch (NoHayProductoException e) {
            this.mensajeEstado = "Error: El producto seleccionado está agotado.";
            this.colorMensaje = Color.RED;
        } finally {
            this.repaint();
            Moneda mVuelto;
            while ((mVuelto = exp.getVuelto()) != null) {
            }
        }
    }

    public void procesarClick(int x, int y) {
        if (y >= 420 && y <= 570 && x >= 10 && x <= 460) {
            monedero.procesarClick(x - 10, y - 420);
        }
    }
}