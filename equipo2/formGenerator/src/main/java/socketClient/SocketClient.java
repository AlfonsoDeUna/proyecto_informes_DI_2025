package socketClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
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
            
            String serverMessage = input.readLine();
            System.out.println("Servidor: " + serverMessage);
            
            Scanner sc = new Scanner(System.in);
            System.out.print("Ingresa un número para elegir una opción del menú: ");
            String userInput = sc.nextLine();
            
            output.println(userInput);
            
            String queryResult;
            while ((queryResult = input.readLine()) != null) {
                queryResults.add(queryResult);
                System.out.println("Respuesta del servidor: " + queryResult);
            }
            
            sc.close();
        }
        
        return queryResults;
    }
    
    public void closeConn() throws IOException {
        System.out.println("Conexión cerrada.");
    }
}
