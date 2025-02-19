package RepasoExamenes.RepasoExamen.consumidoresyproductores;

public class Productor implements Runnable {
    Almacen almacen;

    public Productor(Almacen a){
        almacen = a;
    }

    public void run() {
        while (true) {
            almacen.consumirProducto();
        }
    }

}
