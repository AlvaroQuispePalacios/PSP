package tema3.UDP.Actividad3_6;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            DatagramSocket clienteSocket = new DatagramSocket();
            InetAddress ipServidor = InetAddress.getLocalHost();
            int puerto = 4321;

            String mensaje = "";
            do {
                System.out.println("Dime el mensaje a enviar");
                mensaje = sc.nextLine();

                byte[] mensajeEnviar = new byte[1024];
                mensajeEnviar = mensaje.getBytes();
        
                DatagramPacket envio = new DatagramPacket(mensajeEnviar, mensajeEnviar.length, ipServidor, puerto);
                clienteSocket.send(envio);
        
                byte[] recibidos = new byte[1024];
                DatagramPacket recibo = new DatagramPacket(recibidos, recibidos.length);
                clienteSocket.receive(recibo);
        
                String recibido = new String(recibo.getData()).trim();
                System.out.println("Recibido del servidor: " + recibido);
    
                try {
                    clienteSocket.setSoTimeout(5000);

                } catch (Exception e) {
                    System.out.println("Paquete perdido");
                }
            } while (!mensaje.equals("*"));


            clienteSocket.close();
        } catch (Exception e) {
            System.out.println("Algo fallo");
        }

    }
}
