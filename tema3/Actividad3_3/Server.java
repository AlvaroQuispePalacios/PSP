package tema3.Actividad3_3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(4321);
            Socket clienteConectado = server.accept();
            // Obtener el flujo de salida
            OutputStream salida = clienteConectado.getOutputStream();
            // 
            DataOutputStream flujoSalida = new DataOutputStream(salida);
            System.out.println("Enviando mensaje desde el servidor");
            flujoSalida.writeUTF("Bienvenido Al Server");

            InputStream entrada = clienteConectado.getInputStream();
            DataInputStream flujoEntrada = new DataInputStream(entrada);
            System.out.println("Mensaje recibido en el servidor desde cliente :" + flujoEntrada.readUTF());

            salida.close();
            flujoSalida.close();
            entrada.close();
            flujoEntrada.close();


            clienteConectado.close();
            server.close();

        } catch (Exception e) {}
    }
}
