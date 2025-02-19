package RepasoExamenes.RepasoExamen.consumidoresyproductores;

import java.util.Random;

public class Almacen {
    boolean hayProducto;

    public Almacen() {
        hayProducto = false;
    }

    public synchronized void agregarProducto() {
        if (hayProducto) {
            try {
                wait();
            } catch (Exception e) {}
        }
        try {
            Thread.sleep((new Random().nextInt(4) + 1) * 1000);
        } catch (Exception e) {}
        System.out.println("Agregando producto");
        hayProducto = true;
        notifyAll();
    }

    public synchronized void consumirProducto(){
        if(!hayProducto){
            try {
                System.out.println("Esperando por un producto");
                wait();
            } catch (Exception e) {}
        }
        try {
            Thread.sleep((new Random().nextInt(4) + 1) * 1000);
        } catch (Exception e) {}
        System.out.println("Consumir producto");
        hayProducto = false;
        notifyAll();
    }

}
