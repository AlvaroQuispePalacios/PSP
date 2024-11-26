// package Tema2.barberos;

public class Silla {
    boolean estaLibre;
    Cliente cliente;

    public Silla(){
        this.estaLibre = true;
    }

    public synchronized void ocuparSilla(Cliente cliente){
        this.estaLibre = false;
        this.cliente = cliente;
    }

    public synchronized void liberarSilla(){
        this.cliente = null;
        this.estaLibre = true;
    }

    public synchronized boolean getEstaLibre(){
        return this.estaLibre;
    }

    public synchronized Cliente getCliente(){
        return this.cliente;
    }
}
