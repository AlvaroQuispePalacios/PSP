package repaso_examen_TCP_UDP.UDP.Actividad3_6;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            // EL que iniciar la comunicacion establece primero InetAddress y puerto
            DatagramSocket cliente = new DatagramSocket();
            InetAddress ipServidor = InetAddress.getLocalHost();
            int puerto = 4321;
            
            System.out.println("Dime un mensaje");
            String mensaje = sc.nextLine();

            // Necesitas este array es para enviar el mensaje por aca como un DataOutputStream en TCP
            byte[] enviar = new byte[1024];
            enviar = mensaje.getBytes();

            // Enviando datagrama al servidor
            DatagramPacket envio = new DatagramPacket(enviar, enviar.length, ipServidor, puerto);
            cliente.send(envio);

            // Recibiendo datagrama del servidor
            byte[] buferRecibir = new byte[1024];
            DatagramPacket recibir = new DatagramPacket(buferRecibir, buferRecibir.length);
            cliente.receive(recibir);
            String mensajeDelServidor = new String(recibir.getData());
            System.out.println("Mensaje del servidor: " + mensajeDelServidor);

            sc.close();
            cliente.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
