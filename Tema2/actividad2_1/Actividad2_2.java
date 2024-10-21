package Tema2.actividad2_1;

public class Actividad2_2 {

    public static class Tic implements Runnable {
        public void run() {
            try {
                while (true) {
                    System.out.println("Tic");
                }

            } catch (Exception InterruptedException) {
                
            }
        }
    }

    public static class Tac implements Runnable {
        public void run() {
            try {
                while (true) {
                    System.out.println("Tac");
                }

            } catch (Exception InterruptedException) {

            }

        }
    }
    public static void main(String[] args) {
        Tic tic = new Tic();
        Tac tac = new Tac();
        Thread hiloTac = new Thread(tac);
        Thread hiloTic = new Thread(tic);
        hiloTac.start();
        hiloTic.start();

    }


}
