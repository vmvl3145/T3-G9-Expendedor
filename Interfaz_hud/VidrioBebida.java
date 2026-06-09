package Interfaz_hud;
import javax.swing.ImageIcon;
import java.awt.*;

public class VidrioBebida {
    private int x, y, width, height;
    private Image imgCocaCola, imgSprite, imgFanta, imgSnickers, imgSuper8;

    public VidrioBebida(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        this.imgCocaCola = new ImageIcon("cocaCola.png").getImage();
        this.imgFanta = new ImageIcon("fanta.png").getImage();
        this.imgSuper8 = new ImageIcon("super8.png").getImage();
        this.imgSnickers = new ImageIcon("snickers.png").getImage();
        this.imgSprite = new ImageIcon("sprite.png").getImage();
    }
    public void paintComponent(Graphics g) {
        // vidrio
        g.setColor(new Color(173, 216, 230, 150));
        g.fillRect(this.x, this.y, this.width, this.height);

        // Repisas
        g.setColor(Color.GRAY);
        g.drawLine(this.x, this.y + 110, this.x + this.width, this.y + 110);
        g.drawLine(this.x, this.y + 240, this.x + this.width, this.y + 240);
        g.setFont(new Font("Arial", Font.BOLD, 14));

        // CocaCola (id 1)
        g.setColor(Color.RED); g.fillRect(this.x + 20, this.y + 30, 40, 70);
        g.drawImage(imgCocaCola, this.x + 20, this.y + 30, 40, 70, null);
        g.setColor(Color.BLACK); g.drawString("1", this.x + 35, this.y + 125);

        // Sprite (id 2)
        g.setColor(Color.GREEN.darker()); g.fillRect(this.x + 95, this.y + 30, 40, 70);
        g.drawImage(imgSprite, this.x + 95, this.y + 30, 40, 70, null);
        g.setColor(Color.BLACK); g.drawString("2", this.x + 110, this.y + 125);

        // Fanta (id 3)
        g.setColor(Color.ORANGE); g.fillRect(this.x + 170, this.y + 30, 40, 70);
        g.drawImage(imgFanta, this.x + 170, this.y + 30, 40, 70, null);
        g.setColor(Color.BLACK); g.drawString("3", this.x + 185, this.y + 125);

        // Snickers (id 4)
        g.setColor(new Color(101, 67, 33)); g.fillRect(this.x + 20, this.y + 190, 65, 35);
        g.drawImage(imgSnickers, this.x + 20, this.y + 190, 65, 35, null);
        g.setColor(Color.BLACK); g.drawString("4", this.x + 45, this.y + 255);

        // Super8 (id 5)
        g.setColor(Color.YELLOW.darker()); g.fillRect(this.x + 130, this.y + 190, 65, 35);
        g.drawImage(imgSuper8, this.x + 130, this.y + 190, 65, 35, null);
        g.setColor(Color.BLACK); g.drawString("5", this.x + 155, this.y + 255);
    }
}