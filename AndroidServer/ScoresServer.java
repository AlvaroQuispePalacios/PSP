package AndroidServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ScoresServer {
    public static void main(String args[]) {
        ArrayList<String> scores = new ArrayList<String>();
        try {
            ServerSocket s = new ServerSocket(1234);
            System.out.println("Esperant connexions...");
            while (true) {
                Socket client = s.accept();
                BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                PrintWriter out = new PrintWriter(new OutputStreamWriter(client.getOutputStream()), true);
                String data = in.readLine();
                System.out.println(data);
                if (data.equals("SCORES")) {
                    for (int n = 0; n < scores.size(); n++) {
                        out.println(scores.get(n));
                    }
                } else {
                    scores.add(0, data);
                    out.println("OK");
                }
                client.close();
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}