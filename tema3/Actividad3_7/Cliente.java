package tema3.Actividad3_7;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Cliente {
    public static void main(String[] args) throws IOException {
        MulticastSocket ms = new MulticastSocket(54321);
        InetAddress grupo = InetAddress.getByName("225.0.0.1");

        ms.joinGroup(grupo);

        byte[] buf = new byte[1000];
        DatagramPacket recibido = new DatagramPacket(buf, buf.length);
        ms.receive(recibido);

        ms.leaveGroup(grupo);

        ms.close();
    }
}
