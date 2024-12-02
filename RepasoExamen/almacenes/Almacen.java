package RepasoExamen.almacenes;

public class Almacen {
    int productos = 100;
    boolean estaPuertaLibre;

    public Almacen(){
        estaPuertaLibre = true;
    }

    public synchronized int getCantidadProducto(){
        return productos;
    }

    public synchronized void cogerProducto(){
        productos -= 1;
    }

    public synchronized boolean intentarPasarPuerta(){
        if(estaPuertaLibre == false){
            try {
                wait();
            } catch (Exception e) {}
        }
        estaPuertaLibre = false;
        return estaPuertaLibre;
    }

    public synchronized void liberarPuerta(){
        estaPuertaLibre = true;
        System.out.println("Liberar Puerta");
        System.out.println(productos);
        notifyAll();
    }

    
}
