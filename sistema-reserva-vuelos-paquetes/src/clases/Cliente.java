package clases;

import javax.swing.JOptionPane;

import utils.busquedas.Buscador;
import utils.inputOutputJOP.Ingreso;

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
    public static boolean cargarCliente() {
        if (cantidad < MAX_CLIENTES) {
            String nombre = Ingreso.leerString("Ingrese nombre:");
            String apellido = Ingreso.leerString("Ingrese apellido:");
            String dni = Ingreso.leerString("Ingrese DNI:");
            String telefono = Ingreso.leerString("Ingrese teléfono:");
            String email = Ingreso.leerString("Ingrese email:");
            clientes[cantidad++] = new Cliente(nombre, apellido, dni, telefono, email);
            Buscador.ordenarPorId(clientes, cantidad);
            JOptionPane.showMessageDialog(null, "Cliente cargado correctamente.");
            return true;
        }
        JOptionPane.showMessageDialog(null, "No se pudo cargar el cliente. Límite alcanzado.");
        return false;
    }

    // Método para editar un cliente por DNI usando búsqueda binaria
    public static boolean editarCliente() {
        String dniEditar = Ingreso.leerString("Ingrese DNI del cliente a editar:");
        int pos = Buscador.buscarPorId(dniEditar, clientes, cantidad);
        if (pos >= 0) {
            String nuevoNombre = Ingreso.leerString("Ingrese nuevo nombre:");
            String nuevoApellido = Ingreso.leerString("Ingrese nuevo apellido:");
            String nuevoTelefono = Ingreso.leerString("Ingrese nuevo teléfono:");
            String nuevoEmail = Ingreso.leerString("Ingrese nuevo email:");
            clientes[pos].setNombre(nuevoNombre);
            clientes[pos].setApellido(nuevoApellido);
            clientes[pos].telefono = nuevoTelefono;
            clientes[pos].email = nuevoEmail;
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

    /*  Método para ordenar el arreglo por DNI (usando burbuja)
    private static void ordenarPorDni() {
        for (int pasada = 0; pasada < cantidad - 1; pasada++) {
            for (int indice = 0; indice < cantidad - pasada - 1; indice++) {
                if (clientes[indice].getDni().compareTo(clientes[indice + 1].getDni()) > 0) {
                    Cliente clienteTemporal = clientes[indice];
                    clientes[indice] = clientes[indice + 1];
                    clientes[indice + 1] = clienteTemporal;
                }
            }
        }
    }
    */

    /*Método de búsqueda binaria por DNI
    private static int buscarPorDni(String dni) {
        int izquierda = 0;
        int derecha = cantidad - 1;
        while (izquierda <= derecha) {
            int medio = (izquierda + derecha) / 2;
            int cmp = clientes[medio].getDni().compareTo(dni);
            if (cmp == 0)
                return medio;
            if (cmp < 0)
                izquierda = medio + 1;
            else
                derecha = medio - 1;
        }
        return -1;
}
        */
    

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
