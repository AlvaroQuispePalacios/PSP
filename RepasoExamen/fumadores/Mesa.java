package RepasoExamen.fumadores;

import java.util.Random;

public class Mesa {
    boolean tabaco, papel, cerilla;
    String[] objetos = { "tabaco", "papel", "cerilla" };

    public Mesa() {
        this.tabaco = false;
        this.papel = false;
        this.cerilla = false;

    }

    public void ponerObjetosMesa() {
        Random random = new Random();
        int objeto1;
        int objeto2;
        do {
            objeto1 = random.nextInt(3) + 1;
            objeto2 = random.nextInt(3) + 1;
        } while (objeto1 == objeto2);

        if (objeto1 == 1 || objeto2 == 1) {
            this.tabaco = true;
        } else if (objeto1 == 2 || objeto2 == 2) {
            this.papel = true;
        } else if (objeto1 == 3 || objeto2 == 3) {
            this.cerilla = true;
        }

    }

    public synchronized void tomarObjetosDeLaMesa() {

    }

}
