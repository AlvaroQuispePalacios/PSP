package Tema2.exercicis;

public class Exercici5 {
    public static class HiloPar extends Thread {
        public void run() {
            int suma = 0;
            for (int i = 1; i <= 10; i++) {
                if (i % 2 == 0) {
                    System.out.println(i);
                    suma += i;
                };
            }
            System.out.println("La suma de de pares es: "+suma);
        }
    }

    public static class HiloImpar extends Thread {
        public void run() {
            int suma = 0;
            for (int i = 1; i <= 10; i++) {
                if (i % 2 != 0) {
                    System.out.println(i);
                    suma += i;
                };
            }
            System.out.println("La suma de los impares es: " + suma);

        }
    }

    public static void main(String[] args) {
        new HiloPar().start();
        new HiloImpar().start();

    }

}
