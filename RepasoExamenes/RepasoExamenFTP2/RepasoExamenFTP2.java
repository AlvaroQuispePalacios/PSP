package RepasoExamenes.RepasoExamenFTP2;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

public class RepasoExamenFTP2 {
    static String server = "localhost";
    static FTPClient cliente = null;
    static Scanner sc = new Scanner(System.in);
    static String directorioActual;
    static JFileChooser jfc = new JFileChooser();

    public static void main(String[] args) {
        if(iniciarSesion()){
            menu();
        }
    }

    public static boolean iniciarSesion() {
        System.out.print("Dime tu usuario: ");
        String usuario = sc.nextLine();
        System.out.print("Dime tu contraseña: ");
        String pwd = sc.nextLine();

        try {
            cliente = new FTPClient();
            cliente.connect(server);
            if (cliente.login(usuario, pwd))
                return true;
        } catch (Exception e) {

        }
        return false;
    }

    public static void menu() {
        int seleccionar = -1;
        while (seleccionar != 5) {
            mostrarArchivosServidor();
            try {

                System.out.println("1. Subir fichero\n2. Descargar fichero\n5. Salir");
                seleccionar = sc.nextInt();
                sc.nextLine();
                switch (seleccionar) {
                    case 1:
                        subirFichero();
                        break;
                    case 2:
                        descargarFichero();
                    default:
                        break;
                }

            } catch (Exception e) {
                // TODO: handle exception
            }
        }
    }

    public static void mostrarArchivosServidor() {
        try {
            System.out.println("Ruta actual: " + cliente.printWorkingDirectory());
            FTPFile[] archivos = cliente.listFiles(cliente.printWorkingDirectory());
            for (FTPFile archivo : archivos) {
                if (archivo.isDirectory()) {
                    System.out.println("\t" + archivo.getName() + "/");

                }else{
                    System.out.println("\t" + archivo.getName());
                }
            }
        } catch (Exception e) {
            System.out.println("Error al mostrar los archivos del servidor " + e.toString());
        }
    }

    public static void subirFichero() {
        jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        jfc.setDialogTitle("Selecciona el fichero a subir");
        jfc.setApproveButtonText("Subir");
        int resultado = jfc.showOpenDialog(jfc);

        if(resultado == JFileChooser.APPROVE_OPTION){
            String rutaArchivoASubir = jfc.getSelectedFile().getAbsolutePath();
            String[] rutaPartes = rutaArchivoASubir.split("\\\\");
            String nombreArchivo = rutaPartes[rutaPartes.length - 1];

            try {
                BufferedInputStream bis = new BufferedInputStream(new FileInputStream(rutaArchivoASubir));
                if(cliente.storeFile(nombreArchivo, bis)){
                    JOptionPane.showMessageDialog(null, nombreArchivo + "subido correctamente", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                    System.out.println("Fichero subido correctamente");
                }else{
                    System.out.println("No se ha podido subir el fichero");
                }
            } catch (Exception e) {
                System.out.println("Error al subir el fichero " + e.getMessage());
            }
        }
        
    }

    public static void descargarFichero(){
        System.out.println("Dime el nombre del fichero a descargar");
        String nombreDelArchivo = sc.nextLine();

        if(!existeFicheroEnElServidor(nombreDelArchivo)){
            System.out.println("El fichero no existe en el servidor");
            return;
        }

        jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        jfc.setDialogTitle("Selecciona donde se guardara");
        jfc.setApproveButtonText("Guardar");
        int resultado = jfc.showOpenDialog(jfc);

        if(resultado == JFileChooser.APPROVE_OPTION){
            String rutaDestino = jfc.getSelectedFile().getAbsolutePath();
            try {
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(rutaDestino + "\\" + nombreDelArchivo));
                if(cliente.retrieveFile(nombreDelArchivo, bos)){
                    JOptionPane.showMessageDialog(null, nombreDelArchivo + "Descargado correctamente", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                    
                }else{
                    JOptionPane.showMessageDialog(null, nombreDelArchivo + "No se pudo descargado", "Mensaje", JOptionPane.INFORMATION_MESSAGE);

                }
                bos.close();

            } catch (Exception e) {
                // TODO: handle exception
            }
        }


    }


    public static boolean existeFicheroEnElServidor(String nombreArchivo){
        try {
            FTPFile[] archivos = cliente.listFiles();
            for(FTPFile archivo : archivos){
                if(archivo.isFile() && archivo.getName().equals(nombreArchivo)){
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println("Error al comprobar si el archivo existe " + e.getMessage());
        }
        return false;
    }
}
