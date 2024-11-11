package Tema2.filosofos;
// import Tema2.filosofos.Filosofos;
// import Tema2.filosofos.Palillo;
public class Main {
    // En una mesa hay procesos que simulan el comportamiento de unos
    // filósofos que intentan comer de un plato. Cada filósofo tiene un
    // cubierto a su izquierda y uno a su derecha y para poder comer tiene que
    // conseguir los dos. Si lo consigue, mostrará un mensaje en pantalla que
    // indique «Filosofo 2 comiendo».

    // Despues de comer, soltará los cubiertos y esperará al azar un tiempo
    // entre 1000 y 5000 milisegundos, indicando por pantalla «El filósofo 2
    // está pensando».

    // En general todos los objetos de la clase Filósofo está en un bucle infinito dedicándose a comer y a pensar.

    // Simular este problema en un programa Java que muestre el progreso de
    // todos sin caer en problemas de sincronización ni de inanición.
    public static void main(String[] args) throws InterruptedException{
        Filosofos[] filosofos = new Filosofos[5];
        Palillo[] palillos = new Palillo[5];

        for(int i = 0; i < 5; i++){
            if(i + 1 < 5){
                filosofos[i] = new Filosofos(new Palillo(i), new Palillo(i+1));
            }else{
                
            }
            // asoc pal filo
        }


    }
}
