package repaso_examen_TCP_UDP.TCP.Actividad3_7;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Cliente2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        try {
            Socket cliente = new Socket("localhost", 4321);
            int numero = 1;
            ObjectOutputStream flujoSalida = null;
            ObjectInputStream flujoEntrada = null;

            while (numero > 0) {
                System.out.println("Dime un numero");
                numero = sc.nextInt();
                Numeros objNumeros = new Numeros(numero);

                flujoSalida = new ObjectOutputStream(cliente.getOutputStream());
                flujoSalida.writeObject(objNumeros);
                
                flujoEntrada = new ObjectInputStream(cliente.getInputStream());
                Numeros objServidor = (Numeros) flujoEntrada.readObject();
                System.out.println("Cuadrado: " + objServidor.getCuadrado());
                System.out.println("Cubo: " + objServidor.getCubo());

            }

            flujoSalida.close();
            flujoEntrada.close();
            cliente.close();
            sc.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
}
