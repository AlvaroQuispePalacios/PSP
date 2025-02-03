import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Main extends JFrame {
    private JPanel MainPanel;
    private JButton btnDescargar;
    private JButton btnSalir;
    private JPanel pContenido;
    private JList listaDirectorios;

    public Main(){
        setTitle("Actividad 4.2");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 400);
        setVisible(true);
        setContentPane(MainPanel);
        setResizable(false);

        //
        DefaultListModel xd = new DefaultListModel();
        xd.addElement(new String("a"));
        xd.addElement(new String("b"));
        listaDirectorios.setModel(xd);
        listaDirectorios.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                System.out.println(e.getFirstIndex());
            }
        });

    }

    public static void main(String[] args) {
        new Main();

    }
}
