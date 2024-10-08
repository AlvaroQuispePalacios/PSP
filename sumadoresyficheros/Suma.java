package sumadoresyficheros;

import java.io.File;
import java.io.FileWriter;

public class Suma {
    public static void main(String[] args) {
        
        String x = args[0];
        String y = args[1];
    

        if(isNumber(x) && isNumber(y)){
            int xInt = Integer.parseInt(x);
            int yInt = Integer.parseInt(y);
            int suma = xInt + yInt;
            System.out.println(xInt + " + " + yInt + " = " + suma);
        }else{
            System.out.print("Uno de los dos numeros no es valido");
        }
    }

    public static boolean isNumber(String numero){
        boolean esValido = true;
        for (int i = 0; i < numero.length(); i++) {
            char c = numero.charAt(i);
            if(!(c >= 48 && c <= 57)){
                esValido = false;
                break;
            }
        }
        return esValido;
    }

}
