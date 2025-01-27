package ultimo_repaso_examen_tcp_udp.tcp.Actividad3_7;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(4321);

            Socket cliente = server.accept();

            ObjectInputStream flujoEntrada = new ObjectInputStream(cliente.getInputStream());
            Numeros numero = (Numeros) flujoEntrada.readObject();
            numero.setCuadrado(numero.getNumero()*numero.getNumero());
            numero.setCubo( numero.getNumero() *numero.getNumero() * numero.getNumero());

            ObjectOutputStream flujoSalida = new ObjectOutputStream(cliente.getOutputStream());
            flujoSalida.writeObject(numero);

            flujoEntrada.close();
            flujoSalida.close();
            server.close();


        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
    }
}
