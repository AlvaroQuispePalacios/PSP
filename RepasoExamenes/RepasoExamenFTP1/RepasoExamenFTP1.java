package RepasoExamenes.RepasoExamenFTP1;

import java.util.Scanner;

import org.apache.commons.net.ftp.*;

public class RepasoExamenFTP1 {
    static FTPClient cliente = new FTPClient();
    static String server = "localhost";
    boolean isLogin = false;
    static Scanner sc = new Scanner(System.in); 

    public static void main(String[] args) {
        int seleccionar = -1;
        while (seleccionar != 2) {
            System.out.println("1. Ingresar al servidor FTP\n2. Terminar programa");
            seleccionar = sc.nextInt();
            //Limpiar el buffer
            sc.nextLine();
            if(seleccionar == 1){
                if(!iniciarSesion()) return;
                System.out.println("Sesion iniciada correctamente\n");
                menuClienteFTP();
            }
        }
        System.out.println("Adios");

    }

    public static boolean iniciarSesion() {
        boolean login = false;
        System.out.println("Dime el usuario FTP");
        String usuario = sc.nextLine();

        System.out.println("Dime la constraseña FTP");
        String password = sc.nextLine();
        try {
            cliente.connect(server);
            cliente.enterLocalPassiveMode();
            login = cliente.login(usuario, password);
            
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return login;
    }

    public static void menuClienteFTP(){
        int seleccionar = -1;
        while (seleccionar != 3) {
            try {
                System.out.println("Ruta principal:" + cliente.printWorkingDirectory());
                mostrarArchivosDelServidorFTP();
                System.out.println("1. Ver comandos\n2. Ingresar comando");
                seleccionar = sc.nextInt();
                sc.nextLine();
                if(seleccionar == 1){
                    System.out.println("cd --Moverse entre carpetas Ejm: cd \"nombre-carpeta\"");
                    System.out.println("des --Descargar fichero Ejm: \"des nombre-fichero.extension\"");
                    
                }else if(seleccionar == 2){
                    System.out.print("Dime el comando: ");
                    String comando = sc.nextLine();
                    detectarComandos(comando);

                }else{
                    System.out.println("Entrada no valida");
                }
                
            } catch (Exception e) {
                System.out.println(e.toString());
                System.out.println();
                menuClienteFTP();
                

            }
        }
    }

    public static void mostrarArchivosDelServidorFTP(){
        try {
            FTPFile[] archivosDelServidor = cliente.listFiles();
            for(FTPFile archivo : archivosDelServidor){
                if(archivo.isDirectory()){
                    System.out.println("\t"+archivo.getName() + "/");
                }else{
                    System.out.println("\t"+ archivo.getName());
                }
            }
            System.out.println();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public static void detectarComandos(String comando){
        String[] detectarComandos = comando.split(" ");
        String comandoDetectado = detectarComandos[0];
        
        String ruta = detectarComandos[1];
        try {
            switch (comandoDetectado) {
                case "cd":
                    System.out.println("Comando correcto");
                    moverseACarpeta(ruta);
                    break;
                case "descargar":
                    System.out.println("Comando correcto");
                    descargarArchivo(ruta);
                    break;
                default:
                    System.out.println("Comando no valido");
                    break;
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public static void moverseACarpeta(String ruta){
        System.out.println("");
    }

    public static void descargarArchivo(String ruta){

    }
}
