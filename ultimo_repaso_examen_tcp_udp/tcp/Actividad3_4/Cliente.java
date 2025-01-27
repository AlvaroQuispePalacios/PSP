package ultimo_repaso_examen_tcp_udp.tcp.Actividad3_4;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Dime un numero");
            int numero = sc.nextInt();

            Socket cliente = new Socket("localhost", 4321);

            DataOutputStream flujoSalida = new DataOutputStream(cliente.getOutputStream());
            flujoSalida.writeInt(numero);

            DataInputStream flujoEntrada = new DataInputStream(cliente.getInputStream());
            int cuadrado = flujoEntrada.readInt();
            System.out.println("El cuadrado es: " + cuadrado);

            flujoSalida.close();
            flujoEntrada.close();
            cliente.close();
            sc.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
