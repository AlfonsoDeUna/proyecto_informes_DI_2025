package pdfGenerator;

import java.util.List;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;

import dataClases.Book;
import dataClases.Client;
import dataClases.Room;
import databaseAdmin.DatabaseGenerator;
import databaseAdmin.DatabaseQuery;

public class PDFGenerator {
	
	private static DatabaseGenerator dg = new DatabaseGenerator();
	private static DatabaseQuery dq = new DatabaseQuery(dg);
	
	public void execute() {
		String pdfName = "src/main/resources/Reporte_Motel.pdf";
		
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
		for(int i = 0; i < nameTable.size(); i++) {
			String actualName = nameTable.get(i);

			document.add(new Paragraph("\n" + actualName));
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
	            table.addHeaderCell("ID");
	            table.addHeaderCell("Nombre");
	            table.addHeaderCell("Apellido");
	            table.addHeaderCell("Teléfono");
	            table.addHeaderCell("Email");
	            break;

	        case "Habitaciones":
	            content = dq.getRooms();
	            table.addHeaderCell("ID");
	            table.addHeaderCell("Número");
	            table.addHeaderCell("Tipo");
	            table.addHeaderCell("Precio");
	            break;

	        case "Reservas":
	            content = dq.getBooks();
	            table.addHeaderCell("ID");
	            table.addHeaderCell("ID Cliente");
	            table.addHeaderCell("ID Habitación");
	            table.addHeaderCell("Fecha Inicio");
	            table.addHeaderCell("Fecha Fin");
	            table.addHeaderCell("Total");
	            break;
	    }

	    for (Object obj : content) {
	        if (obj instanceof Client) {
	            Client client = (Client) obj;
	            table.addCell(String.valueOf(client.getID()));
	            table.addCell(client.getNombre());
	            table.addCell(client.getApellido());
	            table.addCell(client.getTelefono());
	            table.addCell(client.getEmail());
	        } else if (obj instanceof Room) {
	            Room room = (Room) obj;
	            table.addCell(String.valueOf(room.getID()));
	            table.addCell(String.valueOf(room.getNumero()));
	            table.addCell(room.getTipo());
	            table.addCell(String.valueOf(room.getPrecio()));
	        } else if (obj instanceof Book) {
	            Book book = (Book) obj;
	            table.addCell(String.valueOf(book.getID()));
	            table.addCell(String.valueOf(book.getIdCliente()));
	            table.addCell(String.valueOf(book.getIdHabitacion()));
	            table.addCell(book.getFechaInicio());
	            table.addCell(book.getFechaFin());
	            table.addCell(String.valueOf(book.getTotal()));
	        }
	    }
	}
}