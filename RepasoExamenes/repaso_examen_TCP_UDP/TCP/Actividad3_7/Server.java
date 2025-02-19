package RepasoExamenes.repaso_examen_TCP_UDP.TCP.Actividad3_7;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(4321);
            Socket cliente = server.accept();
            ObjectInputStream objEntrada = null;
            ObjectOutputStream objSalida = null;
            while (true) {
                objEntrada = new ObjectInputStream(cliente.getInputStream());
                Numeros objNumeros = (Numeros) objEntrada.readObject();
                if(objNumeros.getNumero() < 0) break;
                objNumeros.setCuadrado(objNumeros.getNumero() * objNumeros.getNumero());
                objNumeros.setCubo(objNumeros.getNumero() * objNumeros.getNumero() * objNumeros.getNumero());
    
                objSalida = new ObjectOutputStream(cliente.getOutputStream());
                objSalida.writeObject(objNumeros);
                
            }

            objEntrada.close();
            objSalida.close();
            server.close();

            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
