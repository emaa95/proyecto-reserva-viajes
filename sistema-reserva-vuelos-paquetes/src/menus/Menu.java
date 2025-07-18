package menus;
import javax.swing.JOptionPane;

public class Menu {

    public static void mostrarMenuPrincipal() {
        String[] opciones = {"Clientes", "Vendedor", "Informes", "Paquetes", "Reservas", "Vuelos", "Salir"};
        int opcion;
        do {
            opcion = JOptionPane.showOptionDialog(
                null,
                "Seleccione una opción:",
                "Menú Principal",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                opciones,
                opciones[0]
            );

            switch (opcion) {
                case 0:
                    // Llamar al menú de clientes
                    MenuClientes.mostrarMenu();
                    break;
                case 1:
                // Llamar al menú de vendedor
                    //JOptionPane.showMessageDialog(null, "Menú Vendedor seleccionado.");
                    MenuVendedor.mostrarMenu();
                    break;
                case 2:
                // Llamar al menú de informes
                    JOptionPane.showMessageDialog(null, "Menú Informes seleccionado.");
                    break;
                case 3:
                // Llamar al menú de paquetes
                    MenuPaquetes.mostrarMenu();
                    break;
                // Llamar al menú de reservas
                case 4:
                    MenuReservas.mostrarMenu();
                    break;
                // Llamar al menú de vuelos
                case 5:
                    MenuVuelos.mostrarMenu();
                    break;
                // Salir del sistema
                case 6:
                    JOptionPane.showMessageDialog(null, "Saliendo del sistema.");
                    break;
                default:
                    // Si se cierra la ventana, salir
                    opcion = 6;
            }
        } while (opcion != 6);
    }
}