package repaso_examen_TCP_UDP.TCP.Actividad3_4;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(4321);
            System.out.println("El servidor esta escuchando...");

            Socket cliente = server.accept();

            DataInputStream flujoEntrada = new DataInputStream(cliente.getInputStream());
            int numeroDelCliente = flujoEntrada.readInt();

            DataOutputStream flujoSalida = new DataOutputStream(cliente.getOutputStream());
            flujoSalida.writeInt(numeroDelCliente * numeroDelCliente);

            flujoEntrada.close();
            flujoSalida.close();
            server.close();
            
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
}
