package com.proyecto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class GenerarCSVDesdeSQL {

    public static void generateCSV(String archivoSQL) {
        try (BufferedReader lector = new BufferedReader(new FileReader(archivoSQL))) {
            String linea;

            // Configuración para múltiples tablas
            String[] tablas = {"Clientes", "Habitaciones", "Reservas"};
            FileWriter[] escritores = new FileWriter[tablas.length];

            // Inicializar los archivos CSV con encabezados
            for (int i = 0; i < tablas.length; i++) {
                escritores[i] = new FileWriter(tablas[i] + ".csv");
                switch (tablas[i]) {
                    case "Clientes":
                        escritores[i].append("Nombre;Apellido;Telefono;Email\n");
                        break;
                    case "Habitaciones":
                        escritores[i].append("Numero;Tipo;Precio\n");
                        break;
                    case "Reservas":
                        escritores[i].append("ID_Cliente;ID_Habitacion;Fecha_Inicio;Fecha_Fin;Total\n");
                        break;
                }
            }

            // Leer el archivo SQL
            while ((linea = lector.readLine()) != null) {
                // Ignorar comentarios y líneas vacías
                if (linea.trim().isEmpty() || linea.startsWith("--")) continue;

                // Procesar líneas que contienen "INSERT INTO"
                if (linea.startsWith("INSERT INTO")) {
                    // Determinar la tabla
                    String tabla = linea.split(" ")[2];
                    int index = -1;

                    for (int i = 0; i < tablas.length; i++) {
                        if (tabla.equals(tablas[i])) {
                            index = i;
                            break;
                        }
                    }

                    if (index == -1) continue; // Tabla no relevante

                    // Extraer la parte de los valores
                    int valoresInicio = linea.indexOf("VALUES (") + 7;
                    String valores = linea.substring(valoresInicio, linea.lastIndexOf(");"));

                    // Dividir los valores en registros individuales
                    String[] registros = valores.split("\\),\\(");

                    for (String registro : registros) {
                        // Limpiar y dividir cada registro en campos
                        registro = registro.replace("(", "").replace(")", "").replace("'", "");
                        String[] campos = registro.split(",\\s*");

                        // Escribir los campos al archivo CSV
                        escritores[index].append(String.join(";", campos));
                        escritores[index].append("\n");
                    }
                }
            }

            // Cerrar los escritores
            for (FileWriter escritor : escritores) {
                escritor.close();
            }

            System.out.println("Archivos CSV generados exitosamente.");

        } catch (IOException e) {
            System.err.println("Error al procesar el archivo: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        String archivoSQL = "C:\\Users\\thepi\\Desktop\\GithubProject\\proyecto_informes_DI_2025\\equipo1\\hotel.sql";  // Ruta al archivo SQL
        generateCSV(archivoSQL);
    }
}
