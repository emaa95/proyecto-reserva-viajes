package menus;
import javax.swing.JOptionPane;

public class Menu {

    public static void mostrarMenuPrincipal() {
        String[] opciones = {"Clientes", "Informes", "Paquetes", "Reservas", "Vuelos", "Salir"};
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
                // Llamar al menú de informes
                    JOptionPane.showMessageDialog(null, "Menú Informes seleccionado.");
                    break;
                case 2:
                // Llamar al menú de paquetes
                    JOptionPane.showMessageDialog(null, "Menú Paquetes seleccionado.");
                    break;
                // Llamar al menú de reservas
                case 3:
                    JOptionPane.showMessageDialog(null, "Menú Reservas seleccionado.");
                    break;
                // Llamar al menú de vuelos
                case 4:
                    MenuVuelos.mostrarMenu();
                    break;
                // Salir del sistema
                case 5:
                    JOptionPane.showMessageDialog(null, "Saliendo del sistema.");
                    break;
                default:
                    // Si se cierra la ventana, salir
                    opcion = 5;
            }
        } while (opcion != 5);
    }
}