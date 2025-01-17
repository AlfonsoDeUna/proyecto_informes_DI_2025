// //import org.apache.poi.ss.usermodel.*;
// //import org.apache.poi.xssf.usermodel.XSSFWorkbook;

// import java.io.FileOutputStream;
// import java.io.IOException;
// import java.sql.*;
// import java.time.LocalDate;
// import java.time.temporal.WeekFields;
// import java.util.Locale;
// import java.util.HashMap;
// import java.util.Map;

// public class Excel {
//     public static void main(String[] args) {
//         String dbUrl = "jdbc:sqlite:/path/to/hotel.db"; // Reemplaza con la ruta de tu base de datos
//         String excelFilePath = "HotelOccupancyReport.xlsx";

//         try (Connection conn = DriverManager.getConnection(dbUrl)) {
//             if (conn != null) {
//                 // Obtener datos de reservas
//                 Map<String, Integer> weeklyOccupancy = new HashMap<>();
//                 Map<String, Integer> monthlyOccupancy = new HashMap<>();

//                 String query = "SELECT Fecha_Inicio, Fecha_Fin FROM Reservas";
//                 try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
//                     while (rs.next()) {
//                         LocalDate startDate = LocalDate.parse(rs.getString("Fecha_Inicio"));
//                         LocalDate endDate = LocalDate.parse(rs.getString("Fecha_Fin"));

//                         while (!startDate.isAfter(endDate)) {
//                             // Resumen semanal
//                             int week = startDate.get(WeekFields.of(Locale.getDefault()).weekOfYear());
//                             String weekKey = "Semana " + week + " (" + startDate.getYear() + ")";
//                             weeklyOccupancy.put(weekKey, weeklyOccupancy.getOrDefault(weekKey, 0) + 1);

//                             // Resumen mensual
//                             String monthKey = startDate.getMonth() + " " + startDate.getYear();
//                             monthlyOccupancy.put(monthKey, monthlyOccupancy.getOrDefault(monthKey, 0) + 1);

//                             startDate = startDate.plusDays(1); // Incrementar día
//                         }
//                     }
//                 }

//                 // Crear el archivo Excel
//                 try (Workbook workbook = new XSSFWorkbook()) {
//                     // Resumen semanal
//                     Sheet weeklySheet = workbook.createSheet("Resumen Semanal");
//                     createOccupancySheet(weeklySheet, "Semana", weeklyOccupancy);

//                     // Resumen mensual
//                     Sheet monthlySheet = workbook.createSheet("Resumen Mensual");
//                     createOccupancySheet(monthlySheet, "Mes", monthlyOccupancy);

//                     // Guardar el archivo Excel
//                     try (FileOutputStream fileOut = new FileOutputStream(excelFilePath)) {
//                         workbook.write(fileOut);
//                         System.out.println("El reporte de ocupación ha sido generado: " + excelFilePath);
//                     }
//                 }
//             }
//         } catch (SQLException | IOException e) {
//             e.printStackTrace();
//         }
//     }

//     private static void createOccupancySheet(Sheet sheet, String columnHeader, Map<String, Integer> data) {
//         // Crear encabezado
//         Row headerRow = sheet.createRow(0);
//         headerRow.createCell(0).setCellValue(columnHeader);
//         headerRow.createCell(1).setCellValue("Ocupación");

//         // Llenar datos
//         int rowNum = 1;
//         for (Map.Entry<String, Integer> entry : data.entrySet()) {
//             Row row = sheet.createRow(rowNum++);
//             row.createCell(0).setCellValue(entry.getKey());
//             row.createCell(1).setCellValue(entry.getValue());
//         }

//         // Autoajustar columnas
//         sheet.autoSizeColumn(0);
//         sheet.autoSizeColumn(1);
//     }
// }
