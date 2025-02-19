package RepasoExamenes.repaso_examen_TCP_UDP.TCP.Actividad3_5;

import java.io.DataInputStream;
import java.net.Socket;

public class Cliente {
    public static void main(String[] args) {
        try {
            Socket cliente = new Socket("localhost", 4321);
            DataInputStream flujoEntrada = new DataInputStream(cliente.getInputStream());
            System.out.println("Cliente: " + flujoEntrada.readInt());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
