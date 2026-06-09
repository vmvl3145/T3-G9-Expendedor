package Productos.Bebidas;
/** Clase concreta que representa una bebida Coca-Cola */
public class Cocacola extends Bebida {
    public Cocacola(int serie) {
        super(serie);
    }
    /**
     * Implementación del consumo para CocaCola
     * @return El texto "cocacola"
     */
    @Override
    public String consumir() {
        return "cocacola";
    }
}