package vista;
import Productos.Producto;
import logica.Deposito;
import logica.Expendedor;
import javax.swing.ImageIcon;
import java.awt.*;

/** Componente gráfico encargado de simular el interior transparente de la máquina expendedora.
 * Implementa el Stock Visual consultando en tiempo real las estructuras del modelo para mostrar los productos ordenados. */
public class VidrioBebida {
    private final Expendedor exp;
    private int x, y, width, height;
    private Image imgCocaCola, imgSprite, imgFanta, imgSnickers, imgSuper8;

    /** Constructor de la vitrina. Almacena las coordenadas dimensionales y guarda la referencia del expendedor lógico.
     * @param x Ubicación horizontal en el panel.
     * @param y Ubicación vertical en el panel.
     * @param width Ancho de la vitrina.
     * @param height Alto de la vitrina.
     * @param exp Referencia al modelo lógico de la máquina. */
    public VidrioBebida(int x, int y, int width, int height, Expendedor exp) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.exp = exp;
        this.imgCocaCola = new ImageIcon("recursos/cocacola.png").getImage();
        this.imgSprite = new ImageIcon("recursos/sprite.png").getImage();
        this.imgFanta = new ImageIcon("recursos/fanta.png").getImage();
        this.imgSnickers = new ImageIcon("recursos/snickers.png").getImage();
        this.imgSuper8 = new ImageIcon("recursos/super8.png").getImage();
    }

    /** Dibuja el fondo translúcido del vidrio, las repisas
     * @param g El contexto gráfico utilizado para pintar. */
    public void paintComponent(Graphics g) {
        // Fondo vidrio
        g.setColor(new Color(173, 216, 230, 130));
        g.fillRect(this.x, this.y, this.width, this.height);

        // repisas
        g.setColor(Color.GRAY);
        g.drawLine(this.x, this.y + 120, this.x + this.width, this.y + 120); // Repisa Superior
        g.drawLine(this.x, this.y + 250, this.x + this.width, this.y + 250); // Repisa Inferior
        g.setFont(new Font("Arial", Font.BOLD, 14));

        // Fila BEBIDAS (CocaCola id 1, Sprite id 2 y Fanta id 3)
        dibujarFilaProductos(g, 1, "$1000", imgCocaCola, this.x + 0, this.y + 40, 65, 60);
        dibujarFilaProductos(g, 2, "$900", imgSprite,   this.x + 105, this.y + 40, 40, 60);
        dibujarFilaProductos(g, 3, "$900", imgFanta,    this.x + 170, this.y + 40, 65, 60);

        // Fila DULCES (Snickers id 4 y Super8 id 5)
        dibujarFilaProductos(g, 4, "$600", imgSnickers, this.x + 10, this.y + 181, 75, 65);
        dibujarFilaProductos(g, 5, "$300", imgSuper8,   this.x + 130, this.y + 180, 75, 65);
    }

    /**
     * Proyecta en pantalla una fila tridimensional de productos basándose estrictamente en el tamaño del depósito analizado.
     * Aplica desplazamientos escalados (offsets) de forma inversa para simular orden de filas y renderiza señales de desabastecimiento en caso de stock cero.
     * @param g Contexto gráfico de dibujo.
     * @param id Identificador numérico del depósito (1 al 5).
     * @param precio Etiqueta textual del costo comercial.
     * @param img Textura de la imagen del producto.
     * @param baseX Ubicación inicial horizontal en la repisa.
     * @param baseY Ubicación inicial vertical en la repisa.
     * @param ancho Ancho geométrico del producto en píxeles.
     * @param alto Alto geométrico del producto en píxeles.
     */
    private void dibujarFilaProductos(Graphics g, int id, String precio, Image img, int baseX, int baseY, int ancho, int alto) {
        Deposito<Producto> deposito = this.exp.getDepositoPorNum(id);

        if (deposito == null || deposito.size() == 0) {
            // Si no hay stock, pintamos la X
            g.setColor(Color.RED);
            g.drawString("X", baseX + (ancho/2) - 3, baseY + (alto/2) + 5);
            g.setColor(Color.BLACK);
            g.drawString(String.valueOf(id), baseX + (ancho/2) - 3, baseY + alto + 15);
            return;
        }
        int stockActual = deposito.size();
        for (int i = stockActual - 1; i >= 0; i--) {
            int offsetX = i * 5;
            int offsetY = i * 4;
            int drawX = baseX + offsetX;
            int drawY = baseY - offsetY;

            if (i > 0) {
                g.setColor(new Color(0, 0, 0, 40));
                g.fillRect(drawX + 2, drawY + 2, ancho, alto);
            }
            g.drawImage(img, drawX, drawY, ancho, alto, null);
        }
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 13));
        g.drawString(String.valueOf(id), baseX + (ancho / 2) - 4, baseY + alto + 16);
        g.setColor(new Color(0, 100, 0));
        g.setFont(new Font("Arial", Font.BOLD, 15));
        g.drawString(precio, baseX + (ancho / 2) - 14, baseY + alto + 28);
    }
}