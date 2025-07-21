package menus;

import javax.swing.JOptionPane;

import clases.EstadoReserva;
import clases.PaqueteTuristico;
import clases.Reserva;
import clases.Vuelo;
import utils.inputOutputJOP.Ingreso;
import utils.Ordenamientos.OrdenPrecio;

public class MenuInformes {
    public static void mostrarMenu() {
        int opcion = 0;
        do {
            String menu = "MENÚ INFORMES Y LISTADOS\n"
                        + "1. Listar reservas ordenadas por precio ascendente\n"
                        + "2. Listar reservas ordenadas por precio descendente\n"
                        + "3. Buscar vuelo por número (búsqueda binaria)\n"
                        + "4. Listar reservas por estado\n"
                        + "5. Listar paquetes por destino\n"
                        + "6. Mostrar estado actual de todas las reservas\n"
                        + "7. Salir";
            opcion = Ingreso.leerEnteroPositivo(menu);
            
            switch (opcion) {
                case 1:
                    // Ordena los paquetes por precio ascendente
                    OrdenPrecio.mostrarPaquetesOrdenadosPorPrecio(true);
                    break;
                case 2:
                    // Ordena los paquetes por precio descendente
                    OrdenPrecio.mostrarPaquetesOrdenadosPorPrecio(false);
                    break;
                case 3:
                    // Llama al método para buscar vuelo por número
                    String idBuscar = Ingreso.leerString("Ingrese ID del vuelo a buscar:");
                    Vuelo.buscarYMostrarVueloPorId(idBuscar);
                    break;
                case 4:
                    // Opciones de estado válidas
                    String[] opciones = {"CANCELADA", "CONFIRMADA", "RECHAZADA", "PENDIENTE", "PAGADA"};

                    // Mostrar el JOptionPane para seleccionar
                    String seleccion = (String) JOptionPane.showInputDialog(
                            null,
                            "Seleccione un estado de reserva:",
                            "Filtrar reservas por estado",
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            opciones,
                            opciones[0]
                    );

                    // Si el usuario no cancela
                    if (seleccion != null) {
                        EstadoReserva estadoSeleccionado = EstadoReserva.valueOf(seleccion);
                        Reserva.mostrarReservasPorEstado(estadoSeleccionado);
                    } else {
                        JOptionPane.showMessageDialog(null, "Búsqueda cancelada.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                    }
                    break;
                case 5:
                    String destino = Ingreso.leerString("Ingrese el destino a buscar:");
                    PaqueteTuristico.mostrarPaquetesPorDestino(destino);
                    break;
                case 6:
                    // Muestra el estado actual de todas las reservas
                    Reserva.mostrarEstadoActualReservas();
                    break;
                case 7:
                    JOptionPane.showMessageDialog(null, "Saliendo del menú de informes.");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción no válida.");
            }
        } while (opcion != 7);
    }
}