package main;

import databaseAdmin.DatabaseGenerator;
import databaseAdmin.DatabaseQuery;
import excelGenerator.ExcelGenerator;
import pdfGenerator.PDFGenerator;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DatabaseGenerator database = new DatabaseGenerator();
        database.crearTabla();
        
        DatabaseQuery dataQuery = new DatabaseQuery(database);
        ExcelGenerator excelGenerator = new ExcelGenerator("InformeExcel.xlsx");
        PDFGenerator pdfGenerator = new PDFGenerator();
        
        int option;

        do {
            System.out.println("\nMenú Principal:");
            System.out.println("1. Generar archivo Excel con las reservas");
            System.out.println("2. Generar informe PDF");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");

            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    System.out.println("\nGenerando archivo Excel...");
                    excelGenerator.generateExcel();
                    break;

                case 2:
                    System.out.println("\nGenerando archivo PDF");
                    pdfGenerator.execute();
                    break;

                case 3:
                    System.out.println("Saliendo del programa. ¡Hasta luego!");
                    break;

                default:
                    System.out.println("Opción no válida. Por favor, intente de nuevo.");
            }

        } while (option != 3);

        scanner.close();
    }
}
