package RepasoExamenes.repaso_examen_TCP_UDP.TCP.Actividad3_2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try {
            int port = 4321;
            ServerSocket server = new ServerSocket(port);
            System.out.println("Escuchando en "+ server.getLocalPort());
            
            Socket cliente1 = server.accept();
            System.out.println("Cliente 1: " + cliente1.getLocalPort());
            Socket cliente2 = server.accept();

            server.close();
            
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
