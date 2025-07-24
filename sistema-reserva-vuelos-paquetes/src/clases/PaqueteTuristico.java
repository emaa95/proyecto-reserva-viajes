package clases;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import utils.busquedas.Buscador;
import utils.inputOutputJOP.Ingreso;
import utils.inputOutputJOP.Salida;

public class PaqueteTuristico {
    private static int contadorId = 1;
    private String idPaquete;
    private String descripcion;
    private String fechaSalida;
    private String fechaRegreso;
    private String destinos[];
    private Vuelo vuelo;
    private double precioTotal;

    // Arreglo estático para almacenar paquetes
    private static final int MAX_PAQUETES = 100;
    private static PaqueteTuristico[] paquetes = new PaqueteTuristico[MAX_PAQUETES];
    private static int cantidadPaquetes = 0;

    // Constructor
    public PaqueteTuristico(String descripcion, String fechaSalida, String fechaRegreso,
            String[] destinos, Vuelo vuelo, double precioTotal) {
        this.idPaquete = String.format("P%03d", contadorId++);
        this.descripcion = descripcion;
        this.fechaSalida = fechaSalida;
        this.fechaRegreso = fechaRegreso;
        this.destinos = destinos;
        this.vuelo = vuelo;
        this.precioTotal = precioTotal;
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

    public Vuelo getVuelo() {
        return vuelo;
    }

    public void setVuelo(Vuelo vuelo) {
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

    // Método para cargar un nuevo paquete turístico

    public static void cargarPaquete() {

        if (cantidadPaquetes < MAX_PAQUETES) {
                String descripcion = Ingreso.leerString("Ingrese descripción:");
                String fechaSalida = Ingreso.seleccionarFechaConSpinner("Ingrese fecha de salida:");
                String fechaRegreso = Ingreso.seleccionarFechaConSpinner("Ingrese fecha de regreso:");
                String destinosInput = Ingreso.leerString("Ingrese los destinos separados por coma:");
                String[] destinos = destinosInput.split("\\s*,\\s*");
                double precioTotal = Ingreso.leerDouble("Ingrese precio total:"); // Precio por pasajero

                Vuelo vuelo = null;

                do {
                String idVuelo = Ingreso.leerString("Ingrese el ID del vuelo: ");
                vuelo = Vuelo.buscarVueloPorId(idVuelo);
                if (vuelo == null) {
                    Salida.mError("Vuelo no encontrado. Intente nuevamente.", "Error");
                }

                } while (vuelo == null);
                paquetes[cantidadPaquetes++] = new PaqueteTuristico(descripcion, fechaSalida, fechaRegreso, destinos, vuelo, precioTotal);
                JOptionPane.showMessageDialog(null, "Paquete cargado correctamente.");
        } else {
            JOptionPane.showMessageDialog(null, "No se pudo cargar el paquete. Límite alcanzado.");
        }
    }

    public static void agregarPaquete(PaqueteTuristico p) {
    if (cantidadPaquetes < MAX_PAQUETES) {
        paquetes[cantidadPaquetes++] = p;
    }
    }

    // Método para editar un paquete turístico usando búsqueda binaria

    public static void editarPaquete() {
    String idPaqueteEditar = Ingreso.leerString("Ingrese el ID del paquete a editar:");
    int pos = Buscador.buscarPorId(idPaqueteEditar, paquetes, cantidadPaquetes);

    if (pos < 0) {
        JOptionPane.showMessageDialog(null, "Paquete no encontrado.");
        return;
    }

    PaqueteTuristico paquete = paquetes[pos];

    String[] opciones = {
        "1. Descripción",
        "2. Fecha de salida",
        "3. Fecha de regreso",
        "4. Destinos",
        "5. Vuelo",
        "6. Precio total",
        "7. Salir"
    };

    int opcion;

    do {
        opcion = Ingreso.leerEntero(
            "Seleccione el dato que desea editar:\n" +
            String.join("\n", opciones)
        );

        switch (opcion) {
            case 1:
                String nuevaDescripcion = Ingreso.leerString("Ingrese nueva descripción:");
                paquete.setDescripcion(nuevaDescripcion);
                break;
            case 2:
                String nuevaFechaSalida = Ingreso.seleccionarFechaConSpinner("Ingrese nueva fecha de salida:");
                paquete.setFechaSalida(nuevaFechaSalida);
                break;
            case 3:
                String nuevaFechaRegreso = Ingreso.seleccionarFechaConSpinner("Ingrese nueva fecha de regreso:");
                paquete.setFechaRegreso(nuevaFechaRegreso);
                break;
            case 4:
                String destinosInput = Ingreso.leerString("Ingrese los nuevos destinos separados por coma:");
                String[] nuevosDestinos = destinosInput.split("\\s*,\\s*");
                paquete.setDestinos(nuevosDestinos);
                break;
            case 5:
                Vuelo nuevoVuelo = null;
                do {
                    String idVuelo = Ingreso.leerString("Ingrese el ID del nuevo vuelo:");
                    nuevoVuelo = Vuelo.buscarVueloPorId(idVuelo);
                    if (nuevoVuelo == null) {
                        Salida.mError("Vuelo no encontrado. Intente nuevamente.", "Error");
                    }
                } while (nuevoVuelo == null);
                paquete.setVuelo(nuevoVuelo);
                break;
            case 6:
                double nuevoPrecio = Ingreso.leerDouble("Ingrese nuevo precio total:");
                paquete.setPrecioTotal(nuevoPrecio);
                break;
            case 7:
                Salida.mMensaje("Edición finalizada", "Edición de Paquete Turístico");
                break;
            default:
                Salida.mError("Opción inválida", "Error");
        }

    } while (opcion != 7);
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
    if (cantidadPaquetes == 0) {
        JOptionPane.showMessageDialog(null, "No hay paquetes registrados.");
        return;
    }

    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < cantidadPaquetes; i++) {
        sb.append(paquetes[i].toString()).append("\n");
    }

      JTextArea textArea = new JTextArea(sb.toString());
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new java.awt.Dimension(400, 300));

    JOptionPane.showMessageDialog(null, scrollPane, "Lista de Paquetes", JOptionPane.INFORMATION_MESSAGE);
}


    public static PaqueteTuristico buscarPaquetePorId(String idPaqueteTuristico) {
        int pos = Buscador.buscarPorId(idPaqueteTuristico, paquetes, cantidadPaquetes);
        if (pos >= 0) {
            return paquetes[pos];
        }
        return null;
    }

    @Override
    public String toString() {
        return "ID: " + idPaquete + "\n"
                + "Descripción: " + descripcion + "\n"
                + "Fecha de salida: " + fechaSalida + "\n"
                + "Fecha de regreso: " + fechaRegreso + "\n"
                + "Cantidad de noches: " + calcularCantidadNoches() + "\n"
                + "Destinos: " + String.join(", ", destinos) + "\n"
                + "Vuelo: " + (vuelo != null ? vuelo.toString() : "Sin vuelo asignado") + "\n"
                + "Precio total: $" + precioTotal + "\n"
                + "--------------------------------------------------";
    }

    
    // Metodo para listar paquetes por destino (Llamados desde informes)
   public static void mostrarPaquetesPorDestino(String destinoBuscado) {
    boolean encontrado = false;
    StringBuilder sb = new StringBuilder();

    for (int i = 0; i < getCantidad(); i++) {
        PaqueteTuristico paquete = getPaquete(i);

        for (String destino : paquete.getDestinos()) {
            if (destino.equalsIgnoreCase(destinoBuscado)) {
                sb.append(paquete.toString()).append("\n");
                encontrado = true;
                break; // no hace falta seguir buscando en este paquete
            }
        }
    }

    if (encontrado) {
        JTextArea textArea = new JTextArea(sb.toString());
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new java.awt.Dimension(400, 300));
        JOptionPane.showMessageDialog(null, scrollPane, "Paquetes con destino: " + destinoBuscado, JOptionPane.INFORMATION_MESSAGE);
    } else {
        JOptionPane.showMessageDialog(null, "No se encontraron paquetes para el destino: " + destinoBuscado,
                "Resultado de búsqueda", JOptionPane.INFORMATION_MESSAGE);
    }
}


    public int calcularCantidadNoches() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDate salida = LocalDate.parse(fechaSalida, formatter);
    LocalDate regreso = LocalDate.parse(fechaRegreso, formatter);
    return (int) (regreso.toEpochDay() - salida.toEpochDay());
    }
}
