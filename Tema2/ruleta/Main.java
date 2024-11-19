package Tema2.ruleta;

public class Main {



    public static void main(String[] args) {
        Ruleta ruleta = new Ruleta();
        // Thread hiloRuleta = new Thread(ruleta);
        Jugador[] jugadores = new Jugador[4];
        Thread[] hilosAsociados = new Thread[4];

        for(int i = 0; i < 4; i++){
            jugadores[i] = new Jugador(ruleta);
            hilosAsociados[i] = new Thread(jugadores[i]);
            hilosAsociados[i].start();
        }
        // hiloRuleta.start();
        
        
        // ??? Casino?

    }
    
}
