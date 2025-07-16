package clases;

import java.util.Arrays;

import javax.swing.JOptionPane;

import utils.busquedas.Buscador;
import utils.inputOutputJOP.Ingreso;

public class PaqueteTuristico {

    private int idPaquete = 0;
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
    public PaqueteTuristico(int idPaquete, String descripcion, String fechaSalida, String fechaRegreso,
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
            int idPaquete = Ingreso.leerEntero("Ingrese id del paquete:");
                String descripcion = Ingreso.leerString("Ingrese descripción:");
                String fechaSalida = Ingreso.leerString("Ingrese fecha de salida:");
                String fechaRegreso = Ingreso.leerString("Ingrese fecha de regreso:");
                String destinos[];
                String vuelo = Ingreso.leerString("Ingrese vuelos:"); // Array de idVuelos
                double precioTotal = Ingreso.leerDouble("Ingrese precio total:"); // Precio por pasajero
            paquetes[cantidadPaquetes++] = new PaqueteTuristico(idPaquete, descripcion, fechaSalida, fechaRegreso,
                    destinos, vuelo, precioTotal);
            Buscador.ordenarPorId(destinos, idPaquete);
            JOptionPane.showMessageDialog(null, "Paquete cargado correctamente.");
            return true;
        }
        JOptionPane.showMessageDialog(null, "No se pudo cargar el paquete. Límite alcanzado.");
        return false;
    }

    // Método para editar un paquete turístico usando búsqueda binaria

    public static boolean editarPaquete() {
        int idPaqueteEditar = Ingreso.leerEntero("Ingrese id del paquete a editar:");
        int pos = Buscador.buscarPorId(idPaqueteEditar, paquetes, cantidadPaquetes);
        if (pos >= 0) {
            String descripcionEditar = Ingreso.leerString("Ingrese la nueva descripción:");
            String fechaSalidaEditar = Ingreso.leerString("Ingrese la nueva fecha de salida:");
            String fechaRegresoEditar = Ingreso.leerString("Ingrese la nueva fecha de regreso:");
            String destinosEditar[];
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
        int idPaqueteEliminar = Ingreso.leerEntero("Ingrese el id del paquete a eliminar:");
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
                        .append(", Destinos: ").append(paquetes[i].destinos).append("\n")
                        .append(", Vuelo: ").append(paquetes[i].vuelo).append("\n")
                        .append(", Precio total: ").append(paquetes[i].precioTotal).append("\n");
                sb.append("====================================\n\n");
            }
        }
        JOptionPane.showMessageDialog(null, sb.toString());
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
    public int getIdPaquete() {
        return idPaquete;
    }

    public void setIdPaquete(int idPaquete) {
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

}
