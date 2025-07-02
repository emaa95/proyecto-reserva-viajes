package utils.inputOutputJOP;

import javax.swing.JOptionPane;

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
                n = Double.parseDouble(input.trim());
                if (n <= 0) {
                    throw new IllegalArgumentException("Debe ingresar un número decimal mayor que cero.");
                }
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
}
