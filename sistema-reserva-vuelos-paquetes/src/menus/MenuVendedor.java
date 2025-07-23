package menus;

import javax.swing.JOptionPane;

import clases.Vendedor;
import utils.inputOutputJOP.Ingreso;
import utils.inputOutputJOP.Salida;

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
            opcion = Ingreso.leerEnteroPositivo(menu);

            switch (opcion) {
                case 1:
                    Vendedor.cargarVendedor();
                    break;
                case 2:
                    Vendedor.editarVendedor();
                    break;
                case 3:
                    Vendedor.eliminarVendedor();
                    break;
                case 4:
                    Vendedor.listarVendedores();
                    break;
                case 5:
                    Salida.mMensaje("Saliendo del menú de vendedores", "Salir");
                    break;
                default:
                    Salida.mError("Opción no válida", "Error");
            }
        } while (opcion != 5);
    }
}
