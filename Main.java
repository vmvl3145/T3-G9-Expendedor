import Excepciones.*;
import Moneda.*;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        /* Configuramos el entorno, haciendo un expendedor que retorne 1 objeto de cada bebida, dulce
         */
        Expendedor exp = new Expendedor(1);

        /* PRUEBA 1: COMPRADOR TRATA DE COMPRAR SIN MONEDA
        DEBERIA RETORNAR PagoIncorrectoException
         */
        System.out.println("PRUEBA 1: PagoIncorrectoException");
        try {
            Comprador c1 = new Comprador(null, 1, exp);
        } catch (PagoIncorrectoException e) {
            System.out.println("Excepción capturada: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error inesperado" + e);
        }

        /* PRUEBA 2: COMPRADOR TRATA DE COMPRAR CON MONEDA DE VALOR INSUFICIENTE
        DEBERIA RETORNAR MISMA MONEDA, NO PRODUCTO
         */
        System.out.println("\nPRUEBA 2: PagoInsuficienteException");
        try {
            Moneda m1 = new Moneda100();
            Comprador c2 = new Comprador(m1, 1, exp);
        } catch (PagoInsuficienteException e) {
            System.out.println("Excepcion capturada: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error inesperado" + e);
        }

        /* PRUEBA 3: COMPRADOR TRATA DE COMPRAR PRODUCTO INEXISTENTE
        DEBERIA RETORNAR NoHayProductoException AL QUEDAR SIN STOCK
         */
        System.out.println("\nPRUEBA 3: NoHayProductoException");
        try {
            Moneda m2 = new Moneda1000();
            Moneda m3 = new Moneda1000();

            // Compra 1 (exitosa)
            Comprador c3 = new Comprador(m2, 1, exp);
            System.out.println("Comprador 3 consumido: " + c3.getSaborConsumido());

            // Compra 2 (deberia lanzar exception)
            Comprador c4 = new Comprador(m3, 1, exp);
        } catch (NoHayProductoException e) {
            System.out.println("Excepción capturada: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e);
        }

        /* EL SIGUIENTE CODIGO ORDENARA LAS MONEDAS EN UNA LISTA
        USANDO EL METODO *.sort()

        PARA ESO SE HARA UNA PRUEBA, AL IGUAL QUE EN DONDE LAS EXCEPCIONES ANTERIORES
         */

        System.out.println("\nPRUEBA MONEDAS ORDENADAS");
        ArrayList<Moneda> listaM = new ArrayList<>();
        listaM.add(new Moneda1000());
        listaM.add(new Moneda100());
        listaM.add(new Moneda500());

        System.out.println("Lista Monedas Original: ");
        for(Moneda m : listaM) {
            System.out.println("$" + m.getValor());
        }

        // Ordenamos Lista
        Collections.sort(listaM);

        System.out.println("Lista Monedas Ordenada: ");
        for(Moneda m : listaM) {
            System.out.println("$" + m.getValor());
        }
    }
}