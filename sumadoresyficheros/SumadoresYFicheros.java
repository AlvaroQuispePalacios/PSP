package sumadoresyficheros;

import java.io.File;
import java.io.FileReader;
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
                        recuperarSuma(sc);
                        break;
                    case "3":
                        salir = true;
                        break;
                    default:
                        break;
                }
            } while (!salir);

            sc.close();

        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public static void sumarDosNumeros(Scanner sc) throws IOException {
        System.out.println("Dime un numero");
        String x = sc.nextLine();
        System.out.println("Dime el otro numero");
        String y = sc.nextLine();
        ProcessBuilder pb = new ProcessBuilder("java", "sumadoresyficheros\\Suma.java", x, y);
        guardarSuma(pb);
        Process p = pb.start();
    }

    public static void recuperarSuma(Scanner sc) throws IOException {
        // Crea la carpeta donde se guardaran las sumas si no existe
        File directorio = new File(".\\sumadoresyficheros\\sumas");
        if (!directorio.exists()) {
            directorio.mkdir();
        }

        // Lista los archivos de la carpeta que contiene las sumas
        File[] listarArchivos = directorio.listFiles();
        if (listarArchivos != null && listarArchivos.length > 0) {
            for (File archivo : listarArchivos) {
                if (archivo.isFile()) {
                    System.out.println("Archivo: " + archivo.getName());
                }
            }
            System.out.println(
                    "Escribe el nombre del archivo sin extensión para recuperar la suma que contiene. Ejm: suma1");
            String ficheroSuma = sc.nextLine();
            
            try {
                FileReader f = new FileReader(".\\sumadoresyficheros\\sumas\\" + ficheroSuma +".txt");
                int c = f.read();
                while (c != -1) {
                    System.out.print((char) c);
                    c = f.read();
                }
                f.close();
            } catch (Exception e) {
                System.out.println("No se ha encontrado el archivo");
            }

            System.out.println();
        } else {
            System.out.println("No hay ninguna suma guardada");
        }
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
