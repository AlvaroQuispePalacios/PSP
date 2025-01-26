package repaso_examen_TCP_UDP.TCP.Actividad3_3;

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

            DataOutputStream flujoSalida = new DataOutputStream(cliente.getOutputStream());
            flujoSalida.writeUTF("MENSAJE DEL SERVIDOR D:");

            DataInputStream flujoEntrada = new DataInputStream(cliente.getInputStream());
            String mensajeCliente = flujoEntrada.readUTF();
            System.out.println(mensajeCliente);

            flujoSalida.close();
            server.close();
        } catch (Exception e) {
            // TODO: handle exception
        }

    }
}
