package tema3.Actividad3_1;

import java.util.Scanner;
import java.net.*;
public class Main {
    public static void main(String[] args) {
        InetAddress dir = null; 
        Scanner sc = new Scanner(System.in);
        System.out.println("Dime una URL o una direccion IP");
        String direccion = sc.nextLine();

        try {
            dir = InetAddress.getByName(direccion);
            System.out.println(dir.getAddress());
            System.out.println(dir.getHostName());
            System.out.println(dir.getCanonicalHostName());
            InetAddress[] links = InetAddress.getAllByName(dir.getHostName());
            
            for (InetAddress inetAddress : links) {
                System.out.println(inetAddress.toString());
            }

        } catch (Exception e) {
            // TODO: handle exception
        }
        sc.close();

    }
}
