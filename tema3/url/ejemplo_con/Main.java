package tema3.url.ejemplo_con;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;

public class Main {
    public static void main(String[] args) {
        try {
            URL url = new URL("http://localhost/vernombre.php");
            URLConnection con = url.openConnection();
            con.setDoOutput(true);


            String cadena = "nombre=Alvaro&apellidos=Quispe Palacios";
            PrintWriter output = new PrintWriter(con.getOutputStream());
            output.write(cadena);
            output.close();


            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String linea;
            while((linea = reader.readLine()) != null){
                System.out.println(linea);
            }

            reader.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
