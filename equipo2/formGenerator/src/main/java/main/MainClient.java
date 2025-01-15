package main;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Scanner;

import socketClient.SocketClient;

public class MainClient {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String ip;

        while (true) {
            System.out.println("Introduce la IP del servidor (o 'salir' para terminar):");
            ip = sc.nextLine();
            
            if (ip.equalsIgnoreCase("salir")) {
                System.out.println("Cerrando cliente...");
                break;
            }
            
            SocketClient client = new SocketClient(ip);

            try {
                List<String> respuesta = client.connectServer();
                
                if (respuesta.isEmpty()) {
                    System.out.println("Sesión cerrada. No hay respuesta del servidor.");
                    client.closeConn();
                } else {
                    System.out.println("Respuestas del servidor:");
                    for (String res : respuesta) {
                        System.out.println(res);
                    }
                }
            } catch (UnknownHostException e) {
                System.out.println("Error: No se pudo encontrar el servidor.");
                e.printStackTrace();
            } catch (IOException e) {
                System.out.println("Error en la conexión o comunicación.");
                e.printStackTrace();
            }
        }
        sc.close();
    }
}
