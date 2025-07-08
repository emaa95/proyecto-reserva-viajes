package clases;

public class Persona {
    private String nombre;
    private String apellido;
    private String dni;

    // Arreglo estático para almacenar personas
    // private static final int MAX_PERSONAS = 100;
    // private static Persona[] personas = new Persona[MAX_PERSONAS];
    // private static int cantidad = 0;
    
    // Constructor
    public Persona(String nombre, String apellido, String dni) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
    }

    // Getters y Setters
    public String getNombre() {return nombre;}

    public String getApellido() {return apellido;}

    public String getDni() {return dni;}

    public void setNombre(String nombre) {this.nombre = nombre;}

    public void setApellido(String apellido) {this.apellido = apellido;}

    public void setDni(String dni) {this.dni = dni;}

    @Override
    public String toString() {
        return "Nombre: " + nombre + ", Apellido: " + apellido + ", DNI: " + dni;
    }

    // Método para cargar una nueva persona
//     public static boolean cargarPersona(String nombre, String apellido, String dni) {
//         if (cantidad < MAX_PERSONAS) {
//             personas[cantidad++] = new Persona(nombre, apellido, dni);
//             ordenarPorDni();
//             JOptionPane.showMessageDialog(null, "Persona cargada correctamente.");
//             return true;
//         }
//         JOptionPane.showMessageDialog(null, "No se pudo cargar la persona. Límite alcanzado.");
//         return false;
//     }

    
//     // Método para editar una persona por DNI usando búsqueda binaria
//     public static boolean editarPersona(String dni, String nuevoNombre, String nuevoApellido) {
//         int pos = buscarPorDni(dni);
//         if (pos >= 0) {
//             personas[pos].nombre = nuevoNombre;
//             personas[pos].apellido = nuevoApellido;
//             JOptionPane.showMessageDialog(null, "Persona editada correctamente.");
//             return true;
//         }
//         JOptionPane.showMessageDialog(null, "Persona no encontrada.");
//         return false;
//     }

    
//     // Método para eliminar una persona por DNI usando búsqueda binaria
//     public static boolean eliminarPersona(String dni) {
//         int pos = buscarPorDni(dni);
//         if (pos >= 0) {
//             personas[pos] = personas[cantidad - 1];
//             personas[cantidad - 1] = null;
//             cantidad--;
//             ordenarPorDni();
//             JOptionPane.showMessageDialog(null, "Persona eliminada correctamente.");
//             return true;
//         }
//         JOptionPane.showMessageDialog(null, "Persona no encontrada.");
//         return false;
//     }
//     // Método para ver todas las personas
//     public static void verTodasLasPersonas() {
//         StringBuilder sb = new StringBuilder();
//         if (cantidad == 0){
//             sb.append("No hay personas registradas.");
//         } else {
//             for (int i = 0; i < cantidad; i++) {
//                 sb.append("====================================\n");
//                 sb.append("Nombre: ").append(personas[i].nombre)
//                   .append(", Apellido: ").append(personas[i].apellido)
//                   .append(", DNI: ").append(personas[i].dni).append("\n");
//                 sb.append("====================================\n\n");
//             }
//         }
//         JOptionPane.showMessageDialog(null, sb.toString());
//     }

    
//     // Método de búsqueda binaria por DNI
//     private static int buscarPorDni(String dni) {
//         int izquierda = 0;
//         int derecha = cantidad - 1;
//         while (izquierda <= derecha) {
//             int medio = (izquierda + derecha) / 2;
//             int cmp = personas[medio].dni.compareTo(dni);
//             if (cmp == 0) return medio;
//             if (cmp < 0) izquierda = medio + 1;
//             else derecha = medio - 1;
//         }
//         return -1;
//     }

    
//     // Método para ordenar el arreglo por DNI (usando burbuja)
//     private static void ordenarPorDni() {
//     for (int pasada = 0; pasada < cantidad - 1; pasada++) {
//         for (int indice = 0; indice < cantidad - pasada - 1; indice++) {
//             if (personas[indice].dni.compareTo(personas[indice + 1].dni) > 0) {
//                 Persona personaTemporal = personas[indice];
//                 personas[indice] = personas[indice + 1];
//                 personas[indice + 1] = personaTemporal;
//                 }
//             }
//         }
//     }

//     public static int getCantidad() {
//     return cantidad;
// }
}
