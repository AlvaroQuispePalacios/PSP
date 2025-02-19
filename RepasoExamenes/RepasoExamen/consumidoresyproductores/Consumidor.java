package RepasoExamenes.RepasoExamen.consumidoresyproductores;

public class Consumidor implements Runnable {
    Almacen almacen;

    public Consumidor(Almacen a){
        almacen = a;
    }

    public void run(){
        while (true) {
            almacen.agregarProducto();
        }
    }
}
