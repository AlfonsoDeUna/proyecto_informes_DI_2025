package pdfGenerator;

import java.sql.SQLException;
import java.util.List;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;

import databaseAdmin.DatabaseGenerator;
import databaseAdmin.DatabaseQuery;

public class PDFGenerator {
	
	private static DatabaseGenerator dg = new DatabaseGenerator();
	private static DatabaseQuery dq = new DatabaseQuery(dg);
	
	public void execute() {
		String pdfName = "Reporte_Motel.pdf";
		
        try {
            PdfWriter writer = new PdfWriter(pdfName);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            document.add(new Paragraph("Reporte de reservas Motel"));

            generateTables(document);
            
            document.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	private static void generateTables(Document document) {
		List<String> nameTable = dg.getTableNames();
		for(int i = 0; i <= nameTable.size(); i++) {
			String actualName = nameTable.get(i);

			document.add(new Paragraph(actualName));
			int numColumns = dg.countColumns(actualName);
			
			Table table = new Table(numColumns);
			generateCells(table, actualName);
			document.add(table);
		}
	}
	
	private static void generateCells(Table table, String actualName) {
		List<?> content = null;
		
		switch (actualName) {
			case "Clientes":
				content = dq.getClients();
				break;
				
			case "Habitaciones":
				content = dq.getRooms();
				break;
				
			case "Reservas":
				content = dq.getBooks();
				break;
		}
		
		for(int i = 0; i <= content.size(); i++) {
			table.addCell(content.get(i).toString());
		}
	}
}
