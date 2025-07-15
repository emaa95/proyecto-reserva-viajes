package menus;

import javax.swing.JOptionPane;
import utils.inputOutputJOP.Ingreso;
import utils.EliminarPorId;

import clases.Vuelo;

public class MenuVuelos {
        public static void mostrarMenu() {
            int opcion = 0;
            do {
                String menu = "MENÚ VUELOS\n"
                            + "1. Registrar nuevo vuelo\n"
                            + "2. Modificar datos de vuelo\n"
                            + "3. Eliminar vuelo\n"
                            + "4. Listar todos los vuelos\n"
                            + "5. Buscar vuelo por número\n"
                            + "6. Salir";
                opcion = Ingreso.leerEnteroPositivo(menu);
                
                switch (opcion) {
                    case 1:
                        Vuelo.cargarVuelo();
                        break;
                    case 2:
                        Vuelo.editarVuelo();
                        break;
                    case 3:
                        String idEliminar = Ingreso.leerString("Ingrese ID del vuelo a eliminar:");
                        boolean eliminado = EliminarPorId.eliminarPorId(idEliminar, Vuelo.getVuelos(), new int[]{Vuelo.getCantidad()});
                        //Actualizar la cantidad
                        if (eliminado) {
                            Vuelo.setCantidad(Vuelo.getCantidad() - 1);
                        }
                        break;
                    case 4:
                        Vuelo.verTodosLosVuelos();
                        break;
                    case 5:
                        String idBuscar = Ingreso.leerString("Ingrese ID del vuelo a buscar:");
                        Vuelo.buscarYMostrarVueloPorId(idBuscar);
                        break;
                    case 6:
                        JOptionPane.showMessageDialog(null, "Saliendo del menú de vuelos.");
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Opción no válida.");
                }
            } while (opcion != 6);
        }
}
