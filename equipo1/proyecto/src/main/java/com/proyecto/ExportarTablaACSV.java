package com.proyecto;

import java.io.FileWriter;
import java.io.IOException;

public class ExportarTablaACSV {
    public static void reportar(String destExcel) {
        String archivoCSV = "TablaExportada.csv";

        String[][] datos = {
            {"ID", "ID_Cliente", "ID_Habitación", "Fecha_Inicio", "Fecha_Fin", "Total"},
            {"1", "3", "5", "11-5-2024", "20-6-2024", "156$"},
            {"7", "5", "69", "12-1-2003", "28-2-2003", "462$"},
            {"12", "50", "13", "15-10-2025", "31-10-2025", "709$"},
    };
    
        // Generar archivo CSV
        try (FileWriter writer = new FileWriter(archivoCSV)) {
            for (String[] fila : datos) {
                // Usar punto y coma (;) como delimitador
                writer.append(String.join(";", fila));
                writer.append("\n");
            }
            System.out.println("Archivo CSV generado exitosamente: " + archivoCSV);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
