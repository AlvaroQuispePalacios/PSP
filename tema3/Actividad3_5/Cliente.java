package tema3.Actividad3_5;

import java.io.DataInputStream;
import java.io.InputStream;
import java.net.Socket;

public class Cliente {
    public static void main(String[] args) {
        try {
            Socket cliente = new Socket("localhost", 4321);
            InputStream entrada = cliente.getInputStream();
            DataInputStream flujoEntrada = new DataInputStream(entrada);
            System.out.println(flujoEntrada.readUTF());
            System.out.println("Puerto Local: "+cliente.getLocalPort());
            System.out.println("Puerto: "+ cliente.getPort());
            System.out.println("Puerto local: " + cliente.getInetAddress());

            entrada.close();
            flujoEntrada.close();
            cliente.close();
        } catch (Exception e) {}

    }
}
