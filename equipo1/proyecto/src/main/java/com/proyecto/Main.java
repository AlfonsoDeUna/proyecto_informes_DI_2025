package com.proyecto;


public class Main {
    public static void main(String[] args) {
        DatabaseInitializer di = new DatabaseInitializer();
        di.initializeDatabase(); // Inicializa la BBDD

        String dest = "ReporteHotelEquipo1.pdf";
        PdfReportGenerator pdf = new PdfReportGenerator(); // Genera pdf
        pdf.generateReport(dest);

        String destExcel = "ReporteHotelEquipo1Excel.csv";
        ExportarTablaACSV.reportar(destExcel);
    }
}
