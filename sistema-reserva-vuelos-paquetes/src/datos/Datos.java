package datos;

import clases.Cliente;
import clases.EstadoReserva;
import clases.PaqueteTuristico;
import clases.Reserva;
import clases.TipoServicio;
import clases.Vendedor;
import clases.Vuelo;

public class Datos {

    public static void precargarTodo() {
        precargarVendedores();
        precargarClientes();
        precargarVuelos();
        precargarPaquetes();
        precargarReservas();
    }

    // Vendedores
    public static void precargarVendedores() {
        agregarVendedor(new Vendedor("Juan", "Pérez", "12345678", "V001", "Full time"));
        agregarVendedor(new Vendedor("María", "Gómez", "87654321", "V002", "Part time"));
        agregarVendedor(new Vendedor("Luis", "Rodríguez", "11223344", "V003", "Full time"));
    }

    private static void agregarVendedor(Vendedor v) {
        Vendedor.agregarVendedor(v);
    }

    // Clientes
    public static void precargarClientes() {
        agregarCliente(new Cliente("Ana", "Torres", "44556677", "111-222", "ana@mail.com"));
        agregarCliente(new Cliente("Carlos", "Fernández", "99887766", "222-333", "carlos@mail.com"));
        agregarCliente(new Cliente("Laura", "García", "33445566", "333-444", "laura@mail.com"));
    }

    private static void agregarCliente(Cliente c) {
        Cliente.agregarCliente(c);
    }

    // Vuelos (ida y vuelta)
    public static void precargarVuelos() {
        agregarVuelo(new Vuelo("V1001", "Buenos Aires", "Madrid", "2025-08-01 10:00", 1200.00, 12.5));
        agregarVuelo(new Vuelo("V1002", "Madrid", "Buenos Aires", "2025-08-15 18:00", 1150.00, 12.0));

        agregarVuelo(new Vuelo("V1003", "Buenos Aires", "Miami", "2025-09-10 09:30", 950.00, 9.0));
        agregarVuelo(new Vuelo("V1004", "Miami", "Buenos Aires", "2025-09-20 22:15", 980.00, 9.2));

        agregarVuelo(new Vuelo("V1005", "Córdoba", "Cancún", "2025-10-05 08:00", 1300.00, 11.0));
        agregarVuelo(new Vuelo("V1006", "Cancún", "Córdoba", "2025-10-18 16:00", 1250.00, 10.8));
    }

    private static void agregarVuelo(Vuelo v) {
        Vuelo.agregarVuelo(v);  
    }

    // Paquetes turísticos
    public static void precargarPaquetes() {
        agregarPaquete(new PaqueteTuristico(
            "P001", 
            "Madrid y Barcelona - 2 semanas", 
            "2025-08-01", 
            "2025-08-15", 
            new String[] {"Madrid", "Barcelona"}, 
            "V1001,V1002", 
            2400.00));

        agregarPaquete(new PaqueteTuristico(
            "P002", 
            "Miami All-Inclusive", 
            "2025-09-10", 
            "2025-09-20", 
            new String[] {"Miami"}, 
            "V1003,V1004", 
            2100.00));

        agregarPaquete(new PaqueteTuristico(
            "P003", 
            "Cancún Playas", 
            "2025-10-05", 
            "2025-10-18", 
            new String[] {"Cancún"}, 
            "V1005,V1006", 
            2500.00));
    }

    private static void agregarPaquete(PaqueteTuristico p) {
        PaqueteTuristico.agregarPaquete(p); 
    }

    public static void precargarReservas() {
        Cliente c1 = Cliente.buscarClientePorDni("44556677"); // Ana Torres
        Cliente c2 = Cliente.buscarClientePorDni("99887766"); // Carlos Fernández
        Cliente c3 = Cliente.buscarClientePorDni("33445566"); // Laura García

        Vendedor v1 = Vendedor.buscarVendedorPorId("V001");
        Vendedor v2 = Vendedor.buscarVendedorPorId("V002");

        Vuelo ida1 = Vuelo.buscarVueloPorId("V1001");
        Vuelo vuelta1 = Vuelo.buscarVueloPorId("V1002");

        Vuelo ida2 = Vuelo.buscarVueloPorId("V1003");
        Vuelo vuelta2 = Vuelo.buscarVueloPorId("V1004");

        PaqueteTuristico paquete1 = PaqueteTuristico.buscarPaquetePorId("P001");
        PaqueteTuristico paquete2 = PaqueteTuristico.buscarPaquetePorId("P002");

        Reserva[] reservas = Reserva.getReservas();
        int cantidad = Reserva.getCantidad();

        reservas[cantidad++] = new Reserva(null, c1, v1, TipoServicio.VUELO, ida1, vuelta1, null, 2);
        reservas[cantidad++] = new Reserva(null, c2, v2, TipoServicio.PAQUETE, null, null, paquete1, 3);
        reservas[cantidad++] = new Reserva(null, c3, v1, TipoServicio.VUELO, ida2, vuelta2, null, 1);
        reservas[cantidad++] = new Reserva(null, c1, v2, TipoServicio.PAQUETE, null, null, paquete2, 4);

        // Opcional: cambiar estados de prueba
        reservas[1].setEstado(EstadoReserva.RESERVADA);
        reservas[2].setEstado(EstadoReserva.CONFIRMADA);
        reservas[3].setEstado(EstadoReserva.PAGADA);

        Reserva.setCantidad(cantidad);
    }
}

