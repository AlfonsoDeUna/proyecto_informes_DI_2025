package com.proyecto;

import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;

public class PdfReportGenerator {

    public static void generateReport(String dest) {
        try {
            PdfWriter writer = new PdfWriter(dest);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            // Fuente en negrita
            PdfFont boldFont = PdfFontFactory.createFont("Helvetica-Bold");

            // Título
            Text title = new Text("Reporte de Reservas de Hotel")
                    .setFont(boldFont)
                    .setFontSize(18)
                    .setFontColor(new DeviceRgb(0, 102, 204));
            document.add(new Paragraph().add(title));

            // Tabla de ejemplo
            Table table = new Table(3);
            table.addCell("Cliente");
            table.addCell("Habitación");
            table.addCell("Fecha de Reserva");

            table.addCell("Juan Pérez");
            table.addCell("101");
            table.addCell("2025-01-10");

            table.addCell("Ana López");
            table.addCell("202");
            table.addCell("2025-01-12");

            document.add(table);

            document.close();
            System.out.println("PDF generado: " + dest);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
