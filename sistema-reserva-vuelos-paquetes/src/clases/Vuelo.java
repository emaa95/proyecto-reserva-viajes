package clases;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import javax.swing.JOptionPane;

public class Vuelo {
    private String idVuelo;
    private String origen;
    private String destino;
    private String hora;
    private double precio;
    private double duracionHoras;

    // Decaración del array
    private static final int MAX_VUELOS = 100;
    private static Vuelo[] vuelos = new Vuelo[MAX_VUELOS];
    private static int cantidad = 0;

    // Constructor
        public Vuelo(String idVuelo, String origen, String destino, String hora, double precio, double duracionHoras) {
        this.idVuelo = idVuelo;
        this.origen = origen;
        this.destino = destino;
        this.hora = hora;
        this.precio = precio;
        this.duracionHoras = duracionHoras;
    }

    // Getters y Setters
    public String getIdVuelo() { return idVuelo; }
    public void setIdVuelo(String idVuelo) { this.idVuelo = idVuelo; }

    public String getOrigen() { return origen; }
    public void setOrigen(String origen) { this.origen = origen; }

    public String getDestino() { return destino; }
    public void setDestino(String destino) { this.destino = destino; }

    public String getHora() { return hora; }
    public void setHora(String hora) { this.hora = hora; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    public double getDuracionHoras() { return duracionHoras; }
    public void setDuracionHoras(double duracionHoras) { this.duracionHoras = duracionHoras; }

    // Método para cargar un nuevo vuelo
    public static boolean cargarVuelo(String idVuelo, String origen, String destino, String fechaSalida, double precio, double duracionHoras) {
        // Verificar si ya existe un vuelo con el mismo idVuelo
        if (existeIdVuelo(idVuelo)) {
            JOptionPane.showMessageDialog(null, "Ya existe un vuelo con ese ID.");
            return false;
        }
        if (cantidad < MAX_VUELOS) {
            vuelos[cantidad++] = new Vuelo(idVuelo, origen, destino, fechaSalida, precio, duracionHoras);
            ordenarPorIdVuelo();
            JOptionPane.showMessageDialog(null, "Vuelo cargado correctamente.");
            return true;
        }
        JOptionPane.showMessageDialog(null, "No se pudo cargar el vuelo. Límite alcanzado.");
        return false;
    }

    // Métodos estáticos de gestión
    // Método para editar un vuelo por idVuelo usando búsqueda binaria
    public static boolean editarVuelo(String idVuelo, String nuevoOrigen, String nuevoDestino, String nuevaHoraSalida, double nuevoPrecio, double nuevaDuracionHoras) {
        int pos = buscarPorIdVuelo(idVuelo);
         if (pos >= 0) {
            vuelos[pos].setOrigen(nuevoOrigen);
            vuelos[pos].setDestino(nuevoDestino);
            vuelos[pos].setHora(nuevaHoraSalida);
            vuelos[pos].setPrecio(nuevoPrecio);
            vuelos[pos].setDuracionHoras(nuevaDuracionHoras);
            JOptionPane.showMessageDialog(null, "Vuelo editado correctamente.");
            return true;
        }
        JOptionPane.showMessageDialog(null, "Vuelo no encontrado.");
        return false;
    }

    // Método para eliminar un vuelo por idVuelo usando búsqueda binaria
    public static boolean eliminarVuelo(String idVuelo) {
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

    // Método para ver todos los vuelos utilizando JTextArea y JScrollPane
    public static void verTodosLosVuelos() {
        StringBuilder sb = new StringBuilder();
        if (cantidad == 0) {
            sb.append("No hay vuelos registrados.");
        } else {
            for (int i = 0; i < cantidad; i++) {
                sb.append(vuelos[i].toString()).append("\n");
            }
        }
        JTextArea textArea = new JTextArea(sb.toString());
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new java.awt.Dimension(400, 300));
        JOptionPane.showMessageDialog(null, scrollPane, "Listado de Vuelos", JOptionPane.INFORMATION_MESSAGE);
    }

     // Método de búsqueda binaria por idVuelo
    private static int buscarPorIdVuelo(String idVuelo) {
        int inicio = 0;
        int fin = cantidad - 1;
        while (inicio <= fin) {
            int medio = (inicio + fin) / 2;
            if (vuelos[medio].idVuelo.equals(idVuelo)) return medio;
            if (vuelos[medio].idVuelo.compareTo(idVuelo) < 0) inicio = medio + 1;
            else fin = medio - 1;
        }
        return -1;
    }
    
    // Método para buscar y mostrar un vuelo por idVuelo
    public static void buscarYMostrarVueloPorId(String idVuelo) {
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
                if (vuelos[indice].idVuelo.compareTo(vuelos[indice + 1].idVuelo) > 0) {
                    Vuelo vueloTemporal = vuelos[indice];
                    vuelos[indice] = vuelos[indice + 1];
                    vuelos[indice + 1] = vueloTemporal;
                }
            }
        }
    }

    // Método para verificar si existe un vuelo con el mismo idVuelo
    public static boolean existeIdVuelo(String idVuelo) {
        for (int i = 0; i < cantidad; i++) {
            if (vuelos[i].idVuelo.equals(idVuelo)) {
                return true;
            }
        }
        return false;
    }

    // Método para obtener la cantidad de vuelos registrados
    public static int getCantidadVuelos() {
        return cantidad;
    }

    // Método para crear una cadena de texto con los detalles del vuelo
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("====================================\n");
        sb.append("ID Vuelo: ").append(idVuelo).append("\n");
        sb.append("Origen: ").append(origen).append("\n");
        sb.append("Destino: ").append(destino).append("\n");
        sb.append("Hora de Salida: ").append(hora).append("\n");
        sb.append("Precio: $").append(String.format("%.2f", precio)).append("\n");
        sb.append("Duración: ").append(duracionHoras).append(" horas\n");
        sb.append("====================================");
        return sb.toString();
    }
}
