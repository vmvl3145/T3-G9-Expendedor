package Interfaz_hud;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;
import logica.Expendedor;

public class PanelExpendedor extends JPanel {
    private Expendedor exp;
    private VidrioBebida vidrio;

    public PanelExpendedor(Expendedor exp) {
        this.exp = exp;
        this.setBackground(Color.LIGHT_GRAY);
        this.vidrio = new VidrioBebida(80, 80, 250, 350, this.exp);
    }

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
        g.setColor(Color.ORANGE); g.fillRect(360, 260, 30, 25);
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

    public int procesarClick(int x, int y) {
        if (x >= 360 && x <= 390) {
            if (y >= 190 && y <= 215) return 1; // CocaCola
            if (y >= 225 && y <= 250) return 2; // Sprite
            if (y >= 260 && y <= 285) return 3; // Fanta
            if (y >= 295 && y <= 320) return 4; // Snickers
            if (y >= 330 && y <= 355) return 5; // Super8
        }

        // vidrio
        if (y >= 110 && y <= 180) {
            if (x >= 100 && x <= 140) return 1;
            if (x >= 175 && x <= 215) return 2;
            if (x >= 250 && x <= 290) return 3;
        }
        if (y >= 270 && y <= 310) {
            if (x >= 100 && x <= 165) return 4;
            if (x >= 210 && x <= 275) return 5;
        }
        return -1;
    }
}