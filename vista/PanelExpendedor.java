package vista;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;
import logica.Expendedor;

/** Representación gráfica del chasis físico de la máquina expendedora.
 * Dibuja el cuerpo contenedor, los botones numéricos de selección comercial (1 al 5), la ranura de monedas y aloja de forma interna la vitrina. */
public class PanelExpendedor extends JPanel {
    private Expendedor exp;
    private VidrioBebida vidrio;
    /** Constructor que enlaza el expendedor lógico y crea el interior de la vitrina.
     * @param exp Instancia del expendedor lógico del modelo. */
    public PanelExpendedor(Expendedor exp) {
        this.exp = exp;
        this.setBackground(Color.LIGHT_GRAY);
        this.vidrio = new VidrioBebida(80, 80, 250, 350, this.exp);
    }

    /** Dibuja los componentes estructurales fijos de la máquina expendedora, incluyendo el fondo, los botones de selección y la tolva de retiro.
     *  @param g El contexto gráfico utilizado para pintar. */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // maquina
        g.setColor(Color.GRAY);
        g.fillRect(50, 50, 400, 550);

        // vidrio
        this.vidrio.paintComponent(g);

        // ranura monedas
        g.setColor(Color.BLACK);
        g.fillRect(360, 120, 10, 40);
        g.setFont(new Font("Arial", Font.BOLD, 12));

        // CocaCola
        g.setColor(Color.RED); g.fillRect(360, 190, 30, 25);
        g.setColor(Color.WHITE); g.drawString("1", 372, 207);

        // Sprite
        g.setColor(Color.GREEN.darker()); g.fillRect(360, 225, 30, 25);
        g.setColor(Color.WHITE); g.drawString("2", 372, 242);

        // Fanta
        g.setColor(new Color(0xB4703D)); g.fillRect(360, 260, 30, 25);
        g.setColor(Color.WHITE); g.drawString("3", 372, 277);

        // Snickers
        g.setColor(new Color(101, 67, 33)); g.fillRect(360, 295, 30, 25);
        g.setColor(Color.WHITE); g.drawString("4", 372, 312);

        // Super8
        g.setColor(Color.YELLOW.darker()); g.fillRect(360, 330, 30, 25);
        g.setColor(Color.BLACK); g.drawString("5", 372, 347);

        // out de item
        g.setColor(Color.BLACK);
        g.fillRect(140, 480, 140, 70);
    }

    /** Traduce las coordenadas espaciales de un clic en una instrucción lógica para la máquina.
     * Evalúa si se presionó un botón de compra o si se hizo clic en el vidrio protector.
     * @param x Coordenada horizontal del clic.
     * @param y Coordenada vertical del clic.
     * @return ID del producto (1-5), -2 si es una orden de rellenar la máquina, o -1 si fue fuera de rango. */
    public int procesarClick(int x, int y) {
        if (x >= 360 && x <= 390) {
            if (y >= 190 && y <= 215) return 1; // CocaCola
            if (y >= 225 && y <= 250) return 2; // Sprite
            if (y >= 260 && y <= 285) return 3; // Fanta
            if (y >= 295 && y <= 320) return 4; // Snickers
            if (y >= 330 && y <= 355) return 5; // Super8
        }

        // vidrio
        if (x >= 80 && x <= 330 && y >= 80 && y <= 430) {
            return -2; // -2 = Rellenado de máquina
        }
        return -1;
    }
}