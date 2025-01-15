package com.example;
 
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Image;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.List;
 
public class CrearPDF {
    public static void main(String[] args) {
        String dest = "example.pdf";
 
        try {
            // Crear un escritor de PDF
            PdfWriter writer = new PdfWriter(dest);
 
            // Crear base de datos
           
 
            // Crear un documento PDF
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);
 
            // Añadir un párrafo al documento
            document.add(new Paragraph("¡Hola, este es un PDF generado en Java!"));
            document.add(new Paragraph("¡Esto es dandys papá!"));
 
            ImageData imageData = ImageDataFactory.create("C:\\Users\\sergi\\OneDrive\\Escritorio\\2DAM\\Interfaces\\Tema 4 Informes\\10-01-2024\\informepdf\\src\\main\\resources\\madrid.jpg");
            Image img = new Image(imageData);
            img.setWidth(450);
            img.setHeight(300);
            document.add(img);
 
            Table table = new Table(3); // 3 columnas
            table.addCell("Columna 1");
            table.addCell("Columna 2");
            table.addCell("Columna 3");
            document.add(table);
 
            List list = new List();
            list.add("Elemento 1");
            list.add("Elemento 2");
            list.add("Elemento 3");
            document.add(list);
 
            // Cerrar el documento
            document.close();
 
            System.out.println("PDF creado en: " + dest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}