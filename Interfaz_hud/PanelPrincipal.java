package interfaz_hud;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;

import Interfaz_hud.PanelComprador;
import Interfaz_hud.PanelExpendedor;
import logica.Expendedor;

public class PanelPrincipal extends JPanel {
    private PanelComprador com;
    private PanelExpendedor exp;
    private Expendedor expendedorLogico;
    public PanelPrincipal() {
        this.expendedorLogico = new Expendedor(3);
        this.com = new PanelComprador();
        this.exp = new PanelExpendedor();
        this.setBackground(Color.white);
        this.setLayout(new GridLayout(1, 2));
        this.add(com);
        this.add(exp);
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}