package com.example;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        GeneradorReportesExcel crearxlsx = new GeneradorReportesExcel();
        GeneradorReportesPDF crearPdf = new GeneradorReportesPDF();

        Scanner scanner = new Scanner(System.in);
        int opcion;

        // Menú principal
        do {
            System.out.println("Menú de Opciones:");
            System.out.println("1. Sacar informe en PDF");
            System.out.println("2. Sacar informe en Excel");
            System.out.println("3. Salir");
            System.out.print("Elige una opción (1-3): ");
            opcion = scanner.nextInt();

            // Ejecutar según la opción seleccionada
            switch (opcion) {
                case 1:
                    crearPdf.crearInformePDF();
                    break;
                case 2:
                    crearxlsx.crearxlsx();
                    break;
                case 3:
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
