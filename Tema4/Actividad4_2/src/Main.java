import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import javax.swing.*;
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
    private JTextField tfUsuario;
    private JLabel lbUsuario;
    private JLabel lbPassword;
    private JPasswordField tfPassword;
    private JButton btnIniciarSesion;

    DefaultListModel listaFicheros = new DefaultListModel();

    FTPClient cliente = new FTPClient();
    String servidor = "localhost";
    boolean isLogin = false;


    public Main() {
        setTitle("Actividad 4.2");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 400);
        setVisible(true);
        setContentPane(MainPanel);
        setResizable(false);

        // Iniciar Sesion
        btnIniciarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciarSesion(tfUsuario.getText().toString(), tfPassword.getText().toString());
            }
        });

        // Descargar el fichero seleccionado
        btnDescargar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ficheroSeleccionado = JListArchivos.getSelectedValue().toString();
                descargarFichero(ficheroSeleccionado);
            }
        });

        btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cerrar();
            }
        });

    }

    public void iniciarSesion(String usuario, String password) {
        try {
            // Primero nos conectamos luego hacemos login
            System.out.println("Conectadose al sevidor");
            cliente.connect(servidor);
            cliente.setFileType(FTPClient.BINARY_FILE_TYPE);
            cliente.enterLocalPassiveMode();
            isLogin = cliente.login(usuario, password);
            if (isLogin) {
                cargarListaFicheros();
                tfUsuario.setEnabled(false);
                tfPassword.setEnabled(false);
                btnIniciarSesion.setEnabled(false);
            } else {

            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }

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

    public void descargarFichero(String nombreArchivo) {
        JFileChooser buscador = new JFileChooser();
        buscador.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        //buscador.setAcceptAllFileFilterUsed(false);

        int resultado = buscador.showOpenDialog(buscador);
        if (resultado == JFileChooser.APPROVE_OPTION) {
            try {
                // Desde donde se descargara el archivo
                String directorio_descarga = "C:\\FileZilla Server\\FTP\\";
                cliente.changeWorkingDirectory(directorio_descarga);

                // Ruta de destino del archivo descargado desde el servidor si la ruta de destino es un fichero este
                // se sobreescribe si es una carpeta crea un fichero con el mismo nombre del fichero del servidor
                File ruta_de_destino = buscador.getSelectedFile();

                if (ruta_de_destino.isDirectory()) {
                    File nuevaRutaDestino = new File(ruta_de_destino + "\\" + nombreArchivo);
                    // Si el fichero no existe en la carpeta seleccionada lo crea
                    if (!nuevaRutaDestino.exists()) {
                        if (nuevaRutaDestino.createNewFile()) {
                            ruta_de_destino = nuevaRutaDestino;
                        }
                    } else {
                        // Si el archivo existe preguntar al usuario si quiere sobreescribir el archivo

                    }
                }

                System.out.println(ruta_de_destino);

                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(ruta_de_destino));
                if (cliente.retrieveFile(nombreArchivo, out)) {
                    System.out.println("Archivo descargado correctamente");
                } else {
                    System.out.println("No se ha podido descargar el archivo");

                }
                out.close();

            } catch (Exception ex) {
                System.out.println("Algo fallo al descargar el archivo D:");
                System.out.println(ex.toString());
            }
        }
        // elegir el lugar donde descargar el archivo
    }

    public void cerrar() {
        try {
            if(isLogin){
                cliente.logout();
                cliente.disconnect();
            }
            System.exit(0);
        } catch (Exception e) {
            System.out.println("Error al cerrar la sesión");
        }
    }

    public static void main(String[] args) {
        new Main();

    }
}
