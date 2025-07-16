package menus;

import javax.swing.JOptionPane;

import clases.Cliente;

public class MenuClientes {
    public static void mostrarMenu() {
        int opcion;
        do {
            String menu = "MENÚ CLIENTES\n"
                    + "1. Registrar un cliente\n"
                    + "2. Editar cliente\n"
                    + "3. Eliminar cliente\n"
                    + "4. Listar todos los clientes\n"
                    + "5. Salir";
            opcion = Integer.parseInt(JOptionPane.showInputDialog(menu));

            switch (opcion) {
                case 1:
                    Cliente.cargarCliente();
                    break;
                case 2:
                    Cliente.editarCliente();
                    break;
                case 3:
                    Cliente.eliminarCliente();
                case 4:
                    Cliente.verTodosLosClientes();
                    break;
                case 5:
                    JOptionPane.showMessageDialog(null, "Saliendo del menú de clientes.");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción no válida.");
            }
        } while (opcion != 5);
    }

}
