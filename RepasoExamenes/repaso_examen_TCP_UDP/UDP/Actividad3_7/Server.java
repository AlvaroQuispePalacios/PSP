package RepasoExamenes.repaso_examen_TCP_UDP.UDP.Actividad3_7;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;


public class Server {
    public static void main(String[] args) {
        try {
            DatagramSocket server = new DatagramSocket(4321);
            System.out.println("Servidor escuchando...");

            byte[] flujoEntrada = new byte[1024];
            DatagramPacket entrada = new DatagramPacket(flujoEntrada, flujoEntrada.length);
            server.receive(entrada);
            ByteArrayInputStream bais = new ByteArrayInputStream(flujoEntrada);
            ObjectInputStream ois = new ObjectInputStream(bais);
            Numeros objCliente = (Numeros) ois.readObject(); 
            ois.close();

            objCliente.setCuadrado(objCliente.getNumero() * objCliente.getNumero());
            objCliente.setCubo(objCliente.getNumero() * objCliente.getNumero() * objCliente.getNumero());

            byte[] flujoSalida = new byte[1024];
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream ous = new ObjectOutputStream(baos);
            ous.writeObject(objCliente);
            ous.close();
            flujoSalida = baos.toByteArray();
            DatagramPacket salida = new DatagramPacket(flujoSalida, flujoSalida.length, entrada.getAddress(), entrada.getPort());
            server.send(salida);

            server.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
}
