package clases;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import utils.busquedas.Buscador;
import utils.inputOutputJOP.Ingreso;
import utils.inputOutputJOP.Salida;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Reserva {
    private static int contadorId = 1;
    private String idReserva;
    private Cliente cliente;
    private Vendedor vendedor;
    private TipoServicio tipo;
    private Vuelo vueloIda;
    private Vuelo vueloVuelta;
    private PaqueteTuristico paquete;
    private int cantidadPasajeros;
    private EstadoReserva estado;
    private double precioFinal;
    private LocalDateTime fechaCreacion;
    
    public enum FormaDePago {
        EFECTIVO,
        TARJETA_CREDITO,
        TARJETA_DEBITO,
        TRANSFERENCIA,
        MERCADO_PAGO
    }

    private FormaDePago formaDePago;

    // Declaración de array Reservas
    private static final int MAX_RESERVAS = 100;
    private static Reserva[] reservas = new Reserva[MAX_RESERVAS];
    private static int cantidad = 0;

    public Reserva(Cliente cliente, Vendedor vendedor, TipoServicio tipo,
            Vuelo vueloIda, Vuelo vueloVuelta, PaqueteTuristico paquete,
            int cantidadPasajeros, LocalDateTime fechaCreacion, FormaDePago formaDePago) {

        this.idReserva = String.format("R%03d", contadorId++);
        this.cliente = cliente;
        this.vendedor = vendedor;
        this.tipo = tipo;
        this.vueloIda = vueloIda;
        this.vueloVuelta = vueloVuelta;
        this.paquete = paquete;
        this.cantidadPasajeros = cantidadPasajeros;
        this.estado = EstadoReserva.PENDIENTE;
        this.precioFinal = 0.0;
        this.fechaCreacion = (fechaCreacion != null) ? fechaCreacion : LocalDateTime.now();
        this.formaDePago = (formaDePago != null) ? formaDePago : FormaDePago.EFECTIVO;
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

    public void setEstado(EstadoReserva estado) {
        this.estado = estado;
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

    public double getPrecioFinal() {
        return precioFinal;
    }

    public void setPrecioFinal(double precioFinal) {
        this.precioFinal = precioFinal;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public FormaDePago getFormaDePago() {
        return formaDePago;
    }

    public void setFormaDePago(FormaDePago formaDePago) {
        this.formaDePago = formaDePago;
    }

    // Métodos
    public double calcularTotal() {
    double total = 0;

    if (tipo == TipoServicio.VUELO) {
        if (vueloIda != null) {
            total += vueloIda.calcularPrecioFinal(); 
        }
        if (vueloVuelta != null) {
            total += vueloVuelta.calcularPrecioFinal();
        }
    } else if (tipo == TipoServicio.PAQUETE) {
        if (paquete != null) {
            total += paquete.getPrecioTotal();
        }
    }

    total *= cantidadPasajeros;
    this.precioFinal = total;

    return total;
}


    public static void cargarReserva() {

        if (cantidad >= MAX_RESERVAS) {
            Salida.mError("Capacidad máxima de reservas alcanzada", "Error");
            return;
        }

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

        int cantidadPasajeros = Ingreso.leerEnteroPositivo("Ingrese cantidad de pasajeros: ");

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

                if (vueloIda.getAsientosDisponibles() < cantidadPasajeros) {
                    Salida.mError("No hay suficientes asientos disponibles en el vuelo de ida.", "Error");
                    vueloIda = null; 
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

                    if (vueloVuelta.getAsientosDisponibles() < cantidadPasajeros) {
                        Salida.mError("No hay suficientes asientos disponibles en el vuelo de vuelta.", "Error");
                        vueloVuelta = null; 
                    }
                } while (vueloVuelta == null);
            }
               vueloIda.reservarAsientos(cantidadPasajeros);
        if (vueloVuelta != null) {
            vueloVuelta.reservarAsientos(cantidadPasajeros);
        }
        } else {
            do {
                String idPaquete = Ingreso.leerString("Ingrese el ID del paquete: ");
                paquete = PaqueteTuristico.buscarPaquetePorId(idPaquete);
                if (paquete == null) {
                    Salida.mError("Paquete no encontrado. Intente nuevamente.", "Error");
                }
            } while (paquete == null);

             Vuelo vueloDelPaquete = paquete.getVuelo();
            if (vueloDelPaquete != null) {
        if (vueloDelPaquete.getAsientosDisponibles() < cantidadPasajeros) {
            Salida.mError("No hay suficientes asientos disponibles en el vuelo del paquete.", "Error");
            return; 
        }
        vueloDelPaquete.reservarAsientos(cantidadPasajeros);
    }
        }


        Reserva reservaTemporal = new Reserva(cliente, vendedor, tipoSeleccionado, vueloIda, vueloVuelta, paquete,
                cantidadPasajeros, null, null);

        double totalDolares = reservaTemporal.calcularTotal();

        Salida.mMensaje(String.format("Precio final en dólares: $%.2f", totalDolares), "Precio de la Reserva");

        boolean consultaEnPesos = Ingreso.leerBoolean("¿Desea ver el precio en pesos?");

        if (consultaEnPesos) {
            double precioDolar = Ingreso.leerDoublePositivo("Ingrese el precio del dólar del día:");
            double precioPesos = totalDolares * precioDolar;
            Salida.mMensaje(String.format("Precio final en pesos: $%.2f", precioPesos), "Precio de la Reserva");
        }

        FormaDePago[] formas = FormaDePago.values();
        String[] opcionesPago = new String[formas.length];
        for (int i = 0; i < formas.length; i++) opcionesPago[i] = formas[i].name();

        int selPago = Ingreso.nOpciones("Seleccione forma de pago:", opcionesPago, "Forma de Pago");
        reservaTemporal.setFormaDePago(formas[selPago]);

        reservaTemporal.setPrecioFinal(totalDolares);

        reservas[cantidad++] = reservaTemporal;

        Salida.mMensaje("Reserva cargada correctamente.\n\n" + reservaTemporal.toString(), "Nueva Reserva");

    }

    public static void modificarReserva() {
        if (cantidad == 0) {
            Salida.mError("No hay reservas para modificar.", "Error");
            return;
        }

        String id = Ingreso.leerString("Ingrese ID de la reserva a modificar:");
        Reserva reserva = buscarReservaPorId(id);

        if (reserva == null) {
            Salida.mError("Reserva no encontrada.", "Error");
            return;
        }

        EstadoReserva estado = reserva.getEstado();

        // No se puede modificar en estados finales
        if (estado == EstadoReserva.COMPLETADA || estado == EstadoReserva.CANCELADA ||
                estado == EstadoReserva.RECHAZADA || estado == EstadoReserva.EXPIRADA ||
                estado == EstadoReserva.FALLIDA || estado == EstadoReserva.REEMBOLSADA) {
            Salida.mError("No se puede modificar una reserva con estado: " + estado, "Acción no permitida");
            return;
        }

        boolean continuar = true;

        while (continuar) {
            String[] opciones;

            // Definir menú según estado actual
            if (estado == EstadoReserva.PENDIENTE || estado == EstadoReserva.EN_PROCESO) {
                opciones = new String[] { "Modificar cantidad de pasajeros", "Modificar vuelos o paquete",
                        "Modificar estado", "Salir" };
            } else if (estado == EstadoReserva.RESERVADA) {
                opciones = new String[] { "Modificar cantidad de pasajeros", "Modificar estado", "Salir" };
            } else if (estado == EstadoReserva.PAGADA ||  estado == EstadoReserva.EN_CURSO) {
                opciones = new String[] { "Modificar estado", "Salir" };
            } else {
                opciones = new String[] { "Salir" };
            }


            int seleccion = Ingreso.nOpciones("Seleccione acción para la reserva (" + estado + "):", opciones,
                    "Modificar Reserva");
            String opcion = opciones[seleccion];

            switch (opcion) {
                case "Modificar cantidad de pasajeros":
                     int nuevaCantidad = Ingreso.leerEnteroPositivo("Ingrese nueva cantidad de pasajeros:");
    // Actualizamos asientos usando el método centralizado
    reserva.actualizarAsientos(nuevaCantidad, reserva.getVueloIda(), reserva.getVueloVuelta());
    Salida.mMensaje("Cantidad de pasajeros y asientos actualizados.", "OK");
    break;

                case "Modificar vuelos o paquete":
                    int cantidadPasajeros = reserva.getCantidadPasajeros();
                    if (reserva.getTipo() == TipoServicio.VUELO) {
                        Vuelo ida = null;
                        if (reserva.vueloIda != null) {
                        reserva.vueloIda.liberarAsientos(cantidadPasajeros);
                    }
                    if (reserva.vueloVuelta != null) {
                        reserva.vueloVuelta.liberarAsientos(cantidadPasajeros);
                    }
                        do {
                            String idIda = Ingreso.leerString("Nuevo ID de vuelo de ida:");
                            ida = Vuelo.buscarVueloPorId(idIda);
                            if (ida == null) {
                            Salida.mError("Vuelo no encontrado.", "Error");
                        } else if (ida.getAsientosDisponibles() < cantidadPasajeros) {
                            Salida.mError("No hay suficientes asientos disponibles.", "Error");
                            ida = null;
                        }
                        } while (ida == null);
                        reserva.vueloIda = ida;

                        boolean editarVuelta = Ingreso.leerBoolean("¿Desea modificar o eliminar vuelo de vuelta?");
                        if (editarVuelta) {
                            boolean agregar = Ingreso.leerBoolean("¿Asignar un nuevo vuelo de vuelta?");
                            if (agregar) {
                                Vuelo vuelta = null;
                                do {
                                    String idVuelta = Ingreso.leerString("Nuevo ID de vuelo de vuelta:");
                                    vuelta = Vuelo.buscarVueloPorId(idVuelta);
                                      if (vuelta == null) {
                                    Salida.mError("Vuelo no encontrado.", "Error");
                                } else if (vuelta.getAsientosDisponibles() < cantidadPasajeros) {
                                    Salida.mError("No hay suficientes asientos disponibles.", "Error");
                                    vuelta = null;
                                }
                                } while (vuelta == null);
                                vuelta.reservarAsientos(cantidadPasajeros);
                                reserva.vueloVuelta = vuelta;
                            } else {
                                reserva.vueloVuelta = null;
                            }
                        }

                    } else { // PAQUETE
                        PaqueteTuristico nuevo = null;
                        do {
                            String idPaq = Ingreso.leerString("Nuevo ID de paquete:");
                            nuevo = PaqueteTuristico.buscarPaquetePorId(idPaq);
                            if (nuevo == null) {
                                Salida.mError("Paquete no encontrado.", "Error");
                            }
                        } while (nuevo == null);
                        reserva.paquete = nuevo;
                    }
                    Salida.mMensaje("Servicio actualizado.", "OK");
                    break;

                case "Modificar estado":
                    EstadoReserva[] posiblesEstados;
                    if (estado == EstadoReserva.PENDIENTE || estado == EstadoReserva.EN_PROCESO) {
                        posiblesEstados = new EstadoReserva[] { EstadoReserva.RESERVADA, EstadoReserva.CANCELADA };
                    } else if (estado == EstadoReserva.RESERVADA) {
                        posiblesEstados = new EstadoReserva[] { EstadoReserva.CONFIRMADA, EstadoReserva.CANCELADA };
                    } else if (estado == EstadoReserva.CONFIRMADA) {
                        posiblesEstados = new EstadoReserva[] { EstadoReserva.PAGADA, EstadoReserva.CANCELADA };
                    } else if (estado == EstadoReserva.PAGADA) {
                        posiblesEstados = new EstadoReserva[] { EstadoReserva.EN_CURSO, EstadoReserva.REEMBOLSADA };
                    } else if (estado == EstadoReserva.EN_CURSO) {
                        posiblesEstados = new EstadoReserva[] { EstadoReserva.COMPLETADA, EstadoReserva.SUSPENDIDA };
                    } else if (estado == EstadoReserva.SUSPENDIDA) {
                        posiblesEstados = new EstadoReserva[] { EstadoReserva.EN_CURSO, EstadoReserva.CANCELADA };
                    } else {
                        posiblesEstados = new EstadoReserva[0];
                    }

                    if (posiblesEstados.length == 0) {
                        Salida.mError("No se puede cambiar el estado actual.", "Error");
                        break;
                    }

                    String[] opcionesEstado = new String[posiblesEstados.length];
                    for (int i = 0; i < posiblesEstados.length; i++) {
                        opcionesEstado[i] = posiblesEstados[i].name();
                    }

                    int seleccionEstado = Ingreso.nOpciones("Seleccione nuevo estado:", opcionesEstado, "Estado");
                    reserva.estado = posiblesEstados[seleccionEstado];
                    estado = reserva.estado;
                    Salida.mMensaje("Estado actualizado a " + estado, "OK");
                    break;

                case "Salir":
                    continuar = false;
                    break;
            }
        }

        Salida.mConfirmacion("Modificación de reserva finalizada.", "OK");
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ID Reserva: ").append(idReserva).append("\n");
        sb.append("Cliente: ").append(cliente.getNombre()).append(" ").append(cliente.getApellido())
                .append(" (DNI: ").append(cliente.getDni()).append(")\n");
        sb.append("Vendedor: ").append(vendedor.getNombre())
                .append(" (Legajo: ").append(vendedor.getIdVendedor()).append(")\n");
        sb.append("Tipo de servicio: ").append(tipo).append("\n");

        if (tipo == TipoServicio.VUELO) {
            sb.append("Vuelo Ida: ").append(vueloIda != null ? vueloIda.getIdVuelo() : "No asignado").append("\n");
            sb.append("Vuelo Vuelta: ").append(vueloVuelta != null ? vueloVuelta.getIdVuelo() : "No asignado")
                    .append("\n");
        } else if (tipo == TipoServicio.PAQUETE) {
            sb.append("Paquete: ").append(paquete != null ? paquete.getDescripcion() : "No asignado").append("\n");
        }

        sb.append("Cantidad de Pasajeros: ").append(cantidadPasajeros).append("\n");
        sb.append("Estado: ").append(estado).append("\n");
        sb.append("Precio Final (USD): $").append(String.format("%.2f", precioFinal)).append("\n");
        sb.append("Forma de Pago: ").append(formaDePago.name()).append("\n");
        return sb.toString();
    }

    public static void verTodasLasReservas() {
        if (cantidad == 0) {
            JOptionPane.showMessageDialog(null, "No hay reservas registradas.", "Reservas",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        StringBuilder sb = new StringBuilder("LISTADO DE RESERVAS\n\n");

        for (int i = 0; i < cantidad; i++) {
            Reserva r = reservas[i];
            sb.append(reservas[i].toString());
            sb.append("Fecha de creación: ").append(r.getFechaCreacion().format(formatter)).append("\n");
            sb.append("------------------------------------------\n");
        }

        JTextArea textArea = new JTextArea(sb.toString());
        textArea.setEditable(false);
        textArea.setCaretPosition(0); // Empieza desde arriba
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new java.awt.Dimension(400, 300));

        JOptionPane.showMessageDialog(null, scrollPane, "Reservas", JOptionPane.INFORMATION_MESSAGE);
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

    // Método para buscar y mostrar reservas por estado
    public static void mostrarReservasPorEstado(EstadoReserva estadoBuscado) {
        boolean encontrado = false;
        StringBuilder sb = new StringBuilder("Reservas con estado: " + estadoBuscado + "\n\n");

        for (int i = 0; i < cantidad; i++) {
            Reserva r = reservas[i];
            if (r != null && r.getEstado() == estadoBuscado) {
                sb.append("ID: ").append(r.idReserva)
                    .append("\nCliente: ").append(r.cliente.getNombre())
                    .append("\nVendedor: ").append(r.vendedor.getNombre())
                    .append("\nTipo Servicio: ").append(r.tipo)
                    .append("\nCantidad Pasajeros: ").append(r.cantidadPasajeros)
                    .append("\nEstado: ").append(r.estado)
                    .append("\nPrecio Final: $").append(r.precioFinal)
                    .append("\n------------------------\n");
                encontrado = true;
            }
        }

        if (encontrado) {
            JOptionPane.showMessageDialog(null, sb.toString(), "Resultados de búsqueda", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "No se encontraron reservas con estado: " + estadoBuscado, "Sin resultados", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // Método para mostrar el estado de todas las reservas
    public static void mostrarEstadoActualReservas() {
        if (cantidad == 0) {
            JOptionPane.showMessageDialog(null, "No hay reservas registradas.", "Estado de Reservas", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        StringBuilder sb = new StringBuilder("Estado actual de todas las reservas:\n\n");

        for (int i = 0; i < cantidad; i++) {
            Reserva r = reservas[i];
            if (r != null) {
                sb.append("ID: ").append(r.idReserva)
                .append(" | Cliente: ").append(r.cliente.getNombre()) 
                .append(" | Estado: ").append(r.estado)
                .append("\n");
            }
        }
        JOptionPane.showMessageDialog(null, sb.toString(), "Estado de Reservas", JOptionPane.INFORMATION_MESSAGE);
        }

    private void actualizarAsientos(int nuevaCantidadPasajeros, Vuelo nuevoVueloIda, Vuelo nuevoVueloVuelta) {
    // Liberar asientos anteriores
    if (vueloIda != null) {
        vueloIda.liberarAsientos(cantidadPasajeros);
    }
    if (vueloVuelta != null) {
        vueloVuelta.liberarAsientos(cantidadPasajeros);
    }

    // Actualizar vuelos
    this.vueloIda = nuevoVueloIda;
    this.vueloVuelta = nuevoVueloVuelta;

    // Reservar asientos nuevos
    if (vueloIda != null) {
        vueloIda.reservarAsientos(nuevaCantidadPasajeros);
    }
    if (vueloVuelta != null) {
        vueloVuelta.reservarAsientos(nuevaCantidadPasajeros);
    }

    // Actualizar cantidad
    this.cantidadPasajeros = nuevaCantidadPasajeros;
}

}
