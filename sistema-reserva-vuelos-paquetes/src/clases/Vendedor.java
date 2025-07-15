package clases;

import javax.swing.JOptionPane;

import utils.EliminarPorId;
import utils.busquedas.Buscador;
import utils.inputOutputJOP.Ingreso;
import utils.inputOutputJOP.Salida;

public class Vendedor extends Persona {
    private String idVendedor;
    private String condicion; // "Part time, Full time"

    // Decaración del array
    private static final int MAX_VENDEDORES = 100;
    private static Vendedor[] vendedores = new Vendedor[MAX_VENDEDORES];
    private static int cantidad = 0;

    public Vendedor(String nombre, String apellido, String dni, String id, String condicion) {
        super(nombre, apellido, dni);
        this.idVendedor = id;
        this.condicion = condicion;
    }

    public String getIdVendedor() {
        return idVendedor;
    }

    public String getCondicion() {
        return condicion;
    }

    public void setCondicion(String condicion) {
        this.condicion = condicion;
    }

    public static int getCantidad() {
        return cantidad;
    }

    public static void setCantidad(int nuevaCantidad) {
        cantidad = nuevaCantidad;
    }

    public static void cargarVendedor() {
        if (cantidad >= MAX_VENDEDORES) {
            Salida.mError("Capacidad máxima alcanzada", "Error");
        }

        String nombre = Ingreso.leerString("Ingrese el nombre: ");
        String apellido = Ingreso.leerString("Ingrese apellido");
        String dni = Ingreso.leerString("Ingrese el dni: ");
        String id = Ingreso.leerString("Ingrese el legajo: ");
        String condicion = Ingreso.leerString("Ingrese la condición laboral: ");

        vendedores[cantidad] = new Vendedor(nombre, apellido, dni, id, condicion);
        cantidad++;

        Salida.mConfirmacion("Vendedor cargado correctamente", "Nuevo Vendedor");
    }

    public static void agregarVendedor(Vendedor v) {
        if (cantidad >= MAX_VENDEDORES) {
            Salida.mError("Capacidad máxima alcanzada", "Error");
            return;
        }
        vendedores[cantidad] = v;
        cantidad++;
    }

    public static void editarVendedor() {
        String idBuscado = Ingreso.leerString("Ingrese el legajo (ID) del vendedor a editar:");

        Vendedor vendedor = buscarVendedorPorId(idBuscado);

        if (vendedor == null) {
            Salida.mError("Vendedor no encontrado", "Error");
            return;
        }

        // Mostrar datos actuales
        String mensaje = String.format(
                "Datos actuales:\nNombre: %s\nApellido: %s\nDNI: %s\nCondición: %s",
                vendedor.getNombre(),
                vendedor.getApellido(),
                vendedor.getDni(),
                vendedor.getCondicion());
        Salida.mMensaje(mensaje, "Datos actuales del vendedor");

        // Pedir nuevos datos
        String nuevoNombre = Ingreso.leerString("Ingrese nuevo nombre (actual: " + vendedor.getNombre() + "):");
        String nuevoApellido = Ingreso.leerString("Ingrese nuevo apellido (actual: " + vendedor.getApellido() + "):");
        String nuevoDni = Ingreso.leerString("Ingrese nuevo DNI (actual: " + vendedor.getDni() + "):");
        String nuevaCondicion = Ingreso
                .leerString("Ingrese nueva condición laboral (actual: " + vendedor.getCondicion() + "):");

        // Asignar nuevos datos
        vendedor.setNombre(nuevoNombre);
        vendedor.setApellido(nuevoApellido);
        vendedor.setDni(nuevoDni);
        vendedor.setCondicion(nuevaCondicion);

        Salida.mConfirmacion("Vendedor actualizado correctamente", "Edición exitosa");
    }

    public static void eliminarVendedor() {
        if (Vendedor.getCantidad() == 0) {
            JOptionPane.showMessageDialog(null, "No hay vendedores registrados.");
            return;
        }
        String idEliminar = Ingreso.leerString("Ingrese el legajo del vendedor a eliminar:");

        boolean eliminado = EliminarPorId.eliminarPorId(idEliminar, vendedores, new int[] { cantidad });

        if (eliminado) {
            setCantidad(getCantidad() - 1);
        }
    }

    public static void listarVendedores() {
        StringBuilder lista = new StringBuilder("Lista de Vendedores:\n");
        for (int i = 0; i < cantidad; i++) {
            Vendedor v = vendedores[i];
            lista.append("ID: ").append(v.getIdVendedor())
                    .append(" | Nombre: ").append(v.getNombre()).append(" ").append(v.getApellido())
                    .append(" | DNI: ").append(v.getDni())
                    .append(" | Condición: ").append(v.getCondicion())
                    .append("\n");
        }

        Salida.mMensaje(lista.toString(), "Listado");
    }

    public static Vendedor buscarVendedorPorId(String idVendedor) {
        int pos = Buscador.buscarPorId(idVendedor, vendedores, cantidad);
        if (pos >= 0) {
            return vendedores[pos];
        }
        return null;
    }

}