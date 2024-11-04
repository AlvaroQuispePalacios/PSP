package Tema2.almacenes;

public class Main {

    public static class Cliente extends Thread {
        private int intentos = 0;
        private Contenedor contenedor;

        public void run() {
            this.intentos = contenedor.obtenerProducto(intentos);
        }
    }

    public static class Contenedor {
        private int productos = 100;
        private boolean alguienDentro = false;

        public synchronized int obtenerProducto(int intentos) {
            if (intentos != 10) {
                while (alguienDentro) {
                    try {
                        // Si alguien esta dentro esperamos
                        wait();
                    } catch (Exception e) {
                        // TODO: handle exception
                    }
                }
                // Si no hay nadie dentro entramos
                alguienDentro = true;
                if (productos > 0) {
                    productos--;
                } else {
                    intentos++;
                }
                alguienDentro = false;
                notifyAll();
            }else{
                // Ya no entra
            }
            return intentos;
        }

    }

    public static void main(String[] args) {
        Cliente cliente = new Cliente();
        Contenedor contenedor = new Contenedor(cliente.intentos);
    }
}
