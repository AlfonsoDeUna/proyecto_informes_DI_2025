package databaseAdmin;

import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseQuery {

    private DatabaseGenerator database;

    public DatabaseQuery(DatabaseGenerator database) {
        this.database = database;
    }

    // Método para obtener clientes
    public List<String> getClients() {
        List<String> list = new ArrayList<>();
        try (Connection conn = database.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Clientes")) {

            while (rs.next()) {
                String client = "ID: " + rs.getInt("ID") +
                                ", Nombre: " + rs.getString("Nombre") +
                                ", Apellido: " + rs.getString("Apellido") +
                                ", Teléfono: " + rs.getString("Telefono") +
                                ", Email: " + rs.getString("Email");
                list.add(client);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // Método para obtener habitaciones
    public List<String> getRooms() {
        List<String> list = new ArrayList<>();
        try (Connection conn = database.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Habitaciones")) {

            while (rs.next()) {
                String room = "ID: " + rs.getInt("ID") +
                              ", Número: " + rs.getInt("Numero") +
                              ", Tipo: " + rs.getString("Tipo") +
                              ", Precio: " + rs.getDouble("Precio");
                list.add(room);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // Método para obtener reservas
    public List<String> getBooks() {
        List<String> list = new ArrayList<>();
        try (Connection conn = database.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Reservas")) {

            while (rs.next()) {
                String book = "ID: " + rs.getInt("ID") +
                              ", ID Cliente: " + rs.getInt("ID_Cliente") +
                              ", ID Habitación: " + rs.getInt("ID_Habitación") +
                              ", Fecha Inicio: " + rs.getString("Fecha_Inicio") +
                              ", Fecha Fin: " + rs.getString("Fecha_Fin") +
                              ", Total: " + rs.getDouble("Total");
                list.add(book);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}