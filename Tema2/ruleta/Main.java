package Tema2.ruleta;

public class Main {

    // Problema: simulador de casino
    /*
        Se desea simular los posibles beneficios de diversas estrategias de
        juego en un casino. La ruleta francesa es un juego en el que hay una
        ruleta con 37 números (del 0 al 36). Cada 3000 milisegundos el croupier
        saca un número al azar y los diversos hilos apuestan para ver si ganan.
        Todos los hilos empiezan con 1.000 euros y la banca (que controla la
        ruleta) con 50.000. Cuando los jugadores pierden dinero, la banca
        incrementa su saldo.

            Se puede jugar a un número concreto. Habrá 4 hilos que eligen
            números al azar del 1 al 36 (no el 0) y restarán 10 euros de su saldo
            para apostar a ese ese número. Si sale su número su saldo se incrementa
            en 360 euros (36 veces lo apostado).

            Se puede jugar a par/impar. Habrá 4 hilos que eligen al azar si
            apuestan a que saldrá un número par o un número impar. Siempre restan 10
            euros para apostar y si ganan incrementan su saldo en 20 euros.

            Se puede jugar a la «martingala». Habrá 4 hilos que eligen
            números al azar. Elegirán un número y empezarán restando 10 euros de su
            saldo para apostar a ese número. Si ganan incrementan su saldo en 360
            euros. Si pierden jugarán el doble de su apuesta anterior (es decir, 20,
            luego 40, luego 80, y así sucesivamente)

            La banca acepta todas las apuestas pero nunca paga más dinero del que tiene.
            
            Si sale el 0, todo el mundo pierde y la banca se queda con todo el dinero. 
     */
    
    public static void main(String[] args) {
        Ruleta ruleta = new Ruleta();
        Jugador[] jugadores = new Jugador[4];
        Thread[] hilosAsociados = new Thread[4];

        for(int i = 0; i < 4; i++){
            jugadores[i] = new Jugador(ruleta);
            hilosAsociados[i] = new Thread(jugadores[i]);
            hilosAsociados[i].start();
        }

        
    }
    
}
