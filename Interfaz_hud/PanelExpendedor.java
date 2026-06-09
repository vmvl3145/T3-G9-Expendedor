package Interfaz_hud;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import logica.Expendedor;

public class PanelExpendedor extends JPanel {
    private Expendedor exp;

    public PanelExpendedor(Expendedor exp) {
        this.exp = exp;
        this.setBackground(Color.LIGHT_GRAY);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // maquina
        g.setColor(Color.DARK_GRAY);
        g.fillRect(50, 50, 400, 550);

        // vidrio
        g.setColor(new Color(173, 216, 230));
        g.fillRect(80, 80, 250, 350);

        // monedas y numpad
        g.setColor(Color.BLACK);
        g.fillRect(360, 150, 10, 40);
        g.fillRect(150, 480, 120, 80);
    }

    public void procesarClick(int x, int y) {
        System.out.println("Click recibido en el Expendedor en (X:" + x + " Y:" + y + ")");
    }
}