package RepasoExamen.almacenes;

public class Cliente implements Runnable {
    Almacen almacen;
    int maxIntentos = 10;
    int intentos = 0;
    boolean logroPasarPuerta = false;

    public Cliente(Almacen a) {
        almacen = a;
    }

    public void run() {
        String nombre = Thread.currentThread().getName();
        for (int i = 0; i < maxIntentos; i++) {
            logroPasarPuerta = !almacen.intentarPasarPuerta();
            if (logroPasarPuerta) {
                System.out.println(nombre + " ha pasado la puerta");
                if (almacen.getCantidadProducto() > 0) {
                    System.out.println(nombre + "Agarro un producto");
                    almacen.cogerProducto();
                }
                almacen.liberarPuerta();
            }
        }

    }
}
