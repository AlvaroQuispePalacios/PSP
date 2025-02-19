package RepasoExamenes.repaso_examen_TCP_UDP.UDP.Actividad3_6;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Cliente2 {
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        try {
            InetAddress ipServidor = InetAddress.getLocalHost();
            int puerto = 4321;
            DatagramSocket cliente = new DatagramSocket();
            String mensaje = "a";

            do {
                System.out.println("Dime un mensaje para el servidor");
                mensaje = sc.nextLine();
    
                byte[] flujoSalida = new byte[1024];
                flujoSalida = mensaje.getBytes();
                DatagramPacket salida = new DatagramPacket(flujoSalida, flujoSalida.length, ipServidor, puerto);
                cliente.send(salida);
                
                byte[] flujoEntrada = new byte[1024];
                DatagramPacket entrada = new DatagramPacket(flujoEntrada, flujoEntrada.length);
                cliente.receive(entrada);
                String mensajeEntrada = new String(entrada.getData());
                System.out.println(mensajeEntrada);
                
            } while (!mensaje.equals("*"));


            cliente.close();
            sc.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
