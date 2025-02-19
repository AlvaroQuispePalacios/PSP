
#TCP #UDP #URL #Java
# TCP 
Recuerda que las conexiones TCP consisten en que mediante unos credenciales se accede a una conexión a un servidor lo que por tanto nos permite tener una conexión estable entre servidor y cliente.
## Cliente 

**Como conectarse**
````JAVA
//Especificamos el servidor que en este caso es localhost
String Host = "localhost";

//El puerto que queramos usar
int puerto = 6000;

//Especificandole al socket de cliente el host y el puerto este se conectara
Socket cliente = new Socket(Host,puerto);
````

**Métodos de la Clase Socket**
````java
.getPort() //-> devuelve el puerto que esta utilizando el socket
.getInetAddress() //-> Devuelve la direccion IP
.getLocalPort() //-> Devuelve el puerto local al que se ha conectado el cliente
.close()//-> Cierra la conexion (IMP pornerlo al final)
````

**Como pasar información hacia el Servidor**
````java
// (Le envia algo al servidor)
DataOutputStream flujoSalida = new DataOutputStream(Cliente.getOutputStream());
flujoSalida.writeUTF(res); //-> res puede ser cualquier cosa en este caso es un string que he creado previamente
````
**Con objetos**
````java
// FLUJO DE salida para objetos
ObjectOutputStream perSal = new ObjectOutputStream(cliente.getOutputStream());
perSal.writeObject(dato);//->se envia el dato en un objeto que hemos creado previamente
````


**Recibir datos del Servidor**
````java
// (Lee lo que le recibe del servidor)
DataInputStream flujoEntrada = new DataInputStream(Cliente.getInputStream());
String entrada = flujoEntrada.readUTF();//-> lee los datos que le ha enviado el servidor y los guarda en un string para hacer cosas con el
````
**Con objetos**
````java
ObjectInputStream perEnt = new ObjectInputStream(cliente.getInputStream());
Persona dato = (Persona) perEnt.readObject();// recibo objeto y lo guardo en una variable (importante hacerle un parse para que coincida)
````


---
## Servidor

**Encender el servidor**
````java
int puerto = 6000;//declara el puerto
ServerSocket Servidor = new ServerSocket(puerto);//Crea el servidor y se pone a esperar clientes
System.out.println("Escuchando en " + Servidor.getLocalPort());
````

**Aceptar un cliente en el server**
Una vez con el servidor lanzado este se quedara en espera de que llegue un cliente por lo que tendremos que especificarle que acepte los clientes que le lleguen.
````java
Socket cliente1 = Servidor.accept(); //Declara en cliente y le acepta la conexion
````

**Métodos de la clase ServerSocket**
````java
.close()//-> para cerrar la conexion
.accept()//-> para aceptar la conexion de un cliente al servidor
````

**Como enviar información hacia el cliente**
````java
//Lo que le envia al cliente
OutputStream salida = cliente1.getOutputStream();
DataOutputStream flujosalida = new DataOutputStream(salida);
flujosalida.writeUTF("HOLA CARA DE COLA");//-> especificamos lo que queremos enviarle al cliente
````
**Con objetos**
````java
// Se prepara un flujo de salida para objetos
ObjectOutputStream outObjeto = new ObjectOutputStream(cliente.getOutputStream());
// Se prepara un objeto y se envía
Persona per = new Persona("Juan", 20);
outObjeto.writeObject(per); // enviando objeto
````


**Recibir datos del servidor**
````java
//Lee lo que envia el cliente
InputStream entrada = cliente1.getInputStream();
DataInputStream flujoEntrada = new DataInputStream(entrada);
System.out.println(flujoEntrada.readUTF());//-> muestra por pantalla lo que ha enviado el cliente
````
**Con objetos**
````java
// Se obtiene un stream para leer objetos
ObjectInputStream inObjeto = new ObjectInputStream(cliente.getInputStream());
Persona dato = (Persona) inObjeto.readObject();//-> Revibe el objeto
System.out.println("Recibo: " + dato.getNombre() + "*" + dato.getEdad());//-> un ejemplo de lo que se puede hacer con el objeto persona
````


---
---
# UDP
Recuerda que UDP no esta orientado a conexion por tanto el servidor y el cliente hacen tanto de servidor como de cliente. EX cuando un cliente quiere enviar algo se vuelve servidor y lo recibe el servidor como cliente y viceversa.
## Cliente
**Métodos**
````java
.close()//-> para cerrar la conexion
.send()//-> para envir el paquete que este dentro de los parentesis
.receive()//-> recibe el paquete del servidor dentro del paquete que este dentro del parentesis
````

**Conexión al servidor**
En UDP uno se conecta al servidor cuando quiere hacer el envió de los paquetes
````java
DatagramSocket cliente = new DatagramSocket();//-> declaramos un datagram socket que hara la funcion de cliente para enviar los ficheros
InetAddress IPServidor = InetAddress.getLocalHost();//-> cogera la ip local y la establecera como ip del servidor
int puerto = 12345;
````


**Como enviar información al servidor**
````java
//Primero preparamos una cadena para enviarla en formato de bytes
String cadena = "hola cara de cola";//-> declaramos la cadena
byte[] enviados = new byte[1024];//-> declaramos el array de bytes
enviados = cadena.getBytes();//-> transformamos la cadena a bytes

//Una vez echo esto si que podremos hacer la conexion y enviar el paquete
DatagramPacket envio = new DatagramPacket(enviados, enviados.length, IPServidor, puerto);//-> se declara el paquete dandole como parametros: el array, su nonjitud,la ip del servidor a conectar y el puerto.
cliente.send(envio);//-> mediante nuestro cliente se envia la peticion
````
**Con Objetos**
````java
// De objeto a bytes
ByteArrayOutputStream bs = new ByteArrayOutputStream();
ObjectOutputStream out = new ObjectOutputStream(bs);
out.writeObject(numero);//-> el objeto se envia al outputStream
out.close();
byte[] bytes = bs.toByteArray();


// Preparar el enviador
DatagramPacket envio = new DatagramPacket(bytes, bytes.length, InetAddress.getLocalHost(), 54321);//-> se prepara el paquete
clienteSocket.send(envio);//-> se envia el paquete
````


**Como recibir información del servidor**
````java
byte[] bufer = new byte[1024];//-> como al enviar, tenemos que declarar un array de bytes para almacenar la respuesta
DatagramPacket recibo = new DatagramPacket(bufer, bufer.length);//-> declaramos la variable de recibo con el array de bytes y su longitud
cliente.receive(recibo);//-> lo que recibe (dentro del parentesis donde se almacenara)
String resultado = new String(recibo.getData());//-> le hacemos un "parse" a string
System.out.println(resultado);//-> mostramos el resultado
````
**Con Objetos**
````java
//Recibe el objeto como si fuera un paquete normal

//De bytes a objeto
ByteArrayInputStream bais = new ByteArrayInputStream(buferNumber);//-> el buferNumber es el mismo array de bytes que he utilizado para recibir el paquete
ObjectInputStream in = new ObjectInputStream(bais);
Numeros num2 = (Numeros) in.readObject();//-> leemos el fichero y lo convertimos al objeto Numero
in.close();
````

---
## Servidor
**Métodos**
````java
.receive()//-> recibe lo que le envia el cliente y lo almacena en el paquete que esta entre parentesis
.setSoTimeout()//-> una vez recibido el paquete espera la cantidad de tiempo especificada entre los parentesis
.send()//-> envia el paquete especificado entre los parentesis
.close()//-> cierra la conexion
````

**Encender el Servidor**
````java
DatagramSocket socket = new DatagramSocket(12345);//-> creamos el socket y le especificamos un puerto que estara escuchando peticiones de clientes
````

**Enviar información al cliente**
````java
InetAddress IPOrigen = recibo.getAddress();//-> necesitamos saber de donde viene para poder especificar ha donde va para "devolverle" el mensaje al cliente
int puerto = recibo.getPort();//-> lo mismo pero con el puerto

//Preparamos una respuesta con una array de bytes
String cadena = solucion;
byte[] enviados = new byte[1024];
enviados = cadena.getBytes();//-> transforma la cadena a bytes

DatagramPacket envio = new DatagramPacket(enviados, enviados.length, IPOrigen, puerto);//-> Como con el cliente se prepara con el array de bytes, su longitud, la ip del cliente, el puerto que esta utilizando
socket.send(envio);//-> se envia el paquete
````
**Con Objetos**
````java
//Como en el cliente primero transformamos el objeto a bytes 
ByteArrayOutputStream bs = new ByteArrayOutputStream();
ObjectOutputStream out = new ObjectOutputStream(bs);
out.writeObject(num);
out.close();
//Una vez transformado lo enviamos
````

**Recibir información del cliente**
````java
bufer = new byte[1024];//-> preparamos el array de bytes
DatagramPacket recibo = new DatagramPacket(bufer, bufer.length);//-> preparamos el paquete con el array de bytes
socket.receive(recibo);//-> recibimos el paquete del cliente
````
**Con Objetos**
````java
//Recibimos el array de bytes del cliente y lo transformamos en un objeto
ByteArrayInputStream bais = new ByteArrayInputStream(buferNumber);
ObjectInputStream in = new ObjectInputStream(bais);
num = (Numeros) in.readObject();
in.close();
````

---
---
# URL
Con la URL podemos hacer conexiones a una web lanzada en internet como podría ser `www.google.es`.

**Métodos**
````java
.getHostName()//-> la URL
.getHostAddress()//-> la ip del sitio web
.getLocalHost()//-> mi ip o la ip del router
.getByName()//-> la URL y la ip del sitio
.getCanonicalHostName()
````

**Como conectarse a una URL**
````java
InetAddress dir = InetAddress.getByName("www.google.es");//-> aqui declaramos el sitio al que nos queremos conectar (podriamos poner tambien localhost)

System.out.println("\tDIRECCIONES IP PARA: " + dir.getHostName());//-> ya una muestra de lo que podemos hacer
````
