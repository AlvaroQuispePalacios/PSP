package Tema2.ruleta;

public class Jugador implements Runnable {
    Ruleta ruleta;
    int saldo = 1000;
    int numeroAlAzar;
    int numeroRuleta;
    int estrategia;

    public Jugador(Ruleta ruleta) {
        this.ruleta = ruleta;
    }

    public void setNumeroRuleta(int numeroRuleta) {
        this.numeroRuleta = numeroRuleta;
    }

    public int getNumeroRuleta() {
        return numeroRuleta;
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

    public synchronized void apuesta() {
        try {
            wait();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void run() {
        String miNombre = Thread.currentThread().getName();
        while (saldo > 0) {
            elegirNumeroAlAzar();
            System.out.println(miNombre + " -> " + this.numeroAlAzar + ", saldo -> " + this.saldo);
            try {
                apuesta();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            if (this.numeroAlAzar == this.numeroRuleta) {
                ruleta.restarSaldoBanca(360);
                incrementarSaldoJugador(360);
            } else {
                restarSaldoJugador(10);
                ruleta.incrementarSaldoBanca(10);
            }
        }
    }

    public synchronized void liberarJugador() {
        notifyAll();
    }

}
