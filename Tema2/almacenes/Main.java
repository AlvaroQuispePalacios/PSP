package Tema2.almacenes;

public class Main {

    public static class Cliente extends Thread {
        private int nCliente;
        private int intentos = 0;
        private Contenedor contenedor;

        public Cliente(int nCliente, Contenedor c) {
            this.nCliente = nCliente;
            this.contenedor = c;
        }

        public void run() {
            // System.out.println("Cliente: " + this.nCliente +"\t" + this.intentos);
            this.intentos = contenedor.intentarPasarPuerta(this.intentos);
            System.out.println(intentos);
        }
    }

    public static class Contenedor {
        private int productos = 100;
        private boolean alguienDentro = false;


        // Probar en crear dos metodos uno que siempre intente pasar y otro que vea si hay alguien dentro o sea el cliente que avise que esta dentro
        // Intentar pasar puerta
        public synchronized int intentarPasarPuerta(int intentos) {
        while (intentos < 0) {
            if(alguienDentro){
                try {
                    System.out.println("esperando");
                    intentos++;
                    wait();
                } catch (Exception e) {}
            }else{

            }

        }    
        return intentos;

            
            // if (intentos < 10) {
            //     while (alguienDentro == true) {
            //         try {
            //             // Si alguien esta dentro esperamos
            //             System.out.println("esperando");
            //             intentos++;
            //             wait();
            //         } catch (Exception e) {

            //         }
            //     }

            //     // alguienDentro = true;
            //     // if (productos > 0) {
            //     //     productos--;
            //     // } else {
            //     //     // El cliente se va
            //     // }
            //     // alguienDentro = false;
            //     // notifyAll();

            //     // // Si no hay nadie dentro entramos
            // }

            
            // alguienDentro = true;
            // if (productos > 0) {
            //     productos--;
            // } else {
            //     // El cliente se va
            // }
            // alguienDentro = false;
            // notifyAll();

            // // Si no hay nadie dentro entramos
            // System.out.println(intentos);
            // return intentos;

            // System.out.println(productos);
        }

        




    }

    public static void main(String[] args) {
        Contenedor c = new Contenedor();
        for (int i = 1; i <= 300; i++) {
            new Cliente(i, c).start();
        }
    }
}
