package RepasoExamenes.repaso_examen_TCP_UDP.TCP.Actividad3_5;

import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        int NUM_CLIENTES = 3;
        try {
            ServerSocket server = new ServerSocket(4321);
            Socket cliente = null;
            DataOutputStream flujoSalida = null;
            
            for (int i = 1; i <= NUM_CLIENTES; i++) {
                cliente = server.accept();
                flujoSalida = new DataOutputStream(cliente.getOutputStream());
                flujoSalida.writeInt(i);
            }
            flujoSalida.close();
            server.close();
            
        } catch (Exception e) {
            // TODO: handle exception
        }


    }
}
