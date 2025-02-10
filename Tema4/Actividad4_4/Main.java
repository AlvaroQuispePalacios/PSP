package Tema4.Actividad4_4;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Dime el servidor: ");
        String server = sc.nextLine();

        System.out.println("Necesita negociación TLS (S/N)");
        char tls = sc.next().trim().toUpperCase().charAt(0);

        System.out.println("Dime el usuario");
        String usuario = sc.nextLine();

        System.out.println("Dime la contraseña");
        String pwd = sc.nextLine();

        System.out.println("Dime el puerto");
        

        System.out.println("Correo del remitente");
        
        System.out.println("Correo del destinatario");

        System.out.println("Introduce el asunto");

        System.out.println("Introduce el examen, finalizará cuando se introduzca un *");

    }
}
