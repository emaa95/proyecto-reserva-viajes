package menus;

import javax.swing.JOptionPane;

import clases.Persona;
import utils.inputOutputJOP.Ingreso;

public class MenuClientes {
    public static void mostrarMenu() {
        int opcion;
        do {
            String menu = "MENÚ CLIENTES\n"
                        + "1. Registrar un cliente\n"
                        + "2. Editar cliente\n"
                        + "3. Eliminar cliente\n"
                        + "4. Listar todos los clientes\n"
                        + "5. Salir";
            opcion = Integer.parseInt(JOptionPane.showInputDialog(menu));

            switch (opcion) {
                case 1:
                    String nombre = Ingreso.leerString("Ingrese nombre:");
                    String apellido = Ingreso.leerString("Ingrese apellido:");
                    String dni = Ingreso.leerString("Ingrese DNI:");
                    //Persona.cargarPersona(nombre, apellido, dni);
                    break;
                case 2:
                // Verificar si hay clientes registrados antes de editar
                    //if (Persona.getCantidad() == 0) {
                      //  JOptionPane.showMessageDialog(null, "No hay clientes registrados.");
                        //break;
                    //}
                    String dniEditar = Ingreso.leerString("Ingrese DNI del cliente a editar:");
                    String nuevoNombre = Ingreso.leerString("Ingrese nuevo nombre:");
                    String nuevoApellido = Ingreso.leerString("Ingrese nuevo apellido:");
                    //Persona.editarPersona(dniEditar, nuevoNombre, nuevoApellido);
                    break;
                case 3:
                // Verificar si hay clientes registrados antes de eliminar
                    // if (Persona.getCantidad() == 0) {
                    //     JOptionPane.showMessageDialog(null, "No hay clientes registrados.");
                    //     break;
                    // }
                    String dniEliminar = Ingreso.leerString("Ingrese DNI del cliente a eliminar:");
                    //Persona.eliminarPersona(dniEliminar);
                    break;
                case 4:
                    //Persona.verTodasLasPersonas();
                    break;
                case 5:
                    JOptionPane.showMessageDialog(null, "Saliendo del menú de clientes.");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción no válida.");
            }
        } while (opcion != 5);
    }
}
