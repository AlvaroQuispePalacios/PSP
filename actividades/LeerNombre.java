package actividades;

import java.io.IOException;

public class LeerNombre {
    public static void main(String[] args) throws IOException {
        // Actividad 1.4

        String nombre = args[0];
        boolean esValido = true;

        // Iterar sobre el nombre
        for(int i = 0; i < nombre.length(); i++){
            char c = nombre.charAt(i);
            // Verificar que el nombre solo tenga letras o un espacio
            if(!((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z') || c == ' ')){
                esValido = false;
                break;
            }
        }

        if(esValido){
            System.exit(1);
        }else{
            System.exit(-1); 
        }
    }
}
