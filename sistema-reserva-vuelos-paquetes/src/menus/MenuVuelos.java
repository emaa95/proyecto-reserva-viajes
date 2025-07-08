package menus;

import javax.swing.JOptionPane;
import utils.inputOutputJOP.Ingreso;
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
                        String idVuelo = Ingreso.leerString("Ingrese ID de vuelo:");
                        if (Vuelo.existeIdVuelo(idVuelo)) {
                            JOptionPane.showMessageDialog(null, "Ya existe un vuelo con el ID: " + idVuelo);
                            break; 
                        }
                        String origen = Ingreso.leerString("Ingrese origen:");
                        String destino = Ingreso.leerString("Ingrese destino:");
                        String fechaSalida = Ingreso.seleccionarFechaConSpinner("Ingrese fecha de salida:");
                        double precio = Ingreso.leerDoublePositivo("Ingrese precio:");
                        double duracionHoras = Ingreso.leerDoublePositivo("Ingrese duración en horas:");
                        Vuelo.cargarVuelo(idVuelo, origen, destino, fechaSalida, precio, duracionHoras);
                        break;
                    case 2:
                        String idEditar = Ingreso.leerString("Ingrese ID del vuelo a modificar:");
                        // Verificar si el vuelo existe antes de editar
                        if (!Vuelo.existeIdVuelo(idEditar)) {
                            JOptionPane.showMessageDialog(null, "No existe un vuelo con ese ID.");
                            break;
                        }
                        String nuevoOrigen = Ingreso.leerString("Ingrese nuevo origen:");
                        String nuevoDestino = Ingreso.leerString("Ingrese nuevo destino:");
                        String nuevaFecha = Ingreso.seleccionarFechaConSpinner("Ingrese nueva fecha de salida:");
                        double nuevoPrecio = Ingreso.leerDoublePositivo("Ingrese nuevo precio:");
                        double nuevaDuracion = Ingreso.leerDoublePositivo("Ingrese nueva duración en horas:");
                        Vuelo.editarVuelo(idEditar, nuevoOrigen, nuevoDestino, nuevaFecha, nuevoPrecio, nuevaDuracion);
                        break;
                    case 3:
                        String idEliminar = Ingreso.leerString("Ingrese ID del vuelo a eliminar:");
                        // Verificar si el vuelo existe antes de eliminar
                        if (!Vuelo.existeIdVuelo(idEliminar)) {
                            JOptionPane.showMessageDialog(null, "No existe un vuelo con ese ID.");
                            break;
                        }
                        Vuelo.eliminarVuelo(idEliminar);
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
