import datos.Datos;
import menus.Menu;

public class App {
    public static void main(String[] args) throws Exception {
        Datos.precargarVendedores();
        Menu.mostrarMenuPrincipal();
    }
}
