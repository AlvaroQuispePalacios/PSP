package Tema4.Actividad4_4;

import java.util.Scanner;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;

import org.apache.commons.net.smtp.AuthenticatingSMTPClient;
import org.apache.commons.net.smtp.SMTP;
import org.apache.commons.net.smtp.SMTPClient;
import org.apache.commons.net.smtp.SMTPReply;

public class Main {
    static SMTPClient cliente = new SMTPClient();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            System.out.print("Introduce el servidor SMTP: ");
            String server = sc.nextLine().trim();

            System.out.print("Necesita negociación TLS (S/N): ");
            char tls = sc.next().trim().toUpperCase().charAt(0);
            sc.nextLine();

            System.out.print("Dime el usuario: ");
            String usuario = sc.nextLine();

            System.out.print("Dime la contraseña: ");
            String pwd = sc.nextLine();

            System.out.print("Dime el puerto: ");
            int puerto = sc.nextInt();
            sc.nextLine();

            System.out.print("Correo del remitente: ");
            String correoRemitente = sc.nextLine();

            System.out.print("Correo del destinatario: ");
            String correoDestinatario = sc.nextLine();

            System.out.print("Introduce el asunto: ");
            String asunto = sc.nextLine();

            System.out.println("Introduce el mensaje, finalizará cuando se introduzca un *: ");
            String mensaje = sc.nextLine();
            
            cliente.connect(server, puerto);
            int respuesta = cliente.getReplyCode();

            if(!SMTPReply.isPositiveCompletion(respuesta)){
                System.out.println("Conexión rechazada");
                cliente.disconnect();
            }

            cliente.login();

        } catch (Exception e) {
            System.out.println("Error al introducir datos: " + e.getMessage());

        }

    }



}
