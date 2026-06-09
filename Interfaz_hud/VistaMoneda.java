package Interfaz_hud;
import java.awt.*;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class VistaMoneda extends JPanel {
    private int x;
    private int y;
    private int valor;
    private Image imagen;
    private int serie;

    public VistaMoneda(int x, int y, int valor, int serie) {
        this.x = x;
        this.y = y;
        this.valor = valor;
        this.serie = serie;
        String address = "recursos/moneda" + this.valor + ".png";
        this.imagen = new ImageIcon(address).getImage();
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(this.imagen, this.x, this.y + 10, 60, 60, this);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 11));
        g.drawString("$" + this.valor, this.x + 15, this.y + 85);
        g.drawString("Serie:" + this.serie, this.x + 3, this.y + 5);
    }

    public int getX() { return this.x; }
    public int getY() { return this.y; }
    public int getValor() { return this.valor; }
}