package Tema2.exercicis;

import java.util.Scanner;

public class Exercici1 {
    public static class PrimerHilo extends Thread {
        public void run() {
            Scanner sc = new Scanner(System.in);
            System.out.println("Dime el nombre del empleado");
            String nombre = sc.nextLine();
            System.out.println("Ingrese el dia");
            String dia = sc.nextLine();
            System.out.println("Ingrese la hora");
            double hora = sc.nextDouble();
            if (hora > 8) {
                System.out.println(nombre + "llego tarde el dia " + dia);
            } else {
                System.out.println(nombre + "llego temprano el dia " + dia);

                sc.close();
            }
        }
    }

    public static class SegundoHilo extends Thread {
        public void run() {
            Scanner sc = new Scanner(System.in);
            System.out.println("Dime el nombre del empleado");
            String nombre = sc.nextLine();
            System.out.println("Ingrese el dia");
            String dia = sc.nextLine();
            System.out.println("Ingrese la hora");
            double hora = sc.nextDouble();
            if (hora > 8) {
                System.out.println(nombre + " llego tarde el dia " + dia);
            } else {
                System.out.println(nombre + " llego temprano el dia " + dia);

                sc.close();
            }
        }
    }

    public static void main(String[] args) {
        new PrimerHilo().start();
        new SegundoHilo().start();
    }

}
