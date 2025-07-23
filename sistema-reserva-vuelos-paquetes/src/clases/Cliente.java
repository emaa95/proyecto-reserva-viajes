package clases;

import javax.swing.JOptionPane;
import utils.busquedas.Buscador;
import utils.inputOutputJOP.Ingreso;
import utils.inputOutputJOP.Salida;

public class Cliente extends Persona {
    private String telefono;
    private String email;
    
    // Arreglo estático para almacenar clientes
    private static final int MAX_CLIENTES = 100;
    private static Cliente[] clientes = new Cliente[MAX_CLIENTES];
    private static int cantidad = 0;

    // Constructor
    public Cliente(String nombre, String apellido, String dni, String telefono, String email) {
        super(nombre, apellido, dni);
        this.telefono = telefono;
        this.email = email;
    }

    // Método para cargar un nuevo cliente
    public static Cliente cargarCliente() {
        if (cantidad < MAX_CLIENTES) {
            String nombre = Ingreso.leerString("Ingrese nombre:");
            String apellido = Ingreso.leerString("Ingrese apellido:");
            String dni = Ingreso.leerString("Ingrese DNI:");
            // Validar que el DNI no exista
            int indiceExistente = Buscador.buscarPorId(dni, clientes, cantidad);
            if (indiceExistente != -1) {
                JOptionPane.showMessageDialog(null, "Ya existe un cliente con ese DNI.");
                return null;
            }
            String telefono = Ingreso.leerString("Ingrese teléfono:");
            String email = Ingreso.leerString("Ingrese email:");

            Cliente nuevoCliente = new Cliente(nombre, apellido, dni, telefono, email);
            clientes[cantidad++] = nuevoCliente;
            Buscador.ordenarPorId(clientes, cantidad);
            JOptionPane.showMessageDialog(null, "Cliente cargado correctamente.");
            return nuevoCliente;
        } else {
            JOptionPane.showMessageDialog(null, "No se pudo cargar el cliente. Límite alcanzado.");
            return null;
        }
    }

    public static void agregarCliente(Cliente c) {
        if (cantidad < MAX_CLIENTES) {
            clientes[cantidad++] = c;
        }
    }

    // Método para editar un cliente por DNI usando búsqueda binaria
    public static boolean editarCliente() {
        String dniEditar = Ingreso.leerString("Ingrese DNI del cliente a editar:");
        int pos = Buscador.buscarPorId(dniEditar, clientes, cantidad);
        if (pos >= 0) {

            // Mostrar datos actuales
            String mensaje = String.format(
                "Datos actuales:\nNombre: %s\nApellido: %s\nDNI: %s\nTeléfono: %s\nEmail: %s",
                clientes[pos].getNombre(),
                clientes[pos].getApellido(),
                clientes[pos].getDni(),
                clientes[pos].getTelefono(),
                clientes[pos].getEmail());
            Salida.mMensaje(mensaje, "Datos actuales del cliente");

            String nuevoNombre = Ingreso.leerString("Ingrese nuevo nombre (actual: " + clientes[pos].getNombre() + "):");
            String nuevoApellido = Ingreso.leerString("Ingrese nuevo apellido (actual: " + clientes[pos].getApellido() + "):");
            String nuevoTelefono = Ingreso.leerString("Ingrese nuevo teléfono (actual: " + clientes[pos].getTelefono() + "):");
            String nuevoEmail = Ingreso.leerString("Ingrese nuevo email (actual: " + clientes[pos].getEmail() + "):");
            
            // Actualizar los datos del cliente
            clientes[pos].setNombre(nuevoNombre);
            clientes[pos].setApellido(nuevoApellido);
            clientes[pos].setTelefono(nuevoTelefono);
            clientes[pos].setEmail(nuevoEmail);
            JOptionPane.showMessageDialog(null, "Cliente editado correctamente.");
            return true;
        }
        JOptionPane.showMessageDialog(null, "Cliente no encontrado.");
        return false;
    }

    // Método para eliminar un cliente por DNI usando búsqueda binaria
    public static boolean eliminarCliente() {
        String dniEliminar = Ingreso.leerString("Ingrese DNI del cliente a eliminar:");
        int pos = Buscador.buscarPorId(dniEliminar, clientes, cantidad);
        if (pos >= 0) {
            clientes[pos] = clientes[cantidad - 1];
            clientes[cantidad - 1] = null;
            cantidad--;
            Buscador.ordenarPorId(clientes, cantidad);
            JOptionPane.showMessageDialog(null, "Cliente eliminado correctamente.");
            return true;
        }
        JOptionPane.showMessageDialog(null, "Cliente no encontrado.");
        return false;
    }

    // Método para ver todos los clientes
    public static void verTodosLosClientes() {
        StringBuilder sb = new StringBuilder();
        if (cantidad == 0) {
            sb.append("No hay clientes registrados.");
        } else {
            for (int i = 0; i < cantidad; i++) {
                sb.append("====================================\n");
                sb.append("Nombre: ").append(clientes[i].getNombre())
                        .append(", Apellido: ").append(clientes[i].getApellido())
                        .append(", DNI: ").append(clientes[i].getDni()).append("\n")
                        .append("Número de teléfono: ").append(clientes[i].getTelefono()).append("\n")
                        .append("Email: ").append(clientes[i].getEmail()).append("\n");
                sb.append("====================================\n\n");
            }
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }

    public static Cliente buscarClientePorDni(String dni) {
        int pos = Buscador.buscarPorId(dni, clientes, cantidad);
        if (pos >= 0) {
            return clientes[pos];
        }
        return null;
    }

    public static Cliente obtenerCliente() {
    String dniCliente = Ingreso.leerString("Ingrese el DNI del cliente: ");
    Cliente cliente = Cliente.buscarClientePorDni(dniCliente);
    if (cliente == null) {
        Salida.mError("Cliente no encontrado", "Error");
        boolean respuesta = Ingreso.leerBoolean("¿Desea agregar un nuevo cliente?");
        if (respuesta) {
            cliente = Cliente.cargarCliente();
        }
    }
    return cliente;
    }

    // Método para mostrar datos

    @Override
    public String toString() {
        return "Cliente [telefono=" + telefono + ", email=" + email + "]";
    }

    // Getters y Setters
    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
