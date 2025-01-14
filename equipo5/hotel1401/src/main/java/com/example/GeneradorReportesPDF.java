package com.example;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.VerticalAlignment;

public class GeneradorReportesPDF {
    public void sacarInformePDF(String rutaPdf) {
        String namePdf = rutaPdf;

        try {
            // Crear un escritor para poder escribir en el PDF
            PdfWriter writer = new PdfWriter(namePdf); 

            // Crear el documento PDF
            PdfDocument pdfDocument = new PdfDocument(writer);
            Document document = new Document(pdfDocument);

            // Crear un paragrafo para añadir un título al documento PDF
            Paragraph title = new Paragraph("RESERVAS HOTEL");

            // Formato del título
            title.setVerticalAlignment(VerticalAlignment.MIDDLE); // Alinear el titulo de manera vertical y en el centro de la pantalla
            title.setFontSize(32); // Fuente
            


            document.add(title);




            document.close();





        } catch (Exception e) {
        }
    }
}
