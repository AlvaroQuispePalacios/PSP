import javax.swing.*;

public class Main extends JFrame {
    private JPanel MainPanel;

    public Main(){
        setTitle("Actividad 4.2");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 400);
        setVisible(true);
        setContentPane(MainPanel);
        setResizable(false);
    }

    public static void main(String[] args) {
        new Main();
    }
}
