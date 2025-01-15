package com.example;

import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {

        GeneradorReportesExcel crearxlsx = new GeneradorReportesExcel();
       
         Scanner scanner = new Scanner(System.in);
        int opcion;

        // Menú principal
        do {
            System.out.println("Menú de Opciones:");
            System.out.println("1. Meter una nueva reserva");
            System.out.println("2. Sacar informe en PDF");
            System.out.println("3. Sacar informe en Excel");
            System.out.println("4. Salir");
            System.out.print("Elige una opción (1-4): ");
            opcion = scanner.nextInt();

            // Ejecutar según la opción seleccionada
            switch (opcion) {
                case 1:
                   // meterReserva();
                    break;
                case 2:
                   // sacarInformePDF();
                    break;
                case 3:
                   // sacarInformeExcel();
                   crearxlsx.crearxlsx();
                    break;
                case 4:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida, por favor elige entre 1 y 3.");
                    break;
            }

        } while (opcion != 4);

        scanner.close();
    }
   
}
