import Excepciones.*;
import Moneda.*;
import java.util.Scanner;

/**
 * Clase Principal interactiva que simula el uso de una maquina expendedora.
 * <p>
 *     Esta clase proporciona una interfaz de consola (CLI) donde el usuario
 *     selecciona un producto, luego elige la moneda para pagar y visualiza el resultado.
 *     No existe saldo previo ni almacenamiento de monedas entre sesiones.
 * </p>
 */
public class MainInteractivo {

    /**
     * Metodo principal que inicia el bucle de interaccion con el usuario.
     * <p>
     *     Gestiona la entrada por teclado, instanciando un expendedor con stock inicial.
     *     En cada transaccion se selecciona primero el producto y luego la moneda.
     *     Captura excepciones de tipo {@link PagoIncorrectoException},
     *     {@link PagoInsuficienteException} y {@link NoHayProductoException}.
     * </p>
     * @param args Argumentos de la linea de comandos.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Instancia un expendedor con 5 unidades en stock
        Expendedor expendedor = new Expendedor(5);
        boolean maquinaFuncionando = true;

        System.out.println("¡BIENVENIDO A LA MÁQUINA EXPENDEDORA!");
        System.out.println("=========================================");

        while(maquinaFuncionando) {
            System.out.println("\nMENU PRINCIPAL");
            System.out.println("1. Comprar Producto.");
            System.out.println("0. Salir.");

            System.out.print("\nElige una opción: ");
            int opcionMenu = scanner.nextInt();

            if(opcionMenu == 0) {
                System.out.println("Muchas gracias por haber usado nuestros servicios.");
                System.out.println("Apagando máquina...");
                maquinaFuncionando = false;
            } else if(opcionMenu == 1) {

                // 1. SELECCIÓN DE PRODUCTO PRIMERO
                System.out.println("\n===== VITRINA =====");
                System.out.println("1. Coca Cola. $1000");
                System.out.println("2. Sprite. $900");
                System.out.println("3. Fanta. $900");
                System.out.println("4. Snickers. $600");
                System.out.println("5. Super 8. $300");
                System.out.println("======================");

                System.out.print("Que deseas comprar? ");
                int eleccionProducto = scanner.nextInt();

                if(eleccionProducto < 1 || eleccionProducto > 5) {
                    System.out.println("Producto Invalido.");
                    continue;
                }

                // 2. SELECCIÓN DE MONEDA (SIN SALDO PREVIO)
                System.out.println("\nQué moneda deseas utilizar?");
                System.out.println("1. $100");
                System.out.println("2. $500");
                System.out.println("3. $1000");
                System.out.println("4. Intentar pagar sin moneda (Prueba de error)");

                System.out.print("Tu elección: ");
                int eleccionMoneda = scanner.nextInt();
                Moneda monedaInsertada = null;

                switch (eleccionMoneda) {
                    case 1 -> monedaInsertada = new Moneda100();
                    case 2 -> monedaInsertada = new Moneda500();
                    case 3 -> monedaInsertada = new Moneda1000();
                    case 4 -> monedaInsertada = null;
                    default -> {
                        System.out.println("Opción inválida. Se procesara como pago nulo.");
                        monedaInsertada = null;
                    }
                }

                System.out.println("\nProcesando compra...");

                try {
                    /** El Comprador ejecuta la compra y retira todo el vuelto internamente */
                    Comprador comprador = new Comprador(monedaInsertada, eleccionProducto, expendedor);

                    System.out.println("¡Compra Exitosa!");
                    System.out.println("Consumiste: " + comprador.getSaborConsumido());
                    System.out.println("La máquina te devuelve tu vuelto de: $" + comprador.getVueltoTotal());

                } catch (PagoInsuficienteException | PagoIncorrectoException | NoHayProductoException e) {
                    System.out.println("ERROR: " + e.getMessage());
                    // Nota: Si el pago falla, el comprador retira la moneda del depósito de vuelto
                } catch (Exception e) {
                    System.out.println("ERROR CRÍTICO: " + e);
                }
            } else {
                System.out.println("Opción de menú no válida.");
            }
        }
        scanner.close();
    }
}