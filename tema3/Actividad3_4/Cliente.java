package tema3.Actividad3_4;

import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("Dime un numero");
            int numero = sc.nextInt();

            Socket cliente = new Socket("localhost", 4321);
            OutputStream salida = cliente.getOutputStream();
            DataOutputStream flujoSalida = new DataOutputStream(salida);
            flujoSalida.writeInt(numero);

            

            salida.close();
            flujoSalida.close();
            cliente.close();
            sc.close();

        } catch (Exception e) {}
    }    
}
