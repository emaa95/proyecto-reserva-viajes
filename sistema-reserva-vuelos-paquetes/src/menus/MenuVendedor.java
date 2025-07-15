package menus;

import javax.swing.JOptionPane;

import clases.Vendedor;
import utils.inputOutputJOP.Ingreso;

public class MenuVendedor {
    public static void mostrarMenu() {
        int opcion;
        do {
            String menu = "MENÚ VENDEDOR\n"
                        + "1. Registrar un vendedor\n"
                        + "2. Editar vendedor\n"
                        + "3. Eliminar vendedor\n"
                        + "4. Listar todos los vendedores\n"
                        + "5. Salir";
            opcion = Integer.parseInt(JOptionPane.showInputDialog(menu));

            switch (opcion) {
                case 1:
                    Vendedor.cargarVendedor();
                    break;
                case 2:
                // Verificar si hay vendedores registrados antes de editar
                    if (Vendedor.getCantidad() == 0) {
                        JOptionPane.showMessageDialog(null, "No hay vendedores registrados.");
                        break;
                    }
                    Vendedor.editarVendedor();
                    break;
                case 3:
                // Verificar si hay vendedores registrados antes de eliminar
                    if (Vendedor.getCantidad() == 0) {
                        JOptionPane.showMessageDialog(null, "No hay vendedores registrados.");
                        break;
                    }
                    Vendedor.eliminarVendedor();
                    break;
                case 4:
                    Vendedor.listarVendedores();
                    break;
                case 5:
                    JOptionPane.showMessageDialog(null, "Saliendo del menú de vendedores.");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción no válida.");
            }
        } while (opcion != 5);
    }
}
