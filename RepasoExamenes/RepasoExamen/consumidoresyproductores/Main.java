package RepasoExamenes.RepasoExamen.consumidoresyproductores;

public class Main {
    public static void main(String[] args) {
        Almacen almacen = new Almacen();
        Productor productor = new Productor(almacen);
        Consumidor consumidor = new Consumidor(almacen);
        Consumidor consumidor2 = new Consumidor(almacen);


        Thread h1 = new Thread(productor);
        Thread h2 = new Thread(consumidor);
        Thread h3 = new Thread(consumidor2);

        h1.start();
        h2.start();
        h3.start();
    }
}
