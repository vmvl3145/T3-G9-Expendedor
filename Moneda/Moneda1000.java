package Moneda;
/** Clase para moneda 1000 pesos */
public class Moneda1000 extends Moneda {
    /** Constructor de clase - Llama a Constructor desde Moneda usando el super() */
    public Moneda1000() {
        super();
    }
    /** Sobreescritura para devolver el valor real de la moneda */
    @Override
    public int getValor() {
        return 1000;
    }
}