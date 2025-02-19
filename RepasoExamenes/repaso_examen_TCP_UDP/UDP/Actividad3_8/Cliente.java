package RepasoExamenes.repaso_examen_TCP_UDP.UDP.Actividad3_8;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Cliente {
    public static void main(String[] args) {
        try {
            DatagramSocket cliente = new DatagramSocket();
            InetAddress host = InetAddress.getLocalHost();
            int puerto = 4321;

            Persona personaEnviar = new Persona("Alvaro", 23);

            byte[] enviar = new byte[1024];
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(baos);
            out.writeObject(personaEnviar);
            out.close();
            enviar = baos.toByteArray();
            DatagramPacket datagramEnviar = new DatagramPacket(enviar, enviar.length, host, puerto);
            cliente.send(datagramEnviar);

            byte[] recibir = new byte[1024];
            DatagramPacket datagramRecibir = new DatagramPacket(recibir, recibir.length);
            cliente.receive(datagramRecibir);

            ByteArrayInputStream bais = new ByteArrayInputStream(recibir);
            ObjectInputStream objRecibido = new ObjectInputStream(bais);
            Persona personaRecibida = (Persona) objRecibido.readObject();
            System.out.println(personaRecibida.getEdad());
            objRecibido.close();
            cliente.close();

        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
