package tema3.Actividad3_4;

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

            InputStream entrada = clienteConectado.getInputStream();
            DataInputStream flujoEntrada = new DataInputStream(entrada);
            int mensajeCliente = flujoEntrada.readInt();

            OutputStream salida = clienteConectado.getOutputStream();
            DataOutputStream flujoSalida = new DataOutputStream(salida);
            flujoSalida.writeInt(mensajeCliente*mensajeCliente);

            entrada.close();
            flujoEntrada.close();
            salida.close();
            flujoSalida.close();

            clienteConectado.close();
            server.close();


        } catch (Exception e) {}
    }
}
