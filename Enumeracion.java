/** Enumeración que contiene precio de los productos y nombres correspondientemente */
public enum Enumeracion {
    COCA_COLA(1000, "CocaCola"),
    SPRITE(900, "Sprite"),
    FANTA(900, "Fanta"),
    SNICKERS(600, "Snickers"),
    SUPER8(300, "Super8");

    private final int precio;
    private final String nombre;

    /**
     * Constructor Enum
     * @param precio El costo del producto
     * @param nombre El nombre identificador del producto
     */
    Enumeracion(int precio, String nombre) {
        this.precio = precio;
        this.nombre = nombre;
    }
    /**
     * Entrega el precio del producto seleccionado.
     * @return El precio entero del producto.
     */
    public int getPrecio() {
        return precio;
    }
    /**
     * Entrega el nombre del producto seleccionado.
     * @return El String con el nombre del producto.
     */
    public String getNombre() {
        return nombre;
    }
}