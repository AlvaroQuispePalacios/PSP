package RepasoExamenes.RepasoExamen.almacenes;

public class Main {
    public static void main(String[] args) {
        final int NUM_CLIENTES = 300;
        Almacen almacen = new Almacen();
        Cliente[] clientes = new Cliente[NUM_CLIENTES];
        Thread[] hilos = new Thread[NUM_CLIENTES];
        for(int i = 0; i < NUM_CLIENTES; i++) {
            clientes[i] = new Cliente(almacen);
            hilos[i] = new Thread(clientes[i]);
            hilos[i].start();
        }
    }
}
