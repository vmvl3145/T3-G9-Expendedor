package Productos.Dulce;
/** Clase concreta que representa un dulce Snickers */
public class Snickers extends Dulce {
    public Snickers(int serie) {
        super(serie);
    }
    /**
     * Implementación del consumo para Sniuckers
     * @return El texto "snickers"
     */
    @Override
    public String consumir() {
        return "snickers";
    }
}