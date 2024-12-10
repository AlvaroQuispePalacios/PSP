package tema3.Actividad3_5;

import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) {
        // Scanner sc = new Scanner(System.in);
        // int numeroClientes = sc.nextInt();
        // OutputStream salida = null;
        // DataOutputStream flujoSalida = null;
        // Socket cliente = null;
        // try {
        //     ServerSocket server = new ServerSocket(4321);
        //     for(int i = 1; i <= numeroClientes; i++){
        //         cliente = server.accept();
        //         salida = cliente.getOutputStream();
        //         flujoSalida = new DataOutputStream(salida);
        //         flujoSalida.writeUTF("Cliente numero "+ i);
        //     }
        //     salida.close();
        //     flujoSalida.close();
        //     cliente.close();
        //     server.close();
        //     sc.close();
            
        // } catch (Exception e) {}

        String numeroclientes = args[0];
        int clientes = Integer.parseInt(numeroclientes);
        OutputStream salida = null;
        DataOutputStream flujoSalida = null;
        Socket cliente = null;
        try {
            ServerSocket server = new ServerSocket(4321);
            for(int i = 1; i <= clientes; i++){
                cliente = server.accept();
                salida = cliente.getOutputStream();
                flujoSalida = new DataOutputStream(salida);
                flujoSalida.writeUTF("Cliente numero "+ i);
                
            }
            salida.close();
            flujoSalida.close();
            cliente.close();
            server.close();
            
        } catch (Exception e) {}
    }
    
}
