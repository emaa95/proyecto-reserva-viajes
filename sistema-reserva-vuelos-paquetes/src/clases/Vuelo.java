package clases;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import utils.inputOutputJOP.Ingreso;
import utils.inputOutputJOP.Salida;
import utils.busquedas.Buscador;

import java.time.LocalDate;
import java.time.Month;

import javax.swing.JOptionPane;

public class Vuelo {
    private String idVuelo;
    private String origen;
    private String destino;
    private String fecha;
    private String hora;
    private double duracionHoras;
    private int asientosTotales;
    private int asientosReservados;
    private double precioBase;

    // Decaración del array
    private static final int MAX_VUELOS = 100;
    private static Vuelo[] vuelos = new Vuelo[MAX_VUELOS];
    private static int cantidad = 0;

    // Constructor
    public Vuelo(String idVuelo, String origen, String destino, String fecha, String hora, double precioBase,
            double duracionHoras, int asientosTotales) {
        this.idVuelo = idVuelo;
        this.origen = origen;
        this.destino = destino;
        this.fecha = fecha;
        this.hora = hora;
        this.precioBase = precioBase;
        this.duracionHoras = duracionHoras;
        this.asientosTotales = asientosTotales;
        this.asientosReservados = 0;
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

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public double getPrecioBase() {
        return precioBase;
    }

    public void setPrecioBase(double precioBase) {
        this.precioBase = precioBase;
    }

    public double getDuracionHoras() {
        return duracionHoras;
    }

    public void setDuracionHoras(double duracionHoras) {
        this.duracionHoras = duracionHoras;
    }

    public int getAsientosTotales() {
        return asientosTotales;
    }

    public void setAsientosTotales(int asientosTotales) {
        if (asientosTotales < this.asientosReservados) {
            throw new IllegalArgumentException("El total no puede ser menor que los asientos reservados.");
        }
        this.asientosTotales = asientosTotales;
    }

    public int getAsientosReservados() {
        return asientosReservados;
    }

    public void setAsientosReservados(int asientosReservados) {
        this.asientosReservados = asientosReservados;
    }

    public int getAsientosDisponibles() {
        return asientosTotales - asientosReservados;
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
        String fecha = Ingreso.seleccionarFechaConSpinner("Ingrese la fecha de salida:");
        String hora = Ingreso.seleccionarHoraConSpinner("Ingrese la hora de salida:");
        double precioBase = Ingreso.leerDoublePositivo("Ingrese precio base:");
        double duracionHoras = Ingreso.leerDoublePositivo("Ingrese duración en horas:");
        int asientosTotales = Ingreso.leerEnteroPositivo("Ingrese cantidad total de asientos:");

        vuelos[cantidad++] = new Vuelo(idVuelo, origen, destino, fecha, hora, precioBase, duracionHoras,
                asientosTotales);
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
    int pos = Buscador.buscarPorId(idVueloAEditar, vuelos, cantidad);

    if (pos < 0) {
        JOptionPane.showMessageDialog(null, "Vuelo no encontrado.");
        return false;
    }

    Vuelo vuelo = vuelos[pos];
    boolean seguirEditando = true;

    while (seguirEditando) {
        String[] opciones = {
            "Origen",
            "Destino",
            "Fecha de salida",
            "Hora de salida",
            "Precio base",
            "Duración en horas",
            "Asientos totales",
            "Finalizar edición"
        };

        int seleccion = Ingreso.nOpciones("Seleccione el campo a editar:", opciones, "Editar Vuelo");

        switch (seleccion) {
            case 0:
                vuelo.setOrigen(Ingreso.leerString("Nuevo origen:"));
                break;
            case 1:
                vuelo.setDestino(Ingreso.leerString("Nuevo destino:"));
                break;
            case 2:
                vuelo.setFecha(Ingreso.seleccionarFechaConSpinner("Nueva fecha de salida:"));
                break;
            case 3:
                vuelo.setHora(Ingreso.seleccionarHoraConSpinner("Nueva hora de salida:"));
                break;
            case 4:
                vuelo.setPrecioBase(Ingreso.leerDoublePositivo("Nuevo precio base:"));
                break;
            case 5:
                vuelo.setDuracionHoras(Ingreso.leerDoublePositivo("Nueva duración (en horas):"));
                break;
            case 6:
                vuelo.setAsientosTotales(Ingreso.leerEnteroPositivo("Nueva cantidad de asientos totales:"));
                break;
            case 7:
            default:
                seguirEditando = false;
                break;
        }
    }

    Salida.mMensaje("Vuelo editado correctamente.", "Éxito");
    return true;
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

    // Método para devolver un objeto Vuelo
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
        sb.append("Fecha: ").append(fecha).append("\n");
        sb.append("Hora de Salida: ").append(hora).append("\n");
        sb.append("Precio Base: $").append(String.format("%.2f", precioBase)).append("\n");
        sb.append("Duración: ").append(duracionHoras).append(" horas\n");
        sb.append("Asientos Disponibles: ").append(getAsientosDisponibles()).append(" de ").append(asientosTotales)
                .append("\n");
        sb.append("Temporada: ").append(getTemporada()).append("\n");
        sb.append("====================================");
        return sb.toString();
    }

    public String getTemporada() {
        try {
            LocalDate fechaVuelo = LocalDate.parse(fecha);

            int dia = fechaVuelo.getDayOfMonth();
            Month mes = fechaVuelo.getMonth();

            // Temporada alta: 15 dic - 15 feb
            if ((mes == Month.DECEMBER && dia >= 15) || (mes == Month.JANUARY)
                    || (mes == Month.FEBRUARY && dia <= 15)) {
                return "alta";
            }

            // Temporada alta: 1 jul - 15 ago
            if ((mes == Month.JULY) || (mes == Month.AUGUST && dia <= 15)) {
                return "alta";
            }

            return "baja";

        } catch (Exception e) {
            Salida.mError("Fecha inválida para determinar temporada: " + fecha, "Error");
            return "baja";
        }
    }

    public void reservarAsientos(int cantidad) {
        if (cantidad <= getAsientosDisponibles()) {
            asientosReservados += cantidad;
        } else {
            throw new IllegalArgumentException("No hay suficientes asientos disponibles.");
        }
    }

    public double calcularPrecioFinal() {
        double precio = precioBase;

        String temporada = getTemporada();
        if ("alta".equalsIgnoreCase(temporada)) {
            precio *= 1.20;
        } else if ("baja".equalsIgnoreCase(temporada)) {
            precio *= 0.85;
        }

        double porcentajeDisponibles = (double) getAsientosDisponibles() / asientosTotales;
        if (porcentajeDisponibles < 0.1) {
            precio *= 1.30;
        } else if (porcentajeDisponibles < 0.3) {
            precio *= 1.15;
        }

        try {
            LocalDate fechaSalida = LocalDate.parse(fecha);
            long diasParaSalida = fechaSalida.toEpochDay() - LocalDate.now().toEpochDay();

            if (diasParaSalida <= 7) {
                precio *= 1.40;
            } else if (diasParaSalida <= 30) {
                precio *= 1.20;
            }
        } catch (Exception e) {
            Salida.mError("Erro al parsear la fecha", "Error");
        }

        return precio;
    }
}