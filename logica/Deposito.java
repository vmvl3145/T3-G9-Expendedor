import java.util.ArrayList;
/**
 * - Clase genérica con Deposito<T>.
 * - Se implementa estructura FIFO
 * @param <T> Tipo de objeto a almacenar (Producto o Moneda)
 */
public class Deposito<T> {
    private final ArrayList<T> lista;

    public Deposito() {
        this.lista = new ArrayList<>();
    }

    /** Añade un objeto al final del depósito
     * @param item Objeto a añadir
     */
    public void add(T item) {
        if (item != null) {
            lista.add(item);
        }
    }

    /** Retira y devuelve el primero objeto dentro de Deposito
     * @return El objeto en el índice 0 o si no, null si Deposito esta vacío
     */
    public T get() {
        if (lista.isEmpty()) {
            return null;
        }
        return lista.remove(0);
    }
}

