package RepasoExamenes.repaso_examen_TCP_UDP.TCP.Actividad3_3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(4321);
            System.out.println("Server escuchando ");
            Socket cliente = server.accept();

            // Salida
            OutputStream salida = null;
            salida = cliente.getOutputStream();
            DataOutputStream flujoSalida = new DataOutputStream(salida);
            flujoSalida.writeUTF("MENSAJE DEL SERVIDOR");
            System.out.println("El sevidor envio el mensaje");

            // Entrada
            DataInputStream flujoEntrada = new DataInputStream(cliente.getInputStream());
            System.out.println("Mensaje del cliente: " + flujoEntrada.readUTF());

            flujoEntrada.close();
            salida.close();
            flujoSalida.close();
            server.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
