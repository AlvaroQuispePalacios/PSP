package ultimo_repaso_examen_tcp_udp.tcp.Actividad3_7;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Cliente {
    public static void main(String[] args) {
        try {
            Socket cliente = new Socket("localhost", 4321);

            Numeros numero = new Numeros(6);
            ObjectOutputStream flujoSalida = new ObjectOutputStream(cliente.getOutputStream());
            flujoSalida.writeObject(numero);

            ObjectInputStream flujoEntrada = new ObjectInputStream(cliente.getInputStream());
            Numeros numeroServidor = (Numeros) flujoEntrada.readObject();
            System.out.println(numeroServidor.getCuadrado());
            System.out.println(numeroServidor.getCubo());

            flujoSalida.close();
            flujoEntrada.close();
            cliente.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
