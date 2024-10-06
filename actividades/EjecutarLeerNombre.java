package actividades;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class EjecutarLeerNombre {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Dime un nombre");
        String nombre = sc.nextLine();
        ProcessBuilder pb = new ProcessBuilder("java", "actividades\\LeerNombre.java", nombre);
        sc.close();
        Process p = pb.start();
        int exitVal;

        try {
            exitVal = p.waitFor();
            InputStream is = p.getInputStream();
            int c;
            while ((c = is.read()) != -1) {
                System.out.println((char) c);
            }
            is.close();
            
            if(exitVal == 1){
                System.out.println("El nombre es valido");
                System.out.println(exitVal);
            }else if(exitVal == -1){
                System.out.println("El nombre no es valido");
                System.out.println(exitVal);
            }else{
                System.out.println("Algo no funciono ¯\\_(ツ)_/¯");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
