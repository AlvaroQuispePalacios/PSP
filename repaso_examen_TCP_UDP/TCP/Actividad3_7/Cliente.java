package repaso_examen_TCP_UDP.TCP.Actividad3_7;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        try {
            Socket cliente = new Socket("localhost", 4321);
            ObjectOutputStream objSalida = null;
            ObjectInputStream objEntrada = null;
            Scanner sc = new Scanner(System.in);
            int numero = 1;

            while (numero > 0) {
                System.out.println("Dime el numero");
                numero = sc.nextInt();
                Numeros objNumero = new Numeros(numero);

                objSalida = new ObjectOutputStream(cliente.getOutputStream());
                objSalida.writeObject(objNumero);
                
                objEntrada = new ObjectInputStream(cliente.getInputStream());
                Numeros objNumeroServidor = (Numeros) objEntrada.readObject();
                System.out.println("El cuadrado es: " + objNumeroServidor.getCuadrado());
                System.out.println("El cubo es: " + objNumeroServidor.getCubo());
                
                
            }

            sc.close();
            objSalida.close();
            cliente.close();
            
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
