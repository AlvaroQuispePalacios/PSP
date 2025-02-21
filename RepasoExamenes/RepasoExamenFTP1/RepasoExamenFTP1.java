package RepasoExamenes.RepasoExamenFTP1;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
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
                    System.out.println("Cuando un parametro esta entre \"[]\" significa que es opcional");
                    System.out.println("cd --Moverse entre carpetas Ejm: \"cd -nombre-carpeta\"");
                    System.out.println("des --Descargar fichero Ejm: \"des -nombreFichero.extension -rutaDeDestino\"");
                    System.out.println(
                            "subir --Subir fichero Ejm: \"subir -rutaDelArchivo\\archivo.txt -[nuevoNombreDelArchivo.txt]\"");

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
                    archivosOrdenados.add(0, "\t" + archivo.getName() + "/");
                } else {
                    archivosOrdenados.add(archivosOrdenados.size(), "\t" + archivo.getName());
                }
            }

            for (String archivoOrdenado : archivosOrdenados) {
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

            // Subir
        } else if (comandoM.startsWith("subir")) {
            String[] parametrosComandoSubir;
            try {
                parametrosComandoSubir = comandoM.substring(7, comandoM.length()).split("-");
                // System.out.println("Numeros de parametros del comando subir: " + parametrosComandoSubir.length);
                String rutaArchivo = parametrosComandoSubir[0].trim();
                if (parametrosComandoSubir.length == 1) {
                    // System.out.println("Ruta del archivo " + rutaArchivo);
                    subirArchivo(rutaArchivo);
                } else if (parametrosComandoSubir.length == 2) {
                    String nuevoNombreArchivo = parametrosComandoSubir[1].trim();
                    subirArchivoConNuevoNombre(rutaArchivo, nuevoNombreArchivo);
                } else {
                    System.out.println("Se ha agregado parametros demas");
                }
            } catch (Exception e) {
                System.out.println("No se a agregado ningun parametro " + e.getMessage());
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
        File rutaDestinoFichero = new File(rutaDestino);

        if (!archivoExiste) {
            System.out.println("El archivo no existe");
            return;
        }

        if (!rutaDestinoFichero.exists()) {
            try {
                if (rutaDestinoFichero.createNewFile()) {
                    System.out.println("El fichero se ha creado correctamente");
                } else {
                    System.out.println("El fichero no se ha podido crear");
                    ;
                }

            } catch (Exception e) {
                System.out.println("El archivo no se puedo crear" + e.toString());
            }
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
            System.out.println("Error en descargarArchivos " + e.toString());
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

    public static void subirArchivo(String rutaArchivo) {
        String[] partesRutaArchivo = rutaArchivo.split("\\\\");
        String nombreArchivo = partesRutaArchivo[partesRutaArchivo.length - 1];
        try {
            if (!cliente.changeWorkingDirectory(directorioActual)) return;
            // Verificar si el archivo existe en el servidor si existe preguntar si quiere sobreescribir el archivo
            if(archivoExisteServidor(nombreArchivo)){
                System.out.println("El archivo ya existe en el servidor. ¿Quiere sobreescribirlo? (Y/N)");
                String sobreescribir = sc.nextLine().trim().toUpperCase();
                if(sobreescribir.equals("N")) return;
            }

            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(rutaArchivo));
            // 
            if(cliente.storeFile(nombreArchivo, bis)){
                System.out.println("Archivo subido correctamente");
            }else{
                System.out.println("No se ha podido subir el fichero");
            }
            bis.close();
        } catch (Exception e) {
            System.out.println("Error al subir el fichero " + e.getMessage());
        }
    }

    public static void subirArchivoConNuevoNombre(String rutaArchivo, String nuevoNombre) {
        try {
            if (!cliente.changeWorkingDirectory(directorioActual)) return;

            if(archivoExisteServidor(nuevoNombre)){
                System.out.println("El archivo ya existe en el servidor. ¿Quiere sobreescribirlo? (Y/N)");
                String sobreescribir = sc.nextLine().trim().toUpperCase();
                if(sobreescribir.equals("N")) return;
            }

            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(rutaArchivo));

            if(cliente.storeFile(nuevoNombre, bis)){
                System.out.println("Archivo subido correctamente");
            }else{
                System.out.println("No se ha podido subir el fichero");
            }
            bis.close();
        } catch (Exception e) {
            System.out.println("Error al subir el fichero " + e.getMessage());
        }
    }
}
