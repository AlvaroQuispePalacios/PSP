package repaso_examen_TCP_UDP.UDP.Actividad3_7;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        try {
            DatagramSocket cliente = new DatagramSocket();
            InetAddress ipServidor = InetAddress.getLocalHost();
            int puerto = 4321;

            Scanner sc = new Scanner(System.in);
            System.out.println("Dime un nuero");
            int numero = sc.nextInt();
            Numeros objNumeros = new Numeros(numero);

            byte[] flujoSalida = new byte[1024];
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(objNumeros);
            oos.close();
            flujoSalida = baos.toByteArray();
            DatagramPacket salida = new DatagramPacket(flujoSalida, flujoSalida.length, ipServidor, puerto);
            cliente.send(salida);

            byte[] flujoEntrada = new byte[1024];
            DatagramPacket entrada = new DatagramPacket(flujoEntrada, flujoEntrada.length);
            cliente.receive(entrada);
            ByteArrayInputStream bais = new ByteArrayInputStream(flujoEntrada);
            ObjectInputStream ois = new ObjectInputStream(bais);
            Numeros objEntrada = (Numeros) ois.readObject();
            System.out.println("Cuadrado: " + objEntrada.getCuadrado());
            System.out.println("Cubo: " + objEntrada.getCubo());

            cliente.close();
            sc.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
    }
    
}
