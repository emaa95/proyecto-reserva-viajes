package clases;
import java.util.Arrays;
import javax.swing.JOptionPane;
import utils.busquedas.Buscador;
import utils.inputOutputJOP.Ingreso;

public class PaqueteTuristico {

    private String idPaquete;
    private String descripcion;
    private String fechaSalida;
    private String fechaRegreso;
    private String destinos[];
    private String vuelo;
    private double precioTotal;

    // Arreglo estático para almacenar paquetes
    private static final int MAX_PAQUETES = 100;
    private static PaqueteTuristico[] paquetes = new PaqueteTuristico[MAX_PAQUETES];
    private static int cantidadPaquetes = 0;

    // Constructor
    public PaqueteTuristico(String idPaquete, String descripcion, String fechaSalida, String fechaRegreso,
            String[] destinos, String vuelo, double precioTotal) {
        this.idPaquete = idPaquete;
        this.descripcion = descripcion;
        this.fechaSalida = fechaSalida;
        this.fechaRegreso = fechaRegreso;
        this.destinos = destinos;
        this.vuelo = vuelo;
        this.precioTotal = precioTotal;
    }

    // Método para cargar un nuevo paquete turístico

    public static boolean cargarPaquete() {

        if (cantidadPaquetes < MAX_PAQUETES) {
            String idPaquete = Ingreso.leerString("Ingrese id del paquete:");
                String descripcion = Ingreso.leerString("Ingrese descripción:");
                String fechaSalida = Ingreso.leerString("Ingrese fecha de salida:");
                String fechaRegreso = Ingreso.leerString("Ingrese fecha de regreso:");
                String destinosInput = Ingreso.leerString("Ingrese los destinos separados por coma:");
                String[] destinos = destinosInput.split("\\s*,\\s*");
                String vuelo = Ingreso.leerString("Ingrese vuelos:"); // Array de idVuelos
                double precioTotal = Ingreso.leerDouble("Ingrese precio total:"); // Precio por pasajero
            paquetes[cantidadPaquetes++] = new PaqueteTuristico(idPaquete, descripcion, fechaSalida, fechaRegreso,
                    destinos, vuelo, precioTotal);
            JOptionPane.showMessageDialog(null, "Paquete cargado correctamente.");
            return true;
        }
        JOptionPane.showMessageDialog(null, "No se pudo cargar el paquete. Límite alcanzado.");
        return false;
    }

    public static void agregarPaquete(PaqueteTuristico p) {
    if (cantidadPaquetes < MAX_PAQUETES) {
        paquetes[cantidadPaquetes++] = p;
    }
    }


    // Método para editar un paquete turístico usando búsqueda binaria

    public static boolean editarPaquete() {
        String idPaqueteEditar = Ingreso.leerString("Ingrese id del paquete a editar:");
        int pos = Buscador.buscarPorId(idPaqueteEditar, paquetes, cantidadPaquetes);
        if (pos >= 0) {
            String descripcionEditar = Ingreso.leerString("Ingrese la nueva descripción:");
            String fechaSalidaEditar = Ingreso.leerString("Ingrese la nueva fecha de salida:");
            String fechaRegresoEditar = Ingreso.leerString("Ingrese la nueva fecha de regreso:");
            String destinosInput = Ingreso.leerString("Ingrese los nuevos destinos separados por coma:");
            String[] destinosEditar = destinosInput.split("\\s*,\\s*");
            String vueloEditar = Ingreso.leerString("Ingrese los nuevos vuelos:");
            double precioTotaEditar = Ingreso.leerDouble("Ingrese el nuevo precio total:");
            paquetes[pos].descripcion = descripcionEditar;
            paquetes[pos].fechaSalida = fechaSalidaEditar;
            paquetes[pos].fechaRegreso = fechaRegresoEditar;
            paquetes[pos].destinos = destinosEditar;
            paquetes[pos].vuelo = vueloEditar;
            paquetes[pos].precioTotal = precioTotaEditar;

            JOptionPane.showMessageDialog(null, "Paquete editado correctamente.");
            return true;
        }
        JOptionPane.showMessageDialog(null, "Paquete no encontrado.");
        return false;
    }

    // Método para eliminar un paquete turístico usando búsqueda binaria
    public static boolean eliminarPaquete() {
        String idPaqueteEliminar = Ingreso.leerString("Ingrese el id del paquete a eliminar:");
        int pos = Buscador.buscarPorId(idPaqueteEliminar, paquetes, cantidadPaquetes);
        if (pos >= 0) {
            paquetes[pos] = paquetes[cantidadPaquetes - 1];
            paquetes[cantidadPaquetes - 1] = null;
            cantidadPaquetes--;
            Buscador.ordenarPorId(paquetes, pos);
            ;
            JOptionPane.showMessageDialog(null, "Paquete eliminado correctamente.");
            return true;
        }
        JOptionPane.showMessageDialog(null, "Paquete no encontrado.");
        return false;
    }

    // Método para ver todos los paquetes turísticos
    public static void verTodosLosPaquetes() {
        StringBuilder sb = new StringBuilder();
        if (cantidadPaquetes == 0) {
            sb.append("No hay paquetes registrados.");
        } else {
            for (int i = 0; i < cantidadPaquetes; i++) {
                sb.append("====================================\n");
                sb.append("Descripción: ").append(paquetes[i].descripcion).append("\n")
                        .append(", Fecha salida: ").append(paquetes[i].fechaSalida).append("\n")
                        .append(", Fecha regreso: ").append(paquetes[i].fechaRegreso).append("\n")
                        .append("Destinos: ");
                        String[] destinos = paquetes[i].destinos;
            for (int j = 0; j < destinos.length; j++) {
                sb.append(destinos[j]);
                if (j < destinos.length - 1) {
                    sb.append(", ");
                }
            }
            sb.append("\n")
                        .append(", Vuelo: ").append(paquetes[i].vuelo).append("\n")
                        .append(", Precio total: ").append(paquetes[i].precioTotal).append("\n");
                sb.append("====================================\n\n");
            }
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }

    public static PaqueteTuristico buscarPaquetePorId(String idPaqueteTuristico) {
        int pos = Buscador.buscarPorId(idPaqueteTuristico, paquetes, cantidadPaquetes);
        if (pos >= 0) {
            return paquetes[pos];
        }
        return null;
    }

    /*  Método para ordenar el arreglo por id (usando burbuja)
    private static void ordenarPaquetes() {
        for (int pasada = 0; pasada < cantidadPaquetes - 1; pasada++) {
            for (int indice = 0; indice < cantidadPaquetes - pasada - 1; indice++) {
                PaqueteTuristico paqueteTemporal = paquetes[indice];
                if (paquetes[indice].getIdPaquete() > paquetes[indice + 1].getIdPaquete()) {
                    paquetes[indice] = paquetes[indice + 1];
                    paquetes[indice + 1] = paqueteTemporal;
                }
            }
        }
    }
    */

    /*  Método para buscar paquetes por id por busqueda binaria
    private static int buscarPaquete(int idPaqueteBuscar) {
        int izquierda = 0;
        int derecha = cantidadPaquetes - 1;
        while (izquierda <= derecha) {
            int medio = (izquierda + derecha) / 2;
            int idActual = paquetes[medio].getIdPaquete();
            if (idActual == idPaqueteBuscar)
                return medio;
            if (idActual < idPaqueteBuscar)
                izquierda = medio + 1;
            else
                derecha = medio - 1;
        }
        return -1;
    }
*/
    // Mostrar datos

    @Override
    public String toString() {
        return "PaqueteTuristico [idPaquete=" + idPaquete + ", descripcion=" + descripcion + ", fechaSalida="
                + fechaSalida + ", fechaRegreso=" + fechaRegreso + ", destinos=" + Arrays.toString(destinos)
                + ", vuelo=" + vuelo + ", precioTotal=" + precioTotal + "]";
    }

    // Getters y Setters
    public String getIdPaquete() {
        return idPaquete;
    }

    public void setIdPaquete(String idPaquete) {
        this.idPaquete = idPaquete;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(String fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public String getFechaRegreso() {
        return fechaRegreso;
    }

    public void setFechaRegreso(String fechaRegreso) {
        this.fechaRegreso = fechaRegreso;
    }

    public String[] getDestinos() {
        return destinos;
    }

    public void setDestinos(String[] destinos) {
        this.destinos = destinos;
    }

    public String getVuelo() {
        return vuelo;
    }

    public void setVuelo(String vuelo) {
        this.vuelo = vuelo;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public static int getCantidad() {
        return cantidadPaquetes;
    }

    public static PaqueteTuristico getPaquete(int i) {
        return paquetes[i];
    }
    // Metodo para listar paquetes por destino (Llamados desde informes)
   public static void mostrarPaquetesPorDestino(String destinoBuscado) {
        boolean encontrado = false;

        for (int i = 0; i < getCantidad(); i++) {
            PaqueteTuristico paquete = getPaquete(i);

            for (String destino : paquete.getDestinos()) {
                if (destino.equalsIgnoreCase(destinoBuscado)) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("====================================\n");
                    sb.append("Descripción: ").append(paquete.getDescripcion()).append("\n")
                    .append("Fecha salida: ").append(paquete.getFechaSalida()).append("\n")
                    .append("Fecha regreso: ").append(paquete.getFechaRegreso()).append("\n")
                    .append("Destinos: ");
                    String[] destinos = paquete.getDestinos();
                    for (int j = 0; j < destinos.length; j++) {
                        sb.append(destinos[j]);
                        if (j < destinos.length - 1) {
                            sb.append(", ");
                        }
                    }
                    sb.append("\n")
                    .append("Vuelo: ").append(paquete.getVuelo()).append("\n")
                    .append("Precio total: ").append(paquete.getPrecioTotal()).append("\n")
                    .append("====================================\n\n");

                    JOptionPane.showMessageDialog(
                        null,
                        sb.toString(),
                        "Paquete encontrado",
                        JOptionPane.INFORMATION_MESSAGE
                    );

                    encontrado = true;
                    break; 
                }
            }
        }
        if (!encontrado) {
            JOptionPane.showMessageDialog(
                null,
                "No se encontraron paquetes para el destino: " + destinoBuscado,
                "Resultado de búsqueda",
                JOptionPane.INFORMATION_MESSAGE
            );
        }
    }
}
