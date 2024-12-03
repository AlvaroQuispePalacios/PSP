package examen_psp_alvaroquispe;

import java.util.Random;

public class PincharGlobos implements Runnable {
    String nombre;
    Globo[] globos;

    public PincharGlobos(Globo globos[], String nombre) {
        this.globos = globos;
        this.nombre = nombre;
    }

    public void run() {
        // Es a los tres globos actuales
        for (Globo globo : globos) {
            while(globo.getGloboTomado() == true && globo.getEstaPinchado() == false){
                try {
                    Thread.sleep((new Random().nextInt(10) + 1 ) * 1000);
                } catch (Exception e) {
                }
                if(globo.getGloboTomado() == true && globo.getEstaPinchado() == false){
                    globo.pincharGlobo();
                }
            }
        }
    }
}
