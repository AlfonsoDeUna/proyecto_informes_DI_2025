package com.example;


import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class CrearPDF {
    public static void main(String[] args) {
        
        String dest = "hotel_data.pdf"; // Ruta de destino para el PDF
        String dbPath = "proyecteq3\\hotel.db"; // Ruta de la base de datos SQLite

        try {
            // Crear un escritor de PDF
            PdfWriter writer = new PdfWriter(dest);

            // Crear un documento PDF
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            // Conectar a la base de datos SQLite
            Connection connection = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
            Statement statement = connection.createStatement();

            // --------------------------------
            // SELECT de la tabla "Clientes"
            // --------------------------------
            document.add(new Paragraph("Tabla: Clientes"));
            Table tableClientes = new Table(5); // Cinco columnas: ID, Nombre, Apellido, Telefono, Email
            tableClientes.addCell("ID");
            tableClientes.addCell("Nombre");
            tableClientes.addCell("Apellido");
            tableClientes.addCell("Teléfono");
            tableClientes.addCell("Email");

            ResultSet rsClientes = statement.executeQuery("SELECT * FROM Clientes");
            while (rsClientes.next()) {
                tableClientes.addCell(String.valueOf(rsClientes.getInt("ID")));
                tableClientes.addCell(rsClientes.getString("Nombre"));
                tableClientes.addCell(rsClientes.getString("Apellido"));
                tableClientes.addCell(rsClientes.getString("Telefono"));
                tableClientes.addCell(rsClientes.getString("Email"));
            }
            document.add(tableClientes);
            rsClientes.close();

            // --------------------------------
            // SELECT de la tabla "Habitaciones"
            // --------------------------------
            document.add(new Paragraph("\nTabla: Habitaciones"));
            Table tableHabitaciones = new Table(4); // Cuatro columnas: ID, Número, Tipo, Precio
            tableHabitaciones.addCell("ID");
            tableHabitaciones.addCell("Número");
            tableHabitaciones.addCell("Tipo");
            tableHabitaciones.addCell("Precio");

            ResultSet rsHabitaciones = statement.executeQuery("SELECT * FROM Habitaciones");
            while (rsHabitaciones.next()) {
                tableHabitaciones.addCell(String.valueOf(rsHabitaciones.getInt("ID")));
                tableHabitaciones.addCell(String.valueOf(rsHabitaciones.getInt("Numero")));
                tableHabitaciones.addCell(rsHabitaciones.getString("Tipo"));
                tableHabitaciones.addCell(String.valueOf(rsHabitaciones.getDouble("Precio")));
            }
            document.add(tableHabitaciones);
            rsHabitaciones.close();

            // --------------------------------
            // SELECT de la tabla "Reservas"
            // --------------------------------
            document.add(new Paragraph("\nTabla: Reservas"));
            Table tableReservas = new Table(6); // Seis columnas: ID, ID_Cliente, ID_Habitación, Fecha_Inicio, Fecha_Fin, Total
            tableReservas.addCell("ID");
            tableReservas.addCell("ID Cliente");
            tableReservas.addCell("ID Habitación");
            tableReservas.addCell("Fecha Inicio");
            tableReservas.addCell("Fecha Fin");
            tableReservas.addCell("Total");

            ResultSet rsReservas = statement.executeQuery("SELECT * FROM Reservas");
            while (rsReservas.next()) {
                tableReservas.addCell(String.valueOf(rsReservas.getInt("ID")));
                tableReservas.addCell(String.valueOf(rsReservas.getInt("ID_Cliente")));
                tableReservas.addCell(String.valueOf(rsReservas.getInt("ID_Habitación")));
                tableReservas.addCell(rsReservas.getString("Fecha_Inicio"));
                tableReservas.addCell(rsReservas.getString("Fecha_Fin"));
                tableReservas.addCell(String.valueOf(rsReservas.getDouble("Total")));
            }
            document.add(tableReservas);
            rsReservas.close();

            // Cerrar conexión a la base de datos
            statement.close();
            connection.close();

            // Cerrar el documento PDF
            document.close();

            System.out.println("PDF creado en: " + dest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
