package FTP.SubirFichero;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.net.ftp.*;

// public class SubirFichero {
//     public static void main(String[] args) {
//         FTPClient cliente = new FTPClient();

//         String servidor = "localhost";
//         String user = "usuario";
//         String pwd = "usuario";

//         try {
//             cliente.connect(servidor);
//             String ruta_directorio_subir = "/subir";
//             boolean login = cliente.login(user, pwd);

//             if(login){
//                 System.out.println("Login correcto");

//                 if(!cliente.changeWorkingDirectory(ruta_directorio_subir)){
//                     String directorio = "subir";

//                     if(cliente.makeDirectory(directorio)){
//                         System.out.println("Directorio: " + directorio + "creado...");
//                         cliente.changeWorkingDirectory(directorio);

//                     }else {
//                         System.out.println("No se pudo crear");
//                         System.exit(0);
//                     }
//                 }

//                 System.out.println("Directorio actual " + cliente.printWorkingDirectory());
//                 String archivo = "C:\\Users\\ciclesgs\\Desktop\\SqlServerCositas.txt";
//                 BufferedInputStream in = new BufferedInputStream(new FileInputStream(archivo));
//                 if(cliente.storeFile("prueba_subida.txt", in)){
//                     System.out.println("Subido correctamente");
//                 }else{
//                     System.out.println("No se ha podido subir el fichero");
//                 }
//                 in.close();
//                 cliente.logout();
//                 cliente.disconnect();
//             }
//         } catch (Exception e) {
//             System.out.println(e.getMessage());
//         }
//     }
// }
public class SubirFichero {
    public static void main(String[] args) {
        FTPClient cliente = new FTPClient();

        String servidor = "localhost";
        String user = "usuario1";
        String pasw = "usuario1";

        try {
            System.out.println("Conectandose a " + servidor);
            cliente.connect(servidor);
            boolean login = cliente.login(user, pasw);

            cliente.setFileType(FTP.BINARY_FILE_TYPE);
            String direc = "/";
            cliente.enterLocalPassiveMode();

            if (login) {
                System.out.println("Login correcto");

                if (!cliente.changeWorkingDirectory(direc)) {
                    String directorio = "subir";

                    if (cliente.makeDirectory(directorio)) {
                        System.out.println("Directorio :  " +directorio + " creado ...");
                        cliente.changeWorkingDirectory(directorio);
                    } else {
                        System.out.println("No se ha podido crear el Directorio");
                        System.exit(0);
                    }

                }

                System.out.println("Directorio actual: " + cliente.printWorkingDirectory());

                String archivo = "C:\\Users\\ciclesgs\\Desktop\\SqlServerCositas.txt";
                BufferedInputStream in = new BufferedInputStream(new FileInputStream(archivo));

                if (cliente.storeFile("EJEMPLO.txt", in))
                    System.out.println("Subido correctamente... ");
                else
                    System.out.println("No se ha podido subir el fichero... ");

                in.close(); // Cerrar flujo
                cliente.logout();
                cliente.disconnect();
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }// main

}// ..