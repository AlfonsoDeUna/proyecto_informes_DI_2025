package databaseAdmin;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
            	    "ID_Habitacion INTEGER," +
            	    "Fecha_Inicio TEXT NOT NULL," +
            	    "Fecha_Fin TEXT NOT NULL," +
            	    "Total DOUBLE NOT NULL," +
            	    "FOREIGN KEY (ID_Cliente) REFERENCES Clientes(ID)," +
            	    "FOREIGN KEY (ID_Habitacion) REFERENCES Habitaciones(Numero));";

            stmt.executeUpdate(tbClientes);
            stmt.executeUpdate(tbHabitaciones);
            stmt.executeUpdate(tbReservas);

            insertarDatosIniciales();
            c.commit();
            
            System.out.println("Tablas creadas con éxito y datos iniciales insertados.");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            e.printStackTrace();
        }
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
    
    public int countColumns(String tableName) {
        int columnCount = 0;
        try {
            String query = "PRAGMA table_info(" + tableName + ");";
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                columnCount++;
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return columnCount;
    }
    
    public List<String> getTableNames() {
        List<String> tableNames = new ArrayList<>();
        try {
            String query = "SELECT name FROM sqlite_master WHERE type='table' AND name NOT LIKE 'sqlite_%';";
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                tableNames.add(rs.getString("name"));
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tableNames;
    }

    private void insertarDatosIniciales() {
        DatabaseInsert databaseInsert = new DatabaseInsert(this);

        databaseInsert.insertClient("Paul", "Alonso", "+34 675 852 761", "pAlonso12999@gamil.es");
        databaseInsert.insertClient("Alfonso", "Martinez", "+34 671 489 525", "aMartinez33@gmail.com");
        databaseInsert.insertClient("Mario", "Gimenez", "+34 648 456 154", "mGimenez21@gmail.com");
        databaseInsert.insertClient("Angel", "Ramirez", "+34 625 782 894", "aRamirez98@gmail.com");

        databaseInsert.insertRoom(101, "Sencilla", 50.0);
        databaseInsert.insertRoom(102, "Doble", 80.0);
        databaseInsert.insertRoom(105, "Sencilla", 50.0);
        databaseInsert.insertRoom(106, "Doble", 80.0);

        databaseInsert.insertReservation(1, 101, "2023-01-01", "2023-01-03", 100.0);
        databaseInsert.insertReservation(2, 102, "2023-01-05", "2023-01-07", 160.0);
        databaseInsert.insertReservation(3, 105, "2023-01-06", "2023-01-09", 110.0);
        databaseInsert.insertReservation(4, 106, "2023-01-09", "2023-01-12", 190.0);
    }
}