package Productos.Dulce;
import Productos.Producto;

/**
Clase abstracta dulce.
 */
public abstract class Dulce extends Producto {

    /**
     * @param serie Numero de serie asignado x expendedor
     */
    public Dulce(int serie) {
        super(serie);
    }
}