package Tema2.ruleta;

public class Ruleta  {
    int saldo = 50000;
    int numeroActualRuleta;
    boolean ruletaGirando;

    public Ruleta() {
        this.ruletaGirando = false;
    }

    public int girarRuleta() {
        // this.ruletaGirando = true;
        int numeroAleatorio = (int) Math.floor(Math.random() * 37);
        return numeroAleatorio;
    }

    public void pararRuleta() {
        this.ruletaGirando = false;
    }

    public boolean isRuletaGirando(){
        return this.ruletaGirando;
    }

    public int getNumeroActualRuleta() {
        return numeroActualRuleta;
    }

    public int getSaldoRuleta() {
        return this.saldo;
    }

    public synchronized void incrementarSaldoBanca(int saldo) {
        this.saldo += saldo;
    }

    public synchronized void restarSaldoBanca(int saldo) {
        this.saldo -= saldo;
    }

}
