package examen_psp_alvaroquispe;

import java.util.Random;

public class InflarGlobos implements Runnable {
    // Globo globo;
    Globo[] globos;
    String nombreIg;

    public InflarGlobos(Globo globos[], String nombreIg){
        this.globos = globos;
        this.nombreIg = nombreIg;
    }

    public void run(){
        for (Globo globo : globos) {
            if(globo.getGloboTomado() == false){
                globo.tomarGlobo();
                System.out.println(nombreIg + " tomo el " + globo.getNumeroGlobo());
                while (globo.getEstaPinchado() == false) {
                    if(globo.getEstaPinchado()){
                        break;
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        
                    }

                    globo.inflarGlobo();
                }
            }
        }
    }


}
