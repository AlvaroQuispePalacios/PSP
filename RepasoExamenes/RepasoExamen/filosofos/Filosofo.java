package RepasoExamenes.RepasoExamen.filosofos;

import java.util.Random;

public class Filosofo implements Runnable {
    Cubierto derecho;
    Cubierto izquierdo;

    public Filosofo(Cubierto d, Cubierto i){
        derecho = d;
        izquierdo = i;
    }

    public void run() {
        String miNombre = Thread.currentThread().getName();
        Random generador = new Random();
        while (true) {
            /* Comer */
            /* Intentar coger palillos */
            /* Si los coge: */
            derecho.intentarCogerCubierto();
            izquierdo.intentarCogerCubierto();

            System.out.println(miNombre + " comiendo...");
            int milisegs = (1 + generador.nextInt(5)) * 1000;
            esperarTiempoAzar(miNombre, milisegs);
            /* Pensando... */
            // Recordemos soltar los palillos
            derecho.soltarCubierto();
            izquierdo.soltarCubierto();
            System.out.println(miNombre + "  pensando...");
            milisegs = (1 + generador.nextInt(5)) * 1000;
            esperarTiempoAzar(miNombre, milisegs);
        }
    }

    private void esperarTiempoAzar(String miNombre, int milisegs) {
        try {
            Thread.sleep(milisegs);
        } catch (InterruptedException e) {
            System.out.println(
                    miNombre + " interrumpido!!. Saliendo...");
            return;
        }
    }
}
