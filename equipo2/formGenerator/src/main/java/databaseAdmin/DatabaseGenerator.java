package databaseAdmin;

import java.sql.*;

public class DatabaseGenerator {
    private Connection c = null;
    private Statement stmt = null;

    public void crearTabla() {
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:motel.db");

            stmt = c.createStatement();

            String tbClientes = "CREATE TABLE IF NOT EXISTS Clientes (" +
                    "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "Nombre TEXT NOT NULL," +
                    "Apellido TEXT NOT NULL," +
                    "Telefono TEXT," +
                    "Email TEXT);";

            String tbHabitaciones = "CREATE TABLE IF NOT EXISTS Habitaciones (" +
                    "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "Numero INTEGER NOT NULL," +
                    "Tipo TEXT NOT NULL," +
                    "Precio DOUBLE NOT NULL);";

            String tbReservas = "CREATE TABLE IF NOT EXISTS Reservas (" +
                    "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "ID_Cliente INTEGER," +
                    "ID_Habitación INTEGER," +
                    "Fecha_Inicio TEXT NOT NULL," +
                    "Fecha_Fin TEXT NOT NULL," +
                    "Total DOUBLE NOT NULL," +
                    "FOREIGN KEY (ID_Cliente) REFERENCES Clientes(ID)," +
                    "FOREIGN KEY (ID_Habitación) REFERENCES Habitaciones(ID));";

            stmt.executeUpdate(tbClientes);
            stmt.executeUpdate(tbHabitaciones);
            stmt.executeUpdate(tbReservas);

            stmt.close();
            c.commit();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully");
    }

    public void cerrarBBDD() {
        try {
            if (c != null) {
                c.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        try {
            if (c == null || c.isClosed()) {
                c = DriverManager.getConnection("jdbc:sqlite:motel.db");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return c;
    }
}