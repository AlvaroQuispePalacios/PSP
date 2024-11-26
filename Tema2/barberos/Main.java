// package Tema2.barberos;

public class Main {
    public static void main(String[] args) {
        int numSillas = 4;
        int numBarberos = 3;

        
        Silla[] sillas = new Silla[numSillas];

        for(int i = 0; i < numSillas; i++){
            sillas[i] = new Silla();
        }

        while (true) {
            Cliente cliente = new Cliente(sillas, numSillas);
            Thread thread = new Thread(cliente);
            thread.start();
        }

        // while (true) {
        //     for(int i = 0 ; i < 10; i++){
        //         if(i == 0){
        //             System.out.println(i);
        //         }else{
        //             return;
        //         }
        //         System.out.println(i);
        //     }
        // }
    }   
}
