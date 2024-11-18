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
        while (saldo > 0 && ruleta.getSaldoRuleta() > 10) {
            elegirNumeroAlAzar();
            System.out.println(this.numeroAlAzar);
            try {
                wait();
            } catch (Exception e) {
                // TODO: handle exception
            }
            System.out.println(this.numeroAlAzar);
            if(ruleta.getNumeroActualRuleta() == this.numeroAlAzar){
                
            }
        }

    }
}
