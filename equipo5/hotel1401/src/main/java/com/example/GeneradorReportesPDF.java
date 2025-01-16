package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.VerticalAlignment;

public class GeneradorReportesPDF {

    public void sacarInformePDF() {
        String namePdf = "proyecto_informes_DI_2025/equipo5/Reportes/reservas.pdf";
        String bdUrl = "jdbc:sqlite:proyecto_informes_DI_2025/equipo5/hotel1401/src/main/Database/hotel.db";

        try {

            Class.forName("org.sqlite.JDBC");

            Connection conn = DriverManager.getConnection(bdUrl);
            Statement stmt = conn.createStatement();

            String sql = "SELECT * FROM Reservas";
            ResultSet rs = stmt.executeQuery(sql);

            // Crear un escritor para poder escribir en el PDF
            PdfWriter writer = new PdfWriter(namePdf);

            // Crear el documento PDF
            PdfDocument pdfDocument = new PdfDocument(writer);
            Document document = new Document(pdfDocument);

            // Crear un paragrafo para añadir un título al documento PDF
            Paragraph title = new Paragraph("LISTA DE RESERVAS DEL HOTEL");

            // Formato del título
            title.setVerticalAlignment(VerticalAlignment.MIDDLE); // Alinear el titulo de manera vertical y en el centro de la pantalla
            title.setFontSize(32); // Tamaño de la letra del título

            document.add(title);

            document.add(new Paragraph(""));
            document.add(new Paragraph(""));
            document.add(new Paragraph(""));

            Table reservasTable = new Table(6);
            reservasTable.addCell("ID");
            reservasTable.addCell("ID_Cliente");
            reservasTable.addCell("ID_Habitación");
            reservasTable.addCell("Fecha_Inicio");
            reservasTable.addCell("Fecha_Fin");
            reservasTable.addCell("Total");

            while (rs.next()) {
                reservasTable.addCell(rs.getString("ID"));
                reservasTable.addCell(rs.getString("ID_Cliente"));
                reservasTable.addCell(rs.getString("ID_Habitación"));
                reservasTable.addCell(rs.getString("Fecha_Inicio"));
                reservasTable.addCell(rs.getString("Fecha_Inicio"));
                reservasTable.addCell(rs.getString("Total"));
            }

            document.add(reservasTable);
            rs.close();
            stmt.close();
            conn.close();

            System.out.println("PDF creado ");

            document.close();

            System.out.println("PDF creado en: " + namePdf);

        } catch (Exception e) {
            System.out.println("Error al crear el documento PDF: " + e.getMessage());
        }
    }
}
