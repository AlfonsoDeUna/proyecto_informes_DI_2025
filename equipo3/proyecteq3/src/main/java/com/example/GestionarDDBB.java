// package com.example;

// import java.sql.*;

// public class BBDD {
//     private Connection c = null;
//     private Statement stmt = null;

//     public void crearTabla() {
//         try {
//             Class.forName("org.sqlite.JDBC");
//             c = DriverManager.getConnection("jdbc:sqlite:hotel.db");
//             stmt = c.createStatement();

//             // Crear la tabla clientes
//             System.out.println("Creando la tabla Clientes...");
//             String sqlClientes = "CREATE TABLE IF NOT EXISTS Clientes (" +
//                     "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
//                     "Nombre TEXT NOT NULL, " +
//                     "Apellido TEXT NOT NULL, " +
//                     "Telefono TEXT, " +
//                     "Email TEXT)";
//             stmt.executeUpdate(sqlClientes);
//             System.out.println("Tabla clientes creada o ya existia");

//             // Crear la tabla habitaciones
//             System.out.println("Creando la tabla Habitaciones...");
//             String sqlHabitaciones = "CREATE TABLE IF NOT EXISTS Habitaciones (" +
//                     "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
//                     "Numero INTEGER NOT NULL, " +
//                     "Tipo TEXT NOT NULL, " +
//                     "Precio REAL NOT NULL)";
//             stmt.executeUpdate(sqlHabitaciones);
//             System.out.println("Tabla habitaciones creada o ya existia");

//             // Crear la tabla Reservas
//             System.out.println("Creando la tabla Reservas...");
//             String sqlReservas = "CREATE TABLE IF NOT EXISTS Reservas (" +
//                     "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
//                     "ID_Cliente INTEGER, " +
//                     "ID_Habitación INTEGER, " +
//                     "Fecha_Inicio DATE NOT NULL, " +
//                     "Fecha_Fin DATE NOT NULL, " +
//                     "Total REAL NOT NULL, " +
//                     "FOREIGN KEY (ID_Cliente) REFERENCES Clientes(ID), " +
//                     "FOREIGN KEY (ID_Habitación) REFERENCES Habitaciones(ID))";
//             stmt.executeUpdate(sqlReservas);
//             System.out.println("Tabla reservas creada o ya existia");

//             // Insertar datos iniciales en Clientes si está vacío
//             ResultSet rsClientes = stmt.executeQuery("SELECT COUNT(*) AS count FROM Clientes;");
//             rsClientes.next();
//             if (rsClientes.getInt("count") == 0) {
//                 String insertClientes = "INSERT INTO Clientes (Nombre, Apellido, Telefono, Email) VALUES " +
//                         "('Ruben', 'Hernández', '174826395', 'ruben.hernandez@gmail.com'), " +
//                         "('Sergio', 'Rodríguez', '839274615', 'sergio.rodriguez@hotmail.com'), " +
//                         "('Beni', 'Gómez', '526739841', 'beni.gomez@gmail.com'), " +
//                         "('Arantxa', 'Fernández', '912365478', 'arantxa.fernandez@hotmail.com'), " +
//                         "('Alfonso', 'Morales', '647283915', 'alfonso.morales@gmail.com');";

//                 stmt.executeUpdate(insertClientes);
//             }

//             // Insertar datos iniciales en Habitaciones si está vacío
//             ResultSet rsHabitaciones = stmt.executeQuery("SELECT COUNT(*) AS count FROM Habitaciones;");
//             rsHabitaciones.next();
//             if (rsHabitaciones.getInt("count") == 0) {
//                 String insertHabitaciones = "INSERT INTO Habitaciones (ID, Numero, Tipo, Precio) VALUES " +
//                         "(1, 101, 'Individual', 50.0), " +
//                         "(2, 102, 'Doble', 75.0), " +
//                         "(3, 103, 'Suite', 120.0), " +
//                         "(4, 104, 'Triple', 90.0), " +
//                         "(5, 105, 'Doble', 80.0);";
//                 stmt.executeUpdate(insertHabitaciones);
//             }

//             // Insertar datos iniciales en Reservas si está vacío
//             ResultSet rsReservas = stmt.executeQuery("SELECT COUNT(*) AS count FROM Reservas;");
//             rsReservas.next();
//             if (rsReservas.getInt("count") == 0) {
//                 String insertReservas = "INSERT INTO Reservas (ID_Cliente, ID_Habitación, Fecha_Inicio, Fecha_Fin, Total) VALUES "
//                         +
//                         "(1, 4, '2025-01-01', '2025-01-05', 200.0), " +
//                         "(2, 5, '2025-01-10', '2025-01-15', 375.0), " +
//                         "(3, 1, '2025-01-20', '2025-01-25', 600.0), " +
//                         "(4, 3, '2025-02-01', '2025-02-05', 450.0), " +
//                         "(5, 2, '2025-02-10', '2025-02-15', 400.0);";
//                 stmt.executeUpdate(insertReservas);
//             }
//             System.out.println("Tablas y datos inicializados correctamente.");
//         } catch (Exception e) {
//             System.err.println(e.getClass().getName() + ": " + e.getMessage());
//             System.exit(0);
//         }
//     }

//     public void cerrarBBDD() {
//         try {
//             if (stmt != null)
//                 stmt.close();
//             if (c != null)
//                 c.close();
//         } catch (SQLException e) {
//             e.printStackTrace();
//         }
//     }
// }