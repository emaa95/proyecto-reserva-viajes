package clases;

import javax.swing.JOptionPane;

import utils.busquedas.Buscador;
import utils.inputOutputJOP.Ingreso;
import utils.inputOutputJOP.Salida;

public class Reserva {
    private String idReserva;
    private Cliente cliente;
    private Vendedor vendedor;
    private TipoServicio tipo;
    private Vuelo vueloIda;
    private Vuelo vueloVuelta;
    private PaqueteTuristico paquete;
    private int cantidadPasajeros;
    private EstadoReserva estado;

    // Declaración de array Reservas
    private static final int MAX_RESERVAS = 100;
    private static Reserva[] reservas = new Reserva[MAX_RESERVAS];
    private static int cantidad = 0;

    // Contructor
    public Reserva(String idReserva, Cliente cliente, Vendedor vendedor, TipoServicio tipo, Vuelo vueloIda,
            Vuelo vueloVuelta, PaqueteTuristico paquete, int cantidadPasajeros) {
        this.idReserva = idReserva;
        this.cliente = cliente;
        this.vendedor = vendedor;
        this.tipo = tipo;
        this.vueloIda = vueloIda;
        this.vueloVuelta = vueloVuelta;
        this.paquete = paquete;
        this.cantidadPasajeros = cantidadPasajeros;
        this.estado = EstadoReserva.PENDIENTE;
    }

    // Getters y Setters
    public String getIdReserva() {
        return idReserva;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public TipoServicio getTipo() {
        return tipo;
    }

    public Vuelo getVueloIda() {
        return vueloIda;
    }

    public Vuelo getVueloVuelta() {
        return vueloVuelta;
    }

    public PaqueteTuristico getPaquete() {
        return paquete;
    }

    public int getCantidadPasajeros() {
        return cantidadPasajeros;
    }

    public void setCantidadPasajeros(int cantidadPasajeros) {
        this.cantidadPasajeros = cantidadPasajeros;
    }

    public EstadoReserva getEstado() {
        return estado;
    }

    public static int getMaxReservas() {
        return MAX_RESERVAS;
    }

    public static Reserva[] getReservas() {
        return reservas;
    }

    public static void setReservas(Reserva[] reservas) {
        Reserva.reservas = reservas;
    }

    public static int getCantidad() {
        return cantidad;
    }

    public static void setCantidad(int cantidad) {
        Reserva.cantidad = cantidad;
    }

    // Métodos
    public static void cargarReserva() {

        if (cantidad >= MAX_RESERVAS) {
            Salida.mError("Capacidad máxima de reservas alcanzada", "Error");
            return;
        }

        String id = Ingreso.leerString("Ingrese ID de la reserva:");

        Cliente cliente = Cliente.obtenerCliente();
        if (cliente == null) {
            Salida.mError("No se pudo obtener un cliente válido.", "Error");
            return;
        }

        Vendedor vendedor = null;

        do {
            String idVendedor = Ingreso.leerString("Ingrese legajo del vendedor:");
            vendedor = Vendedor.buscarVendedorPorId(idVendedor);
            if (vendedor == null) {
                Salida.mError("Vendedor no encontrado. Intente nuevamente.", "Error");
            } else {
                JOptionPane.showMessageDialog(null, "Vendedor encontrado: " + vendedor.getNombre());
            }

        } while (vendedor == null);

        Vuelo vueloIda = null;
        Vuelo vueloVuelta = null;
        PaqueteTuristico paquete = null;

        TipoServicio[] tipos = TipoServicio.values();
        String[] opciones = new String[tipos.length];
        for (int i = 0; i < tipos.length; i++) {
            opciones[i] = tipos[i].name();
        }

        int seleccion = Ingreso.nOpciones("Seleccione un tipo de servicio: ", opciones, "Tipo de Servicio");
        TipoServicio tipoSeleccionado = tipos[seleccion];


        if (tipoSeleccionado == TipoServicio.VUELO) {
            do {
                String idVuelo = Ingreso.leerString("Ingrese el ID del vuelo: ");
                vueloIda = Vuelo.buscarVueloPorId(idVuelo);
                if (vueloIda == null) {
                    Salida.mError("Vuelo no encontrado. Intente nuevamente.", "Error");
                }
            } while (vueloIda == null);

            boolean agregarViajeVuelta = Ingreso.leerBoolean("Desea agregar vuelo de vuelta?");
            if (agregarViajeVuelta) {
                do {
                    String idVuelo = Ingreso.leerString("Ingrese el ID del vuelo: ");
                    vueloVuelta = Vuelo.buscarVueloPorId(idVuelo);
                    if (vueloVuelta == null) {
                        Salida.mError("Vuelo no encontrado. Intente nuevamente.", "Error");
                    }
                } while (vueloVuelta == null);
            }
        } else {
            do {
                String idPaquete = Ingreso.leerString("Ingrese el ID del paquete: ");
                paquete = PaqueteTuristico.buscarPaquetePorId(idPaquete);
                if (paquete == null) {
                    Salida.mError("Paquete no encontrado. Intente nuevamente.", "Error");
                }
            } while (paquete == null);
        }

        int cantidadPasajeros = Ingreso.leerEnteroPositivo("Ingrese cantidad de pasajeros: ");

        reservas[cantidad++] = new Reserva(id, cliente, vendedor, tipoSeleccionado, vueloIda, vueloVuelta, paquete,
                cantidadPasajeros);

        Salida.mConfirmacion("Reserva cargada correctamente", "Nueva Reserva");

    }

    public static void modificarReserva() {
        if (cantidad == 0) {
            Salida.mError("No hay reservas para modificar.", "Error");
            return;
        }

    }

    public static void verTodasLasReservas() {
        if (cantidad == 0) {
            JOptionPane.showMessageDialog(null, "No hay reservas registradas.", "Reservas",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        StringBuilder sb = new StringBuilder("LISTADO DE RESERVAS\n\n");

        for (int i = 0; i < cantidad; i++) {
            Reserva r = reservas[i];
            sb.append("ID: ").append(r.getIdReserva()).append("\n");
            sb.append("Cliente: ").append(r.getCliente().getNombre()).append(" ").append(r.getCliente().getApellido())
                    .append(" (DNI: ").append(r.getCliente().getDni()).append(")\n");
            sb.append("Vendedor: ").append(r.getVendedor().getNombre()).append(" (Legajo: ")
                    .append(r.getVendedor().getIdVendedor()).append(")\n");
            sb.append("Tipo de servicio: ").append(r.getTipo()).append("\n");

            if (r.getTipo() == TipoServicio.VUELO) {
                sb.append("Vuelo ida: ").append(r.getVueloIda() != null ? r.getVueloIda().getIdVuelo() : "No asignado")
                        .append("\n");
                sb.append("Vuelo vuelta: ")
                        .append(r.getVueloVuelta() != null ? r.getVueloVuelta().getIdVuelo() : "No asignado")
                        .append("\n");
            } else {
                sb.append("Paquete: ").append(r.getPaquete() != null ? r.getPaquete().getDescripcion() : "No asignado")
                        .append("\n");
            }

            sb.append("Cantidad de pasajeros: ").append(r.getCantidadPasajeros()).append("\n");
            sb.append("Estado: ").append(r.getEstado()).append("\n");
            sb.append("------------------------------------------\n");
        }

        JOptionPane.showMessageDialog(null, sb.toString(), "Reservas", JOptionPane.INFORMATION_MESSAGE);
    }

    // Método que devuelvo un objeto Reserva
    public static Reserva buscarReservaPorId(String idReserva) {
        int pos = Buscador.buscarPorId(idReserva, reservas, cantidad);
        if (pos >= 0) {
            return reservas[pos];
        }
        return null;
    }

    // Método para buscar y mostrar un vuelo por idVuelo
    public static void buscarYMostrarReservaPorId(String idReserva) {
        Reserva encontrada = buscarReservaPorId(idReserva);
        if (encontrada != null) {
            Salida.mMensaje(encontrada.toString(), idReserva);
        } else {
            Salida.mError("Reserva no encontrada", "Búsqueda de reserva");
        }
    }

}
