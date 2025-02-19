package RepasoExamenes.repaso_examen_TCP_UDP.TCP.Actividad3_7;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server2 {
    public static void main(String[] args) {

        try {
            ServerSocket server = new ServerSocket(4321);
            System.out.println("Servidor escuchando...");
            Socket cliente = server.accept();
            ObjectInputStream flujoEntrada = null;
            ObjectOutputStream flujoSalida = null;
            while (true) {
                flujoEntrada = new ObjectInputStream(cliente.getInputStream());
                Numeros objCliente = (Numeros) flujoEntrada.readObject();
                if(objCliente.getNumero() <= 0) break;
                objCliente.setCuadrado(objCliente.getNumero() * objCliente.getNumero());
                objCliente.setCubo(objCliente.getNumero() * objCliente.getNumero() * objCliente.getNumero());

                flujoSalida = new ObjectOutputStream(cliente.getOutputStream());
                flujoSalida.writeObject(objCliente);

            }
            flujoEntrada.close();
            flujoSalida.close();
            server.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
