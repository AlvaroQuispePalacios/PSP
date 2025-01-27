package ultimo_repaso_examen_tcp_udp.udp.Actividad3_8;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Server {
    public static void main(String[] args) {
        try {
            DatagramSocket server = new DatagramSocket(4321);

            byte[] flujoEntrada = new byte[1024];
            DatagramPacket entrada = new DatagramPacket(flujoEntrada, flujoEntrada.length);
            server.receive(entrada);
            ByteArrayInputStream bais = new ByteArrayInputStream(flujoEntrada);
            ObjectInputStream ois = new ObjectInputStream(bais);
            Persona persona = (Persona) ois.readObject();
            persona.setEdad(24);
            persona.setNombre("Pedro");

            byte[] flujoSalida = new byte[1024];
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream ous = new ObjectOutputStream(baos);
            ous.writeObject(persona);
            flujoSalida = baos.toByteArray();
            DatagramPacket salida = new DatagramPacket(flujoSalida, flujoSalida.length, entrada.getAddress(), entrada.getPort());
            server.send(salida);
            server.close();


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
