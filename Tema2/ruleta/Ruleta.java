package Tema2.ruleta;

public class Ruleta implements Runnable {
    int saldo = 50000;
    int numeroActualRuleta;
    boolean ruletaGirando;

    public Ruleta() {
        this.ruletaGirando = false;
    }

    public void girarRuleta() {
        this.ruletaGirando = true;
        int numeroAleatorio = (int) Math.floor(Math.random() * 37);
        this.numeroActualRuleta = numeroAleatorio;
    }

    public void pararRuleta() {
        this.ruletaGirando = false;
    }

    public int getNumeroActualRuleta() {
        return numeroActualRuleta;
    }

    public int getSaldoRuleta() {
        return this.saldo;
    }

    public void incrementarSaldoBanca(int saldo) {
        this.saldo += saldo;
    }

    public void restarSaldoBanca(int saldo) {
        this.saldo -= saldo;
    }

    public void run() {
        while (this.saldo > 0) {
            try {
                Thread.sleep(3000);
            } catch (Exception e) {
                // TODO: handle exception
            }
            girarRuleta();
            notifyAll();
        }
    }

    // girarRuleta() cada 3seg
}
