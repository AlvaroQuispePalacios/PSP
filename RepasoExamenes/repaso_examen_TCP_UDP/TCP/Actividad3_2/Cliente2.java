package RepasoExamenes.repaso_examen_TCP_UDP.TCP.Actividad3_2;

import java.net.InetAddress;
import java.net.Socket;

public class Cliente2 {
    public static void main(String[] args) {
        try {
            Socket cliente = new Socket("localhost", 4321);
            
            InetAddress i = cliente.getInetAddress();
            System.out.println("Puerto local: " + cliente.getLocalPort());
            System.out.println("Puerto remoto: " + cliente.getPort());
            System.out.println("Nombre Host/IP" + cliente.getInetAddress());
            System.out.println("Host remoto: " + i.getHostAddress().toString());
            System.out.println("IP host remoto: " + i.getHostAddress().toString());

            cliente.close();

        } catch (Exception e) {
            
        }
    }
}
