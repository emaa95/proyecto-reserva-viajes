package utils;

import javax.swing.JOptionPane;

public class EliminarPorId {
    public static boolean eliminarPorId(String id, Object[] arreglo, int[] cantidad) {
        String nombreClase = "Objeto"; // Valor por defecto

        // Intentar encontrar el nombre de la clase desde el arreglo si no está vacío
        for (int i = 0; i < cantidad[0]; i++) {
            if (arreglo[i] != null) {
                nombreClase = arreglo[i].getClass().getSimpleName();
                break;
            }
        }

        for (int i = 0; i < cantidad[0]; i++) {
            if (arreglo[i] == null)
                continue;

            String idActual = obtenerId(arreglo[i]);

            if (idActual.equals(id)) {
                nombreClase = arreglo[i].getClass().getSimpleName(); // Reconfirmar tipo antes de eliminar

                // Eliminar el objeto (swap con el último)
                arreglo[i] = arreglo[cantidad[0] - 1];
                arreglo[cantidad[0] - 1] = null;
                cantidad[0]--;

                JOptionPane.showMessageDialog(null, nombreClase + " eliminado correctamente.");
                return true;
            }
        }

        JOptionPane.showMessageDialog(null, nombreClase + " no encontrado.");
        return false;
    }

    private static String obtenerId(Object obj) {
        if (obj instanceof clases.Vuelo)
            return ((clases.Vuelo) obj).getIdVuelo();
        // Agregar las instancias que hagan falta
        if (obj instanceof clases.Reserva)
            return ((clases.Reserva) obj).getIdReserva();
        if (obj instanceof clases.Vendedor)
            return ((clases.Vendedor) obj).getIdVendedor();
        return "";
    }
}