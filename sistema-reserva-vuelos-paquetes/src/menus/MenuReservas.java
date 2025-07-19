package menus;

import javax.swing.JOptionPane;
import clases.Reserva;

public class MenuReservas {

    public static void mostrarMenu() {
        int opcion;

        do {
            opcion = mostrarOpciones();

            switch (opcion) {
                case 1:
                    Reserva.cargarReserva();
                    break;
                case 2:
                    Reserva.modificarReserva();
                    break;
                case 3:
                    Reserva.verTodasLasReservas();
                    break;
                case 4:
                    JOptionPane.showMessageDialog(null, "Saliendo del menú de reservas...");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida. Intente de nuevo.");
            }

        } while (opcion != 4);
    }

    private static int mostrarOpciones() {
        String[] opciones = {
            "1. Cargar nueva reserva",
            "2. Editar reserva",
            "3. Ver todas las reservas",
            "4. Salir"
        };

        String menu = "MENÚ DE RESERVAS\n\n";
        for (String op : opciones) {
            menu += op + "\n";
        }

        String input = JOptionPane.showInputDialog(menu + "\nSeleccione una opción:");
        
        if (input == null) return 4; // Si presiona "Cancelar"

        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return -1; // Opción inválida
        }
    }
}
