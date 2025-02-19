package RepasoExamenes.examen_alvaro_quispe_tcp_udp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try {
            Asignatura[] asignaturas1 = { new Asignatura(1, "Programacion"), new Asignatura(7, "Acceso a datos") };
            Asignatura[] asignaturas2 = { new Asignatura(2, "Algebra") };
            Asignatura[] asignaturas3 = { new Asignatura(3, "Filosofia") };
            Asignatura[] asignaturas4 = { new Asignatura(4, "Quimica") };
            Asignatura[] asignaturas5 = { new Asignatura(5, "a"), new Asignatura(6, "b") };

            String[] nombreEspecialidades = { "Informatica", "Carpinteria", "Ciencias", "a", "Filosofia" };
            Especialidad[] especialidades = new Especialidad[5];
            for (int i = 0; i < especialidades.length; i++) {
                especialidades[i] = new Especialidad(i, nombreEspecialidades[i]);
            }

            Profesor[] profesores = new Profesor[5];
            profesores[0] = new Profesor(1, "Pedro", asignaturas1, especialidades[0]);
            profesores[1] = new Profesor(2, "Juan", asignaturas2, especialidades[2]);
            profesores[2] = new Profesor(3, "Nicolas", asignaturas3, especialidades[1]);
            profesores[3] = new Profesor(4, "Daniel", asignaturas4, especialidades[4]);
            profesores[4] = new Profesor(5, "Pep", asignaturas5, especialidades[3]);


            ServerSocket server = new ServerSocket(4321);
            System.out.println("Servidor iniciado... ");

            int identificadorCliente = 1;
            while (true) {
                Socket cliente = server.accept();
                System.out.println("Cliente " + identificadorCliente + " conectado: ");

                // Envia mensaje al cliente con su identificado
                DataOutputStream identificadorCli = new DataOutputStream(cliente.getOutputStream());
                identificadorCli.writeInt(identificadorCliente);

                // Recibe el identifador del profesor introducido por el usuario
                DataInputStream identificadorProfesor = new DataInputStream(cliente.getInputStream());
                int parseIdentificadorProfesor = Integer.parseInt(identificadorProfesor.readUTF());
                
                // Si el cliente introdujo un identificador de profesor valido envia el objeto Profesor al cliente, si el profesor no existe envia un objeto profesor vacio
                Profesor profesorAEnviar;
                if (parseIdentificadorProfesor >= 1 && parseIdentificadorProfesor <= 5) {
                    System.out.println("Consultando id: " + parseIdentificadorProfesor + ", solicitado por cliente "+ identificadorCliente);
                    profesorAEnviar = profesores[parseIdentificadorProfesor - 1];
                        
                }else{
                    profesorAEnviar = new Profesor();
                }
                ObjectOutputStream objProfesor = new ObjectOutputStream(cliente.getOutputStream());
                objProfesor.writeObject(profesorAEnviar);

                identificadorCliente++;
            }
        

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
