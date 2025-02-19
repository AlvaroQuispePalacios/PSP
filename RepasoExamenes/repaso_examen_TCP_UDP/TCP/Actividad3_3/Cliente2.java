package RepasoExamenes.repaso_examen_TCP_UDP.TCP.Actividad3_3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class Cliente2 {
    public static void main(String[] args) {
        try {
            Socket cliente = new Socket("localhost", 4321);

            DataInputStream flujoEntrada = new DataInputStream(cliente.getInputStream());
            String mensaje = new String(flujoEntrada.readUTF());
            System.out.println(mensaje);

            DataOutputStream flujoSalida = new DataOutputStream(cliente.getOutputStream());
            flujoSalida.writeUTF(mensaje.toLowerCase());

            flujoEntrada.close();
            flujoSalida.close();
            cliente.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
