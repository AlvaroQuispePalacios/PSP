package actividades;

import java.io.IOException;
// import java.util.Scanner;

public class LeerNombre {
    public static void main(String[] args) throws IOException {
        // Actividad 1.4
        // Scanner sc = new Scanner(System.in);
        // System.out.println("Dime un nombre");
        // String nombre = sc.nextLine();
        if (args[0].equals("Alvaro")) {
            System.out.println("Nombre valido");
            System.exit(1);
        } else {
            System.out.println("El nombre no es valido");
            System.exit(-1);
        }
        // sc.close();
    }
}
