package Excepciones;

/** Exception que se lanza cuando el pago recibido es null */
public class PagoIncorrectoException extends Exception {
    public PagoIncorrectoException(String mensaje) {
        super(mensaje);
    }
}