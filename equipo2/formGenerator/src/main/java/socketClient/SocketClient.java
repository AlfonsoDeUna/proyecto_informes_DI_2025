package socketClient;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SocketClient {

    private String ip;
    private int port;

    public SocketClient(String ip) {
        this.ip = ip;
        this.port = 5000;
    }

    public List<String> connectServer() throws IOException {
        List<String> queryResults = new ArrayList<>();

        try (Socket socket = new Socket(ip, port)) {
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

            Scanner sc = new Scanner(System.in);
            while (true) {
                // Recibir menú del servidor
                String serverMessage;
                while ((serverMessage = input.readLine()) != null && !serverMessage.equals("END")) {
                    System.out.println("Servidor: " + serverMessage);
                }

                System.out.print("Ingresa un número para elegir una opción del menú: ");
                String userInput = sc.nextLine();

                // Enviar la opción al servidor
                output.println(userInput);

                if (userInput.equals("4")) {
                    System.out.println("Cerrando conexión...");
                    break;
                }

                // Recibir respuesta del servidor
                String queryResult;
                while ((queryResult = input.readLine()) != null && !queryResult.equals("END")) {
                    queryResults.add(queryResult);
                    System.out.println("Respuesta del servidor: " + queryResult);
                }
            }
            sc.close();
        }
        return queryResults;
    }

    public static void main(String[] args) {
        try {
            SocketClient client = new SocketClient("127.0.0.1");
            client.connectServer();
        } catch (IOException e) {
            System.err.println("Error en el cliente: " + e.getMessage());
        }
    }
}