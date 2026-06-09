package Excepciones;

/** Exception que se lanza cuando ya no existe/hay stock */
public class NoHayProductoException extends Exception {
    public NoHayProductoException(String mensaje) {
        super(mensaje);
    }
}