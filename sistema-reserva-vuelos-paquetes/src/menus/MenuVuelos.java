package menus;

import javax.swing.JOptionPane;
import utils.inputOutputJOP.Ingreso;
import clases.Vuelo;

public class MenuVuelos {
        public static void mostrarMenu() {
        int opcion;
        do {
            String menu = "MENÚ VUELOS\n"
                        + "1. Registrar nuevo vuelo\n"
                        + "2. Modificar datos de vuelo\n"
                        + "3. Eliminar vuelo\n"
                        + "4. Listar todos los vuelos\n"
                        + "5. Buscar vuelo por número\n"
                        + "6. Salir";
            opcion = Integer.parseInt(JOptionPane.showInputDialog(menu));

             switch (opcion) {
                case 1:
                    int idVuelo = Ingreso.leerEntero("Ingrese ID de vuelo:");
                    String origen = Ingreso.leerString("Ingrese origen:");
                    String destino = Ingreso.leerString("Ingrese destino:");
                    String fechaSalida = Ingreso.leerString("Ingrese fecha de salida:");
                    double precio = Ingreso.leerDoublePositivo("Ingrese precio:");
                    int duracionHoras = Ingreso.leerEntero("Ingrese duración en horas:");
                    Vuelo.cargarVuelo(idVuelo, origen, destino, fechaSalida, precio, duracionHoras);
                    break;
                case 2:
                    int idEditar = Ingreso.leerEntero("Ingrese ID del vuelo a modificar:");
                    String nuevoOrigen = Ingreso.leerString("Ingrese nuevo origen:");
                    String nuevoDestino = JOptionPane.showInputDialog("Ingrese nuevo destino:");
                    String nuevaFecha = JOptionPane.showInputDialog("Ingrese nueva fecha de salida:");
                    double nuevoPrecio = Double.parseDouble(JOptionPane.showInputDialog("Ingrese nuevo precio:"));
                    int nuevaDuracion = Integer.parseInt(JOptionPane.showInputDialog("Ingrese nueva duración en horas:"));
                    Vuelo.editarVuelo(idEditar, nuevoOrigen, nuevoDestino, nuevaFecha, nuevoPrecio, nuevaDuracion);
                    break;
                case 3:
                    int idEliminar = Ingreso.leerEnteroPositivo("Ingrese ID del vuelo a eliminar:");
                    Vuelo.eliminarVuelo(idEliminar);
                    break;
                case 4:
                    Vuelo.verTodosLosVuelos();
                    break;
                case 5:
                    int idBuscar = Ingreso.leerEnteroPositivo("Ingrese ID del vuelo a buscar:");
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
