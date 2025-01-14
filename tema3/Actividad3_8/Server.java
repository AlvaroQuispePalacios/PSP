package tema3.Actividad3_8;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Server {
    public static void main(String[] args) throws Exception {
        DatagramSocket server = new DatagramSocket(4321);
        System.out.println("Servidor esperando mensaje");
        
        // Recibir paquetes del cliente
        byte[] recibidos = new byte[1024];
        DatagramPacket paqRecibido = new DatagramPacket(recibidos, recibidos.length);
        server.receive(paqRecibido);

        // Transformando el objeto que viene en bytes en un objeto Persona
        ByteArrayInputStream bais = new ByteArrayInputStream(recibidos);
        ObjectInputStream in = new ObjectInputStream(bais);
        Persona persona = (Persona) in.readObject();

        System.out.println(persona.getNombre());
        System.out.println(persona.getEdad());

        persona.setNombre("Pedro");
        persona.setEdad(44);
        in.close();

        // Enviando transformando el objeto Persona a bytes para enviar
        ByteArrayOutputStream bs = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(bs);
        out.writeObject(persona);
        out.close();
        byte[] bytes = bs.toByteArray();

        // Obteniendo la ip y el puerto por el cual se conecto el cliente y enviando los bytes de persona
        InetAddress ipCliente = paqRecibido.getAddress();
        int puerto = paqRecibido.getPort();
        DatagramPacket enviar = new DatagramPacket(bytes, bytes.length, ipCliente, puerto);
        server.send(enviar);

        server.close();
    }
}
