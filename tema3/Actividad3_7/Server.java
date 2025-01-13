package tema3.Actividad3_7;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Server {
    public static void main(String[] args) throws IOException{
        MulticastSocket ms = new MulticastSocket();
        int puerto = 54321;
        InetAddress grupo = InetAddress.getByName("225.0.0.1");
        
        String msg = "Bienvenido al grupo";

        DatagramPacket paquete = new DatagramPacket(msg.getBytes(), msg.length(), grupo, puerto);
        ms.send(paquete);

        ms.close();

    }
}
