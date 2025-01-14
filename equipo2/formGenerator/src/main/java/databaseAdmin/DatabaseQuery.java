package databaseAdmin;

import java.util.ArrayList;
import java.util.List;

import dataClases.Book;
import dataClases.Client;
import dataClases.Room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseQuery {

    private DatabaseGenerator database;

    public DatabaseQuery(DatabaseGenerator database) {
        this.database = database;
    }

    // Método para obtener clientes
    public List<Client> getClients() {
        List<Client> list = new ArrayList<Client>();
        try (Connection conn = database.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Clientes")) {

            while (rs.next()) {
                Client client = new Client(rs.getInt("ID"),rs.getString("Nombre"),rs.getString("Apellido"),rs.getString("Telefono"),rs.getString("Email"));
                list.add(client);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // Método para obtener habitaciones
    public List<Room> getRooms() {
        List<Room> list = new ArrayList<Room>();
        try (Connection conn = database.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Habitaciones")) {

            while (rs.next()) {
                Room room = new Room(rs.getInt("ID"), rs.getInt("Numero"), rs.getString("Tipo"), rs.getDouble("Precio"));
                list.add(room);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // Método para obtener reservas
    public List<Book> getBooks() {
        List<Book> list = new ArrayList<Book>();
        try (Connection conn = database.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Reservas")) {

            while (rs.next()) {
                Book book = new Book(rs.getInt("ID"),rs.getInt("ID_Cliente"),rs.getInt("ID_Habitación"),rs.getString("Fecha_Inicio"), rs.getString("Fecha_Fin"), rs.getDouble("Total"));
                list.add(book);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public List<Book> getBookByClient(int clientID) {
        List<Book> list = new ArrayList<>();
        String sql = "SELECT * FROM Reservas WHERE ID_Cliente = ?";

        try (Connection conn = database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, clientID);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("ID");
                int idCliente = rs.getInt("ID_Cliente");
                int idHabitacion = rs.getInt("ID_Habitación");
                String fechaInicio = rs.getString("Fecha_Inicio");
                String fechaFin = rs.getString("Fecha_Fin");
                double total = rs.getDouble("Total");

                Book book = new Book(id, idCliente, idHabitacion, fechaInicio, fechaFin, total);
                list.add(book);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}