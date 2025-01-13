package tema3.Actividad3_7;

import java.io.Serializable;

public class Numeros implements Serializable {
    int numero;
    long cuadrado;
    long cubo;

    public Numeros() {}
    public Numeros(int numero) {
        this.numero = numero;
    }


    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public long getCuadrado() {
        return cuadrado;
    }

    public void setCuadrado(long cuadrado) {
        this.cuadrado = cuadrado;
    }

    public long getCubo() {
        return cubo;
    }

    public void setCubo(long cubo) {
        this.cubo = cubo;
    }



    
}
