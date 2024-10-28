package Tema2.ejemplo_sincronizacion;

public class Ejemplo {

    public static class Productor extends Thread {
        private Contenedor contenedor;

        public Productor(Contenedor c) {
            contenedor = c;
        }

        public void run() {
            for (int i = 0; i < 10; i++) {
                contenedor.put(i);
                System.out.println("Productor. put: " + i);
                try {
                    sleep((int) (Math.random() * 100));
                } catch (InterruptedException e) {
                }
            }
        }
    }

    public static class Consumidor extends Thread {
        private Contenedor contenedor;

        public Consumidor(Contenedor c) {
            contenedor = c;
        }

        public void run() {
            int value = 0;
            for (int i = 0; i < 10; i++) {
                value = contenedor.get();
                System.out.println("Consumidor. get: " + value);
            }
        }
    }

    public static class Contenedor {
        private int dato;
        private boolean hayDato = false;
        

        public synchronized int get() {
            while (hayDato == false) {
                try {
                    // espera a que el productor coloque un valor
                    wait();
                } catch (InterruptedException e) {
                }
            }
            hayDato = false;
            // notificar que el valor ha sido consumido
            notifyAll();
            return dato;
        }

        public synchronized void put(int valor) {
            while (hayDato == true) {
                try {
                    // espera a que se consuma el dato
                    wait();
                } catch (InterruptedException e) {
                }
            }
            dato = valor;
            hayDato = true;
            // notificar que ya hay dato.
            notifyAll();
        }
    }


    public static void main(String[] args) {
        Contenedor c = new Contenedor();
        Productor produce = new Productor(c);
        Consumidor consume = new Consumidor(c);
        produce.start();
        consume.start();
    }
}
