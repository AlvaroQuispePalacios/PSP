package actividades;

import java.io.IOException;

public class LeerNombre {
    public static void main(String[] args) throws IOException {
        // Actividad 1.4
        if (args[0].equals("Alvaro")) {
            System.out.print("Nombre valido");
            System.exit(1);
        } else {
            System.out.println("El nombre no es valido");
            System.exit(-1);
        }
    }
}
