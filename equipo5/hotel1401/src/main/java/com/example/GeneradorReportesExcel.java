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

    public void crearxlsx(){

        String dbUrl = "jdbc:sqlite:equipo5/hotel1401/src/main/Database/hotel.db";
            // Asegúrate de usar la ruta correcta
        String dest = "proyecto_informes_DI_2025/equipo5/Reportes/Reservas.xlsx"; // Nombre del archivo Excel a generar

        try {
            // Registrar el controlador JDBC de SQLite
            Class.forName("org.sqlite.JDBC");

            // Conectar a la base de datos SQLite
            Connection conn = DriverManager.getConnection(dbUrl);
            Statement stmt = conn.createStatement();
            
            // Ejecutar una consulta para obtener los datos de la tabla
            String sql = "SELECT * FROM Clientes"; // Cambia "recetas" por el nombre de tu tabla
            ResultSet rs = stmt.executeQuery(sql);

            // Crear un libro de trabajo Excel
            Workbook workbook = new XSSFWorkbook();
            // Crear una hoja de trabajo (sheet)
            Sheet sheet = workbook.createSheet("Clientes");

            // Crear una fila para los encabezados de las columnas
            Row headerRow = sheet.createRow(0);
            
            headerRow.createCell(0).setCellValue("Nombre");
            headerRow.createCell(0).setCellValue("Apellido");

            // Llenar la hoja con los datos de la base de datos
            int rowNum = 1;
            while (rs.next()) {
                Row row = sheet.createRow(rowNum++);
                
                row.createCell(0).setCellValue(rs.getString("Nombre"));
                row.createCell(0).setCellValue(rs.getString("Apellido"));  // Cambia "id" por el nombre de tu columna

            }
            // Ajustar el tamaño de las columnas para que el texto se ajuste bien
            for (int i = 0; i < 3; i++) {
                sheet.autoSizeColumn(i);
            }

            // Escribir el archivo Excel en el disco
            FileOutputStream fileOut = new FileOutputStream(dest);
            workbook.write(fileOut);
            fileOut.close();

            // Cerrar la conexión y el libro de trabajo
            workbook.close();
            rs.close();
            stmt.close();
            conn.close();

            System.out.println("Excel creado en: " + dest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
