package tema3.Actividad3_3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Cliente {
    public static void main(String[] args) {
        try {
            Socket cliente = new Socket("localhost", 4321);

            InputStream entrada = cliente.getInputStream();
            DataInputStream flujoEntrada = new DataInputStream(entrada);
            String mensajeEntrada = flujoEntrada.readUTF();
            System.out.println("Mensaje recibido en cliente: " + mensajeEntrada);

            OutputStream salida = cliente.getOutputStream();
            DataOutputStream flujoSalida = new DataOutputStream(salida);
            System.out.println("Enviando mensaje al servidor desde cliente");
            flujoSalida.writeUTF(mensajeEntrada.toLowerCase());
            
            entrada.close();
            flujoEntrada.close();
            salida.close();
            flujoSalida.close();

            cliente.close();


        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
