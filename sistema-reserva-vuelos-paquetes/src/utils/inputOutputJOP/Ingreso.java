package utils.inputOutputJOP;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;

public class Ingreso {

    private static String cError = "Error de Dato";
    
    public static String leerString(String cMens) {
        boolean repetir;
        String texto = "";
    
        do {
            repetir = false;
            try {
                texto = JOptionPane.showInputDialog(null, cMens);
                if (texto == null) {
                    System.exit(0); 
                }
                texto = texto.trim();
                if (texto.length() < 3) {
                    throw new IllegalArgumentException("El texto debe tener al menos 3 caracteres.");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
                repetir = true;
            }
        } while (repetir);
        
        return texto;
    }
    
    public static String leerNumeroCuenta(String cMens) {
        boolean repetir;
        String texto = "";

        do {
            repetir = false;
            try {
                texto = JOptionPane.showInputDialog(null, cMens);
                if (texto == null) System.exit(0); 
                texto = texto.trim();
                if (texto.length() < 10) {
                    throw new IllegalArgumentException("El número de cuenta debe tener al menos 10 caracteres.");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
                repetir = true;
            }
        } while (repetir);

        return texto;
    }

    public static int leerEntero(String cMens) {
        boolean repetir;
        int n = 0;

        do {
            repetir = false;
            try {
                String input = JOptionPane.showInputDialog(null, cMens);
                if (input == null) System.exit(0); 
                n = Integer.parseInt(input.trim());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Debe ingresar un número entero válido.");
                repetir = true;
            }
        } while (repetir);

        return n;
    }

    public static int leerEnteroPositivo(String cMens) {
        boolean repetir;
        int n = 0;

        do {
            repetir = false;
            try {
                String input = JOptionPane.showInputDialog(null, cMens);
                if (input == null) System.exit(0);
                n = Integer.parseInt(input.trim());
                if (n <= 0) {
                    throw new IllegalArgumentException("Debe ingresar un número entero mayor que cero.");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
                repetir = true;
            }
        } while (repetir);

        return n;
    }

    public static double leerDouble(String cMens) {
        boolean repetir;
        double n = 0.0;

        do {
            repetir = false;
            try {
                String input = JOptionPane.showInputDialog(null, cMens);
                if (input == null) System.exit(0); 
                n = Double.parseDouble(input.trim());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Debe ingresar un número decimal válido.");
                repetir = true;
            }
        } while (repetir);

        return n;
    }

    public static double leerDoublePositivo(String cMens) {
         boolean repetir;
    double n = 0.0;

    do {
        repetir = false;
        try {
            String input = JOptionPane.showInputDialog(null, cMens);
            if (input == null) System.exit(0);
            input = input.trim();
            if (input.isEmpty()) {
                throw new IllegalArgumentException("Debe ingresar un número decimal mayor que cero.");
            }
            n = Double.parseDouble(input);
            if (n <= 0) {
                throw new IllegalArgumentException("Debe ingresar un número decimal mayor que cero.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Debe ingresar solo números (use punto para decimales).");
            repetir = true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            repetir = true;
        }
    } while (repetir);

    return n;
    }

    public static int nOpciones(String cMensaje, String[] opc, String cTitulo) {
        int seleccion = -1;
        boolean valido = false;
        do {
            try {
                seleccion = JOptionPane.showOptionDialog(null, cMensaje,
                        cTitulo, JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                        null, opc, opc[0]);
                if (seleccion == -1)
                    throw new Exception("La Debe seleccionar una Opción");
                valido = true;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), cError, 0);
            }
        } while (!valido);
        return seleccion;
    }

    // Método para seleccionar una fecha con un JSpinner
    public static String seleccionarFechaConSpinner(String mensaje) {
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    sdf.setLenient(false);
    String fechaFormateada = null;
    boolean repetir;
    do {
        repetir = false;
        JSpinner spinner = new JSpinner(new SpinnerDateModel());
        spinner.setEditor(new JSpinner.DateEditor(spinner, "dd/MM/yyyy"));
        int opcion = JOptionPane.showOptionDialog(
            null,
            new Object[]{mensaje, spinner},
            "Seleccionar fecha",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            null,
            null
        );
        if (opcion == JOptionPane.OK_OPTION) {
            Date fechaSeleccionada = (Date) spinner.getValue();
            // Obtener la fecha actual sin hora
            Date hoy = new Date();
            try {
                Date soloFechaHoy = sdf.parse(sdf.format(hoy));
                Date soloFechaSeleccionada = sdf.parse(sdf.format(fechaSeleccionada));
                if (soloFechaSeleccionada.before(soloFechaHoy)) {
                    JOptionPane.showMessageDialog(null, "La fecha debe ser igual o posterior a hoy.");
                    repetir = true;
                } else {
                    fechaFormateada = sdf.format(fechaSeleccionada);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al validar la fecha.");
                repetir = true;
            }
        } else {
            System.exit(0);
        }
    } while (repetir);
    return fechaFormateada;
    }
    
    // Método para seleccionar una hora con un JSpinner
    public static String seleccionarHoraConSpinner(String mensaje) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String horaFormateada = null;
        boolean repetir;
        do {
            repetir = false;
            JSpinner spinner = new JSpinner(new SpinnerDateModel());
            spinner.setEditor(new JSpinner.DateEditor(spinner, "HH:mm"));
            int opcion = JOptionPane.showOptionDialog(
                null,
                new Object[]{mensaje, spinner},
                "Seleccionar hora",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                null,
                null
            );
            if (opcion == JOptionPane.OK_OPTION) {
                Date horaSeleccionada = (Date) spinner.getValue();
                horaFormateada = sdf.format(horaSeleccionada);
            } else {
                JOptionPane.showMessageDialog(null, "Operación cancelada.");
                return null;
            }
        } while (repetir);
        return horaFormateada;
    }
}