package datos;

import clases.Vendedor;

public class Datos {
    public static void precargarVendedores() {
        agregarVendedor(new Vendedor("Juan", "Pérez", "12345678", "V001", "Full time"));
        agregarVendedor(new Vendedor("María", "Gómez", "87654321", "V002", "Part time"));
        agregarVendedor(new Vendedor("Luis", "Rodríguez", "11223344", "V003", "Full time"));
    }

    private static void agregarVendedor(Vendedor v) {
        Vendedor.agregarVendedor(v);
    }
}
