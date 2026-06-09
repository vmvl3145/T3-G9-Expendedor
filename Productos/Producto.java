package Productos;
/**
 * Clase abstracta que representa un producto genérico de la maquina expendedora*/
public abstract class Producto {
    public Producto() {
    }
    /** Método abstracto para asignar a cada producto decir qué es
     * @return El nombre o sabor de producto
     */
    public abstract String consumir();
}