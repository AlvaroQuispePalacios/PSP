package tema3.Actividad3_7;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server2 {
    public static void main(String[] args) throws Exception{
        int numeroPuerto = 4321;
        ServerSocket servidor = new ServerSocket(numeroPuerto);
        Socket cliente = null;
        ObjectInputStream inObj = null;
        ObjectOutputStream outObj = null;

        while (true) {
            System.out.println("Esperando cliente");
            cliente = servidor.accept();
            
            inObj = new ObjectInputStream(cliente.getInputStream());
            Numeros dato = (Numeros) inObj.readObject();
            if(dato.getNumero() < 0) break;
            dato.setCuadrado(dato.getNumero() * dato.getNumero());
            dato.setCubo(dato.getNumero() * dato.getNumero() * dato.getNumero());
    
            outObj = new ObjectOutputStream(cliente.getOutputStream());
            outObj.reset();
            outObj.writeObject(dato);
            
        }

        inObj.close();
        outObj.close();
        cliente.close();
        servidor.close();
    }
}
