package utils.busquedas;

import clases.Cliente;
import clases.PaqueteTuristico;
import clases.Reserva;
import clases.Vuelo;
import clases.Vendedor;

public class Buscador {
    public static int buscarPorId(String id, Object[] arreglo, int cantidad) {
        ordenarPorId(arreglo, 0);

        int inicio = 0;
        int fin = cantidad - 1;

        while (inicio <= fin) {
            int medio = (inicio + fin) / 2;
            Object obj = arreglo[medio];
            String idActual = obtenerId(obj);

            if (idActual.equals(id))
                return medio;
            if (idActual.compareTo(id) < 0)
                inicio = medio + 1;
            else
                fin = medio - 1;
        }

        return -1;
    }

    public static void ordenarPorId(Object[] arreglo, int cantidad) {
        for (int i = 0; i < cantidad - 1; i++) {
            for (int j = i + 1; j < cantidad; j++) {
                String id1 = obtenerId(arreglo[i]);
                String id2 = obtenerId(arreglo[j]);

                if (id1 != null && id2 != null && id1.compareTo(id2) > 0) {
                    Object temp = arreglo[i];
                    arreglo[i] = arreglo[j];
                    arreglo[j] = temp;
                }
            }
        }
    }

    private static String obtenerId(Object obj) {
        if (obj instanceof Vuelo)
            return ((Vuelo) obj).getIdVuelo();
        if (obj instanceof Reserva)
            return ((Reserva) obj).getIdReserva();
        if (obj instanceof Vendedor)
            return ((Vendedor) obj).getIdVendedor();
        if (obj instanceof Cliente)
        return ((Cliente) obj).getDni();
        if (obj instanceof PaqueteTuristico)
            return String.valueOf(((PaqueteTuristico) obj).getIdPaquete()); // Convierte int en string
        return "";
    }

}
