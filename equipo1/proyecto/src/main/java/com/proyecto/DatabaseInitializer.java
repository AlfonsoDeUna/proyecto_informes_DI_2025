package com.proyecto;

import java.sql.Connection;
import java.sql.Statement;

public class DatabaseInitializer {
    public static void initializeDatabase() {
        try (Connection conn = DataBaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            // Tabla Clientes
            String createClientes = "CREATE TABLE IF NOT EXISTS Clientes ("
                    + "ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "Nombre TEXT NOT NULL, "
                    + "Apellido TEXT NOT NULL, "
                    + "Telefono TEXT, "
                    + "Email TEXT);";

            // Tabla Habitaciones
            String createHabitaciones = "CREATE TABLE IF NOT EXISTS Habitaciones ("
                    + "ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "Numero INTEGER NOT NULL, "
                    + "Tipo TEXT NOT NULL, "
                    + "Precio REAL NOT NULL);";

            // Tabla Reservas
            String createReservas = "CREATE TABLE IF NOT EXISTS Reservas ("
                    + "ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "ID_Cliente INTEGER, "
                    + "ID_Habitacion INTEGER, "
                    + "Fecha_Inicio DATE NOT NULL, "
                    + "Fecha_Fin DATE NOT NULL, "
                    + "Total REAL NOT NULL, "
                    + "FOREIGN KEY (ID_Cliente) REFERENCES Clientes(ID), "
                    + "FOREIGN KEY (ID_Habitacion) REFERENCES Habitaciones(ID));";

            // Ejecutar las sentencias SQL
            stmt.execute(createClientes);
            stmt.execute(createHabitaciones);
            stmt.execute(createReservas);

            System.out.println("Base de datos inicializada correctamente.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
