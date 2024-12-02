package RepasoExamen.simulacion_bancaria;

public class Cliente implements Runnable {
    int saldo;
    Cuenta cuenta;

    public Cliente(Cuenta cuenta, int saldo){
        this.cuenta = cuenta;
        this.saldo = saldo;
    }

    public void run(){
        cuenta.cambiarSaldo(saldo);
        System.out.println("Saldo de la cuenta " + cuenta.getSaldoActual());
        try {
            Thread.sleep(1000);
        } catch (Exception e) {}
    }
}
