package RepasoExamen.simulacion_bancaria;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        final int ingresan100 = 40;
        final int ingresan50 = 20;
        final int ingresan20 = 60;
        Cuenta cuenta = new Cuenta(100);

        Thread[] hilosIngresan100 = new Thread[ingresan100];
        Thread[] hilosIngresan50 = new Thread[ingresan50];
        Thread[] hilosIngresan20 = new Thread[ingresan20];

        Thread[] hilosRetiran100 = new Thread[ingresan100];
        Thread[] hilosRetiran50 = new Thread[ingresan50];
        Thread[] hilosRetiran20 = new Thread[ingresan20];

        for (int i = 0; i < ingresan100; i++) {
            hilosIngresan100[i] = new Thread(new Cliente(cuenta, 100));
            hilosRetiran100[i] = new Thread(new Cliente(cuenta, -100));

            hilosIngresan100[i].start();
            hilosRetiran100[i].start();
        }

        for (int i = 0; i < ingresan50; i++) {
            hilosIngresan50[i] = new Thread(new Cliente(cuenta, 50));
            hilosRetiran50[i] = new Thread(new Cliente(cuenta, -50));

            hilosIngresan50[i].start();
            hilosRetiran50[i].start();
        }

        for (int i = 0; i < ingresan20; i++) {
            hilosIngresan20[i] = new Thread(new Cliente(cuenta, 20));
            hilosRetiran20[i] = new Thread(new Cliente(cuenta, -20));

            hilosIngresan20[i].start();
            hilosRetiran20[i].start();
        }

        for (int i=0; i<ingresan100;i++){
            hilosIngresan100[i].join();
            hilosRetiran100[i].join();
        }

        for (int i=0; i<ingresan50;i++){
            hilosIngresan50[i].join();
            hilosRetiran50[i].join();
        }

        for (int i=0; i<ingresan20;i++){
            hilosIngresan20[i].join();
            hilosRetiran20[i].join();
        }
        if (cuenta.correcto()){
            System.out.println("La simulación fue correcta");
        } else {
            System.out.println("La simulación falló ");
            System.out.println("La cuenta tiene:"+ cuenta.getSaldoActual());
            System.out.println("Revise sus synchronized");
        }

    }
}
