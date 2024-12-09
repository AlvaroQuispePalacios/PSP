package tema3.Actividad3_2;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(4321);
            System.out.println("Escuchando en : " + server.getLocalPort());
            Socket cliente1 = server.accept();
            System.out.println("Puerto local: " + cliente1.getLocalPort());
            System.out.println("Puerto local: " + cliente1.getPort());
            System.out.println("Puerto local: " + cliente1.getInetAddress());
            // System.out.println("Puerto local: " + cliente1.getLocalPort());

// 

            server.close();
        } catch (Exception e) {
            
        }
    }
}
