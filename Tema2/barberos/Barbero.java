
public class Barbero implements Runnable{
    Silla[] sillas;


    public Barbero(Silla[] sillas){
        this.sillas = sillas;
    }

    public void run(){
        String nombre = Thread.currentThread().getName();
        while(true) {
            for (Silla silla : sillas) {
                if(!silla.getEstaLibre()){
                    System.out.println("Barbero" + nombre + "cortando");
                    silla.getCliente().liberarCliente();
                }
            }
        }
    }

}
