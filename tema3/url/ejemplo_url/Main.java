package tema3.url.ejemplo_url;

import java.net.URL;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String stringUrl = sc.nextLine();
        try {
            URL url = new URL(stringUrl);
            Visualizar(url);
        } catch (Exception e) {
            // TODO: handle exception
        }

    }

    public static void Visualizar(URL url){
        System.out.println("\tURL completa: " + url.toString());	
        System.out.println("\tgetProtocol(): " + url.getProtocol());
        System.out.println("\tgetHost(): " + url.getHost());
        System.out.println("\tgetPort(): " + url.getPort());
        System.out.println("\tgetFile(): " + url.getFile());
        System.out.println("\tgetUserInfo(): " + url.getUserInfo());
        System.out.println("\tgetPath(): " + url.getPath());
        System.out.println("\tgetAuthority(): " + url.getAuthority());
        System.out.println("\tgetQuery(): " + url.getQuery());
        System.out.println("\tgetDefaultPort(): "+ url.getDefaultPort());
        System.out.println("==================================================");

    }
}
