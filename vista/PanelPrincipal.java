package vista;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import logica.Expendedor;
/** Panel contenedor principal que actúa como el orquestador del MVC en la vista.
 * Implementa MouseListener para centralizar la captura de clics y coordina la comunicación directa entre el PanelExpendedor y PanelComprador. */
public class PanelPrincipal extends JPanel implements MouseListener {
    private PanelComprador com;
    private PanelExpendedor exp;
    private Expendedor expendedorLogico;

    /** Constructor que inicializa el modelo lógico y los subpaneles visuales, dividiendo la pantalla simétricamente en dos columnas mediante un GridLayout. */
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

    /** Renderiza los componentes gráficos del panel.
     * @param g El contexto gráfico utilizado para pintar */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
    /** Evento invocado cuando el mouse ha sido clickeado. No se utiliza para garantizar precisión frente a arrastres involuntarios del cursor.
     * @param e El evento del mouse. */
    @Override
    public void mouseClicked(MouseEvent e) {}

    /** Captura y procesa de manera instantánea el descenso del botón del mouse.
     * Determina en qué parte de la pantalla ocurrió el clic y delega la acción al componente correspondiente (compra, reabastecimiento o selección de monedas).
     * @param e El evento del mouse que gatilla la acción. */
    @Override
    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        int mitad = this.getWidth() / 2;

        if (x < mitad) {
            int idSeleccionado = exp.procesarClick(x, y);
            if (idSeleccionado > 0) {
                com.efectuarCompra(idSeleccionado);
            } else if (idSeleccionado == -2) {
                this.expendedorLogico.rellenarExpendedor();
                System.out.println("Máquina reabastecida.");
            }
        } else {
            com.procesarClick(x - mitad, y);
        }
        this.repaint();
    }

    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}
}