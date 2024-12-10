package tema3.Actividad3_5;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class Lanzador {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Dime el numero de clientes a conectarse al servidor");
        String numeroClientes = sc.nextLine();
        ProcessBuilder pbServer = new ProcessBuilder("java", "tema3\\Actividad3_5\\Server.java", numeroClientes);
        sc.close();
        Process pServer = pbServer.start();
        
        ProcessBuilder pbCliente = new ProcessBuilder("java", "tema3\\Actividad3_5\\Cliente.java");
        Process pCliente = null;
        for (int i = 0; i < Integer.parseInt(numeroClientes); i++) {
            pCliente = pbCliente.start();
            int exitVal;
            try {
                exitVal = pCliente.waitFor();
                InputStream is = pCliente.getInputStream();
                int c;
                while ((c = is.read()) != -1) {
                    System.out.print((char) c);
                }
                is.close();
                System.out.println();
            } catch (Exception e) {}
        }

    }
    
}
