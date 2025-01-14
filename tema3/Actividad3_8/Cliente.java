package tema3.Actividad3_8;

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
            InetAddress ipServidor = InetAddress.getLocalHost();
            int puerto = 4321;
            
            // Transformando el objeto Persona a bytes para ser enviado al server
            Persona persona = new Persona("Alvaro", 23);
            ByteArrayOutputStream bs = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(bs);
            out.writeObject(persona);
            byte[] bytes = bs.toByteArray();
            out.close();
            
            // Enviando los bytes del objeto Persona al Server
            DatagramPacket envio = new DatagramPacket(bytes, bytes.length, ipServidor, puerto);
            cliente.send(envio);

            // Recibiendo los bytes del Objeto persona que viene del Server
            byte[] recibidos = new byte[1024];
            DatagramPacket paqRecibido = new DatagramPacket(recibidos, recibidos.length);
            cliente.receive(paqRecibido);

            // Transformando los Bytes del objeto Persona que viene del Server a un objeto Persona
            ByteArrayInputStream bais = new ByteArrayInputStream(recibidos);
            ObjectInputStream in = new ObjectInputStream(bais);
            Persona personaServer = (Persona) in.readObject();
            System.out.println(personaServer.getNombre());
            System.out.println(personaServer.getEdad());

            in.close();
            cliente.close();


        } catch (Exception e) {
            // TODO: handle exception
        }


    }
}
