package ultimo_repaso_examen_tcp_udp.udp.Actividad3_8;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
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
            
            Persona persona = new Persona("Alvaro", 23);
            byte[] flujoSalida = new byte[1024];
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(persona);
            oos.close();
            flujoSalida = baos.toByteArray();
            DatagramPacket salida = new DatagramPacket(flujoSalida, flujoSalida.length, ipServidor, puerto);
            cliente.send(salida);

            byte[] flujoEntrada = new byte[1024];
            DatagramPacket entrada = new DatagramPacket(flujoEntrada, flujoEntrada.length);
            cliente.receive(entrada);
            ByteArrayInputStream bais = new ByteArrayInputStream(flujoEntrada);
            ObjectInputStream ois = new ObjectInputStream(bais);
            Persona personaServidor = (Persona) ois.readObject();
            System.out.println(personaServidor.getNombre());
            System.out.println(personaServidor.getEdad());

            cliente.close();

        } catch (Exception e) {
            System.out.println(e.getStackTrace().toString());
        }
    }
}
