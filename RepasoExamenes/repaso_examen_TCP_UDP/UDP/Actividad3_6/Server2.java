package RepasoExamenes.repaso_examen_TCP_UDP.UDP.Actividad3_6;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Server2 {
    public static void main(String[] args) {
        try {
            DatagramSocket server = new DatagramSocket(4321);
            System.out.println("Esperando datagrama...");
            String mensajeEntrada = "";

            
            do {
                byte[] flujoEntrada = new byte[1024];
                DatagramPacket entrada = new DatagramPacket(flujoEntrada, flujoEntrada.length);
                server.receive(entrada);
                mensajeEntrada = new String(entrada.getData());
                
                if(mensajeEntrada.equals("*")) break;
                System.out.println("Mensaje del cliente: " + mensajeEntrada);
                
                byte[] flujoSalida = new byte[1024];
                String mensajeSalida = mensajeEntrada.toUpperCase();
                flujoSalida = mensajeSalida.getBytes();
                DatagramPacket salida = new DatagramPacket(flujoSalida, flujoSalida.length, entrada.getAddress(), entrada.getPort());

                server.send(salida);

            } while (!mensajeEntrada.equals("*"));

            server.close();            
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
