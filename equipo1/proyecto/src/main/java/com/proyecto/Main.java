package com.proyecto;


public class Main {
    public static void main(String[] args) {
        DatabaseInitializer.initializeDatabase(); // Inicializa la BBDD

        String dest = "ReporteHotelEquipo1.pdf"; // Genera pdf
        PdfReportGenerator.generateReport(dest);

        String destExcel = "ReporteHotelEquipo1Excel.csv";
        ExportarTablaACSV.reportar(destExcel);
    }
}
