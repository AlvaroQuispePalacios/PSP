package RepasoExamenes.RepasoExamen.filosofos;

public class Main {
    public static void main(String[] args) {
        final int NUM_FILOSOFOS = 5;
        final int NUM_CUBIERTOS = 5;

        Filosofo[] filosofos = new Filosofo[NUM_FILOSOFOS];
        Cubierto[] cubiertos = new Cubierto[NUM_CUBIERTOS];
        Thread[] hilos = new Thread[NUM_FILOSOFOS];

        for (int i = 0; i < NUM_CUBIERTOS; i++) {
            cubiertos[i] = new Cubierto();
        }

        for (int i = 0; i < NUM_FILOSOFOS; i++) {
            if(i + 1 == 5){
                filosofos[i] = new Filosofo(cubiertos[0], cubiertos[i]);
            }else{
                filosofos[i] = new Filosofo(cubiertos[i + 1], cubiertos[i]);
            }
            hilos[i] = new Thread(filosofos[i]);
            hilos[i].start();
        }

    }
}
