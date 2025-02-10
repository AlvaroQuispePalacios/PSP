package Tema4.Actividad4_4;

import java.util.Scanner;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;

import org.apache.commons.net.smtp.AuthenticatingSMTPClient;

public class Main {
    public static void main(String[] args) {

        AuthenticatingSMTPClient cliente = new AuthenticatingSMTPClient();

        Scanner sc = new Scanner(System.in);
        
        System.out.println("Introduce el servidor SMTP: ");
        String server = sc.nextLine();

        System.out.println("Necesita negociación TLS (S/N)");
        char tls = sc.next().trim().toUpperCase().charAt(0);



        System.out.println("Dime el usuario");
        String usuario = sc.nextLine();

        System.out.println("Dime la contraseña");
        String pwd = sc.nextLine();

        System.out.println("Dime el puerto");
        int puerto = sc.nextInt();

        System.out.println("Correo del remitente");
        String correoRemitente = sc.nextLine();

        System.out.println("Correo del destinatario");
        String correoDestinatario = sc.nextLine();

        System.out.println("Introduce el asunto");
        String asunto = sc.nextLine();


        System.out.println("Introduce el mensaje, finalizará cuando se introduzca un *");
    

        try {
            int respuesta;
            KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            kmf.init(null, null);
            KeyManager km = kmf.getKeyManagers()[0];
            

        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
