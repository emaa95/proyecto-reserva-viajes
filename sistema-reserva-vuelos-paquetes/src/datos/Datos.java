package datos;

import java.time.LocalDateTime;

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
        agregarVendedor(new Vendedor("Juan", "Pérez", "12345678", "Full time"));
        agregarVendedor(new Vendedor("María", "Gómez", "87654321", "Part time"));
        agregarVendedor(new Vendedor("Luis", "Rodríguez", "11223344", "Full time"));
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
    // Modificar fecha a formato argentina (dd:mm:aaaa) y hora (hh:mm)

    public static void precargarVuelos() {
        agregarVuelo(new Vuelo("V1001", "Buenos Aires", "Madrid", "2025-08-01", "10:00", 1200.00, 12.5, 100));
        agregarVuelo(new Vuelo("V1002", "Madrid", "Buenos Aires", "2025-08-15", "18:00", 1150.00, 12.0, 100));

        agregarVuelo(new Vuelo("V1003", "Buenos Aires", "Miami", "2025-09-10", "09:30", 950.00, 9.0, 100));
        agregarVuelo(new Vuelo("V1004", "Miami", "Buenos Aires", "2025-09-20", "22:15", 980.00, 9.2, 100));

        agregarVuelo(new Vuelo("V1005", "Córdoba", "Cancún", "2025-10-05", "08:00", 1300.00, 11.0, 100));
        agregarVuelo(new Vuelo("V1006", "Cancún", "Córdoba", "2025-10-18", "16:00", 1250.00, 10.8, 100));

        agregarVuelo(new Vuelo("V1007", "Buenos Aires", "Roma", "2025-11-01", "12:00", 1350.00, 13.0, 100));
        agregarVuelo(new Vuelo("V1008", "Roma", "Buenos Aires", "2025-11-15", "17:30", 1320.00, 13.2, 100));

        agregarVuelo(new Vuelo("V1009", "Mendoza", "Santiago", "2025-08-20", "08:45", 300.00, 1.5, 80));
        agregarVuelo(new Vuelo("V1010", "Santiago", "Mendoza", "2025-08-25", "19:20", 320.00, 1.6, 80));

        agregarVuelo(new Vuelo("V1011", "Salta", "Lima", "2025-09-12", "06:00", 700.00, 5.0, 90));
        agregarVuelo(new Vuelo("V1012", "Lima", "Salta", "2025-09-25", "23:00", 690.00, 5.1, 90));

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
                new String[] { "Madrid", "Barcelona" },
                "V1001,V1002",
                2400.00));

        agregarPaquete(new PaqueteTuristico(
                "P002",
                "Miami All-Inclusive",
                "2025-09-10",
                "2025-09-20",
                new String[] { "Miami" },
                "V1003,V1004",
                2100.00));

        agregarPaquete(new PaqueteTuristico(
                "P003",
                "Cancún Playas",
                "2025-10-05",
                "2025-10-18",
                new String[] { "Cancún" },
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

        Vendedor v1 = Vendedor.buscarVendedorPorId("VE001");
        Vendedor v2 = Vendedor.buscarVendedorPorId("VE002");

        Vuelo ida1 = Vuelo.buscarVueloPorId("V1001");
        Vuelo vuelta1 = Vuelo.buscarVueloPorId("V1002");

        Vuelo ida2 = Vuelo.buscarVueloPorId("V1003");
        Vuelo vuelta2 = Vuelo.buscarVueloPorId("V1004");

        Vuelo ida3 = Vuelo.buscarVueloPorId("V1005");
        Vuelo vuelta3 = Vuelo.buscarVueloPorId("V1006");

        PaqueteTuristico paquete1 = PaqueteTuristico.buscarPaquetePorId("P001");
        PaqueteTuristico paquete2 = PaqueteTuristico.buscarPaquetePorId("P002");
        PaqueteTuristico paquete3 = PaqueteTuristico.buscarPaquetePorId("P003");

        Reserva[] reservas = Reserva.getReservas();
        int cantidad = Reserva.getCantidad();

        // Fechas simuladas
        LocalDateTime fecha1 = LocalDateTime.of(2025, 6, 1, 10, 0);
        LocalDateTime fecha2 = LocalDateTime.of(2025, 6, 2, 11, 30);
        LocalDateTime fecha3 = LocalDateTime.of(2025, 6, 3, 14, 45);
        LocalDateTime fecha4 = LocalDateTime.of(2025, 6, 4, 9, 15);
        LocalDateTime fecha5 = LocalDateTime.of(2025, 6, 5, 13, 0);
        LocalDateTime fecha6 = LocalDateTime.of(2025, 6, 6, 15, 30);
        LocalDateTime fecha7 = LocalDateTime.of(2025, 6, 7, 17, 45);
        // Carga de reservas con fechas definidas
        // Reservas iniciales
        if (ida1 != null)
            ida1.reservarAsientos(2);
        if (vuelta1 != null)
            vuelta1.reservarAsientos(2);
        reservas[cantidad++] = new Reserva(c1, v1, TipoServicio.VUELO, ida1, vuelta1, null, 2, fecha1);

        reservas[cantidad++] = new Reserva(c2, v2, TipoServicio.PAQUETE, null, null, paquete1, 3, fecha2);

        if (ida2 != null)
            ida2.reservarAsientos(1);
        if (vuelta2 != null)
            vuelta2.reservarAsientos(1);
        reservas[cantidad++] = new Reserva(c3, v1, TipoServicio.VUELO, ida2, vuelta2, null, 1, fecha3);

        reservas[cantidad++] = new Reserva(c1, v2, TipoServicio.PAQUETE, null, null, paquete2, 4, fecha4);

        // Reserva 5 – 10 pasajeros en vuelos a Cancún
        if (ida3 != null)
            ida3.reservarAsientos(10);
        if (vuelta3 != null)
            vuelta3.reservarAsientos(10);
        reservas[cantidad++] = new Reserva(c2, v1, TipoServicio.VUELO, ida3, vuelta3, null, 10, fecha5);

        // Reserva 6 – Exagerada: 80 pasajeros en vuelos a Madrid
        if (ida1 != null)
            ida1.reservarAsientos(80);
        if (vuelta1 != null)
            vuelta1.reservarAsientos(80);
        reservas[cantidad++] = new Reserva(c3, v2, TipoServicio.VUELO, ida1, vuelta1, null, 80, fecha6);

        // Reserva 7 – Paquete turístico Cancún Playas
        reservas[cantidad++] = new Reserva(c1, v1, TipoServicio.PAQUETE, null, null, paquete3, 5, fecha7);

        // Opcional: cambiar estados de prueba
        reservas[1].setEstado(EstadoReserva.RESERVADA);
        reservas[2].setEstado(EstadoReserva.CONFIRMADA);
        reservas[3].setEstado(EstadoReserva.PAGADA);

        Reserva.setCantidad(cantidad);
    }
}
