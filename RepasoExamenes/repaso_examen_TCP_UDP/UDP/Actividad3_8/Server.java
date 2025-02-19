package RepasoExamenes.repaso_examen_TCP_UDP.UDP.Actividad3_8;

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

            // Recibir el obj que viene del cliente
            byte[] recibir = new byte[1024];
            DatagramPacket datagramRecibido = new DatagramPacket(recibir, recibir.length);
            server.receive(datagramRecibido);
            ByteArrayInputStream bais = new ByteArrayInputStream(recibir);
            ObjectInputStream in = new ObjectInputStream(bais);
            Persona personaRecibida = (Persona) in.readObject();
            personaRecibida.setEdad(24);
            

            byte[] enviar = new byte[1024];
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream objOut = new ObjectOutputStream(baos);
            objOut.writeObject(personaRecibida);
            objOut.close();
            enviar = baos.toByteArray();

            DatagramPacket datagramEnviar = new DatagramPacket(enviar, enviar.length, datagramRecibido.getAddress(), datagramRecibido.getPort());

            server.send(datagramEnviar);

            server.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
