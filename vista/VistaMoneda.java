package vista;
import java.awt.*;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/** Encapsulador gráfico individual encargado de la renderización autónoma de una moneda.
 * Almacena de forma persistente su ubicación en pantalla, sus valores nominales lógicos, etc. */
public class VistaMoneda extends JPanel {
    private int x;
    private int y;
    private int valor;
    private Image imagen;
    private int serie;

    /** Constructor que inicializa los atributos espaciales e identitarios de la moneda visual,cargando la ruta del recurso dinámico en memoria.
     * @param x Posición horizontal inicial.
     * @param y Posición vertical inicial.
     * @param valor Denominación monetaria entera ($100, $500, $1000).
     * @param serie Identificador único de serie inmutable. */
    public VistaMoneda(int x, int y, int valor, int serie) {
        this.x = x;
        this.y = y;
        this.valor = valor;
        this.serie = serie;
        String address = "recursos/moneda" + this.valor + ".png";
        this.imagen = new ImageIcon(address).getImage();
    }
    /** Dibuja la textura circular de la moneda, su valor numérico y su número de serie.
     * @param g El contexto gráfico utilizado para pintar. */
    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(this.imagen, this.x, this.y + 10, 60, 60, this);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 11));
        g.drawString("$" + this.valor, this.x + 15, this.y + 85);
        g.drawString("Serie:" + this.serie, this.x + 3, this.y + 5);
    }
    /** @return Coordenada horizontal actual de la moneda. */
    public int getX() { return this.x; }
    /** @return Coordenada vertical actual de la moneda. */
    public int getY() { return this.y; }
    /** @return Valor nominal entero de la moneda. */
    public int getValor() { return this.valor; }
    /** @return Número de serie único de la moneda. */
    public int getSerie() { return this.serie; }
}