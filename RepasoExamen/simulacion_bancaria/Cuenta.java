package RepasoExamen.simulacion_bancaria;

public class Cuenta {
    int saldoInicial;
    int saldo;

    public Cuenta(int saldo){
        this.saldoInicial=saldo;
        this.saldo=saldo;
    }

    public boolean correcto(){
        if(saldo == saldoInicial){
            return true;
        }
        return false;
    }

    public int getSaldoActual(){
        return this.saldo;
    }

    public synchronized void cambiarSaldo (int saldo){
        this.saldo += saldo;
    }

}
