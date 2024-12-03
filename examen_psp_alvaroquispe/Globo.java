package examen_psp_alvaroquispe;

public class Globo {
    String numeroGlobo;
    int volumen;
    boolean estaPinchado;
    boolean globoTomado;
    // boolean[] globos = new boolean[10];

    public Globo(String numeroGlobo) {
        this.numeroGlobo = numeroGlobo;
        this.volumen = 1;
        this.estaPinchado = false;
        this.globoTomado = false;
    }

    public String getNumeroGlobo(){
        return this.numeroGlobo;
    }

    public boolean getEstaPinchado(){
        return this.estaPinchado;
    }

    public synchronized boolean getGloboTomado(){
        return this.globoTomado;
    }

    public synchronized void tomarGlobo(){
        this.globoTomado = true;
    }

    public synchronized void inflarGlobo() {
        if(this.volumen > 5){
            System.out.println(this.numeroGlobo + " exploto");
            this.estaPinchado = true;
        }else{
            System.out.println("Inflando " + this.numeroGlobo);
            this.volumen++;
        }
    }

    public synchronized void pincharGlobo(){
        System.out.println(numeroGlobo + " fue pinchado");
        this.estaPinchado = true;
        
    }

}
