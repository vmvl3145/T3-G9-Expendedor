package Productos.Dulce;
/** Clase concreta que representa un dulce Super8 */
public class Super8 extends Dulce {
    public Super8() {
        super();
    }
    /**
     * Implementación del consumo para Super8
     * @return El texto "super8"
     */
    @Override
    public String consumir() {
        return "super8";
    }
}