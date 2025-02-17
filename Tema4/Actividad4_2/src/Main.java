import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main extends JFrame {
    private JPanel MainPanel;
    private JButton btnDescargar;
    private JButton btnSalir;
    private JPanel pContenido;
    private JList JListArchivos;
    DefaultListModel listaFicheros = new DefaultListModel();

    FTPClient cliente = new FTPClient();
    String servidor = "localhost";
    String usuario = "usuario";
    String pwd = "usuario";


    public Main() {
        setTitle("Actividad 4.2");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 400);
        setVisible(true);
        setContentPane(MainPanel);
        setResizable(false);

        // Inicia sesion
        boolean login = iniciarSesion();
        if (login) {
            cargarListaFicheros();
        }

        // Descargar el fichero seleccionado
        btnDescargar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ficheroSeleccionado = JListArchivos.getSelectedValue().toString();
                descargarFichero(ficheroSeleccionado);
            }
        });

    }

    public boolean iniciarSesion() {
        boolean login = false;
        try {
            // Primero nos conectamos luego hacemos login
            System.out.println("Conectadose al sevidor");
            cliente.connect(servidor);
            login = cliente.login(usuario, pwd);
            cliente.setFileType(FTPClient.BINARY_FILE_TYPE);
            cliente.enterLocalPassiveMode();
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        return login;
    }

    // Cargar los nombres de los ficheros del directorio raiz a la aplicación
    public void cargarListaFicheros() {
        try {
            FTPFile[] listaArchivos = cliente.listFiles();
            for (FTPFile archivo : listaArchivos) {
                if (archivo.isFile()) {
                    listaFicheros.addElement(archivo.getName());
                }
            }
            JListArchivos.setModel(listaFicheros);
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }

    public void descargarFichero(String nombreArchivo){
        JFileChooser buscador = new JFileChooser();
        buscador.setCurrentDirectory(new File("C:\\Users\\ciclesgs\\Desktop"));
        buscador.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        buscador.setAcceptAllFileFilterUsed(false);
        int resultado = buscador.showOpenDialog(buscador);
        if(resultado == JFileChooser.APPROVE_OPTION){
            try{
                // Desde donde se descargara el archivo
                String directorio_descarga = "C:\\FileZilla Server\\FTP\\";
                cliente.changeWorkingDirectory(directorio_descarga);

                // Ruta de destino del archivo descargado desde el servidor
                File ruta_de_destino = buscador.getSelectedFile();

                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(ruta_de_destino));

                if(cliente.retrieveFile(nombreArchivo, out)){
                    System.out.println("Archivo descargado correctamente");
                }else{
                    System.out.println("No se ha podido descargar el archivo");

                }
                out.close();

            }catch (Exception ex){
                System.out.println("Algo fallo al descargar el archivo D:");
                System.out.println(ex.toString());
            }
        }
        // elegir el lugar donde descargar el archivo
    }

    public void cerrar() throws IOException {
        cliente.logout();
        cliente.disconnect();
    }

    public static void main(String[] args) {
        new Main();

    }
}
