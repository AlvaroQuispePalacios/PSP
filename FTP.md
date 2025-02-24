Crea la conexión con el servidor FTP
```java
	FTPClient cliente = new FTPClient();
```

1. Primero creamos el cliente `FTPCLient cliente = new FTPCLient()`.
2. Declaramos el servidor en este caso `localhost`.
3. Declaramos el usuario y la contraseña.
4. Ahora hacemos un `cliente.connect(server)`.
5. Nos loguemos `cliente.login("usuario", "contraseña")`.

| MÉTODOS                                                         | MISION                                                                                                                               |
| --------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------ |
| `void connect(String host)`                                     | Abre la conexión con el servidor                                                                                                     |
| `void disconnect()`                                             | Cierra la conexión con el servidor FTP                                                                                               |
| `boolean logout()`                                              | Sale del servidor FTP                                                                                                                |
| `void enterLocalActiveMode()`                                   |                                                                                                                                      |
| `void enterLocalPassiveMode()`                                  |                                                                                                                                      |
| `String printWorkingDirectory()`                                | Devuelve el nombre de la ruta del directorio de trabajo actual                                                                       |
| `FTPFile [] listFiles()`                                        | Obtiene todos los ficheros y directorios del servidor como un `FTPFile[]`                                                            |
| `FTPFile [] listFiles(ruta)`                                    | Obtiene una lista de ficheros el directorio indicado                                                                                 |
| `String [] listNames()`                                         | Obtiene una lista de ficheros del directorio actual como `String[]`                                                                  |
| `FTPFile[] listDirectories()`                                   | Obtiene una lista de los directorios del servidor                                                                                    |
| `boolean changeWorkingDirectory(ruta)`                          | Cambie el directorio de trabajo actual                                                                                               |
| `boolean changeToParentDirectory()`                             | Cambia el directorio padre del directorio de trabajo actual                                                                          |
| `boolean setFileType(int fileType)`                             | Establece el tipo de fichero a transferir BINARY_FILE_TYPE(imagen binaria), etc                                                      |
| `boolean storeFile(String nombreFichero, InputStream local)`    | Almacena un fichero en el servidor con el nombre indicado tomando como entrada el inputStream, si le fichero existe lo sobreescribe. |
| `boolean retriveFile(String nombreFichero, OutputStream local)` | Recupera un fichero del servidor y lo escribe en el OuputStream dado                                                                 |
| `boolean deleteFile(String ruta)`                               | Elimina un fichero en el servidor                                                                                                    |
| `boolean rename(String nombreActual, String nombreNuevo)`       | Cambia el nombre de un fichero en el servidor FTP                                                                                    |
| `boolean removeDirectory(String ruta)`                          | Elimina un directorio en el servidor FTP(si está vacío)                                                                              |
| `boolean makerDirectory (String ruta)`                          | Crea un nuevo subdirectorio en el servidor FTP en la ruta actual                                                                     |

## JFileChooser
Crear un JFileChooser.
```java
	JFileChooser jfc = new JFileChooser() 
```

| Métodos                                          | Misión                                                                                           |
| ------------------------------------------------ | ------------------------------------------------------------------------------------------------ |
| `jfc.setFileSelectionMode(JFileChooser.tipo)`    | Te permite colocar un filtro para seleccionar un tipo de fichero (DIRECTORIES_ONLY,  FILES_ONLY) |
| `jfc.setDialogTitle(String Titulo)`              | Cambia de nombre el titulo de la ventana emergente                                               |
| `jfc.setApproveButtonText(String texto);`        |                                                                                                  |
| `int jfc.showOpenDialog(jfc)`                    | Devuelve el resultado de la selección del usuario                                                |
| `int resultado = jfc.showOpenDialog(jfc)`        |                                                                                                  |
| `File jfc.getSelectedFile()`                     | Devuelve el fichero seleccionado por el usuario                                                  |
| `String getAbsolutePath()`                       | Devuelve la ruta absoluta del fichero seleccionado                                               |
| `String jfc.getSelectedFile().getAbsolutePath()` |                                                                                                  |
## JOptionPane

`JOptionPane.showMessageDialog(null, nombreArchivo + "subido correctamente", "Mensaje", JOptionPane.INFORMATION_MESSAGE);`