package RepasoExamenes.repaso_examen_TCP_UDP.TCP.Actividad3_3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.net.Socket;

public class Cliente {
    public static void main(String[] args) {
        try {
            Socket cliente = new Socket("localhost", 4321);
            
            // Entrada
            InputStream entrada = cliente.getInputStream();
            DataInputStream flujoEntrada = new DataInputStream(entrada);
            String mensajeDelServidor = flujoEntrada.readUTF();
            
            // Salida
            DataOutputStream flujoSalida = new DataOutputStream(cliente.getOutputStream());
            flujoSalida.writeUTF(mensajeDelServidor.toLowerCase());

            flujoEntrada.close();
            flujoSalida.close();
            cliente.close();


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
