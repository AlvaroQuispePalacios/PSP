package Tema2.filosofos;

import java.util.Random;

public class Filosofos implements Runnable {
    Palillo derecho;
    Palillo izquierdo;

    public Filosofos(Palillo palilloIzquierdo, Palillo palilloDerecho) {
        this.izquierdo = palilloIzquierdo;
        this.derecho = palilloDerecho;
    }

    public void run() {
        String miNombre = Thread.currentThread().getName();
        Random generador = new Random();
        while (true) {
            /* Comer */
            /* Intentar coger palillos */
            int milisegs = (1 + generador.nextInt(5)) * 1000;
            if (derecho.palilloDisponible() && izquierdo.palilloDisponible()) {
                derecho.tomarPalillo();
                izquierdo.tomarPalillo();
                
                System.out.println(miNombre + " comiendo..." + " palillo "+ derecho.numPalillo + ", palillo " + izquierdo.numPalillo);
                esperarTiempoAzar(miNombre, milisegs);

                derecho.soltarPalillo();
                izquierdo.soltarPalillo();

                // Recordemos soltar los palillos
                // System.out.println(miNombre + " pensando...");
                // milisegs = (1 + generador.nextInt(5)) * 1000;
                // esperarTiempoAzar(miNombre, milisegs);
            }
            System.out.println(miNombre + " pensando...");
            milisegs = (1 + generador.nextInt(5)) * 1000;
            esperarTiempoAzar(miNombre, milisegs);
            // /* Si los coge: */
            // System.out.println(miNombre + " comiendo...");
            // int milisegs = (1 + generador.nextInt(5)) * 1000;
            // esperarTiempoAzar(miNombre, milisegs);
            // /* Pensando... */

            // // Recordemos soltar los palillos
            // System.out.println(miNombre + " pensando...");
            // milisegs = (1 + generador.nextInt(5)) * 1000;
            // esperarTiempoAzar(miNombre, milisegs);
        }
    }

    private void esperarTiempoAzar(String miNombre, int milisegs) {
        try {
            Thread.sleep(milisegs);
        } catch (InterruptedException e) {
            System.out.println(miNombre + " interrumpido!!. Saliendo...");
            return;
        }
    }
}