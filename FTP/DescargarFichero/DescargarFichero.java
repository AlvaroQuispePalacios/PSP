package FTP.DescargarFichero;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;

import org.apache.commons.net.ftp.*;

public class DescargarFichero {
    public static void main(String[] args) {
        FTPClient cliente = new FTPClient();

        String servidor = "localhost";
        String user = "usuario";
        String pwd = "usuario";

        try {
            cliente.connect(servidor);
            cliente.enterLocalPassiveMode();
            boolean login = cliente.login(user, pwd);

            if (login) {
                System.out.println("login correcto");
                String directorio_descarga = "/subir";
                cliente.changeWorkingDirectory(directorio_descarga);

                String ruta_descarga_archivo = "C:\\Users\\ciclesgs\\Desktop\\archivo_descarga.txt";
                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(ruta_descarga_archivo));

                if(cliente.retrieveFile("EJEMPLO.txt", out)){
                    System.out.println("Recuperado correctamente");
                }else{
                    System.out.println("No se ha podido descargar...");
                }
                out.close();
                cliente.logout();
                cliente.disconnect();

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
