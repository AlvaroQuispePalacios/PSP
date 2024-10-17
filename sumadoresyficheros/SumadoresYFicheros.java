package sumadoresyficheros;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class SumadoresYFicheros {

    /*
     * Crear un programa que permita parametrizar el lanzamiento de sumadores, que
     * vuelque el contenido de las sumas en ficheros y que permita al programa
     * principal recuperar las sumas de los ficheros parciales.
     */
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        boolean salir = false;

        try {
            //

            do {
                System.out.println("1. Sumar dos numeros\n2. Recuperar Suma\n3. Salir");
                String respuesta = sc.nextLine();
                switch (respuesta) {
                    case "1":
                        sumarDosNumeros(sc);
                        break;
                    case "2":
                        System.out.println("Recuperar una suma");
                        break;
                    case "3":
                        salir = true;
                    default:
                        break;
                }
            } while (!salir);

            sc.close();

        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public static void sumarDosNumeros(Scanner sc) throws IOException{
        System.out.println("Dime un numero");
        String x = sc.nextLine();
        System.out.println("Dime el otro numero");
        String y = sc.nextLine();
        ProcessBuilder pb = new ProcessBuilder("java", "sumadoresyficheros\\Suma.java", x, y);
        guardarSuma(pb);
        Process p = pb.start();
    }

    public static void guardarSuma(ProcessBuilder pb) {
        // Creamos el directorio donde se guardaran las sumas
        File directorio = new File(".\\sumadoresyficheros\\sumas");
        if (!directorio.exists()) {
            directorio.mkdir();
        }

        int contador = 1;
        boolean salir = false;

        do {
            File f = new File(".\\sumadoresyficheros\\sumas\\suma" + contador + ".txt");
            // Si el fichero ya existe suma uno al contador hasta que un fichero sea valido
            if (!f.exists()) {
                pb.redirectOutput(f);
                salir = true;
            } else {
                contador++;
            }
        } while (!salir);

    }
}
