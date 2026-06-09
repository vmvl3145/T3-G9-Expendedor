package Moneda;
/** Clase para moneda 100 pesos */
public class Moneda100 extends Moneda {
    /** Constructor de clase - Llama a Constructor desde Moneda usando el super() */
    public Moneda100() {
        super();
    }
    /** Sobreescritura para devolver el valor real de la moneda */
    @Override
    public int getValor() {
        return 100;
    }
}