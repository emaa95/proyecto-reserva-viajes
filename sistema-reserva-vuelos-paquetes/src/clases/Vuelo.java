package clases;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import utils.inputOutputJOP.Ingreso;
import utils.busquedas.Buscador;

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
    public String getIdVuelo() {
        return idVuelo;
    }

    public void setIdVuelo(String idVuelo) {
        this.idVuelo = idVuelo;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getDuracionHoras() {
        return duracionHoras;
    }

    public void setDuracionHoras(double duracionHoras) {
        this.duracionHoras = duracionHoras;
    }

    public static int getCantidad() {
        return cantidad;
    }

    public static void setCantidad(int nuevaCantidad) {
        cantidad = nuevaCantidad;
    }

    public static Vuelo[] getVuelos() {
        return vuelos;
    }

    // Método para cargar un vuelo.
    public static boolean cargarVuelo() {
        String idVuelo = Ingreso.leerString("Ingrese ID de vuelo:");
        int pos = Buscador.buscarPorId(idVuelo, vuelos, cantidad);
        if (pos >= 0) {
            JOptionPane.showMessageDialog(null, "Ya existe un vuelo con ese ID.");
            return false;
        }
        // Verificar tamaño de array antes de pedir los datos al usuario
        if (cantidad >= MAX_VUELOS) {
            JOptionPane.showMessageDialog(null, "No se pueden cargar más vuelos. Límite alcanzado.");
            return false;
        }

        String origen = Ingreso.leerString("Ingrese origen:");
        String destino = Ingreso.leerString("Ingrese destino:");
        String fechaSalida = Ingreso.seleccionarHoraConSpinner("Ingrese la hora de salida:");
        double precio = Ingreso.leerDoublePositivo("Ingrese precio:");
        double duracionHoras = Ingreso.leerDoublePositivo("Ingrese duración en horas:");
        cantidad++;

        vuelos[cantidad++] = new Vuelo(idVuelo, origen, destino, fechaSalida, precio, duracionHoras);
        JOptionPane.showMessageDialog(null, "Vuelo cargado correctamente.");
        return true;
    }

    public static void agregarVuelo(Vuelo v) {
        if (cantidad < MAX_VUELOS) {
            vuelos[cantidad++] = v;
        }
    }

    // Método para editar un vuelo.
    public static boolean editarVuelo() {
        String idVueloAEditar = Ingreso.leerString("Ingrese ID del vuelo a editar:");
        int pos = Buscador.buscarPorId(idVueloAEditar, vuelos, cantidad); // Busca el vuelo por idVuelo(Busqueda
                                                                          // binaria)
        if (pos >= 0) {
            // Si el vuelo existe, solicita los nuevos datos
            String nuevoOrigen = Ingreso.leerString("Ingrese nuevo origen:");
            String nuevoDestino = Ingreso.leerString("Ingrese nuevo destino:");
            String nuevaHora = Ingreso.seleccionarHoraConSpinner("Ingrese nueva hora de salida:");
            double nuevoPrecio = Ingreso.leerDoublePositivo("Ingrese nuevo precio:");
            double nuevaDuracion = Ingreso.leerDoublePositivo("Ingrese nueva duración en horas:");
            // Actualiza los datos del vuelo
            vuelos[pos].setOrigen(nuevoOrigen);
            vuelos[pos].setDestino(nuevoDestino);
            vuelos[pos].setHora(nuevaHora);
            vuelos[pos].setPrecio(nuevoPrecio);
            vuelos[pos].setDuracionHoras(nuevaDuracion);
            JOptionPane.showMessageDialog(null, "Vuelo editado correctamente.");
            return true;
        }
        JOptionPane.showMessageDialog(null, "Vuelo no encontrado.");
        return false;
    }

    // Método para ver todos los vuelos utilizando JTextArea y JScrollPane(Para
    // poder scrollar si es necesario)
    public static void verTodosLosVuelos() {
        StringBuilder sb = new StringBuilder();
        if (cantidad == 0) {
            sb.append("No hay vuelos registrados.");
        } else {
            for (int i = 0; i < cantidad; i++) {
                if (vuelos[i] != null) { // Verifica que una posición de vuelo no sea nulo
                    sb.append(vuelos[i].toString()).append("\n");
                }
            }
        }
        JTextArea textArea = new JTextArea(sb.toString());
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new java.awt.Dimension(400, 300));
        JOptionPane.showMessageDialog(null, scrollPane, "Listado de Vuelos", JOptionPane.INFORMATION_MESSAGE);
    }

    // Método para buscar y mostrar un vuelo por idVuelo
    public static void buscarYMostrarVueloPorId(String idVuelo) {
        int pos = Buscador.buscarPorId(idVuelo, vuelos, cantidad);
        if (pos >= 0) {
            JOptionPane.showMessageDialog(null, vuelos[pos]);
        } else {
            JOptionPane.showMessageDialog(null, "Vuelo no encontrado.");
        }
    }

    // Devuelvo un objeto Vuelo 
    public static Vuelo buscarVueloPorId(String idVuelo) {
        int pos = Buscador.buscarPorId(idVuelo, vuelos, cantidad);
        if (pos >= 0) {
            return vuelos[pos];
        }
        return null;
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
