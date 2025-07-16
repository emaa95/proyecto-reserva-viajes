package menus;

import javax.swing.JOptionPane;

import clases.PaqueteTuristico;

public class MenuPaquetes {

    public static void mostrarMenu() {
        int opcion;
        do {
            String menu = "MENÚ PAQUETES TURÍSTICOS\n"
                    + "1. Registrar un paquete\n"
                    + "2. Editar paquete\n"
                    + "3. Eliminar paquete\n"
                    + "4. Listar todos los paquetes\n"
                    + "5. Salir";
            opcion = Integer.parseInt(JOptionPane.showInputDialog(menu));

            switch (opcion) {
                case 1:
                    PaqueteTuristico.cargarPaquete();
                    break;
                case 2:
                    PaqueteTuristico.editarPaquete();
                    break;
                case 3:
                    PaqueteTuristico.eliminarPaquete();
                    break;
                case 4:
                    PaqueteTuristico.verTodosLosPaquetes();
                    break;
                case 5:
                    JOptionPane.showMessageDialog(null, "Saliendo del menú de paquetes.");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción no válida.");
            }
        } while (opcion != 5);
    }

}
