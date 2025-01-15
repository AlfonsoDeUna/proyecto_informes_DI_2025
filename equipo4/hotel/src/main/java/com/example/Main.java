package com.example;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import java.util.List;

import com.itextpdf.layout.element.ListItem;
import com.itextpdf.layout.element.Paragraph;


public class Main {
    public static void main(String[] args) {
        String dest = "Hotel.pdf";

        try {
            // Crear un escritor de PDF
            PdfWriter writer = new PdfWriter(dest);

            // Crear la base de datos
            BBDD bbdd = new BBDD();
            bbdd.crearTabla(); // Crea la tabla y las filas
            List<String> habitaciones = bbdd.getElements(); // Devuelve la lista de datos

            // Crear un documento PDF
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            // Crear una lista en el PDF con los datos de las habitaciones
            com.itextpdf.layout.element.List lista = new com.itextpdf.layout.element.List();

            // Añadir los datos de las habitaciones a la lista
            for (int i = 0; i < habitaciones.size(); i += 4) {
                String id = habitaciones.get(i);
                String numero = habitaciones.get(i + 1);
                String tipo = habitaciones.get(i + 2);
                String precio = habitaciones.get(i + 3);

                // Crear un ítem de lista con los datos de la habitación
                ListItem item = new ListItem();
                item.add(new Paragraph("ID: " + id));
                item.add(new Paragraph("Número: " + numero));
                item.add(new Paragraph("Tipo: " + tipo));
                item.add(new Paragraph("Precio: " + precio));

                // Añadir el ítem a la lista
                lista.add(item);
            }

            // Añadir la lista al documento
            document.add(new Paragraph("Lista de Habitaciones:"));
            document.add(lista);

            // Cerrar la base de datos
            bbdd.cerrarBBDD();

            // Cerrar el documento
            document.close();

            System.out.println("PDF creado en: " + dest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}