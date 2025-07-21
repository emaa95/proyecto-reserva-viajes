package utils.Ordenamientos;

import javax.swing.JOptionPane;

import clases.PaqueteTuristico;


public class OrdenPrecio {
    // Método para ordenar paquetes turísticos por precio ascendente(Método Burbuja)
        public static void ordenarPaquetesPrecioAscendente(PaqueteTuristico[] paquetes) {
        int n = paquetes.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (paquetes[j].getPrecioTotal() > paquetes[j + 1].getPrecioTotal()) {
                    // Intercambiar los elementos
                    PaqueteTuristico temp = paquetes[j];
                    paquetes[j] = paquetes[j + 1];
                    paquetes[j + 1] = temp;
                }
            }
        }
    }

    // Método para ordenar los paquetes por precio de forma descendente (Método burbuja)
     public static void ordenarPaquetesPrecioDescendente(PaqueteTuristico[] paquetes) {
        int n = paquetes.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (paquetes[j].getPrecioTotal() < paquetes[j + 1].getPrecioTotal()) {
                    // Intercambiar los elementos
                    PaqueteTuristico temp = paquetes[j];
                    paquetes[j] = paquetes[j + 1];
                    paquetes[j + 1] = temp;
                }
            }
        }
    }

    // Método para mostrar los paquetes ordenados por precio
    public static void mostrarPaquetesOrdenadosPorPrecio(boolean ascendente) {
        if (PaqueteTuristico.getCantidad() == 0) {
            JOptionPane.showMessageDialog(null, "No hay paquetes registrados.");
            return;
        }
        // Crear una copia manual del array
        PaqueteTuristico[] copia = new PaqueteTuristico[PaqueteTuristico.getCantidad()];
        for (int i = 0; i < PaqueteTuristico.getCantidad(); i++) {
            copia[i] = PaqueteTuristico.getPaquete(i);
        }

        // Ordenar manualmente según el parámetro
        if (ascendente) {
            OrdenPrecio.ordenarPaquetesPrecioAscendente(copia);
        } else {
            OrdenPrecio.ordenarPaquetesPrecioDescendente(copia);
        }

        // Construir la salida
        StringBuilder sb = new StringBuilder();
        sb.append("== Paquetes Turísticos Ordenados por Precio ")
        .append(ascendente ? "Ascendente" : "Descendente").append(" ==\n\n");

        for (int i = 0; i < copia.length; i++) {
            PaqueteTuristico p = copia[i];
            sb.append("Descripción: ").append(p.getDescripcion()).append("\n")
            .append("Fecha salida: ").append(p.getFechaSalida()).append("\n")
            .append("Fecha regreso: ").append(p.getFechaRegreso()).append("\n")
            .append("Precio total: $").append(p.getPrecioTotal()).append("\n")
            .append("-------------------------------------\n");
        }

        // Mostrar por JOptionPane
        JOptionPane.showMessageDialog(null, sb.toString());
    }
}
