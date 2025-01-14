package excelGenerator;

import databaseAdmin.DatabaseGenerator;
import databaseAdmin.DatabaseQuery;

public class ExcelGenerator {
	private final String filePath;
	private String fileName;
	private DatabaseGenerator database;
	private DatabaseQuery dataQuery;

	public ExcelGenerator(String fileName) {
		this.filePath = "./src/main/resuorces";
		this.fileName = fileName;
		this.database = new DatabaseGenerator();
		this.dataQuery = new DatabaseQuery(database);
	}

	public void generateExcel() {
		String[] columns = { "Cliente", "Nombre", "Apellido", "Habitacion", "Categoria", "Precio", "Reserva", "Total" };

	}

	private void writeExcel(String data) {
		
	}
}