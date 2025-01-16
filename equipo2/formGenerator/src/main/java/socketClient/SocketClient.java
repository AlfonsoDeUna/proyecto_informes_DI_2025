package socketClient;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class SocketClient {

    private String ip;
    private int port;
    private Socket socket;
    private BufferedReader input;
    private PrintWriter output;

    public SocketClient(String ip) {
        this.ip = ip;
        this.port = 5000;
    }

    public void connect() throws IOException {
        // Establecer la conexión y los flujos
        socket = new Socket(ip, port);
        input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        output = new PrintWriter(socket.getOutputStream(), true);
    }

    public boolean interactWithServer() throws IOException {
        Scanner sc = new Scanner(System.in);

        String serverMessage;
        while ((serverMessage = input.readLine()) != null ) {
        	if (serverMessage.equalsIgnoreCase("END")) {
        		break;
        	}
            System.out.println("Respuesta del servidor: " + serverMessage);
        }


        System.out.print("Ingresa tu respuesta: ");
        String userInput = sc.nextLine();
        output.println(userInput);

        String queryResult;
        while ((queryResult = input.readLine()) != null) {
        	if (queryResult.equalsIgnoreCase("END")) {
        		break;
        	}
	            System.out.println("Respuesta del servidor: " + queryResult);
        }
        if (queryResult.equalsIgnoreCase("close")) {
        	return false;
        }
        return true;
    }

    public void close() throws IOException {
        if (input != null) {
            input.close();
        }
        if (output != null) {
            output.close();
        }
        if (socket != null) {
            socket.close();
        }
    }
}
