package socketServidor;

import java.io.*;
import java.net.*;
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
            serverSocket = new ServerSocket(puerto);
            System.out.println("Servidor iniciado en el puerto " + puerto);

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Cliente conectado: " + socket.getInetAddress().getHostAddress());

                try (BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                     BufferedWriter salida = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {

                    while (true) {
                        salida.write("\nMenú Principal:\n" +
                                "1. Clientes\n" +
                                "2. Habitaciones\n" +
                                "3. Reservas\n" +
                                "4. Salir\n" +
                                "Seleccione una opción: ");
                        salida.newLine();
                        salida.flush();
                        
                        salida.write("END");
                        salida.newLine();
                        salida.flush();

                        String mensaje = entrada.readLine();
                        System.out.println("Opción recibida: " + mensaje);

                        if (mensaje == null || mensaje.equalsIgnoreCase("4")) {
                            System.out.println("El cliente ha terminado la conexión.");
                            salida.write("CLOSE");
                            break;
                        } else if (mensaje.equals("1")) {
                            List<Client> clientes = dq.getClients();
                            for (Object obj : clientes) {
                                salida.write(obj.toString());
                                salida.newLine();
                            }
                        } else if (mensaje.equals("2")) {
                            List<Room> habitaciones = dq.getRooms();
                            for (Object obj : habitaciones) {
                                salida.write(obj.toString());
                                salida.newLine();
                            }
                        } else if (mensaje.equals("3")) {
                            List<Book> reservas = dq.getBooks();
                            for (Object obj : reservas) {
                                salida.write(obj.toString());
                                salida.newLine();
                            }
                        } else {
                            salida.write("Opción no válida.\n");
                        }

                        salida.write("END");
                        salida.newLine();
                        salida.flush();
                    }
                }
                System.out.println("Cliente desconectado.");
                socket.close();
                break;
            }
        } catch (IOException e) {
            System.err.println("Error en el servidor: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SocketServidor socketServidor = new SocketServidor(5000);
        socketServidor.iniciar();
    }
}