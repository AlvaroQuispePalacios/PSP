

public class Cliente implements Runnable {
    Silla[] sillas;
    boolean[] sillasComprobada;

    public Cliente(Silla[] sillas, int numSillas){
        this.sillas = sillas;
        this.sillasComprobada = new boolean[numSillas];
    }
    
    public void esperarCorte(String nombre){
        try {
            System.out.println("Cliente " + nombre + " esperando barbero");
            this.wait();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void liberarCliente(){
        this.notify();
    }

    @Override
    public void run() {
        String nombre = Thread.currentThread().getName();
        while (true) {
            for (Silla silla : sillas) {
                if(silla.getEstaLibre()){
                    silla.ocuparSilla(this);
                    esperarCorte(nombre);
                }
            }
            return;
        }
    }



}
