package RepasoExamenes.RepasoExamenFTP1;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.commons.net.ftp.*;

public class RepasoExamenFTP1 {
    static FTPClient cliente = new FTPClient();
    static String server = "localhost";
    boolean isLogin = false;
    static Scanner sc = new Scanner(System.in);
    static String directorioActual;

    public static void main(String[] args) {
        int seleccionar = -1;
        while (seleccionar != 2) {
            System.out.println("1. Ingresar al servidor FTP\n2. Terminar programa");
            seleccionar = sc.nextInt();
            // Limpiar el buffer
            sc.nextLine();
            if (seleccionar == 1) {
                if (!iniciarSesion())
                    return;
                System.out.println("Sesion iniciada correctamente\n");
                menuClienteFTP();
            }
        }
        System.out.println("Adios");

    }

    public static boolean iniciarSesion() {
        boolean login = false;
        try {
            System.out.println("Dime el usuario FTP");
            String usuario = sc.nextLine();

            System.out.println("Dime la constraseña FTP");
            String password = sc.nextLine();

            cliente.connect(server);
            cliente.enterLocalPassiveMode();
            login = cliente.login(usuario, password);

        } catch (Exception e) {
            System.out.println("Usuario invalido");
            System.out.println(e.toString());
        }
        return login;
    }

    public static void menuClienteFTP() {
        int seleccionar = -1;
        while (seleccionar != 3) {
            try {
                System.out.println("Ruta principal: " + cliente.printWorkingDirectory());
                directorioActual = cliente.printWorkingDirectory();

                mostrarArchivosDelServidorFTP();
                System.out.println("1. Ver comandos\n2. Ingresar comando");
                seleccionar = sc.nextInt();
                sc.nextLine();
                if (seleccionar == 1) {
                    System.out.println("cd --Moverse entre carpetas Ejm: \"cd -nombre-carpeta\"");
                    System.out.println("des --Descargar fichero Ejm: \"des -nombreFichero.extension -rutaDeDestino\"");
                    System.out.println("subir --Descargar fichero Ejm: \"des -nombreFichero.extension -rutaDeDestino\"");


                } else if (seleccionar == 2) {
                    System.out.print("Dime el comando: ");
                    String comando = sc.nextLine();
                    detectarComandos(comando);

                } else {
                    System.out.println("Entrada no valida");
                }

            } catch (Exception e) {
                System.out.println(e.toString());
                System.out.println();
                menuClienteFTP();

            }
        }
    }

    public static void mostrarArchivosDelServidorFTP() {
        ArrayList<String> archivosOrdenados = new ArrayList<String>();
        try {
            FTPFile[] archivosDelServidor = cliente.listFiles(directorioActual);
            for (FTPFile archivo : archivosDelServidor) {
                if (archivo.isDirectory()) {
                    archivosOrdenados.add(0,  "\t" + archivo.getName() + "/");
                } else {
                    archivosOrdenados.add(archivosOrdenados.size(),"\t" + archivo.getName());
                }
            }
            
            for(String archivoOrdenado : archivosOrdenados){
                System.out.println(archivoOrdenado);
            }
            
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public static void detectarComandos(String comando) {
        String comandoM = comando.trim();
        if (comandoM.isEmpty()) {
            System.out.println("El comando no es valido");
            return;
        }

        if (comandoM.startsWith("cd")) {
            System.out.println("Es una movimiento");

        } else if (comandoM.startsWith("des")) {
            String[] comandoDescargar;
            try {
                comandoDescargar = comandoM.substring(5, comandoM.length()).split("-");

                if (comandoDescargar.length == 1) {
                    System.out.println("Falta la ruta de destino");

                } else if (comandoDescargar.length == 2) {
                    String nombreArchivo = comandoDescargar[0].trim();
                    String rutaDestino = comandoDescargar[1].trim();
                    descargarArchivo(nombreArchivo, rutaDestino);

                } else {
                    System.out.println("Se ha agregado un parametro demas");
                }
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("No se a agregado ningun parametro");
            }

        } else {
            System.out.println("El comando no es valido");
        }

    }

    public static void moverseACarpeta(String ruta) {

        System.out.println(ruta);
    }

    public static void descargarArchivo(String nombreArchivo, String rutaDestino) {
        boolean archivoExiste = archivoExisteServidor(nombreArchivo);
        if (!archivoExiste) {
            System.out.println("El archivo no existe");
            return;
        }

        try {
            // Cambiamos a la ruta actual
            cliente.changeWorkingDirectory(directorioActual);
            // Verificar si existe el fichero donde se guardará los datos del fichero del
            // servidor si no existe crea uno con el mismo nombre del fichero del servidor
            // en local

            // Donde se guardará los datos del archivo del servidor
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(rutaDestino));

            // Escribe el archivo del servidor "nombreArchivo" en la ruta de destino
            if (cliente.retrieveFile(nombreArchivo, out)) {
                System.out.println("Archivo descargado correctamente");
            } else {
                System.out.println("No se ha podido descargar el archivo");
            }
            out.close();
        } catch (Exception e) {
            System.out.println("Error en descargarArchivos "+e.toString());
        }

    }

    public static boolean archivoExisteServidor(String nombreArchivo) {
        try {
            FTPFile[] archivosDelServidor = cliente.listFiles(directorioActual);
            for (FTPFile archivo : archivosDelServidor) {
                if (archivo.isFile() && archivo.getName().equals(nombreArchivo)) {
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return false;
    }

    public static void validarRutaDeDestino(){

    }
}
