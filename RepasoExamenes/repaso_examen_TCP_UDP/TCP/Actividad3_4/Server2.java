package RepasoExamenes.repaso_examen_TCP_UDP.TCP.Actividad3_4;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server2 {
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(4321);
            System.out.println("Server escuchando...");

            Socket cliente = server.accept();

            DataInputStream flujoEntrada = new DataInputStream(cliente.getInputStream());
            int numero = flujoEntrada.readInt();
            int cuadrado = numero * numero;

            DataOutputStream flujoSalida = new DataOutputStream(cliente.getOutputStream());
            flujoSalida.writeInt(cuadrado);

            flujoEntrada.close();
            flujoSalida.close();
            server.close();

        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
