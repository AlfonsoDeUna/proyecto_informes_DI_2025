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
	
	public static void main(String[] args) throws SQLException {
		String pdfName = "Reporte_Motel.pdf";
        DatabaseGenerator dbName = getBBDDName();
        getQuery(dbName);
        

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

	private static void getQuery(DatabaseGenerator dbUrl) {
		DatabaseQuery dq = new DatabaseQuery(dbUrl);
	}

	public static void generateTables(Document document) {
		Table table = new Table(3);
		table.addCell("Columna 1");
		table.addCell("Columna 2");
		table.addCell("Columna 3");
		document.add(table);
	}
	
	public static DatabaseGenerator getBBDDName() {
		DatabaseGenerator dg = new DatabaseGenerator();
		return dg;
	}
}
