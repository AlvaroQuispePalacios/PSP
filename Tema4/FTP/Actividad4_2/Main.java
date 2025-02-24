package Tema4.FTP.Actividad4_2;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
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
        if (isLogin) {
            System.out.println("Login correcto");
            if (resultado == JFileChooser.APPROVE_OPTION) {
                subirFichero(buscador.getSelectedFile().getAbsolutePath());
            }
            cerrarSesion();
        }

    }

    public static boolean iniciarSesion() {
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

    public static void subirFichero(String rutaFicheroASubir) {
        System.out.println("Fichero a subir " + rutaFicheroASubir);
        String[] partesRuta = rutaFicheroASubir.split("\\\\");
        String nombreFichero = partesRuta[partesRuta.length - 1];

        try {
            System.out.println("Directorio actual" + cliente.printWorkingDirectory());
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(rutaFicheroASubir));
            if (cliente.storeFile(nombreFichero, bis)) {
                JOptionPane.showMessageDialog(null, nombreFichero + " subido correctamente", "Mensaje",
                        JOptionPane.INFORMATION_MESSAGE);
                System.out.println("Fichero subido correctamente");
            } else {
                System.out.println("No se ha podido subir el fichero");
            }
            bis.close();
        } catch (Exception e) {
            // TODO: handle exception
        }

    }

    public static void cerrarSesion() {
        try {
            cliente.logout();
            cliente.disconnect();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
