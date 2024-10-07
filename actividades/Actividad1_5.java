package actividades;
import java.io.*;
public class Actividad1_5 {
    public static void main(String[] args) throws IOException {
        File fError = new File("error.txt");
        ProcessBuilder pb = new ProcessBuilder("java", "Ejemplo2");
        pb.redirectError(fError);
        pb.start();
    }
}
