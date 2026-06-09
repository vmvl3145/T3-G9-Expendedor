package Productos.Bebidas;
import Productos.Producto;

/**
 * Clase abstracta de bebida.
 */
public abstract class Bebida extends Producto {
    /**
    @param serie Numero de serie unico (lo va a asignar expendendor)
     */
    public Bebida(int serie) {
        super(serie);
    }
}