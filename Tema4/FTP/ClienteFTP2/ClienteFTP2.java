package Tema4.FTP.ClienteFTP2;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

public class ClienteFTP2 {
    public static void main(String[] args) {
        FTPClient cliente = new FTPClient();
        String server = "localhost";
        System.out.println("Nos conectamos a: " + server);
        String usuario = "usuario";
        String pwd = "usuario";
        
        try {
            cliente.connect(server);
            cliente.enterLocalPassiveMode();

            boolean login = cliente.login(usuario, pwd);
            if(login){
                System.out.println("Login correcto");
            } else{
                System.out.println("Login incorrecto");
                cliente.disconnect();
                System.exit(1);
            }
            System.out.println("Directorio actual: " + cliente.printWorkingDirectory());

            FTPFile[] files = cliente.listFiles();
            String tipos[] = {"Fichero", "Directorio", "Enlace simb"};
            System.out.println("Ficheros en el directorio actual: " + files.length);
            for (int i = 0; i < files.length; i++) {
                System.out.println("\t" + files[i].getName() + " => " + tipos[files[i].getType()]);
            }
            boolean logout = cliente.logout();
            if (logout) {
                System.out.println("logout del servidor FTP...");

            }else{
                System.out.println("Error al hacer logout");
            }
            cliente.disconnect();
        } catch (Exception e) {
            // TODO: handle exception
        }

    }
    
}
