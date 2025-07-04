package clases;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import javax.swing.JOptionPane;

public class Vuelo {
    private int idVuelo;
    private String origen;
    private String destino;
    private String fechaSalida;
    private double precio;
    private int duracionHoras;

    // Decaración del array
    private static final int MAX_VUELOS = 100;
    private static Vuelo[] vuelos = new Vuelo[MAX_VUELOS];
    private static int cantidad = 0;

    // Constructor
        public Vuelo(int idVuelo, String origen, String destino, String fechaSalida, double precio, int duracionHoras) {
        this.idVuelo = idVuelo;
        this.origen = origen;
        this.destino = destino;
        this.fechaSalida = fechaSalida;
        this.precio = precio;
        this.duracionHoras = duracionHoras;
    }

    // Método para cargar un nuevo vuelo
    public static boolean cargarVuelo(int idVuelo, String origen, String destino, String fechaSalida, double precio, int duracionHoras) {
        if (cantidad < MAX_VUELOS) {
            vuelos[cantidad++] = new Vuelo(idVuelo, origen, destino, fechaSalida, precio, duracionHoras);
            ordenarPorIdVuelo();
            JOptionPane.showMessageDialog(null, "Vuelo cargado correctamente.");
            return true;
        }
        JOptionPane.showMessageDialog(null, "No se pudo cargar el vuelo. Límite alcanzado.");
        return false;
    }

        // Método para editar un vuelo por idVuelo usando búsqueda binaria
    public static boolean editarVuelo(int idVuelo, String nuevoOrigen, String nuevoDestino, String nuevaFechaSalida, double nuevoPrecio, int nuevaDuracionHoras) {
        int pos = buscarPorIdVuelo(idVuelo);
        if (pos >= 0) {
            vuelos[pos].origen = nuevoOrigen;
            vuelos[pos].destino = nuevoDestino;
            vuelos[pos].fechaSalida = nuevaFechaSalida;
            vuelos[pos].precio = nuevoPrecio;
            vuelos[pos].duracionHoras = nuevaDuracionHoras;
            JOptionPane.showMessageDialog(null, "Vuelo editado correctamente.");
            return true;
        }
        JOptionPane.showMessageDialog(null, "Vuelo no encontrado.");
        return false;
    }

       // Método para eliminar un vuelo por idVuelo usando búsqueda binaria
    public static boolean eliminarVuelo(int idVuelo) {
        int pos = buscarPorIdVuelo(idVuelo);
        if (pos >= 0) {
            vuelos[pos] = vuelos[cantidad - 1];
            vuelos[cantidad - 1] = null;
            cantidad--;
            ordenarPorIdVuelo();
            JOptionPane.showMessageDialog(null, "Vuelo eliminado correctamente.");
            return true;
        }
        JOptionPane.showMessageDialog(null, "Vuelo no encontrado.");
        return false;
    }

       // Método para ver todos los vuelos
    public static void verTodosLosVuelos() {
        StringBuilder sb = new StringBuilder();
        if (cantidad == 0) {
            sb.append("No hay vuelos registrados.");
        } else {
            for (int i = 0; i < cantidad; i++) {
                sb.append("====================================\n");
                sb.append("ID Vuelo: ").append(vuelos[i].idVuelo).append("\n");
                sb.append("Origen: ").append(vuelos[i].origen).append("\n");
                sb.append("Destino: ").append(vuelos[i].destino).append("\n");
                sb.append("Fecha de Salida: ").append(vuelos[i].fechaSalida).append("\n");
                sb.append("Precio: $").append(String.format("%.2f", vuelos[i].precio)).append("\n");
                sb.append("Duración: ").append(vuelos[i].duracionHoras).append(" horas\n");
                sb.append("====================================\n\n");
            }
        }
        JTextArea textArea = new JTextArea(sb.toString());
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new java.awt.Dimension(400, 300));
        JOptionPane.showMessageDialog(null, scrollPane, "Listado de Vuelos", JOptionPane.INFORMATION_MESSAGE);
    }

     // Método de búsqueda binaria por idVuelo
    private static int buscarPorIdVuelo(int idVuelo) {
        int inicio = 0;
        int fin = cantidad - 1;
        while (inicio <= fin) {
            int medio = (inicio + fin) / 2;
            if (vuelos[medio].idVuelo == idVuelo) return medio;
            if (vuelos[medio].idVuelo < idVuelo) inicio = medio + 1;
            else fin = medio - 1;
        }
        return -1;
    }
    public static void buscarYMostrarVueloPorId(int idVuelo) {
    int pos = buscarPorIdVuelo(idVuelo);
    if (pos >= 0) {
        JOptionPane.showMessageDialog(null, vuelos[pos]);
    } else {
        JOptionPane.showMessageDialog(null, "Vuelo no encontrado.");
    }
    }


    // Método para ordenar el arreglo por idVuelo (burbuja)
    private static void ordenarPorIdVuelo() {
        for (int pasada = 0; pasada < cantidad - 1; pasada++) {
            for (int indice = 0; indice < cantidad - pasada - 1; indice++) {
                if (vuelos[indice].idVuelo > vuelos[indice + 1].idVuelo) {
                    Vuelo vueloTemporal = vuelos[indice];
                    vuelos[indice] = vuelos[indice + 1];
                    vuelos[indice + 1] = vueloTemporal;
                }
            }
        }
    }
}
