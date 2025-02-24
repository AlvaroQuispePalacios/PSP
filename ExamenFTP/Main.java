package ExamenFTP;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Scanner;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

public class Main {
    static FTPClient cliente = new FTPClient();
    static String servidor = "localhost";
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        if (iniciarSesion()) {
            try {
                menu();
            } catch (Exception e) {
                // TODO: handle exception
            }
        }

    }

    public static void menu() {
        int seleccionar = -1;
        while (seleccionar != 4) {
            mostrarFicherosServidor();
            System.out.println("1. Descargar ficheros del servidor\n2. Subir fichero\n3. Eliminar archivos");
            seleccionar = sc.nextInt();
            sc.nextLine();

            switch (seleccionar) {
                case 1:
                    bajarArchivosDelServidor();
                    break;
                case 2:
                    subirArchivoAlServidor();
                    break;
                case 3:
                    eliminarFicheros();
                    break;
                default:
                    System.out.println("No se selecciono ninguna opción");
                    break;
            }

        }

    }

    public static boolean iniciarSesion() {
        try {
            cliente.connect(servidor);
            if (cliente.login("examenftp", "123456")) {
                System.out.println("Sesion iniciada");
                return true;
            }

        } catch (Exception e) {
            System.out.println("Algo fallo al iniciar sesion " + e.getMessage());
        }
        return false;
    }

    public static void mostrarFicherosServidor() {
        try {
            cliente.changeWorkingDirectory("alvaro_servidor");
            System.out.println("Directorio actual: " + cliente.printWorkingDirectory());
            FTPFile[] archivos = cliente.listFiles();
            for (FTPFile archivo : archivos) {
                System.out.println("\t" + archivo.getName());
            }
        } catch (Exception e) {
            System.out.println("Algo fallo al mostrar los ficheros del servidor: " + e.getMessage());
        }
    }

    public static void bajarArchivosDelServidor() {

        File carpeta = new File("C:\\alvaro_cliente");
        // Crea la carpeta si no existe
        if (!carpeta.exists()) {
            carpeta.mkdirs();
        }

        try {
            cliente.changeWorkingDirectory("alvaro_servidor");
            System.out.println("Directorio actual: " + cliente.printWorkingDirectory());

            FTPFile[] archivosServidor = cliente.listFiles();

            for (FTPFile archivoServidor : archivosServidor) {
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("C:\\alvaro_cliente\\"));
                String nombreFichero = archivoServidor.getName();
                if (cliente.retrieveFile(nombreFichero, bos)) {
                    System.out.println("Archivo descargado correctamente");
                } else {
                    System.out.println("No se pudo descargar el archivo");
                }
                bos.close();

            }
        } catch (Exception e) {
            System.out.println("Error al descargar el archivo " + e.getMessage());
        }
    }

    public static void subirArchivoAlServidor() {
        try {
            File carpeta = new File("C:\\alvaro_cliente");
            // Crea la carpeta si no existe
            if (!carpeta.exists()) {
                carpeta.mkdirs();
            }

            File[] archivosClientes = carpeta.listFiles();
            System.out.println("Archivos del cliente");

            for (File archivoCliente : archivosClientes) {
                System.out.println(archivoCliente.getName());
            }

            System.out.println("Dime el nombre del fichero a subir");
            String nombreFichero = sc.nextLine();

            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(carpeta + "\\" + nombreFichero));

            cliente.changeWorkingDirectory("alvaro_servidor");

            if (cliente.storeFile(nombreFichero, bis)) {
                System.out.println("Archivo subido correctamente");
            } else {
                System.out.println("El archivo no se pudo subir");
            }

            bis.close();

        } catch (Exception e) {
            System.out.println("Error al subir el archivo");

        }
    }

    public static void eliminarFicheros() {
        try {
            cliente.changeWorkingDirectory("alvaro_servidor");
            FTPFile[] ficherosServidor = cliente.listFiles();
            for (FTPFile ficheroServidor : ficherosServidor) {
                cliente.deleteFile(ficheroServidor.getName());
            }

            File carpeta = new File("C:\\alvaro_cliente");
            // Crea la carpeta si no existe
            if (!carpeta.exists()) {
                carpeta.mkdirs();
            }

            File[] archivosClientes = carpeta.listFiles();
            for (File archivoCliente : archivosClientes) {
                archivoCliente.delete();
            }

            cliente.changeWorkingDirectory("/");

            FTPFile[] directorios = cliente.listDirectories();
            for(FTPFile directorio: directorios){
                if(directorio.getName().equals("registros")){
                    cliente.dele(directorio.getName());
                }
            }


            System.out.println("Archivos eliminados correctamente");

        } catch (Exception e) {
            System.out.println("Error al eliminar los ficheros" + e.getMessage());
        }
    }

    public static void registros() {
        try {
            cliente.changeWorkingDirectory("/");

            FTPFile[] directorios = cliente.listDirectories();
            for(FTPFile directorio: directorios){
                if(!directorio.getName().equals("registros")){
                    cliente.makeDirectory("registros");
                }
            }


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
