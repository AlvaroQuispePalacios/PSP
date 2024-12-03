package examen_psp_alvaroquispe;

public class Main {
    public static void main(String[] args) {
        Globo[] globos = new Globo[10];
        InflarGlobos[] igs = new InflarGlobos[3];
        Thread[] hilosIgs = new Thread[3];
        PincharGlobos pg = new PincharGlobos(globos, "PG1");
        Thread hilopg = new Thread(pg);
        
        for (int i = 0; i < globos.length; i++) {
            globos[i] = new Globo("Globo " + i);
        }
        
        for (int i = 0; i < igs.length; i++) {
            igs[i] = new InflarGlobos(globos, "IG" + i);
            hilosIgs[i] = new Thread(igs[i]);
            hilosIgs[i].start();
        }
        
        hilopg.start();


    }
}
