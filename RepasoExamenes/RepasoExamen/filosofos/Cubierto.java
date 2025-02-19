package RepasoExamenes.RepasoExamen.filosofos;

public class Cubierto {
    boolean estaCubiertoLibre;

    public Cubierto() {
        this.estaCubiertoLibre = true;
    }

    public synchronized void intentarCogerCubierto() {
        while (!estaCubiertoLibre) {
            try {
                System.out.println("Esperando por cubierto");
                wait();
            } catch (Exception e) {
            }
        }
        estaCubiertoLibre = false;

    }

    public synchronized void soltarCubierto() {
        estaCubiertoLibre = true;
        notifyAll();
    }
}
