import Moneda.*;
import Excepciones.*;

/**
 * Clase que representa a un cliente de la máquina expendedora.
 * El comprador interactúa con el expendedor entregando una moneda y recibiendo un producto junto con su vuelto.
 */
public class Comprador {
    private String saborConsumido;
    private int vueltoTotal;

    /**
     * Constructor que realiza la accion de compra.
     * Intenta obtener un producto y retira todo el vuelto disponible,
     * incluso si la compra falla por falta de stock o dinero
     *
     * @param moneda La moneda con la que se desea realizar el pago
     * @param seleccion El codigo numerico del producto a comprar
     * @param expendedor La instancia de la maquina donde se realiza la compra
     * @throws PagoIncorrectoException Si la moneda ingresada es nula
     * @throws NoHayProductoException Si el deposito del producto esta vacio
     * @throws PagoInsuficienteException Si el valor de la moneda no alcanza para el producto
     */
    public Comprador(Moneda moneda, int seleccion, Expendedor expendedor)
            throws PagoIncorrectoException, NoHayProductoException, PagoInsuficienteException {

        this.saborConsumido = null;
        this.vueltoTotal = 0;

        try {
            /** ejecutar compra y consumir producto haciendo una cadena (no se guarda como atributo) */
            this.saborConsumido = expendedor.comprarProducto(moneda, seleccion).consumir();
        } catch (NoHayProductoException | PagoInsuficienteException e) {
            /** si fallo, retirar la moneda devuelta y relanzar al main */
            Moneda monedaVuelto = expendedor.getVuelto();
            while (monedaVuelto != null) {
                this.vueltoTotal += monedaVuelto.getValor();
                monedaVuelto = expendedor.getVuelto();
            }
            throw e;
        }

        /** retirar todo el vuelto y sumar el total */
        Moneda monedaVuelto = expendedor.getVuelto();
        while (monedaVuelto != null) {
            this.vueltoTotal += monedaVuelto.getValor();
            monedaVuelto = expendedor.getVuelto();
        }
    }

    public String getSaborConsumido() { return saborConsumido; }
    public int getVueltoTotal() { return vueltoTotal; }
}