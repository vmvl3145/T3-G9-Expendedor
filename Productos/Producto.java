package Productos;

/**
 * Clase abstracta que representa un producto genérico de la maquina expendedora.
 */
public abstract class Producto {

    private int numeroDeSerie;

    /**
     @param serie
     */
    public Producto(int serie) {
        this.numeroDeSerie = serie;
    }

    /**
    @return numero de serie
     */
    public int getNumeroDeSerie() {
        return numeroDeSerie;
    }
        /**
    @return bebida
     */
    public abstract String consumir();

}