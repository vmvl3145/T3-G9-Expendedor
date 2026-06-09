package Excepciones;

/** Exception que se lanza cuando el valor de la moneda es menor al precio del producto. */
public class PagoInsuficienteException extends Exception {
    public PagoInsuficienteException(String mensaje) {
        super(mensaje);
    }
}