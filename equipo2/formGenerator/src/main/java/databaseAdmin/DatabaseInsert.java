package databaseAdmin;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class DatabaseInsert {

    private DatabaseGenerator database;

    public DatabaseInsert(DatabaseGenerator database) {
        this.database = database;
    }

    // Insertar datos en la tabla Clientes
    public void insertClient(String nombre, String apellido, String telefono, String email) {
        String sql = "INSERT INTO Clientes (Nombre, Apellido, Telefono, Email) VALUES (?, ?, ?, ?)";
        try (Connection conn = database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nombre);
            pstmt.setString(2, apellido);
            pstmt.setString(3, telefono);
            pstmt.setString(4, email);
            pstmt.executeUpdate();

            System.out.println("Cliente insertado correctamente.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Insertar datos en la tabla Habitaciones
    public void insertRoom(int numero, String tipo, double precio) {
        String sql = "INSERT INTO Habitaciones (Numero, Tipo, Precio) VALUES (?, ?, ?)";
        try (Connection conn = database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, numero);
            pstmt.setString(2, tipo);
            pstmt.setDouble(3, precio);
            pstmt.executeUpdate();

            System.out.println("Habitación insertada correctamente.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Insertar datos en la tabla Reservas
    public void insertReservation(int idCliente, int idHabitacion, String fechaInicio, String fechaFin, double total) {
        String sql = "INSERT INTO Reservas (ID_Cliente, ID_Habitación, Fecha_Inicio, Fecha_Fin, Total) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idCliente);
            pstmt.setInt(2, idHabitacion);
            pstmt.setString(3, fechaInicio);
            pstmt.setString(4, fechaFin);
            pstmt.setDouble(5, total);
            pstmt.executeUpdate();

            System.out.println("Reserva insertada correctamente.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}