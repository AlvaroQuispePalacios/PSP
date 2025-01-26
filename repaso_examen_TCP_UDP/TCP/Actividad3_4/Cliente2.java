package repaso_examen_TCP_UDP.TCP.Actividad3_4;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Cliente2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Dime un numero");
        int numero = sc.nextInt();

        try {
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
            
        }
    }
}
