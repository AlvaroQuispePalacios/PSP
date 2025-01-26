package repaso_examen_TCP_UDP.TCP.Actividad3_2;

import java.net.ServerSocket;
import java.net.Socket;

public class Server2 {
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(4321);
            System.out.println("Servidor escuchando en el puerto 4321...");
            Socket cliente = server.accept();
            server.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
}
