package actividades;

import java.io.*;
import java.util.Scanner;

public class Shutdowner {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("1. Apagar\n2. Reiniciar\n3. Suspender");
        int seleccionar = sc.nextInt();
        String comando = "";
        switch (seleccionar) {
            case 1:
                comando = "shutdown /s /t 120";
                break;
            case 2:
                comando = "shutdown /r /t 120";
                break;
            case 3:
                comando = "shutdown /h";
                break;
            default:
                System.out.println("No seleccionado ninguna opcion");
                break;
        }
        Process pb = new ProcessBuilder("CMD", "/C", comando).start();
        System.out.println(comando);
    }
}