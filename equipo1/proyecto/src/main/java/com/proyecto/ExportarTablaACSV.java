package com.proyecto;

import java.io.FileWriter;
import java.io.IOException;

public class ExportarTablaACSV {
    public static void reportar(String destExcel) {
        String archivoCSV = "TablaExportada.csv";

        String[][] datos = {
            {"Cliente", "Habitación", "Fecha de Reserva"},
            {"Juan Pérez", "101", "2025-01-10"},
            {"Ana López", "202", "2025-01-12"}
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
