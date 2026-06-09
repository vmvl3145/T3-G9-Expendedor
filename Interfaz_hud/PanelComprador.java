package Interfaz_hud;
import javax.swing.JPanel;
import java.awt.*;

import logica.Expendedor;

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
        g.drawString("Comprar CocaCola", 60, 275);

        g.setColor(Color.GREEN.darker());
        g.fillRect(40, 300, 160, 40);
        g.setColor(Color.WHITE);
        g.drawString("Comprar Sprite", 70, 325);

        g.setColor(Color.ORANGE);
        g.fillRect(40, 200, 160, 40);
        g.setColor(Color.WHITE);
        g.drawString("Comprar Fanta", 75, 225);

    }

    public void procesarClick(int x, int y) {
        System.out.println("Clic recibido en el Comprador en (X:" + x + " Y:" + y + ")");
    }
}