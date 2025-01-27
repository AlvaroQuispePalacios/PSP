package ultimo_repaso_examen_tcp_udp.udp.Actividad3_6;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Dime un mensaje");
            String mensaje = sc.nextLine();

            DatagramSocket cliente = new DatagramSocket();
            InetAddress ipServidor = InetAddress.getLocalHost();
            int puerto = 4321;

            byte[] flujoSalida = new byte[1024];
            flujoSalida = mensaje.getBytes();
            DatagramPacket salida = new DatagramPacket(flujoSalida, flujoSalida.length, ipServidor, puerto);
            cliente.send(salida);

            byte[] flujoEntrada = new byte[1024];
            DatagramPacket entrada = new DatagramPacket(flujoEntrada, flujoEntrada.length);
            cliente.receive(entrada);
            String mensajeServidor = new String(entrada.getData());
            
            System.out.println(mensajeServidor);
            sc.close();
            cliente.close();
        } catch (Exception e) {
            // TODO: handle exception
        }



    }
}
