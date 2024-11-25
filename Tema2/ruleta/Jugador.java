package Tema2.ruleta;

public class Jugador implements Runnable {
    Ruleta ruleta;
    int saldo = 1000;
    int numeroAlAzar;
    int estrategia; // ?????

    public Jugador(Ruleta ruleta) {
        this.ruleta = ruleta;
    }

    public int getNumeroAlAzar() {
        return this.numeroAlAzar;
    }

    public void incrementarSaldoJugador(int saldo) {
        this.saldo += saldo;
    }

    public void restarSaldoJugador(int saldo) {
        this.saldo -= saldo;
    }

    public void elegirNumeroAlAzar() {
        this.numeroAlAzar = (int) Math.floor(Math.random() * 36 + 1);
    }

    public void run() {
        String miNombre = Thread.currentThread().getName();

        while (saldo > 0) {

            elegirNumeroAlAzar();
            restarSaldoJugador(10);
            System.out.println(miNombre + " -> " + this.numeroAlAzar);
            try {
                wait();
                // Thread.sleep(2000);
            } catch (Exception e) {
                
            }
            // if (!ruleta.isRuletaGirando()) {
            //     ruleta.girarRuleta();
            // } else {
            //     try {
            //         System.out.println("esperando numero ruleta");
            //         wait();
            //     } catch (Exception e) {
            //         System.out.println(e.getMessage());
            //     }
            // }

            // if (ruleta.getNumeroActualRuleta() == this.numeroAlAzar) {
            //     System.out.println("El numero del jugador es igual al de la ruleta :D");
            // }
            // System.out.println();
            // ruleta.pararRuleta();
            // notifyAll();
        }
    }

}
