package Tema2.filosofos;

public class Palillo {
    int numPalillo;
    boolean disponible;

    public Palillo(int numPalillo){
        this.numPalillo = numPalillo;
        this.disponible =  true;
    }

    public boolean palilloDisponible(){
        return this.disponible;
    }

    public synchronized void tomarPalillo(){
        this.disponible = false;
    }

    public synchronized void soltarPalillo(){
        this.disponible = true;
    }
}
