package Productos.Bebidas;
/** Clase concreta que representa una bebida Fanta */
public class Fanta extends Bebida {
    public Fanta() {
        super();
    }
    /**
     * Implementación del consumo para Fanta
     * @return El texto "fanta"
     */
    @Override
    public String consumir() {
        return "fanta";
    }
}