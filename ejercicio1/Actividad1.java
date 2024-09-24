package ejercicio1;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

public class Actividad1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Ingresa el numero de procesos");
        int nProcesos = sc.nextInt();

        System.out.println("Ingrese el tiempo de los procesos");
        ArrayList<Integer> tProcesos = new ArrayList<Integer>();

        for (int i = 0; i < nProcesos; i++) {
            tProcesos.add(sc.nextInt());
        }

        int seleccionar = 0;
        do {
            System.out.println();
            System.out.println("1. FIFO");
            System.out.println("2. RR");
            System.out.println("3. Primero más pequeño");
            System.out.println("4. Primero más pronto en acabar");
            System.out.println("5. Salir");
            seleccionar = sc.nextInt();
            switch (seleccionar) {
                case 1:
                    Fifo(nProcesos);
                    break;
                case 2:
                    RRobin(nProcesos, tProcesos, sc);
                    break;
                case 3:
                    PrimeroMasPequenyo(nProcesos, tProcesos);
                    break;
                case 4:
                    System.out.println("Primero más pronto en acabar");
                    break;
                case 5:
                    System.out.println("Saliendo ...");
                    break;
                default:
                    System.out.println("Eliga una de las opciones");
                    break;
            }
            System.out.println();
        } while (seleccionar != 5);
    }

    public static void Fifo(int nProcesos) {
        System.out.print("FIFO: ");
        for (int i = 1; i <= nProcesos; i++) {
            System.out.print("P" + i + " ");
        }
    }

    public static void RRobin(int nProcesos, ArrayList<Integer> tProcesos, Scanner sc) {
        ArrayList<Integer> tProcesosRR = new ArrayList<>(tProcesos);
        int[] pAcabados = new int[nProcesos];
        System.out.println("Dinos el quantum");
        int quantum = sc.nextInt();
        boolean pPendientes = true;
        while (pPendientes) {
            for (int i = 0; i < nProcesos; i++) {
                if (tProcesosRR.get(i) > 0) {
                    tProcesosRR.set(i, tProcesos.get(i) - quantum);
                } else {
                    System.out.println("El proceso P" + i + " Ha acabado");
                }

            }

        }
    }

    public static void PrimeroMasPequenyo(int nProcesos, ArrayList<Integer> tProcesos) {
        ArrayList<Integer> nuevaLista = new ArrayList<Integer>(tProcesos);
        Collections.sort(nuevaLista);
        // Compara los valores de la nueva lista arreglado de menor a mayor con la lista
        // original y me devuelve el index de la posicion original para mostrarlo por
        // pantalla
        for (int i = 0; i < nProcesos; i++) {
            for (int j : tProcesos) {
                if (j == nuevaLista.get(i)) {
                    System.out.println("P" + (tProcesos.indexOf(j) + 1) + " - Valor " + j);
                }
            }
        }
    }

    public static void PrimeroMasProntoEnAcabar(int nProcesos, ArrayList<Integer> tProcesos, Scanner sc) {
        boolean salir = false;
        int seleccionar = 0;
        int nuevoProceso = 0;
        ArrayList<Integer> nuevaLista = new ArrayList<Integer>(tProcesos);

        do{
            System.out.println("Quieres agregar un nuevo proceso?");
            System.out.println("1. Si \n 2. No");
            // Agregar comprobacion de si el dato introducido es un numero
            if (seleccionar == 1) {
                System.out.println("Dime el tiempo del nuevo proceso");
                nuevoProceso = sc.nextInt();
                nuevaLista.add(nuevoProceso);
            }
        }while (seleccionar != 2);

        Collections.sort(nuevaLista);

    }
}