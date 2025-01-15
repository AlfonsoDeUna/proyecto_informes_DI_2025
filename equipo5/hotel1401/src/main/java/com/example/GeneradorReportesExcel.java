package com.example;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class GeneradorReportesExcel {

    public void crearxlsx() {

        String dbUrl = "jdbc:sqlite:equipo5/hotel1401/src/main/Database/hotel.db"; // Ruta correcta de tu base de datos
        String dest = "equipo5/Reportes/Reservas.xlsx"; // Archivo Excel a generar

        // Usar try-with-resources para asegurarnos de cerrar los recursos
        try (
            Connection conn = DriverManager.getConnection(dbUrl);
            Statement stmt = conn.createStatement();
            Workbook workbook = new XSSFWorkbook();
        ) {
            // Registrar el controlador JDBC de SQLite
            Class.forName("org.sqlite.JDBC");

            // Consulta SQL
            String sql = """
                SELECT 
                    r.ID AS ID_Reserva,
                    c.ID AS ID_Cliente,
                    c.Nombre AS Nombre_Cliente,
                    c.Email AS Email_Cliente,
                    h.ID AS ID_Habitacion,
                    h.Numero AS Numero_Habitacion,
                    h.Tipo AS Tipo_Habitacion,
                    r.Fecha_Inicio,
                    r.Fecha_Fin,
                    r.Total
                FROM Reservas r
                JOIN Clientes c ON r.ID_Cliente = c.ID
                JOIN Habitaciones h ON r.ID_Habitación = h.ID;
            """;
            ResultSet rs = stmt.executeQuery(sql);

            // Crear hoja de trabajo y encabezados
            Sheet sheet = workbook.createSheet("Reservas");
            Row headerRow = sheet.createRow(0);

            String[] headers = { 
                "ID Reserva", "Nombre Cliente", "Email Cliente", 
                "Número Habitación", "Tipo Habitación", 
                "Fecha Inicio", "Fecha Fin", "Total"
            };

            for (int i = 0; i < headers.length; i++) {
                headerRow.createCell(i).setCellValue(headers[i]);
            }

            // Llenar los datos
            int rowNum = 1;
            while (rs.next()) {
                Row row = sheet.createRow(rowNum++);

                row.createCell(0).setCellValue(rs.getInt("ID_Reserva"));
                row.createCell(1).setCellValue(rs.getString("Nombre_Cliente"));
                row.createCell(2).setCellValue(rs.getString("Email_Cliente"));
                row.createCell(3).setCellValue(rs.getString("Numero_Habitacion"));
                row.createCell(4).setCellValue(rs.getString("Tipo_Habitacion"));
                row.createCell(5).setCellValue(rs.getString("Fecha_Inicio"));
                row.createCell(6).setCellValue(rs.getString("Fecha_Fin"));
                row.createCell(7).setCellValue(rs.getDouble("Total"));
            }

            // Ajustar el tamaño de todas las columnas
            for (int i = 0; i < headers.length; i++) {
                sheet.autoSizeColumn(i);
            }

            // Escribir el archivo en disco
            try (FileOutputStream fileOut = new FileOutputStream(dest)) {
                workbook.write(fileOut);
            }

            System.out.println("Excel creado en: " + dest);

        } catch (Exception e) {
            System.err.println("Error al crear el archivo Excel: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
