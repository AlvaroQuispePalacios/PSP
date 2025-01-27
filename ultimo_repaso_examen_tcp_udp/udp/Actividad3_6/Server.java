package ultimo_repaso_examen_tcp_udp.udp.Actividad3_6;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Server {
    public static void main(String[] args) {
        try {
            DatagramSocket server = new DatagramSocket(4321);
            System.out.println("Servidor escuchando...");

            byte[] flujoEntrada = new byte[1024];
            DatagramPacket entrada = new DatagramPacket(flujoEntrada, flujoEntrada.length);
            server.receive(entrada);
            String mensajeEntrada = new String(entrada.getData());

            byte[] flujoSalida = new byte[1024];
            flujoSalida = mensajeEntrada.toUpperCase().getBytes();
            DatagramPacket salida = new DatagramPacket(flujoSalida, flujoSalida.length, entrada.getAddress(), entrada.getPort());
            server.send(salida);
            
            server.close();

        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
