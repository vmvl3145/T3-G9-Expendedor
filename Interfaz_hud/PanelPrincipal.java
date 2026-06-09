package Interfaz_hud;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import logica.Expendedor;

public class PanelPrincipal extends JPanel implements MouseListener {
    private PanelComprador com;
    private PanelExpendedor exp;
    private Expendedor expendedorLogico;

    public PanelPrincipal() {
        this.expendedorLogico = new Expendedor(3);
        this.com = new PanelComprador(this.expendedorLogico);
        this.exp = new PanelExpendedor(this.expendedorLogico);
        this.setBackground(Color.white);
        this.setLayout(new GridLayout(1, 2));
        this.add(exp);
        this.add(com);
        this.addMouseListener(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        int mitad = this.getWidth() / 2;

        if (x < mitad) {
            int idSeleccionado = exp.procesarClick(x, y);
            if (idSeleccionado > 0) {
                com.efectuarCompra(idSeleccionado);
            }
        } else {
            com.procesarClick(x - mitad, y);
        }
        this.repaint();
    }

    @Override public void mousePressed(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}
}