package RepasoExamenes.repaso_examen_TCP_UDP.TCP.Actividad3_2;

import java.net.Socket;

public class Cliente {
    public static void main(String[] args) {
        try {
            Socket cliente = new Socket("localhost", 4321);
        
            System.out.println(cliente.getInetAddress());

        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
