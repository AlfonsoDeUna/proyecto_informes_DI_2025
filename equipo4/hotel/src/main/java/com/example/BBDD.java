package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.List;


public class BBDD {
    public static void main(String[] args) {
        String bdUrl = "jdbc:sqlite:Hotel.db"; // URL de la base de datos
        String dest = "Hotel.pdf"; //Nombre del archivo a generar

        try{
            // Registrar el controlador JDBC de SQLite
            Class.forName("org.sqlite.JDBC");
            // Establecer la conexión con la base de datos
            Connection conn = DriverManager.getConnection(bdUrl);
            Statement stmt = conn.createStatement();
            // Ejecutar la consulta SQL
            ResultSet rs = stmt.executeQuery("SELECT * FROM Habitaciones");
            // Crear un escritor de PDF
            PdfWriter writer = new PdfWriter(dest);
            // Crear un documento PDF
            PdfDocument pdf = new PdfDocument(writer);
            // Crear un documento de texto
            Document document = new Document(pdf);

            // Añadir un título al documento
            document.add(new Paragraph("Listado registro habitaciones").setFontSize(24));
            // Crear una lista de los datos obtenios
            List list = new List();
            list.add("ID");
            list.add("Numero");
            list.add("Tipo");
            list.add("Precio");
            // Recorrer los resultados de la consulta SQL
            while (rs.next()) {
                // Añadir un elemento a la lista
                list.add(rs.getString("ID") + " " +
                 rs.getString("Numero") + " " + 
                 rs.getString("Tipo") + " " + 
                 rs.getString("Precio"));
            }
            // añadir la lista
            document.add(list);

            // cerrar el documento y la conexión a la base de datos
            document.close();
            rs.close();
            stmt.close();
            conn.close();

            System.out.println("PDF creado en: " + dest);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
}
