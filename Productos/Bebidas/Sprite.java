package Productos.Bebidas;
/** Clase concreta que representa una bebida Sprite */
public class Sprite extends Bebida {
    public Sprite() {
        super();
    }
    /**
     * Implementación del consumo para Sprite
     * @return El texto "sprite"
     */
    @Override
    public String consumir() {
        return "sprite";
    }
}