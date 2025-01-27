package ultimo_repaso_examen_tcp_udp.tcp.Actividad3_4;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(4321);
            System.out.println("Servidor escuchando ...");
            Socket cliente = server.accept();

            DataInputStream flujoEntrada = new DataInputStream(cliente.getInputStream());
            int numero = flujoEntrada.readInt();
            System.out.println("El numero enviado es : " + numero);

            DataOutputStream flujoSalida = new DataOutputStream(cliente.getOutputStream());
            flujoSalida.writeInt(numero * numero);

            flujoEntrada.close();
            flujoSalida.close();
            server.close();


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
