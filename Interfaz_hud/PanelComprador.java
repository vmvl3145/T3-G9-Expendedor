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
    private Image imgCoca, imgSprite, imgFanta, imgSnickers, imgSuper8;

    public PanelComprador(Expendedor exp) {
        this.exp = exp;
        this.setBackground(Color.WHITE);
        this.setLayout(null);
        this.monedero = new PanelMonedero();
        this.monedero.setBounds(10, 420, 450, 150);
        this.add(this.monedero);
        this.mensajeEstado = "Esperando interacción...";
        this.colorMensaje = Color.GRAY;
        this.imgCoca = new javax.swing.ImageIcon("recursos/cocaCola.png").getImage();
        this.imgSprite = new javax.swing.ImageIcon("recursos/sprite.png").getImage();
        this.imgFanta = new javax.swing.ImageIcon("recursos/fanta.png").getImage();
        this.imgSnickers = new javax.swing.ImageIcon("recursos/snickers.png").getImage();
        this.imgSuper8 = new javax.swing.ImageIcon("recursos/super8.png").getImage();
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

            Image imgDibujar = null;
            switch (idUltimoProductoComprado) {
                case 1: imgDibujar = imgCoca; break;
                case 2: imgDibujar = imgSprite; break;
                case 3: imgDibujar = imgFanta; break;
                case 4: imgDibujar = imgSnickers; break;
                case 5: imgDibujar = imgSuper8; break;
            }
            if (imgDibujar != null) {
                // Dibujamos la imagen en vez del cuadrado de color
                g.drawImage(imgDibujar, 40, 70, 40, 60, this);
            }
            g.setColor(Color.BLACK);
            g.drawString(nombreUltimoProducto, 90, 100);
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
            vueltoVisual.clear();
            Moneda m;
            int coordX = 40;
            int coordY = 160;

            while ((m = exp.getVuelto()) != null) {
                vueltoVisual.add(new VistaMoneda(coordX, coordY, m.getValor(), 1000 + (int)(Math.random()*100)));
                coordX += 65;
                if (coordX > 350) {
                    coordX = 40;
                    coordY += 70;
                }
            }
            this.repaint();
        }
    }

    public void procesarClick(int x, int y) {
        if (y >= 420 && y <= 570) {
            this.monedero.procesarClick(x - 10, y - 420);
            return;
        }
        // Verificar si se hizo click en alguna moneda del vuelto
        VistaMoneda monedaRecogida = null;
        for (VistaMoneda m : vueltoVisual) {
            // Creamos una hitbox alrededor de la moneda
            if (x >= m.getX() && x <= m.getX() + 60 && y >= m.getY() && y <= m.getY() + 70) {
                monedaRecogida = m;
                break;
            }
        }

        // Si tomamos una moneda, la movemos
        if (monedaRecogida != null) {
            vueltoVisual.remove(monedaRecogida); // Desaparece de la bandeja de la máquina
            this.monedero.recibirVuelto(monedaRecogida.getValor()); // Aparece en el bolsillo
            this.repaint(); // Refrescamos la pantalla
        }
    }
}