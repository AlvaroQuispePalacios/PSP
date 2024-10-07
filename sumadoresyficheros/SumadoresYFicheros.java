package sumadoresyficheros;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Scanner;

public class SumadoresYFicheros {

    /*
        Crear un programa que permita parametrizar el lanzamiento de sumadores, que vuelque el contenido de las sumas en ficheros y que permita al programa principal recuperar las sumas de los ficheros parciales. 
    */
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Dime un numero");
        int x = sc.nextInt();
        System.out.println("Dime el otro numero");
        int y = sc.nextInt();
        ProcessBuilder pb = new ProcessBuilder("java", "sumadoresyficheros\\Suma.java");
        Process p = pb.start();
        OutputStream os = p.getOutputStream();
        os.write(y);
        sc.close();

    }
}
