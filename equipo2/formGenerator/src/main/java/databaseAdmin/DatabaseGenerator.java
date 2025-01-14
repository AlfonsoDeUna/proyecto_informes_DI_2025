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

            insertarDatosIniciales();

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

    private void insertarDatosIniciales() {
        DatabaseInsert databaseInsert = new DatabaseInsert(this);

        databaseInsert.insertClient("Paul", "Alonso", "+34 675 852 761", "pAlonso12999@gamil.es");
        databaseInsert.insertClient("Alfonso", "Martinez", "+34 671 489 525", "aMartinez33@gmail.com");

        databaseInsert.insertRoom(101, "Sencilla", 50.0);
        databaseInsert.insertRoom(102, "Doble", 80.0);

        databaseInsert.insertReservation(1, 101, "2023-01-01", "2023-01-03", 100.0);
        databaseInsert.insertReservation(2, 102, "2023-01-05", "2023-01-07", 160.0);
    }
}