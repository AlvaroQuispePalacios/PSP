package tema3.Actividad3_7;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Cliente2 {
    public static void main(String[] args)throws Exception{
        Scanner sc = new Scanner(System.in);
        Socket cliente = new Socket("localhost", 4321);
        ObjectOutputStream outObjeto = null;
        int numero = 1;
        while(numero > 0){
            System.out.println("Dime un numero");
            numero = sc.nextInt();

            Numeros enviar = new Numeros(numero);
            outObjeto = new ObjectOutputStream(cliente.getOutputStream());
            outObjeto.reset();
            outObjeto.writeObject(enviar);

            ObjectInputStream inObjeto = new ObjectInputStream(cliente.getInputStream());
            Numeros recibir = (Numeros) inObjeto.readObject();
            System.out.println(recibir.getCuadrado());
            System.out.println(recibir.getCubo());
        }
        outObjeto.close();
        cliente.close();
        sc.close();
    }
}
