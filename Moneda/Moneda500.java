package Moneda;
/** Clase para moneda 500 pesos */
public class Moneda500 extends Moneda {
    /** Constructor de clase - Llama a Constructor desde Moneda usando el super() */
    public Moneda500() {
        super();
    }
    /** Sobreescritura para devolver el valor real de la moneda */
    @Override
    public int getValor() {
        return 500;
    }
}