package socketServidor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import dataClases.Book;
import dataClases.Client;
import dataClases.Room;
import databaseAdmin.DatabaseGenerator;
import databaseAdmin.DatabaseQuery;

public class SocketServidor {
	
	private int puerto;
	private ServerSocket serverSocket;
	private static DatabaseQuery dq;
	
	public SocketServidor(int puerto) {
		this.puerto = puerto;
		inicializarBaseDeDatos();
	}
	
	private void inicializarBaseDeDatos() {
        DatabaseGenerator dbGenerator = new DatabaseGenerator();
        dbGenerator.crearTabla();
        dq = new DatabaseQuery(dbGenerator);
    }

    public void iniciar() {
        try {            
            serverSocket = new ServerSocket(5000);
            System.out.println("Servidor iniciado en el puerto " + puerto);
            Socket socket = serverSocket.accept();

            System.out.println("Cliente conectado: " + socket.getInetAddress().getHostAddress());

            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter salida = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            while (true) {
            	salida.write("\nMenú Principal:\n" + 
				            "1. Clientes\n" + 
				    		"2. Habitaciones\n" +
				            "3. Reservas\n" + 
				    		"4. Salir" +
				    		"Seleccione una opción: "
	            );
	            salida.newLine();
	            salida.flush();
            	
                String mensaje = entrada.readLine();
                System.out.println(mensaje);
                if (mensaje == null || mensaje.equalsIgnoreCase("4")) {
                    System.out.println("El cliente ha terminado la conexion");
                    break;
                } else if (mensaje.equals("1")) {
                	List<Client> clientes = dq.getClients();
                	
                	for (Object obj : clientes) {
                		salida.write(obj.toString());
                		salida.newLine();
                	}
                	salida.flush();
                } else if (mensaje.equals("2")) {
                	List<Room> clientes = dq.getRooms();
                	
                	for (Object obj : clientes) {
                		salida.write(obj.toString());
                		salida.newLine();
                	}
                	salida.flush();
                } else if (mensaje.equals("3")) {
                	List<Book> clientes = dq.getBooks();
                	
                	for (Object obj : clientes) {
                		salida.write(obj.toString());
                		salida.newLine();
                	}
                	salida.flush();
                }
                
            }

            cerrarConexion(socket, entrada, salida);

        } catch (IOException e) {
            System.err.println("Error en el servidor: " + e.getMessage());
        }
    }

    private void cerrarConexion(Socket socket, BufferedReader entrada, BufferedWriter salida) throws IOException {
        entrada.close();
        salida.close();
        socket.close();
        serverSocket.close();
        System.out.println("Conexion cerrada");
    }
    
    public static void main(String[] args) {    	
    	SocketServidor socketServidor = new SocketServidor(5000);
    	socketServidor.iniciar();
	}
	
}
