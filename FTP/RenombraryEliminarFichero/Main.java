package FTP.RenombraryEliminarFichero;

import org.apache.commons.net.ftp.*;

public class Main {
    public static void main(String[] args) {
        FTPClient cliente = new FTPClient();

        String servidor = "localhost";
        String user = "usuario";
        String pwd = "usuario";

        try {
            cliente.connect(servidor);
            cliente.enterLocalPassiveMode();
            boolean login = cliente.login(user, pwd);

            cliente.setFileType(FTP.BINARY_FILE_TYPE);
            String directorio_archivo_renombrar_eliminar = "/subir";

            if (login) {
                System.out.println("Login correcto");

                if(!cliente.changeWorkingDirectory(directorio_archivo_renombrar_eliminar)){
                    String nombre_directorio = "subir";
                    if(cliente.makeDirectory(nombre_directorio)){
                        System.out.println("Directorio creado");
                        cliente.changeWorkingDirectory(nombre_directorio);

                    }else{
                        System.out.println("No se ha podido crear el directorio");
                        System.exit(0);
                    }
                }
                System.out.println("Directorio actual: " + cliente.printWorkingDirectory());

                // Renombrar fichero
                if(cliente.rename("EJEMPLO.txt", "prueba_cambio.txt")){
                    System.out.println("El fichero ha sido renombrado");
                    FTPFile[] archivos = cliente.listFiles(cliente.printWorkingDirectory());
                    for (FTPFile ftpFile : archivos) {
                        System.out.println(ftpFile.getName());
                    }
                }

                
                cliente.logout();
                cliente.disconnect();
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
