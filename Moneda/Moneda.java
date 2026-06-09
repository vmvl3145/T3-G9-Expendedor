package Moneda;

/**
 * Clase abstracta para Monedas e implementa Comparable para permitir que las monedas se ordenen por su valor.
 */
public abstract class Moneda implements Comparable<Moneda> {
    public Moneda() {
    }

    /**
     * Define que todas las monedas deben tener algún valor
     * @return El valor entero de la moneda */
    public abstract int getValor();

    /**
     * @param m La moneda con la que se desea comparar
     * @return Un entero negativo, cero o positivo según si esta moneda es menor, igual o mayor, declarando posición en Array
     */
    @Override
    public int compareTo(Moneda m) {
        if (m == null) {
            return 1;
        }
        return Integer.compare(this.getValor(), m.getValor());
    }
    /**
     * Devuelve una representación en texto de la moneda
     * @return String con el valor y el número de serie único
     */
    @Override
    public String toString() {
        return "Moneda de $" + this.getValor() + "(Serie: " + this.hashCode() + ")";
    }
}