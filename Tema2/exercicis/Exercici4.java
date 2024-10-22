package Tema2.exercicis;
import java.util.Scanner;

public class Exercici4 {
    public static class Hilo extends Thread{
        String nombre;
        public Hilo(String nombre){
            this.nombre = nombre;
        }

        public void run(){
            Scanner sc = new Scanner(System.in);
            for (int i = 1; i <= 30; i++) {
                System.out.print(i + "mt  ");
            }
            System.out.println("Llego a la meta " + this.nombre);
            sc.close();
        }
    }

    public static void main(String[] args) {
        new Hilo("Alvaro").start();
        new Hilo("Francisco").start();
        new Hilo("Pedro").start();
    }
    
}
