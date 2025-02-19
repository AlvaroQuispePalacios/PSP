package RepasoExamenes.repaso_examen_TCP_UDP.UDP.Actividad3_6;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Server {
    public static void main(String[] args) {
        try {
            DatagramSocket server = new DatagramSocket(4321);
            System.out.println("Server esperando datagrama...");

            // Recibir datagrama del cliente
            byte[] bufer = new byte[1024];
            DatagramPacket recibo = new DatagramPacket(bufer, bufer.length);
            server.receive(recibo);
            String mensajeDeCliente = new String(recibo.getData());
            System.out.println("El servidor recibe " + mensajeDeCliente);

            // Devolver mensaje
            InetAddress ipCliente = recibo.getAddress();
            int puertoCliente = recibo.getPort();

            String mensajeAEnviar = mensajeDeCliente.toUpperCase();
            byte[] buferEnviar = new byte[1024];
            buferEnviar = mensajeAEnviar.getBytes();
            DatagramPacket enviar = new DatagramPacket(buferEnviar, buferEnviar.length, ipCliente, puertoCliente);
            server.send(enviar);

            server.close();

        } catch (Exception e) {
            // TODO: handle exception
        }
    }
    
}
