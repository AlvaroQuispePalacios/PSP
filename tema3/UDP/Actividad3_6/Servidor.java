package tema3.UDP.Actividad3_6;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Servidor {
    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket(4321);
        System.out.println("Servidor Esperando mensaje.. ");
        DatagramPacket recibo;
        
        
        byte[] bufer = new byte[1024];
        recibo = new DatagramPacket(bufer, bufer.length);
        socket.receive(recibo);

        String mensajeRecibido = new String(recibo.getData()).trim();
        System.out.println("Mensaje recibe: "+ mensajeRecibido);

        // 
        String enviar = mensajeRecibido.toUpperCase();
        InetAddress ipCliente = recibo.getAddress();
        int puerto = recibo.getPort();

        System.out.println("Enviando a cliente ...");
        byte[] mensajeEnviar = new byte[1024];
        mensajeEnviar = enviar.getBytes();

        DatagramPacket envio = new DatagramPacket(mensajeEnviar, mensajeEnviar.length, ipCliente, puerto);
        socket.send(envio);
        
        System.out.println("Cerrando conexion");
        socket.close();
    }
}
