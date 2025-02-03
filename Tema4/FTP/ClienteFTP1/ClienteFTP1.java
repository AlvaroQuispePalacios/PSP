package Tema4.FTP.ClienteFTP1;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

public class ClienteFTP1 {
    public static void main(String[] args) {
        try {
            FTPClient cliente = new FTPClient();
            String servFTP = "ftp.rediris.es";
            System.out.println("Nos conectamos a: " + servFTP);
            cliente.connect(servFTP);

            // Respuesta del servidor FTP
            System.out.println(cliente.getReplyString());
            // Código de respuesta
            int respuesta = cliente.getReplyCode();

            System.out.println(respuesta = cliente.getReplyCode());

            if(!FTPReply.isPositiveCompletion(respuesta)){
                cliente.disconnect();
                System.out.println("Conexión rechazada: " + respuesta);
                System.exit(0);

            }
            
            cliente.disconnect();
            
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
    
}
