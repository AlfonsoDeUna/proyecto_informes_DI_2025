package excelGenerator;

public class ExcelGenerator {
	private final String filePath;
	private String fileName;
	
	public ExcelGenerator(String fileName) {
		this.filePath = "./src/main/resuorces";
		this.fileName = fileName;
	}
	
	
	public void generateExcel() {
		String[] columns = {"ID", "Nombre", "Apellido", "Edad"};
        Object[][] data = {
            
        };
		
	}
	
	private void writeExcel() {
		
	}
	
	
}
