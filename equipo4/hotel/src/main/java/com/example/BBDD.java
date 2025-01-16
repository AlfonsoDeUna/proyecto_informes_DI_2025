package com.example;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class BBDD {
    private Connection c = null;
    private Statement stmt = null;

    public void crearTabla() {
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:Hotel.db");
            stmt = c.createStatement(); // Initialize the statement object

            // Create the Habitaciones table if it doesn't exist
            String sql = "CREATE TABLE IF NOT EXISTS Habitaciones " +
                         "(ID INT PRIMARY KEY NOT NULL," +
                         " Numero TEXT NOT NULL, " +
                         " Tipo TEXT NOT NULL, " +
                         " Precio REAL NOT NULL)";
            stmt.executeUpdate(sql);

            System.out.println("Opened database successfully");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    public List<String> getElements() {
        List<String> list = new ArrayList<>();
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM Habitaciones;");

            while (rs.next()) {
                list.add(rs.getString("ID"));
                list.add(rs.getString("Numero"));
                list.add(rs.getString("Tipo"));
                list.add(rs.getString("Precio"));
            }

            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void cerrarBBDD() {
        try {
            if (stmt != null) stmt.close();
            if (c != null) c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}