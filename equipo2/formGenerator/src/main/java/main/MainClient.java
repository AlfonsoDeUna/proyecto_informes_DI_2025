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

        // Pide la IP una sola vez
        System.out.println("Introduce la IP del servidor:");
        ip = sc.nextLine();

        SocketClient client = null;

        try {
            client = new SocketClient(ip);
            client.connect();

            while (true) {
                List<String> respuesta = client.interactWithServer();

                if (respuesta.isEmpty()) {
                    System.out.println("Sesión cerrada o no hay respuesta del servidor.");
                    break;
                }
            }

        } catch (UnknownHostException e) {
            System.out.println("Error: No se pudo encontrar el servidor.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error en la conexión o comunicación.");
            e.printStackTrace();
        } finally {
            if (client != null) {
                try {
                    client.close();
                } catch (IOException e) {
                    System.out.println("Error al cerrar la conexión.");
                    e.printStackTrace();
                }
            }
        }

        sc.close();
    }
}
