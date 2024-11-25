package Tema2.ruleta;

public class Main {

    public static void main(String[] args) {
        Ruleta ruleta = new Ruleta();
        Jugador[] jugadores = new Jugador[4];
        Thread[] hilosAsociados = new Thread[4];

        for (int i = 0; i < 4; i++) {
            jugadores[i] = new Jugador(ruleta);
            hilosAsociados[i] = new Thread(jugadores[i]);
            hilosAsociados[i].start();
        }

        while (ruleta.getSaldoRuleta() > 0) {
            try {
                int resultadoRuleta = ruleta.girarRuleta();
                System.out.println(resultadoRuleta);
                for (Jugador jugador : jugadores) {
                    jugador.setNumeroRuleta(resultadoRuleta);
                }
                for (Jugador jugador : jugadores) {
                    jugador.liberarJugador();
                }
                Thread.sleep(3000);
            } catch (Exception e) {
                // TODO: handle exception
            }
        }

    }

}
