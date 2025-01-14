package excelGenerator;

import databaseAdmin.DatabaseGenerator;
import databaseAdmin.DatabaseQuery;
import dataClases.Book;
import dataClases.Client;
import dataClases.Room;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelGenerator {
    private final String filePath;
    private String fileName;
    private DatabaseGenerator database;
    private DatabaseQuery dataQuery;

    public ExcelGenerator(String fileName) {
        this.filePath = "./src/main/resources";
        this.fileName = "InformeExcel.xlsx";
        this.database = new DatabaseGenerator();
        this.dataQuery = new DatabaseQuery(database);
    }

    public void generateExcel() {
        String[] columns = {
            "Reserva ID", "Cliente ID", "Nombre", "Apellido", "Habitación ID",
            "Tipo", "Precio", "Fecha Inicio", "Fecha Fin", "Total"
        };

        List<Client> clients = dataQuery.getClients();
        
        
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Reservas");

            Row headerRow = sheet.createRow(0);
            for (int i = 0; i < columns.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columns[i]);
                CellStyle style = workbook.createCellStyle();
                Font font = workbook.createFont();
                font.setBold(true);
                style.setFont(font);
                cell.setCellStyle(style);
            }

            int rowNum = 1;
            for (Client client : clients) {
                for (Book book : dataQuery.getBookByClient(client.getID())) {
                    Row row = sheet.createRow(rowNum++);
                    row.createCell(0).setCellValue(book.getID());
                    row.createCell(1).setCellValue(book.getIdCliente());
                    row.createCell(2).setCellValue(client.getNombre());
                    row.createCell(3).setCellValue(client.getApellido());
                    row.createCell(4).setCellValue(book.getIdHabitacion());
                    row.createCell(5).setCellValue(dataQuery.getRoomType(book.getIdHabitacion()));
                    row.createCell(6).setCellValue(dataQuery.getRoomPrice(book.getIdHabitacion()));
                    row.createCell(7).setCellValue(book.getFechaInicio().toString());
                    row.createCell(8).setCellValue(book.getFechaFin().toString());
                    row.createCell(9).setCellValue(book.getTotal());
                }
            }
            
            for (int i = 0; i < columns.length; i++) {
                sheet.autoSizeColumn(i);
            }

            try (FileOutputStream fileOut = new FileOutputStream(filePath + "/" + fileName)) {
                workbook.write(fileOut);
            }

            System.out.println("Archivo Excel generado correctamente en " + filePath + "/" + fileName);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
