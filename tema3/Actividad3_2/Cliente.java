package tema3.Actividad3_2;

import java.net.Socket;

public class Cliente {
    public static void main(String[] args) {
        try {
            Socket cliente = new Socket("localhost", 4321);
            System.out.println("Cliente 1");
            cliente.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
