package logica;

import Moneda.*;
import Productos.*;
import Productos.Bebidas.*;
import Productos.Dulce.*;
import Excepciones.*;
import java.util.concurrent.ThreadLocalRandom;

/** Clase para manejo y gestión de depósitos junto almacenamiento de máquina Expendedora */
public class Expendedor {
    private Deposito<Producto> cocacola;
    private Deposito<Producto> sprite;
    private Deposito<Producto> fanta;
    private Deposito<Producto> snickers;
    private Deposito<Producto> super8;
    /** Depósito para monedas que luego se retornan en el vuelto*/
    private Deposito<Moneda> montoVuelto;
    private Deposito<Moneda> montoCompra;

    /** Deposito de producto unico */
    private Producto productoComprado;

    /** Constructor para inicializar depósitos y stock de la máquina
     * @param n Cantidad de productos de cada tipo que tendrá la máquina en el comienzo
     */
    public Expendedor(int n) {
        /** Inicio de depósitos genéricos */
        this.cocacola = new Deposito<>();
        this.sprite = new Deposito<>();
        this.fanta = new Deposito<>();
        this.snickers = new Deposito<>();
        this.super8 = new Deposito<>();
        this.montoVuelto = new Deposito<>();
        this.montoCompra = new Deposito<>();
        this.productoComprado = null;

        /** Llenado de máquina con n productos por cada clase */
        for (int i = 0; i < n; i++) {

            // Generada numero aleatorio para series de productos
            int min = 100;
            int max = 999;

            int numeroAleatorio1 = ThreadLocalRandom.current().nextInt(min,max + 1);
            int numeroAleatorio2 = ThreadLocalRandom.current().nextInt(min,max + 1);
            int numeroAleatorio3 = ThreadLocalRandom.current().nextInt(min,max + 1);
            int numeroAleatorio4 = ThreadLocalRandom.current().nextInt(min,max + 1);
            int numeroAleatorio5 = ThreadLocalRandom.current().nextInt(min,max + 1);

            cocacola.add(new Cocacola(numeroAleatorio1));
            sprite.add(new Sprite(numeroAleatorio2));
            fanta.add(new Fanta(numeroAleatorio3));
            snickers.add(new Snickers(numeroAleatorio4));
            super8.add(new Super8(numeroAleatorio5));
        }
    }


 /** @param seleccion Numero del deposito elegido
 * @return La Enumeracion correspondiente
 */
private Enumeracion getEnumeracion(int seleccion) {
    switch (seleccion) {
        case 1: return Enumeracion.COCA_COLA;
        case 2: return Enumeracion.SPRITE;
        case 3: return Enumeracion.FANTA;
        case 4: return Enumeracion.SNICKERS;
        case 5: return Enumeracion.SUPER8;
        default: throw new IllegalArgumentException("Numero de deposito invalido.");
    }
}

/**devuelve el deposito correspondiente al producto seleccionado
 * @param seleccion enum del producto
 * @return deposito correspondiente
 */
private Deposito<Producto> getDeposito(Enumeracion seleccion) {
    switch (seleccion) {
        case COCA_COLA: return cocacola;
        case SPRITE:    return sprite;
        case FANTA:     return fanta;
        case SNICKERS:  return snickers;
        case SUPER8:    return super8;
        default: throw new IllegalArgumentException("Producto no reconocido.");
    }
}

    /**
     * Intenta realizar la compra de un producto.
     * Verifica la validación del pago, la existencia de stock y gestiona el vuelto.
     * @param moneda Moneda entregada por el usuario para el pago.
     * @param numero Indice del deposito del producto deseado.
     * @throws PagoIncorrectoException Si se intenta pagar con una moneda nula
     * @throws NoHayProductoException Si el deposito seleccionado no tiene stock
     * @throws PagoInsuficienteException Si el valor de la moneda es menor al precio del producto
     */
public void comprarProducto(Moneda moneda, int numero)
        throws PagoIncorrectoException, NoHayProductoException, PagoInsuficienteException {

    if (moneda == null) {
        throw new PagoIncorrectoException("No se ha pagado.");
    }

    Enumeracion seleccion = getEnumeracion(numero);

    // Verificar stock
    Deposito<Producto> deposito = getDeposito(seleccion);
    Producto producto = deposito.get();
    if (producto == null) {
        montoVuelto.add(moneda);
        throw new NoHayProductoException("No queda " + seleccion.getNombre());
    }
    // Verificar si el dinero alcanza
    if (moneda.getValor() < seleccion.getPrecio()) {
        montoVuelto.add(moneda); // Se devuelve la moneda ingresada
        throw new PagoInsuficienteException("Dinero insuficiente para " + seleccion.getNombre());
    }

    // Si la compra es exitosa...
    montoCompra.add(moneda);

    this.productoComprado = producto;

    int vuelto = moneda.getValor() - seleccion.getPrecio();
    while(vuelto > 0) {
        int serieAleatoria = ThreadLocalRandom.current().nextInt(100, 1000);

        if (vuelto >= 1000) {
            montoVuelto.add(new Moneda1000(serieAleatoria));
            vuelto -= 1000;
        } else if (vuelto >= 500) {
            montoVuelto.add(new Moneda500(serieAleatoria));
            vuelto -= 500;
        } else if (vuelto >= 100) {
            montoVuelto.add(new Moneda100(serieAleatoria));
            vuelto -= 100;
        }
    }
}

// Getter Producto
public Producto getProducto() {
    Producto aux = this.productoComprado;
    this.productoComprado = null;
    return aux;
}

//Vuelto
public Moneda getVuelto() {
    return montoVuelto.get();
}
}