package Tema2.exercicis;

import java.util.Scanner;
public class Exercici3 {
    public static class Hilo extends Thread {
        public void run(){
            Scanner sc = new Scanner(System.in);
            System.out.println("Ingrese el nombre del atleta");
            String nombre = sc.nextLine();
            for (int i = 1; i <= 30; i++) {
                System.out.print(i + "km  ");
            }
            System.out.println("Llego a la meta: " + nombre);
            sc.close();
        }
        
    }
    public static void main(String[] args) {
        new Hilo().start();
    }
    
}
