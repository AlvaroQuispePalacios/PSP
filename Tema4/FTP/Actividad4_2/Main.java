package Tema4.FTP.Actividad4_2;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.commons.net.ftp.*;

public class Main {
    static FTPClient cliente = new FTPClient();
    static String servidor = "localhost";
    static String usuario = "usuario1";
    static String password = "usuario1";

    public static void main(String[] args) {
        JFileChooser buscador = new JFileChooser();
        buscador.setApproveButtonText("Subir archivo");
        buscador.setMultiSelectionEnabled(true);
        buscador.setFileFilter(new FileNameExtensionFilter("Solo archivos txt", "txt"));
        buscador.setCurrentDirectory(new File("C:\\Users\\Persona\\Desktop"));
        int resultado = buscador.showOpenDialog(buscador);
        boolean isLogin = iniciarSesion();

        if (resultado == JFileChooser.APPROVE_OPTION) {
            subirFichero(buscador.getSelectedFile().getAbsolutePath());
        }

    }

    public static boolean iniciarSesion(){
        boolean login = false;
        try {
            System.out.println("Conectandose al servidor " + servidor);
            cliente.connect(servidor);
            login = cliente.login(usuario, password);
            cliente.setFileType(FTP.BINARY_FILE_TYPE);
            cliente.enterLocalPassiveMode();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return login;
    }

    public static void subirFichero(String ruta) {
        System.out.println(ruta);
        try {



            String directorio = "/";
            if (login) {
                System.out.println("Login correcto");
                // Intenta cambiar al directorio /
                if (!cliente.changeWorkingDirectory(directorio)) {
                    String direc = "subir";
                    if (cliente.makeDirectory(direc)) {
                        System.out.println("Directorio " + direc + " creado");

                    } else {
                        System.out.println("No se ha podido crear el directorio");
                    }
                }
                System.out.println("Directorio actual: " + cliente.printWorkingDirectory());
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
