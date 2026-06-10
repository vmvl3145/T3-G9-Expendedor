package logica;

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
    /**
     * Devuelve la cantidad actual de elementos en el depósito.
     * @return Cantidad de elementos
     */
    public int size() {
        return this.lista.size();
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

    /**
     * Permite examinar un elemento en una posición específica sin removerlo.
     * @param index Posición del elemento
     * @return El objeto en dicha posición o null si está fuera de rango
     */
    public T getElemento(int index) {
        if (index >= 0 && index < this.lista.size()) {
            return this.lista.get(index);
        }
        return null;
    }

}

