package Tema2.actividad2_1;

public class Actividad2_1 {

    public static class Tic extends Thread {
        public void run() {
            try {
                while (true) {
                    System.out.println("Tic");
                    sleep(2000);
                }

            } catch (Exception InterruptedException) {
                
            }
        }
    }

    public static class Tac extends Thread {
        public void run() {
            try {
                while (true) {
                    System.out.println("Tac");
                    sleep(2000);
                }

            } catch (Exception InterruptedException) {

            }

        }
    }
    public static void main(String[] args) {
        Tic tic = new Tic();
        Tac tac = new Tac();
        tic.start();
        tac.start();
    }


}
