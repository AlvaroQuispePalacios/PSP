package examen_alvaro_quispe_tcp_udp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        // :C El servidor puede aceptar varios clientes pero los clientes solo pueden hacer una consulta una vez (Esto se hace con hilos)
        Scanner sc = new Scanner(System.in);

        try {
            Socket cliente = new Socket("localhost", 4321);
            DataInputStream flujoEntrada = new DataInputStream(cliente.getInputStream());
            int id = flujoEntrada.readInt();
            System.out.println("SOY EL CLIENTE " + id);

            while (true) {
                System.out.println("Introduce el identificado a consultar: ");
                String identificadorProfesor = sc.nextLine();

                if(identificadorProfesor.equals("*")) break;

                // Recibe el identificador del servidor
                DataOutputStream enviarIdentificadorProfesor = new DataOutputStream(cliente.getOutputStream());
                enviarIdentificadorProfesor.writeUTF(identificadorProfesor);

                // Recibe el objeto profesor del servidor
                ObjectInputStream profesorServidor = new ObjectInputStream(cliente.getInputStream());
                Profesor objProfesorServidor = (Profesor) profesorServidor.readObject();
                if(objProfesorServidor == null){
                    System.out.println("El profesor con el identificador " + identificadorProfesor + " no existe");
                }else{
                    objProfesorServidor.mostrarDatos();

                }
                
            }
            sc.close();
            cliente.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } 
    }
}