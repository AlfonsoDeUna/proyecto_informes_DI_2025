package pdfGenerator;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

public class PDFGenerator {
	public static void main(String[] args) {
        String dest = "example.pdf";

        try {
            // Crear un escritor de PDF
            PdfWriter writer = new PdfWriter(dest);

            // Crear un documento PDF
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            // Añadir un párrafo al documento
            document.add(new Paragraph("¡Hola, este es un PDF generado en Java!"));

            // Cerrar el documento
            document.close();

            System.out.println("PDF creado en: " + dest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
