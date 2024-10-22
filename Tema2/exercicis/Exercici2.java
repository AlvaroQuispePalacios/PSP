package Tema2.exercicis;

public class Exercici2 {
    public static class Hilo extends Thread {
        public void run() {
            try {
                for (int i = 1; i <= 20; i++) {
                    System.out.println(i);
                    sleep(1500);
                }

            } catch (Exception e) {
                
            }
        }

    }

    public static void main(String[] args) {
        new Hilo().start();
    }

}
