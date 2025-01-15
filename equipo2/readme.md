# Proyecto

## Intro

## Nombres del equipo
Dario Arroyo Vacas | David Casado Rodríguez

## Documentación del proyecto

### Hechas por David
Quitame los inserts de la clase DatabaseGenerator y ponmelos segun ya esta hecho en la clase DatabaseInsert

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
       
       tbClientes = "INSERT INTO Clientes (Nombre,Apellido,Telefono,Email) " +
                      "VALUES ('Paul', 'Alonso', '+34 675 852 761', 'pAlonso12999@gamil.es');"; 
       stmt.executeUpdate(tbClientes);
       
       tbClientes = "INSERT INTO Clientes (Nombre,Apellido,Telefono,Email) " +
                      "VALUES ('Alfonso', 'Martinez', '+34 671 489 525', 'aMartinez33@gmail.com');"; 
       stmt.executeUpdate(tbClientes);
       
//       tbHabitaciones = "INSERT INTO COMPANY (ID,NAME) " +
//    		   "VALUES (1, 'Paul');"; 
//       stmt.executeUpdate(tbHabitaciones);
//       
//       tbHabitaciones = "INSERT INTO COMPANY (ID,NAME) " +
//    		   "VALUES (2, 'Alfonso');"; 
//       stmt.executeUpdate(tbHabitaciones);
//       
//       tbReservas = "INSERT INTO COMPANY (ID,NAME) " +
//    		   "VALUES (1, 'Paul');"; 
//       stmt.executeUpdate(tbReservas);
//       
//       tbReservas = "INSERT INTO COMPANY (ID,NAME) " +
//    		   "VALUES (2, 'Alfonso');"; 
//       stmt.executeUpdate(tbReservas);
       
       stmt.close();
       c.commit();
      

    } catch ( Exception e ) {
       System.err.println( e.getClass().getName() + ": " + e.getMessage() );
       System.exit(0);
    }
        System.out.println("Opened database successfully");
   } 

   public void cerrarBBDD() {
    try {
        c.close();
    } catch (SQLException e) {
        
        e.printStackTrace();
    }
   }
}

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

ChatGPT Plus
ChatGPT
Aquí tienes la clase DatabaseGenerator modificada para que solo se encargue de crear las tablas, eliminando los INSERT que ahora estarán gestionados por la clase DatabaseInsert. También he añadido un método getConnection en DatabaseGenerator para que funcione correctamente con DatabaseInsert.

Database Refactor
Ahora los métodos insertClient, insertRoom y insertReservation en DatabaseInsert son responsables de insertar datos en la base de datos. También se agregó el método getConnection en DatabaseGenerator para proporcionar la conexión a la base de datos cuando sea necesario.

¿Hay algo más que te gustaría ajustar o mejorar?





Tú dijiste:
Quiero llamar a la clase para instertar los datos desde la clase DatabaseGenerator
ChatGPT Plus
ChatGPT
Editado
He añadido un método privado insertarDatosIniciales a la clase DatabaseGenerator. Este método utiliza la clase DatabaseInsert para insertar datos iniciales en las tablas, y lo he llamado al final del método crearTabla.

¿Hay algo más que te gustaría modificar o agregar?





Tú dijiste:
No soy capaz de que me devuelva el numero de columnas que tiene una tabla en concreto

private int countColumns(String op) {
        try {
        	String count = "SHOW COLUMNS FROM " + op;
        	stmt = c.createStatement();
			ResultSet data = (int) stmt.executeQuery(count);
			return data;
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return 0;
    }
ChatGPT Plus
ChatGPT
El método SHOW COLUMNS FROM no es compatible con SQLite, que es el motor de base de datos que estás utilizando. Para obtener el número de columnas de una tabla en SQLite, puedes usar el método PRAGMA table_info para obtener la estructura de la tabla y contar el número de columnas en el ResultSet.

A continuación, te muestro cómo corregir tu método countColumns:

java
Copiar código
private int countColumns(String tableName) {
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
Detalles del código:
PRAGMA table_info(tableName): Devuelve la estructura de la tabla especificada.
Contador: Iteramos sobre el ResultSet para contar cuántas filas hay, ya que cada fila representa una columna de la tabla.
Cerrar recursos: Se cierra el ResultSet y el Statement después de usarlos para evitar fugas de recursos.
Uso:
Llama a este método pasando el nombre de la tabla, por ejemplo:

java
Copiar código
int columnas = countColumns("Clientes");
System.out.println("La tabla Clientes tiene " + columnas + " columnas.");
¿Esto resuelve tu problema? 😊





Tú dijiste:
Me podrias completar el metodo getTableNames() para que me devuelva el nombre de las tablas que haya en la base de datos

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
    
    public String getTableNames() {
    	
    	return null;
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
ChatGPT Plus
ChatGPT
Database Refactor
1
2
3
4
5
6
7
8
9
10
11
12
13
14
15
16
17
18
19
20
21
22
23
24
25
26
27
28
29
30
31
32
33
34
35
36
37
38
39
40
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
                    "ID_Habitación INTEGER," +
                    "Fecha_Inicio TEXT NOT NULL," +
                    "Fecha_Fin TEXT NOT NULL," +
                    "Total DOUBLE NOT NULL," +
                    "FOREIGN KEY (ID_Cliente) REFERENCES Clientes(ID)," +
                    "FOREIGN KEY (ID_Habitación) REFERENCES Habitaciones(ID));";

Editado
El método getTableNames ahora está implementado para devolver una lista de nombres de tablas presentes en la base de datos. Este método utiliza la consulta SELECT name FROM sqlite_master para obtener los nombres de las tablas, excluyendo las internas del sistema SQLite (sqlite_%).

Puedes llamarlo de la siguiente manera:

java
Copiar código
DatabaseGenerator dbGen = new DatabaseGenerator();
dbGen.crearTabla();
List<String> tableNames = dbGen.getTableNames();
System.out.println("Tablas en la base de datos: " + tableNames);
¿Hay algo más que quieras ajustar? 😊

Al insertar los datos me devuelve solo las direcciones de memoria de los datos

package pdfGenerator;

import java.util.List;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;

import databaseAdmin.DatabaseGenerator;
import databaseAdmin.DatabaseQuery;

public class PDFGenerator {
	
	private static DatabaseGenerator dg = new DatabaseGenerator();
	private static DatabaseQuery dq = new DatabaseQuery(dg);
	
	public void execute() {
		String pdfName = "Reporte_Motel.pdf";
		
        try {
            PdfWriter writer = new PdfWriter(pdfName);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            document.add(new Paragraph("Reporte de reservas Motel"));

            generateTables(document);
            
            document.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	private static void generateTables(Document document) {
		List<String> nameTable = dg.getTableNames();
		for(int i = 0; i < nameTable.size(); i++) {
			String actualName = nameTable.get(i);

			document.add(new Paragraph(actualName));
			int numColumns = dg.countColumns(actualName);
			
			Table table = new Table(numColumns);
			generateCells(table, actualName);
			document.add(table);
		}
	}
	
	private static void generateCells(Table table, String actualName) {
		List<?> content = null;
		
		switch (actualName) {
			case "Clientes":
				content = dq.getClients();
				break;
				
			case "Habitaciones":
				content = dq.getRooms();
				break;
				
			case "Reservas":
				content = dq.getBooks();
				break;
		}
		
		for(int i = 0; i < content.size(); i++) {
			table.addCell(content.get(i).toString());
		}
	}
}

package dataClases;

public class Book {

    private int ID;
    private int idCliente;
    private int idHabitacion;
    private String fechaInicio;
    private String fechaFin;
    private double total;

    public Book(int ID, int idCliente, int idHabitacion, String fechaInicio, String fechaFin, double total) {
        this.ID = ID;
        this.idCliente = idCliente;
        this.idHabitacion = idHabitacion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.total = total;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdHabitacion() {
        return idHabitacion;
    }

    public void setIdHabitacion(int idHabitacion) {
        this.idHabitacion = idHabitacion;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}

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
                Book book = new Book(rs.getInt("ID"),rs.getInt("ID_Cliente"),rs.getInt("ID_Habitacion"),rs.getString("Fecha_Inicio"), rs.getString("Fecha_Fin"), rs.getDouble("Total"));
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
                int idHabitacion = rs.getInt("ID_Habitacion");
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
    
    public double getRoomPrice(int roomID) {
        double price = -1;
        String sql = "SELECT Precio FROM Habitaciones WHERE Numero = ?";

        try (Connection conn = database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, roomID);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                price = rs.getDouble("precio");
            } else {
                System.out.println("No se encontró una habitación con ID: " + roomID);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return price;
    }

    
    public String getRoomType(int roomNumber) {
        String roomType = null;
        String sql = "SELECT Tipo FROM Habitaciones WHERE Numero = ?";

        try (Connection conn = database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, roomNumber);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                roomType = rs.getString("Tipo");
            } else {
                System.out.println("No se encontró una habitación con número: " + roomNumber);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return roomType;
    }



}
ChatGPT Plus
ChatGPT
El problema de que solo se muestren las direcciones de memoria en lugar del contenido de los datos se debe a que estás utilizando el método toString() de los objetos en la lista, pero las clases como Book, Client y Room no tienen un método toString() sobrescrito. Por defecto, el método toString() de la clase Object devuelve una representación que incluye la dirección de memoria del objeto, lo cual no es útil en este caso.

Solución:
Debes sobrescribir el método toString() en cada una de las clases (Book, Client y Room) para proporcionar una representación significativa de los datos de esos objetos. Aquí tienes un ejemplo de cómo hacerlo:

Clase Book:
java
Copiar código
@Override
public String toString() {
    return "ID: " + ID +
           ", ID Cliente: " + idCliente +
           ", ID Habitación: " + idHabitacion +
           ", Fecha Inicio: " + fechaInicio +
           ", Fecha Fin: " + fechaFin +
           ", Total: " + total;
}
Clase Client:
Supongamos que Client tiene los campos id, nombre, apellido, telefono y email:

java
Copiar código
@Override
public String toString() {
    return "ID: " + id +
           ", Nombre: " + nombre +
           ", Apellido: " + apellido +
           ", Teléfono: " + telefono +
           ", Email: " + email;
}
Clase Room:
Supongamos que Room tiene los campos id, numero, tipo y precio:

java
Copiar código
@Override
public String toString() {
    return "ID: " + id +
           ", Número: " + numero +
           ", Tipo: " + tipo +
           ", Precio: " + precio;
}
Pasos a seguir:
Sobrescribe el método toString() en cada clase de datos como se muestra arriba.
Cuando el método generateCells llame a content.get(i).toString(), ahora obtendrás una representación legible de los datos.
Ejemplo ajustado del método generateCells:
java
Copiar código
private static void generateCells(Table table, String actualName) {
    List<?> content = null;
    
    switch (actualName) {
        case "Clientes":
            content = dq.getClients();
            break;
        case "Habitaciones":
            content = dq.getRooms();
            break;
        case "Reservas":
            content = dq.getBooks();
            break;
    }
    
    for (int i = 0; i < content.size(); i++) {
        table.addCell(content.get(i).toString());
    }
}
Resultado esperado:
Cuando generes el PDF, las tablas contendrán datos legibles y descriptivos en lugar de las direcciones de memoria de los objetos.






Tú dijiste:
Me podiras poner que una celda fuera solo de id otra de nombre y asi.

Por cierto aqui te paso los data class

package dataClases;

public class Book {

    private int ID;
    private int idCliente;
    private int idHabitacion;
    private String fechaInicio;
    private String fechaFin;
    private double total;

    public Book(int ID, int idCliente, int idHabitacion, String fechaInicio, String fechaFin, double total) {
        this.ID = ID;
        this.idCliente = idCliente;
        this.idHabitacion = idHabitacion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.total = total;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdHabitacion() {
        return idHabitacion;
    }

    public void setIdHabitacion(int idHabitacion) {
        this.idHabitacion = idHabitacion;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
    @Override
    public String toString() {
        return "ID: " + ID +
               ", ID Cliente: " + idCliente +
               ", ID Habitación: " + idHabitacion +
               ", Fecha Inicio: " + fechaInicio +
               ", Fecha Fin: " + fechaFin +
               ", Total: " + total;
    }

}

package dataClases;

public class Client {
	
	private int ID;
	private String nombre;
	private String apellido;
	private String telefono;
	private String email;
	
	public Client(int ID, String nombre, String apellido, String telefono, String email) {
		
		this.ID = ID;
		this.nombre = nombre;
		this.apellido = apellido;
		this.telefono = telefono;
		this.email = email;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
	    return "ID: " + ID +
	           ", Nombre: " + nombre +
	           ", Apellido: " + apellido +
	           ", Teléfono: " + telefono +
	           ", Email: " + email;
	}

	
}

package dataClases;

public class Room {

    private int ID;
    private int numero;
    private String tipo;
    private double precio;

    public Room(int ID, int numero, String tipo, double precio) {
        this.ID = ID;
        this.numero = numero;
        this.tipo = tipo;
        this.precio = precio;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
    @Override
    public String toString() {
        return "ID: " + ID +
               ", Número: " + numero +
               ", Tipo: " + tipo +
               ", Precio: " + precio;
    }

}
ChatGPT Plus
ChatGPT
Si deseas que cada columna de la tabla represente un atributo diferente (por ejemplo, una columna para ID, otra para Nombre, etc.), puedes modificar el método generateCells para agregar celdas específicas según los atributos de las clases Book, Client y Room.

Modificación de generateCells
java
Copiar código
private static void generateCells(Table table, String actualName) {
    List<?> content = null;

    // Encabezados de la tabla
    switch (actualName) {
        case "Clientes":
            content = dq.getClients();
            table.addHeaderCell("ID");
            table.addHeaderCell("Nombre");
            table.addHeaderCell("Apellido");
            table.addHeaderCell("Teléfono");
            table.addHeaderCell("Email");
            break;

        case "Habitaciones":
            content = dq.getRooms();
            table.addHeaderCell("ID");
            table.addHeaderCell("Número");
            table.addHeaderCell("Tipo");
            table.addHeaderCell("Precio");
            break;

        case "Reservas":
            content = dq.getBooks();
            table.addHeaderCell("ID");
            table.addHeaderCell("ID Cliente");
            table.addHeaderCell("ID Habitación");
            table.addHeaderCell("Fecha Inicio");
            table.addHeaderCell("Fecha Fin");
            table.addHeaderCell("Total");
            break;
    }

    // Agregar datos a las celdas
    for (Object obj : content) {
        if (obj instanceof Client) {
            Client client = (Client) obj;
            table.addCell(String.valueOf(client.getID()));
            table.addCell(client.getNombre());
            table.addCell(client.getApellido());
            table.addCell(client.getTelefono());
            table.addCell(client.getEmail());
        } else if (obj instanceof Room) {
            Room room = (Room) obj;
            table.addCell(String.valueOf(room.getID()));
            table.addCell(String.valueOf(room.getNumero()));
            table.addCell(room.getTipo());
            table.addCell(String.valueOf(room.getPrecio()));
        } else if (obj instanceof Book) {
            Book book = (Book) obj;
            table.addCell(String.valueOf(book.getID()));
            table.addCell(String.valueOf(book.getIdCliente()));
            table.addCell(String.valueOf(book.getIdHabitacion()));
            table.addCell(book.getFechaInicio());
            table.addCell(book.getFechaFin());
            table.addCell(String.valueOf(book.getTotal()));
        }
    }
}
Explicación de los cambios:
Encabezados de las tablas:

Según el nombre de la tabla (actualName), se añaden celdas de encabezado con los nombres de las columnas correspondientes.
Iteración sobre los objetos:

Para cada objeto en content, se verifica su tipo (Client, Room o Book).
Se accede a los atributos relevantes utilizando los métodos get y se añaden como celdas de la tabla.
Compatibilidad con varios tipos de datos:

Cada caso (Clientes, Habitaciones, Reservas) maneja específicamente los atributos de su clase respectiva.
Resultado:
Para la tabla Clientes, las columnas serán ID, Nombre, Apellido, Teléfono, Email.
Para la tabla Habitaciones, las columnas serán ID, Número, Tipo, Precio.
Para la tabla Reservas, las columnas serán ID, ID Cliente, ID Habitación, Fecha Inicio, Fecha Fin, Total.
Ventajas:
Claridad: Cada columna de la tabla en el PDF tendrá un propósito claro.
Adaptabilidad: Es fácil añadir más clases o atributos si se expande la funcionalidad.


### Hechas por Dario

dame los codigos para hacer estas querys
CREATE TABLE Clientes (
    ID INTEGER PRIMARY KEY AUTOINCREMENT,
    Nombre TEXT NOT NULL,
    Apellido TEXT NOT NULL,
    Telefono TEXT,
    Email TEXT
);

CREATE TABLE Habitaciones (
    ID INTEGER PRIMARY KEY AUTOINCREMENT,
    Numero INTEGER NOT NULL,
    Tipo TEXT NOT NULL,
    Precio REAL NOT NULL
);

CREATE TABLE Reservas (
    ID INTEGER PRIMARY KEY AUTOINCREMENT,
    ID_Cliente INTEGER,
    ID_Habitación INTEGER,
    Fecha_Inicio DATE NOT NULL,
    Fecha_Fin DATE NOT NULL,
    Total REAL NOT NULL,
    FOREIGN KEY (ID_Cliente) REFERENCES Clientes(ID),
    FOREIGN KEY (ID_Habitación) REFERENCES Habitaciones(ID)
);
public class DatabaseQuery {
	
	private DatabaseGenerator database;
	
	public DatabaseQuery(DatabaseGenerator database) {
		this.database = database;
	}
	
	public List<String> getClients(){
		List<String> list = new ArrayList<String>();
		
		
		
		return list;
	}
	
	public List<String> getRooms(){
		List<String> list = new ArrayList<String>();
		
		
		return list;
	}
	
	public List<String> getBooks() {
		List<String> list = new ArrayList<String>();
		
		
		return list;
	}

}
ChatGPT Plus
ChatGPT

Aquí tienes las consultas SQL y el código Java correspondiente para realizar las operaciones necesarias en las tablas. Asegúrate de que el proyecto tenga una conexión adecuada a la base de datos.
Consultas SQL
Crear Tablas

CREATE TABLE Clientes (
    ID INTEGER PRIMARY KEY AUTOINCREMENT,
    Nombre TEXT NOT NULL,
    Apellido TEXT NOT NULL,
    Telefono TEXT,
    Email TEXT
);

CREATE TABLE Habitaciones (
    ID INTEGER PRIMARY KEY AUTOINCREMENT,
    Numero INTEGER NOT NULL,
    Tipo TEXT NOT NULL,
    Precio REAL NOT NULL
);

CREATE TABLE Reservas (
    ID INTEGER PRIMARY KEY AUTOINCREMENT,
    ID_Cliente INTEGER,
    ID_Habitación INTEGER,
    Fecha_Inicio DATE NOT NULL,
    Fecha_Fin DATE NOT NULL,
    Total REAL NOT NULL,
    FOREIGN KEY (ID_Cliente) REFERENCES Clientes(ID),
    FOREIGN KEY (ID_Habitación) REFERENCES Habitaciones(ID)
);

Código Java para consultas
Clase DatabaseQuery

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
Notas importantes

    Clases requeridas: Asegúrate de tener una clase DatabaseGenerator que maneje la conexión a la base de datos. Ejemplo básico:

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseGenerator {

    private String url;
    private String user;
    private String password;

    public DatabaseGenerator(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public Connection getConnection() throws Exception {
        return DriverManager.getConnection(url, user, password);
    }
}

Dependencias: Si usas SQLite, asegúrate de incluir su dependencia en tu proyecto. Por ejemplo, en un archivo Maven pom.xml:

    <dependency>
        <groupId>org.xerial</groupId>
        <artifactId>sqlite-jdbc</artifactId>
        <version>3.36.0.3</version>
    </dependency>

    Ejecución de SQL: Antes de usar los métodos en Java, asegúrate de haber ejecutado las consultas SQL para crear las tablas en tu base de datos.

¿Necesitas más ayuda con algún detalle? 😊
Tú dijiste:
dame un codigo para insertar datos en las tablas
ChatGPT Plus
ChatGPT

Aquí tienes un código Java que inserta datos en las tablas Clientes, Habitaciones y Reservas que definiste. Este ejemplo usa PreparedStatement para prevenir inyecciones SQL y mejorar la eficiencia.
Código Java para insertar datos
Clase DatabaseInsert

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

Uso del código
Ejemplo de inserción

public class Main {

    public static void main(String[] args) {
        // Configuración de la conexión a la base de datos
        String url = "jdbc:sqlite:mi_base_de_datos.db";
        DatabaseGenerator database = new DatabaseGenerator(url, null, null);

        // Crear instancia de la clase DatabaseInsert
        DatabaseInsert dbInsert = new DatabaseInsert(database);

        // Insertar clientes
        dbInsert.insertClient("Juan", "Pérez", "123456789", "juan.perez@example.com");
        dbInsert.insertClient("María", "López", "987654321", "maria.lopez@example.com");

        // Insertar habitaciones
        dbInsert.insertRoom(101, "Sencilla", 50.0);
        dbInsert.insertRoom(102, "Doble", 75.0);

        // Insertar reservas
        dbInsert.insertReservation(1, 101, "2025-01-20", "2025-01-25", 250.0);
        dbInsert.insertReservation(2, 102, "2025-01-22", "2025-01-28", 450.0);
    }
}

Notas importantes

    Fechas: En este ejemplo, las fechas (fechaInicio, fechaFin) están en formato YYYY-MM-DD. Asegúrate de que el formato sea compatible con tu base de datos.
    Control de errores: Se ha usado try-with-resources para cerrar automáticamente las conexiones y declaraciones. Esto ayuda a prevenir fugas de recursos.
    Dependencias: Si usas SQLite, asegúrate de incluir la dependencia en tu proyecto, como se explicó anteriormente.

Si necesitas más ejemplos o ayuda con alguna parte específica, ¡avísame! 😊
Tú dijiste:
dame una de estas para cada tabla
package dataClases;

public class Client {
	
	private int ID;
	private String nombre;
	private String apellido;
	private String telefono;
	private String email;
	
	public Client(int ID, String nombre, String apellido, String telefono, String email) {
		
		this.ID = ID;
		this.nombre = nombre;
		this.apellido = apellido;
		this.telefono = telefono;
		this.email = email;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
CREATE TABLE Clientes (
    ID INTEGER PRIMARY KEY AUTOINCREMENT,
    Nombre TEXT NOT NULL,
    Apellido TEXT NOT NULL,
    Telefono TEXT,
    Email TEXT
);

CREATE TABLE Habitaciones (
    ID INTEGER PRIMARY KEY AUTOINCREMENT,
    Numero INTEGER NOT NULL,
    Tipo TEXT NOT NULL,
    Precio REAL NOT NULL
);

CREATE TABLE Reservas (
    ID INTEGER PRIMARY KEY AUTOINCREMENT,
    ID_Cliente INTEGER,
    ID_Habitación INTEGER,
    Fecha_Inicio DATE NOT NULL,
    Fecha_Fin DATE NOT NULL,
    Total REAL NOT NULL,
    FOREIGN KEY (ID_Cliente) REFERENCES Clientes(ID),
    FOREIGN KEY (ID_Habitación) REFERENCES Habitaciones(ID)
);
ChatGPT Plus
ChatGPT

Aquí tienes las clases para cada tabla (Habitaciones y Reservas) siguiendo el mismo patrón que la clase Client:
Clase Room (para la tabla Habitaciones)

package dataClases;

public class Room {

    private int ID;
    private int numero;
    private String tipo;
    private double precio;

    public Room(int ID, int numero, String tipo, double precio) {
        this.ID = ID;
        this.numero = numero;
        this.tipo = tipo;
        this.precio = precio;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}

Clase Reservation (para la tabla Reservas)

package dataClases;

import java.util.Date;

public class Reservation {

    private int ID;
    private int idCliente;
    private int idHabitacion;
    private Date fechaInicio;
    private Date fechaFin;
    private double total;

    public Reservation(int ID, int idCliente, int idHabitacion, Date fechaInicio, Date fechaFin, double total) {
        this.ID = ID;
        this.idCliente = idCliente;
        this.idHabitacion = idHabitacion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.total = total;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdHabitacion() {
        return idHabitacion;
    }

    public void setIdHabitacion(int idHabitacion) {
        this.idHabitacion = idHabitacion;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}

Notas importantes:

    Tipo de datos Date en Java: Se usa java.util.Date para representar las fechas de inicio y fin. Si trabajas con java.time (recomendado en proyectos modernos), puedes usar LocalDate.
    Clases consistentes: Todas las clases siguen el mismo patrón con getters, setters y un constructor para inicializar todos los atributos.
    Encapsulamiento: Los atributos están privados y se acceden mediante métodos públicos para garantizar el encapsulamiento.

¿Te gustaría agregar métodos adicionales a las clases, como validaciones o representaciones en forma de cadena (toString)? 😊
Tú dijiste:
CREATE TABLE Clientes (
    ID INTEGER PRIMARY KEY AUTOINCREMENT,
    Nombre TEXT NOT NULL,
    Apellido TEXT NOT NULL,
    Telefono TEXT,
    Email TEXT
);

CREATE TABLE Habitaciones (
    ID INTEGER PRIMARY KEY AUTOINCREMENT,
    Numero INTEGER NOT NULL,
    Tipo TEXT NOT NULL,
    Precio REAL NOT NULL
);

CREATE TABLE Reservas (
    ID INTEGER PRIMARY KEY AUTOINCREMENT,
    ID_Cliente INTEGER,
    ID_Habitación INTEGER,
    Fecha_Inicio DATE NOT NULL,
    Fecha_Fin DATE NOT NULL,
    Total REAL NOT NULL,
    FOREIGN KEY (ID_Cliente) REFERENCES Clientes(ID),
    FOREIGN KEY (ID_Habitación) REFERENCES Habitaciones(ID)
);public List<Book> getBookByClient(int clientID) {
    	List<Book> list = new ArrayList<Book>();
    	
    	
    	return list;
    }
ChatGPT Plus
ChatGPT

Aquí tienes un método para obtener reservas (de la tabla Reservas) asociadas a un cliente específico usando su ID. También incluye la estructura necesaria para la clase Book (que parece representar las reservas) y el método completo:
Clase Book

Esta clase debe representar una reserva.

package dataClases;

import java.util.Date;

public class Book {

    private int ID;
    private int idCliente;
    private int idHabitacion;
    private Date fechaInicio;
    private Date fechaFin;
    private double total;

    public Book(int ID, int idCliente, int idHabitacion, Date fechaInicio, Date fechaFin, double total) {
        this.ID = ID;
        this.idCliente = idCliente;
        this.idHabitacion = idHabitacion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.total = total;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdHabitacion() {
        return idHabitacion;
    }

    public void setIdHabitacion(int idHabitacion) {
        this.idHabitacion = idHabitacion;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}

Método getBookByClient

Este método consulta las reservas asociadas a un cliente específico, utilizando su clientID:

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DatabaseQuery {

    private DatabaseGenerator database;

    public DatabaseQuery(DatabaseGenerator database) {
        this.database = database;
    }

    // Método para obtener reservas por cliente
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
                Date fechaInicio = rs.getDate("Fecha_Inicio");
                Date fechaFin = rs.getDate("Fecha_Fin");
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

Explicación del código

    Consulta SQL:

    SELECT * FROM Reservas WHERE ID_Cliente = ?

    Esto selecciona todas las reservas donde el ID_Cliente coincida con el clientID proporcionado.

    Uso de PreparedStatement:
        Protege contra inyecciones SQL.
        Permite parametrizar consultas.

    Iteración sobre el ResultSet:
        Extrae los datos de cada fila y los mapea a una instancia de la clase Book.

    Resultado:
        Devuelve una lista de objetos Book que representan las reservas asociadas al cliente.

Ejemplo de uso

public class Main {

    public static void main(String[] args) {
        String url = "jdbc:sqlite:mi_base_de_datos.db";
        DatabaseGenerator database = new DatabaseGenerator(url, null, null);

        DatabaseQuery query = new DatabaseQuery(database);

        // Obtener reservas del cliente con ID 1
        List<Book> books = query.getBookByClient(1);

        for (Book book : books) {
            System.out.println("Reserva ID: " + book.getID() +
                               ", ID Habitación: " + book.getIdHabitacion() +
                               ", Fecha Inicio: " + book.getFechaInicio() +
                               ", Fecha Fin: " + book.getFechaFin() +
                               ", Total: " + book.getTotal());
        }
    }
}

Notas importantes

    Formato de fechas: Se usa java.util.Date. Si usas LocalDate, tendrás que ajustar el código.
    Validaciones: Considera agregar validaciones en caso de que no se encuentren reservas para un cliente.
    Ejecución de la base de datos: Asegúrate de que la tabla Reservas tiene datos antes de probar el código.

¿Necesitas ayuda con otro método o funcionalidad? 😊
Tú dijiste:
package excelGenerator;

import databaseAdmin.DatabaseGenerator;
import databaseAdmin.DatabaseQuery;

public class ExcelGenerator {
	private final String filePath;
	private String fileName;
	private DatabaseGenerator database;
	private DatabaseQuery dataQuery;

	public ExcelGenerator(String fileName) {
		this.filePath = "./src/main/resuorces";
		this.fileName = fileName;
		this.database = new DatabaseGenerator();
		this.dataQuery = new DatabaseQuery(database);
	}

	public void generateExcel() {
		String[] columns = { "Reserva", "Cliente", "Nombre", "Apellido", "Habitacion", "Categoria", "Precio", "Reserva", "Fecha Inicio", "Fecha Fin", "Total" };
		
	}

	private void writeExcel(String data) {
		
	}
}
ChatGPT Plus
ChatGPT

Aquí tienes una implementación para la clase ExcelGenerator que genera un archivo Excel con los datos de las reservas, clientes y habitaciones usando la biblioteca Apache POI. Asegúrate de agregar la dependencia de Apache POI en tu archivo de configuración (pom.xml si usas Maven).
Dependencia Maven (Apache POI)

Si estás usando Maven, agrega esta dependencia a tu pom.xml:

<dependency>
    <groupId>org.apache.poi</groupId>
    <artifactId>poi-ooxml</artifactId>
    <version>5.2.3</version>
</dependency>

Implementación completa de la clase ExcelGenerator

package excelGenerator;

import databaseAdmin.DatabaseGenerator;
import databaseAdmin.DatabaseQuery;
import dataClases.Book;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelGenerator {
    private final String filePath;
    private String fileName;
    private DatabaseGenerator database;
    private DatabaseQuery dataQuery;

    public ExcelGenerator(String fileName) {
        this.filePath = "./src/main/resources";
        this.fileName = fileName;
        this.database = new DatabaseGenerator();
        this.dataQuery = new DatabaseQuery(database);
    }

    public void generateExcel() {
        String[] columns = {
            "Reserva ID", "Cliente ID", "Nombre", "Apellido", "Habitación ID",
            "Categoría", "Precio", "Reserva ID", "Fecha Inicio", "Fecha Fin", "Total"
        };

        // Obtener datos de reservas
        List<Book> reservations = dataQuery.getAllReservations(); // Este método debe ser implementado en DatabaseQuery

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Reservas");

            // Crear el encabezado
            Row headerRow = sheet.createRow(0);
            for (int i = 0; i < columns.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columns[i]);
                CellStyle style = workbook.createCellStyle();
                Font font = workbook.createFont();
                font.setBold(true);
                style.setFont(font);
                cell.setCellStyle(style);
            }

            // Escribir los datos de las reservas
            int rowNum = 1;
            for (Book book : reservations) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(book.getID());
                row.createCell(1).setCellValue(book.getIdCliente());
                row.createCell(2).setCellValue("NombreCliente"); // Reemplaza con los datos del cliente
                row.createCell(3).setCellValue("ApellidoCliente"); // Reemplaza con los datos del cliente
                row.createCell(4).setCellValue(book.getIdHabitacion());
                row.createCell(5).setCellValue("TipoHabitacion"); // Reemplaza con los datos de la habitación
                row.createCell(6).setCellValue(100.0); // Reemplaza con el precio de la habitación
                row.createCell(7).setCellValue(book.getID());
                row.createCell(8).setCellValue(book.getFechaInicio().toString());
                row.createCell(9).setCellValue(book.getFechaFin().toString());
                row.createCell(10).setCellValue(book.getTotal());
            }

            // Ajustar columnas al contenido
            for (int i = 0; i < columns.length; i++) {
                sheet.autoSizeColumn(i);
            }

            // Guardar el archivo
            try (FileOutputStream fileOut = new FileOutputStream(filePath + "/" + fileName)) {
                workbook.write(fileOut);
            }

            System.out.println("Archivo Excel generado correctamente en " + filePath + "/" + fileName);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

Método adicional en DatabaseQuery

Necesitas implementar un método en DatabaseQuery para obtener todas las reservas. Aquí tienes un ejemplo:

public List<Book> getAllReservations() {
    List<Book> list = new ArrayList<>();
    String sql = "SELECT * FROM Reservas";

    try (Connection conn = database.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql);
         ResultSet rs = pstmt.executeQuery()) {

        while (rs.next()) {
            int id = rs.getInt("ID");
            int idCliente = rs.getInt("ID_Cliente");
            int idHabitacion = rs.getInt("ID_Habitación");
            Date fechaInicio = rs.getDate("Fecha_Inicio");
            Date fechaFin = rs.getDate("Fecha_Fin");
            double total = rs.getDouble("Total");

            Book book = new Book(id, idCliente, idHabitacion, fechaInicio, fechaFin, total);
            list.add(book);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return list;
}

Notas importantes

    Datos faltantes:
        Los nombres y apellidos del cliente, así como los detalles de la habitación (tipo y precio), deben ser recuperados desde las tablas correspondientes (Clientes y Habitaciones). Puedes hacerlo con una consulta SQL que use JOIN.

    Ruta del archivo: Asegúrate de que el directorio ./src/main/resources existe o cambia la ruta según tus necesidades.

    Errores comunes:
        Si usas una versión antigua de Java, verifica que Apache POI sea compatible.
        Asegúrate de cerrar correctamente las conexiones para evitar fugas de memoria.

¿Te gustaría incluir una consulta con JOIN para completar los datos faltantes? 😊

#### Peticiones erroneas Da mal las querys para solicitar los datos. Se edita la query para poder recoger los datos como corresponde 
necesito la query para poder recoger el el tipo de habitacion a partid del id de la reserva
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
for (Client client : clients) {
            	for (Book book : dataQuery.getBookByClient(client.getID())) {
            		
            		Row row = sheet.createRow(rowNum++);
                    row.createCell(0).setCellValue(book.getID());
                    row.createCell(1).setCellValue(book.getIdCliente());
                    row.createCell(2).setCellValue(client.getNombre()); // Reemplaza con los datos del cliente
                    row.createCell(3).setCellValue(client.getApellido()); // Reemplaza con los datos del cliente
                    row.createCell(4).setCellValue(book.getIdHabitacion());
                    row.createCell(5).setCellValue(); // tipo habitacion
                    row.createCell(7).setCellValue(book.getID());
                    row.createCell(8).setCellValue(book.getFechaInicio().toString());
                    row.createCell(9).setCellValue(book.getFechaFin().toString());
                    row.createCell(10).setCellValue(book.getTotal());
            	}
            }
ChatGPT Plus
ChatGPT

Para obtener el tipo de habitación (Tipo) a partir del ID de la reserva, necesitas crear un método en tu clase DatabaseQuery que consulte la base de datos utilizando el ID de la reserva para relacionar las tablas Reservas y Habitaciones.

La consulta SQL sería algo como esto:

SELECT h.Tipo
FROM Reservas r
JOIN Habitaciones h ON r.ID_Habitación = h.ID
WHERE r.ID = ?;

Aquí tienes cómo podrías implementar este método en tu clase DatabaseQuery:

// Método para obtener el tipo de habitación basado en el ID de la reserva
public String getRoomTypeByBookID(int bookID) {
    String roomType = null;
    String sql = "SELECT h.Tipo " +
                 "FROM Reservas r " +
                 "JOIN Habitaciones h ON r.ID_Habitación = h.ID " +
                 "WHERE r.ID = ?";

    try (Connection conn = database.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        pstmt.setInt(1, bookID);
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            roomType = rs.getString("Tipo");
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return roomType;
}

Luego, en el fragmento del código donde estás generando las filas en tu hoja de Excel, puedes usar este método para obtener el tipo de habitación:

for (Client client : clients) {
    for (Book book : dataQuery.getBookByClient(client.getID())) {
        Row row = sheet.createRow(rowNum++);
        row.createCell(0).setCellValue(book.getID());
        row.createCell(1).setCellValue(book.getIdCliente());
        row.createCell(2).setCellValue(client.getNombre());
        row.createCell(3).setCellValue(client.getApellido());
        row.createCell(4).setCellValue(book.getIdHabitacion());
        row.createCell(5).setCellValue(dataQuery.getRoomTypeByBookID(book.getID())); // Llama al método para obtener el tipo de habitación
        row.createCell(7).setCellValue(book.getID());
        row.createCell(8).setCellValue(book.getFechaInicio().toString());
        row.createCell(9).setCellValue(book.getFechaFin().toString());
        row.createCell(10).setCellValue(book.getTotal());
    }
}

Con este enfoque, el método getRoomTypeByBookID se encargará de recuperar el tipo de habitación asociado a una reserva específica. Esto mantiene el código organizado y reutilizable.
Tú dijiste:
haz una implemantacion con un menú de terminal para hacer una llamada a esta clase y las que necesita
package excelGenerator;

import databaseAdmin.DatabaseGenerator;
import databaseAdmin.DatabaseQuery;
import dataClases.Book;
import dataClases.Client;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelGenerator {
    private final String filePath;
    private String fileName;
    private DatabaseGenerator database;
    private DatabaseQuery dataQuery;

    public ExcelGenerator(String fileName) {
        this.filePath = "./src/main/resources";
        this.fileName = "InformeExcel.xlsx";
        this.database = new DatabaseGenerator();
        this.dataQuery = new DatabaseQuery(database);
    }

    public void generateExcel() {
        String[] columns = {
            "Reserva ID", "Cliente ID", "Nombre", "Apellido", "Habitación ID",
            "Categoría", "Precio", "Reserva ID", "Fecha Inicio", "Fecha Fin", "Total"
        };

        // Obtener datos de reservas
        List<Client> clients = dataQuery.getClients();
        
        
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Reservas");

            // Crear el encabezado
            Row headerRow = sheet.createRow(0);
            for (int i = 0; i < columns.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columns[i]);
                CellStyle style = workbook.createCellStyle();
                Font font = workbook.createFont();
                font.setBold(true);
                style.setFont(font);
                cell.setCellStyle(style);
            }

            // Escribir los datos de las reservas
            int rowNum = 1;
            for (Client client : clients) {
                for (Book book : dataQuery.getBookByClient(client.getID())) {
                    Row row = sheet.createRow(rowNum++);
                    row.createCell(0).setCellValue(book.getID());
                    row.createCell(1).setCellValue(book.getIdCliente());
                    row.createCell(2).setCellValue(client.getNombre());
                    row.createCell(3).setCellValue(client.getApellido());
                    row.createCell(4).setCellValue(book.getIdHabitacion());
                    row.createCell(5).setCellValue(dataQuery.getRoomTypeByBookID(book.getID()));
                    row.createCell(7).setCellValue(book.getID());
                    row.createCell(8).setCellValue(book.getFechaInicio().toString());
                    row.createCell(9).setCellValue(book.getFechaFin().toString());
                    row.createCell(10).setCellValue(book.getTotal());
                }
            }

            // Ajustar columnas al contenido
            for (int i = 0; i < columns.length; i++) {
                sheet.autoSizeColumn(i);
            }

            // Guardar el archivo
            try (FileOutputStream fileOut = new FileOutputStream(filePath + "/" + fileName)) {
                workbook.write(fileOut);
            }

            System.out.println("Archivo Excel generado correctamente en " + filePath + "/" + fileName);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
ChatGPT Plus
ChatGPT

Aquí tienes una implementación con un menú de terminal para interactuar con las clases DatabaseGenerator, DatabaseQuery, y ExcelGenerator. Este menú permite al usuario realizar operaciones como generar un archivo Excel, listar clientes, o salir de la aplicación.
Clase Main con Menú

import databaseAdmin.DatabaseGenerator;
import databaseAdmin.DatabaseQuery;
import excelGenerator.ExcelGenerator;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DatabaseGenerator database = new DatabaseGenerator();
        DatabaseQuery dataQuery = new DatabaseQuery(database);
        ExcelGenerator excelGenerator = new ExcelGenerator("InformeExcel.xlsx");

        int option;

        do {
            System.out.println("\nMenú Principal:");
            System.out.println("1. Generar archivo Excel con las reservas");
            System.out.println("2. Listar todos los clientes");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");

            option = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer de entrada

            switch (option) {
                case 1:
                    System.out.println("\nGenerando archivo Excel...");
                    excelGenerator.generateExcel();
                    break;

                case 2:
                    System.out.println("\nLista de Clientes:");
                    dataQuery.getClients().forEach(client -> {
                        System.out.println("ID: " + client.getID() + ", Nombre: " + client.getNombre() +
                                ", Apellido: " + client.getApellido() + ", Teléfono: " + client.getTelefono() +
                                ", Email: " + client.getEmail());
                    });
                    break;

                case 3:
                    System.out.println("Saliendo del programa. ¡Hasta luego!");
                    break;

                default:
                    System.out.println("Opción no válida. Por favor, intente de nuevo.");
            }

        } while (option != 3);

        scanner.close();
    }
}

Explicación de las Opciones

    Generar Archivo Excel: Llama al método generateExcel de la clase ExcelGenerator, que crea un archivo Excel con la información de las reservas.

    Listar Clientes: Utiliza el método getClients de la clase DatabaseQuery para imprimir una lista de todos los clientes en la consola.

    Salir: Cierra la aplicación.

Requisitos Previos

    Asegúrate de que las clases DatabaseGenerator, DatabaseQuery, y ExcelGenerator estén configuradas correctamente con sus dependencias.
    Configura una base de datos y proporciona los detalles de conexión en la clase DatabaseGenerator.
    Añade las librerías necesarias, como Apache POI para generar el archivo Excel.

Ejecución

Para ejecutar el programa:

    Compila todos los archivos .java en tu proyecto.
    Ejecuta el archivo Main desde la terminal.
    Interactúa con el menú para realizar las operaciones disponibles.

Tú dijiste:
Generando archivo Excel...
java.sql.SQLException: No suitable driver found for jdbc:sqlite:motel.db
	at java.sql/java.sql.DriverManager.getConnection(DriverManager.java:707)
	at java.sql/java.sql.DriverManager.getConnection(DriverManager.java:253)
	at databaseAdmin.DatabaseGenerator.getConnection(DatabaseGenerator.java:70)
	at databaseAdmin.DatabaseQuery.getClients(DatabaseQuery.java:26)
	at excelGenerator.ExcelGenerator.generateExcel(ExcelGenerator.java:35)
	at main.Main.main(Main.java:33)
java.lang.NullPointerException: Cannot invoke "java.sql.Connection.createStatement()" because "conn" is null
	at databaseAdmin.DatabaseQuery.getClients(DatabaseQuery.java:27)
	at excelGenerator.ExcelGenerator.generateExcel(ExcelGenerator.java:35)
	at main.Main.main(Main.java:33)
ERROR StatusLogger Log4j2 could not find a logging implementation. Please add log4j-core to the classpath. Using SimpleLogger to log to the console...
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
ChatGPT Plus
ChatGPT

El problema que enfrentas ocurre debido a que no se está encontrando el controlador SQLite para la conexión JDBC. Este error puede ser resuelto asegurándote de que el controlador SQLite esté correctamente incluido en tu proyecto.
Pasos para Resolver el Error

    Asegúrate de Incluir el Driver SQLite
    Asegúrate de que tienes el archivo JAR del controlador SQLite (sqlite-jdbc) en el classpath de tu proyecto. Si estás usando un sistema de construcción como Maven, agrega la dependencia:

<dependency>
    <groupId>org.xerial</groupId>
    <artifactId>sqlite-jdbc</artifactId>
    <version>3.36.0</version>
</dependency>

Si estás usando un IDE como IntelliJ IDEA o Eclipse, asegúrate de agregar el archivo JAR a las bibliotecas del proyecto si no estás usando Maven o Gradle.

Carga del Driver Manualmente
Aunque la mayoría de las implementaciones modernas de JDBC lo cargan automáticamente, puedes cargarlo manualmente para asegurarte:

try {
    Class.forName("org.sqlite.JDBC");
} catch (ClassNotFoundException e) {
    e.printStackTrace();
    System.exit(1);
}

Añade este bloque en el método getConnection de la clase DatabaseGenerator antes de intentar conectarte.

Verifica la Conexión
Asegúrate de que el archivo de la base de datos motel.db esté en el directorio correcto. Si el archivo no existe, SQLite lo creará automáticamente, pero estará vacío.

Puedes verificar la conexión utilizando un bloque como este:

public static void main(String[] args) {
    DatabaseGenerator dbGen = new DatabaseGenerator();
    try (Connection conn = dbGen.getConnection()) {
        if (conn != null) {
            System.out.println("Conexión exitosa a la base de datos.");
        } else {
            System.out.println("Error al conectar con la base de datos.");
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}

Problema con la Versión de Java
Verifica que la versión del controlador SQLite sea compatible con la versión de Java que estás utilizando. Algunas versiones más antiguas del controlador no funcionan correctamente con Java 17 o superior.

Logging de Errores
Sobre el error de Log4j, si no necesitas usar Log4j2 en tu proyecto, puedes ignorar este mensaje, ya que se está utilizando SimpleLogger como fallback. Si necesitas Log4j2, agrega las dependencias necesarias:

    <dependency>
        <groupId>org.apache.logging.log4j</groupId>
        <artifactId>log4j-core</artifactId>
        <version>2.17.2</version>
    </dependency>
    <dependency>
        <groupId>org.apache.logging.log4j</groupId>
        <artifactId>log4j-api</artifactId>
        <version>2.17.2</version>
    </dependency>

Modificación Final del Método getConnection

public Connection getConnection() {
    try {
        if (c == null || c.isClosed()) {
            // Asegúrate de cargar el driver
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:motel.db");
        }
    } catch (ClassNotFoundException e) {
        System.err.println("Error: No se pudo cargar el driver JDBC de SQLite.");
        e.printStackTrace();
    } catch (SQLException e) {
        System.err.println("Error: No se pudo conectar a la base de datos.");
        e.printStackTrace();
    }
    return c;
}

Después de realizar estas modificaciones, prueba nuevamente el programa. Esto debería resolver los problemas relacionados con la conexión a la base de datos y permitirte continuar con la generación del archivo Excel.
Tú dijiste:
Generando archivo Excel...
org.sqlite.SQLiteException: [SQLITE_ERROR] SQL error or missing database (no such table: Clientes)
	at org.sqlite.core.DB.newSQLException(DB.java:1012)
	at org.sqlite.core.DB.newSQLException(DB.java:1024)
	at org.sqlite.core.DB.throwex(DB.java:989)
	at org.sqlite.core.NativeDB.prepare_utf8(Native Method)
	at org.sqlite.core.NativeDB.prepare(NativeDB.java:134)
	at org.sqlite.core.DB.prepare(DB.java:257)
	at org.sqlite.jdbc3.JDBC3Statement.executeQuery(JDBC3Statement.java:66)
	at databaseAdmin.DatabaseQuery.getClients(DatabaseQuery.java:28)
	at excelGenerator.ExcelGenerator.generateExcel(ExcelGenerator.java:35)
	at main.Main.main(Main.java:33)
ERROR StatusLogger Log4j2 could not find a logging implementation. Please add log4j-core to the classpath. Using SimpleLogger to log to the console...
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
ChatGPT Plus
ChatGPT

El error indica que la tabla Clientes no existe en la base de datos. Esto ocurre probablemente porque el método crearTabla no se está ejecutando antes de que intentes acceder a la tabla. Aquí hay algunas soluciones y verificaciones que puedes realizar:
1. Asegúrate de Llamar a crearTabla Antes de Usar la Base de Datos

En el flujo principal de tu programa, asegúrate de que estás llamando al método crearTabla antes de cualquier operación que intente acceder a la base de datos. Por ejemplo:

public static void main(String[] args) {
    DatabaseGenerator dbGen = new DatabaseGenerator();
    dbGen.crearTabla(); // Asegúrate de que se ejecuta antes de cualquier consulta.

    ExcelGenerator excelGen = new ExcelGenerator("InformeExcel.xlsx");
    excelGen.generateExcel(); // Ahora genera el archivo Excel.
}

2. Verifica Si las Tablas Fueron Creadas

Puedes usar un método para verificar qué tablas están presentes en la base de datos. Por ejemplo, podrías imprimir las tablas después de ejecutar crearTabla:

public static void main(String[] args) {
    DatabaseGenerator dbGen = new DatabaseGenerator();
    dbGen.crearTabla();

    List<String> tablas = dbGen.getTableNames();
    System.out.println("Tablas en la base de datos: " + tablas);
}

Si no ves Clientes, significa que algo salió mal al intentar crear las tablas.
3. Problemas con el commit

En el método crearTabla, usas c.commit() después de cerrar el Statement. En SQLite, el commit debería ejecutarse antes de cerrar el Statement. Cambia el orden para garantizar que las transacciones sean confirmadas correctamente:

stmt.executeUpdate(tbClientes);
stmt.executeUpdate(tbHabitaciones);
stmt.executeUpdate(tbReservas);

c.commit(); // Mueve el commit aquí, antes de cerrar el Statement.
stmt.close();

4. Verifica la Existencia de la Base de Datos Física

Confirma que el archivo motel.db está siendo creado y que está en el directorio correcto. Si el archivo no está donde esperas, SQLite no podrá trabajar con él. Puedes comprobar la ruta absoluta en tiempo de ejecución con:

System.out.println("Ruta absoluta de la base de datos: " + new java.io.File("motel.db").getAbsolutePath());

5. Depuración y Registro de Errores

Si aún tienes problemas, agrega más registros para entender qué está fallando. Por ejemplo:

try {
    Class.forName("org.sqlite.JDBC");
    c = DriverManager.getConnection("jdbc:sqlite:motel.db");
    System.out.println("Conexión a la base de datos establecida.");
} catch (ClassNotFoundException e) {
    System.err.println("Driver JDBC no encontrado.");
    e.printStackTrace();
} catch (SQLException e) {
    System.err.println("Error al conectar con la base de datos.");
    e.printStackTrace();
}

6. Confirma que los Datos Iniciales se Insertan Correctamente

En el método insertarDatosIniciales, asegúrate de que no hay errores al insertar los datos. Puedes envolver cada operación con un bloque try-catch para capturar errores y asegurarte de que las tablas existen antes de insertar datos.
Código Final Corregido

Aquí está el código corregido para el método crearTabla:

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

        c.commit(); // Confirma los cambios antes de cerrar el Statement.
        stmt.close();

        insertarDatosIniciales();

        System.out.println("Tablas creadas con éxito y datos iniciales insertados.");
    } catch (Exception e) {
        System.err.println(e.getClass().getName() + ": " + e.getMessage());
        e.printStackTrace();
    }
}

Con estas correcciones y verificaciones, deberías poder solucionar el problema.
Tú dijiste:
donde tengo que llamar a crear tablas
ChatGPT Plus
ChatGPT

Debes asegurarte de llamar al método crearTabla antes de realizar cualquier operación que dependa de la base de datos, como consultar datos o insertar registros adicionales. Esto garantiza que las tablas y los datos iniciales estén disponibles para su uso.
Dónde Llamar a crearTabla

    En el main del programa principal. Llama a crearTabla al inicio del programa, antes de usar cualquier funcionalidad que dependa de la base de datos. Por ejemplo:

import databaseAdmin.DatabaseGenerator;
import excelGenerator.ExcelGenerator;

public class Main {
    public static void main(String[] args) {
        // Crear y configurar la base de datos
        DatabaseGenerator dbGen = new DatabaseGenerator();
        dbGen.crearTabla(); // Asegúrate de crear tablas antes de cualquier operación con la base de datos.

        // Generar el archivo Excel
        ExcelGenerator excelGen = new ExcelGenerator("InformeExcel.xlsx");
        excelGen.generateExcel();

        // Cerrar la conexión a la base de datos (opcional pero recomendado)
        dbGen.cerrarBBDD();
    }
}

    En una clase de inicialización dedicada (opcional). Si tienes una clase o método que se encarga de inicializar los recursos, como la base de datos, puedes mover la llamada allí. Por ejemplo:

public class Initializer {
    public static void initDatabase() {
        DatabaseGenerator dbGen = new DatabaseGenerator();
        dbGen.crearTabla();
        dbGen.cerrarBBDD();
    }
}

Luego, puedes llamar a Initializer.initDatabase() desde tu método principal:

public class Main {
    public static void main(String[] args) {
        // Inicializar la base de datos
        Initializer.initDatabase();

        // Generar el archivo Excel
        ExcelGenerator excelGen = new ExcelGenerator("InformeExcel.xlsx");
        excelGen.generateExcel();
    }
}

    En el constructor de ExcelGenerator (menos recomendado). Puedes modificar el constructor de ExcelGenerator para incluir la llamada a crearTabla. Sin embargo, esto no es una práctica recomendada, ya que viola el principio de responsabilidad única.

public ExcelGenerator(String fileName) {
    this.filePath = "./src/main/resources";
    this.fileName = "InformeExcel.xlsx";
    this.database = new DatabaseGenerator();

    // Crear tablas en el constructor (menos recomendado)
    database.crearTabla();

    this.dataQuery = new DatabaseQuery(database);
}

Resumen

La mejor opción es llamar a crearTabla al inicio del programa en el método main, antes de realizar cualquier operación con la base de datos. Esto asegura que las tablas y los datos iniciales estén listos para ser utilizados por otras partes del programa.
Tú dijiste:
org.sqlite.SQLiteException: [SQLITE_ERROR] SQL error or missing database (unknown column "ID_Habitación" in foreign key definition)
org.sqlite.SQLiteException: [SQLITE_ERROR] SQL error or missing database (unknown column "ID_Habitación" in foreign key definition)
	at org.sqlite.core.DB.newSQLException(DB.java:1012)
	at org.sqlite.core.DB.newSQLException(DB.java:1024)
	at org.sqlite.core.DB.throwex(DB.java:989)
	at org.sqlite.core.NativeDB._exec_utf8(Native Method)
	at org.sqlite.core.NativeDB._exec(NativeDB.java:94)
	at org.sqlite.jdbc3.JDBC3Statement.executeUpdate(JDBC3Statement.java:102)
	at databaseAdmin.DatabaseGenerator.crearTabla(DatabaseGenerator.java:43)
	at main.Main.main(Main.java:14)
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
                    "FOREIGN KEY (ID_Habitación) REFERENCES Habitaciones(ID));";

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
ChatGPT Plus
ChatGPT

El problema está en la definición de la tabla Reservas, específicamente en la clave foránea:

FOREIGN KEY (ID_Habitación) REFERENCES Habitaciones(ID)

El error ocurre porque SQLite no reconoce el nombre de la columna ID_Habitación. Esto se debe a que el nombre correcto en la tabla Reservas es ID_Habitacion (sin tilde).
Solución

Debes corregir la definición de la clave foránea cambiando ID_Habitación por ID_Habitacion. Aquí está la versión corregida del código para la tabla Reservas:

String tbReservas = "CREATE TABLE IF NOT EXISTS Reservas (" +
        "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
        "ID_Cliente INTEGER," +
        "ID_Habitacion INTEGER," +
        "Fecha_Inicio TEXT NOT NULL," +
        "Fecha_Fin TEXT NOT NULL," +
        "Total DOUBLE NOT NULL," +
        "FOREIGN KEY (ID_Cliente) REFERENCES Clientes(ID)," +
        "FOREIGN KEY (ID_Habitacion) REFERENCES Habitaciones(ID));";

Cambios realizados:

    Cambié ID_Habitación a ID_Habitacion para que coincida con el nombre de la columna en la tabla.
    Revisé las definiciones de las claves foráneas para asegurar que se refieren correctamente a las columnas.

Revisión adicional

Después de corregir el código, realiza los siguientes pasos para asegurarte de que no haya conflictos con la base de datos existente:

    Elimina la base de datos anterior: Borra el archivo motel.db si ya existe, para garantizar que se cree una base de datos limpia.
    Ejecuta el programa: Asegúrate de que las tablas se creen correctamente y los datos iniciales se inserten sin errores.

Esto debería resolver el problema y permitir que tu programa funcione correctamente.
Tú dijiste:
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
                    "FOREIGN KEY (ID_Habitacion) REFERENCES Habitaciones(ID));";

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
    
    public String getRoomTypeByBookID(int bookID) {
        String roomType = null;
        String sql = "SELECT h.Tipo " +
                     "FROM Reservas r " +
                     "JOIN Habitaciones h ON r.ID_Habitacion = h.ID " +
                     "WHERE r.ID = ?";

        try (Connection conn = database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, bookID);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                roomType = rs.getString("Tipo");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return roomType;
    }
    
    public String getRoomPriceByBookID(int bookID) {
        String roomType = null;
        String sql = "SELECT h.Precio " +
                     "FROM Reservas r " +
                     "JOIN Habitaciones h ON r.ID_Habitacion = h.ID " +
                     "WHERE r.ID = ?";

        try (Connection conn = database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, bookID);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                roomType = rs.getString("Precio");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return roomType;
    }
}
package excelGenerator;

import databaseAdmin.DatabaseGenerator;
import databaseAdmin.DatabaseQuery;
import dataClases.Book;
import dataClases.Client;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelGenerator {
    private final String filePath;
    private String fileName;
    private DatabaseGenerator database;
    private DatabaseQuery dataQuery;

    public ExcelGenerator(String fileName) {
        this.filePath = "./src/main/resources";
        this.fileName = "InformeExcel.xlsx";
        this.database = new DatabaseGenerator();
        this.dataQuery = new DatabaseQuery(database);
    }

    public void generateExcel() {
        String[] columns = {
            "Reserva ID", "Cliente ID", "Nombre", "Apellido", "Habitación ID",
            "Categoría", "Precio", "Reserva ID", "Fecha Inicio", "Fecha Fin", "Total"
        };

        // Obtener datos de reservas
        List<Client> clients = dataQuery.getClients();
        
        
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Reservas");

            // Crear el encabezado
            Row headerRow = sheet.createRow(0);
            for (int i = 0; i < columns.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columns[i]);
                CellStyle style = workbook.createCellStyle();
                Font font = workbook.createFont();
                font.setBold(true);
                style.setFont(font);
                cell.setCellStyle(style);
            }

            // Escribir los datos de las reservas
            int rowNum = 1;
            for (Client client : clients) {
                for (Book book : dataQuery.getBookByClient(client.getID())) {
                    Row row = sheet.createRow(rowNum++);
                    row.createCell(0).setCellValue(book.getID());
                    row.createCell(1).setCellValue(book.getIdCliente());
                    row.createCell(2).setCellValue(client.getNombre());
                    row.createCell(3).setCellValue(client.getApellido());
                    row.createCell(4).setCellValue(book.getIdHabitacion());
                    row.createCell(5).setCellValue(dataQuery.getRoomTypeByBookID(book.getIdHabitacion()));
                    row.createCell(6).setCellValue(dataQuery.getRoomPriceByBookID(rowNum));
                    row.createCell(7).setCellValue(book.getID());
                    row.createCell(8).setCellValue(book.getFechaInicio().toString());
                    row.createCell(9).setCellValue(book.getFechaFin().toString());
                    row.createCell(10).setCellValue(book.getTotal());
                }
            }

            // Ajustar columnas al contenido
            for (int i = 0; i < columns.length; i++) {
                sheet.autoSizeColumn(i);
            }

            // Guardar el archivo
            try (FileOutputStream fileOut = new FileOutputStream(filePath + "/" + fileName)) {
                workbook.write(fileOut);
            }

            System.out.println("Archivo Excel generado correctamente en " + filePath + "/" + fileName);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
ERROR StatusLogger Log4j2 could not find a logging implementation. Please add log4j-core to the classpath. Using SimpleLogger to log to the console...
java.sql.SQLException: no such column: 'ID_Habitación'
	at org.sqlite.jdbc3.JDBC3ResultSet.findColumn(JDBC3ResultSet.java:49)
	at org.sqlite.jdbc3.JDBC3ResultSet.getInt(JDBC3ResultSet.java:402)
	at databaseAdmin.DatabaseQuery.getBookByClient(DatabaseQuery.java:90)
	at excelGenerator.ExcelGenerator.generateExcel(ExcelGenerator.java:56)
	at main.Main.main(Main.java:35)
java.sql.SQLException: no such column: 'ID_Habitación'
	at org.sqlite.jdbc3.JDBC3ResultSet.findColumn(JDBC3ResultSet.java:49)
	at org.sqlite.jdbc3.JDBC3ResultSet.getInt(JDBC3ResultSet.java:402)
	at databaseAdmin.DatabaseQuery.getBookByClient(DatabaseQuery.java:90)
	at excelGenerator.ExcelGenerator.generateExcel(ExcelGenerator.java:56)
	at main.Main.main(Main.java:35)
java.sql.SQLException: no such column: 'ID_Habitación'
	at org.sqlite.jdbc3.JDBC3ResultSet.findColumn(JDBC3ResultSet.java:49)
	at org.sqlite.jdbc3.JDBC3ResultSet.getInt(JDBC3ResultSet.java:402)
	at databaseAdmin.DatabaseQuery.getBookByClient(DatabaseQuery.java:90)
	at excelGenerator.ExcelGenerator.generateExcel(ExcelGenerator.java:56)
	at main.Main.main(Main.java:35)
java.sql.SQLException: no such column: 'ID_Habitación'
	at org.sqlite.jdbc3.JDBC3ResultSet.findColumn(JDBC3ResultSet.java:49)
	at org.sqlite.jdbc3.JDBC3ResultSet.getInt(JDBC3ResultSet.java:402)
	at databaseAdmin.DatabaseQuery.getBookByClient(DatabaseQuery.java:90)
	at excelGenerator.ExcelGenerator.generateExcel(ExcelGenerator.java:56)
	at main.Main.main(Main.java:35)
ChatGPT Plus
ChatGPT

The error java.sql.SQLException: no such column: 'ID_Habitación' occurs because your SQL schema defines the column as ID_Habitacion (without the accented "ó"), but some parts of your code are trying to access a column named ID_Habitación (with the accented "ó"). SQLite treats column names as case-sensitive and accents-sensitive.
Fix:

    Align the column name in the schema and code:
        Update the table creation query in DatabaseGenerator to use the correct column name consistently. Change this in your Reservas table definition:

    String tbReservas = "CREATE TABLE IF NOT EXISTS Reservas (" +
        "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
        "ID_Cliente INTEGER," +
        "ID_Habitacion INTEGER," +
        "Fecha_Inicio TEXT NOT NULL," +
        "Fecha_Fin TEXT NOT NULL," +
        "Total DOUBLE NOT NULL," +
        "FOREIGN KEY (ID_Cliente) REFERENCES Clientes(ID)," +
        "FOREIGN KEY (ID_Habitacion) REFERENCES Habitaciones(ID));";

    Make sure the column name remains ID_Habitacion throughout.

Update the code referencing ID_Habitación:

    Locate every instance in your code where ID_Habitación is used and replace it with ID_Habitacion (without the accent). For example:

    int idHabitacion = rs.getInt("ID_Habitacion");

    Replace occurrences in:
        DatabaseQuery.getBookByClient
        Any other queries in DatabaseQuery or elsewhere.

Check your test data: If you manually created or populated the database, ensure the column name in the database matches the column name in the schema (ID_Habitacion). You can verify this by querying the SQLite database:

PRAGMA table_info(Reservas);

Rebuild the database: If you’ve already run the application and created the database with the wrong column name, delete the motel.db file and let your code recreate it with the corrected schema.

Correct ExcelGenerator logic: In ExcelGenerator, ensure the query and column references match the corrected schema:

    int idHabitacion = book.getIdHabitacion(); // From your corrected `Book` class

After making these changes, your code should run without throwing the no such column error.
Tú dijiste:
public String getRoomTypeByBookID(int bookID) {
        String roomType = null;
        String sql = "SELECT h.Tipo " +
                     "FROM Reservas r " +
                     "JOIN Habitaciones h ON r.ID_Habitacion = h.ID " +
                     "WHERE r.ID = ?";

        try (Connection conn = database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, bookID);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                roomType = rs.getString("Tipo");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return roomType;
    }
    
    public String getRoomPriceByBookID(int bookID) {
        String roomType = null;
        String sql = "SELECT h.Precio " +
                     "FROM Reservas r " +
                     "JOIN Habitaciones h ON r.ID_Habitacion = h.ID " +
                     "WHERE r.ID = ?";

        try (Connection conn = database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, bookID);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                roomType = rs.getString("Precio");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return roomType;
    }
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
            	    "FOREIGN KEY (ID_Habitacion) REFERENCES Habitaciones(ID));";
ChatGPT Plus
ChatGPT

The column names in your table creation SQL and your Java code now seem consistent (ID_Habitacion without the accent). However, there is an issue in your getRoomPriceByBookID and getRoomTypeByBookID methods.

Here’s a breakdown of issues and fixes:
1. Issue: Misuse of bookID in the query

Both methods expect bookID to filter on the Reservas table's ID column. The SQL queries appear correct. Ensure that bookID passed to these methods corresponds to the ID column of the Reservas table.
2. Issue: Incorrect column retrieval in getRoomPriceByBookID

The method retrieves the Precio column but stores it in a String variable (roomType), which doesn't align with the expected data type.
Fix:

Change the return type and variable to double, as Precio is a DOUBLE in the schema.

Updated Method:

public double getRoomPriceByBookID(int bookID) {
    double roomPrice = 0.0; // Initialize to a default value
    String sql = "SELECT h.Precio " +
                 "FROM Reservas r " +
                 "JOIN Habitaciones h ON r.ID_Habitacion = h.ID " +
                 "WHERE r.ID = ?";

    try (Connection conn = database.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        pstmt.setInt(1, bookID);
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            roomPrice = rs.getDouble("Precio"); // Use getDouble for DOUBLE fields
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return roomPrice;
}

3. Issue: Ensure getRoomTypeByBookID is working as intended

The getRoomTypeByBookID method seems correct, provided the Tipo column in Habitaciones is a TEXT type. Ensure the column exists and matches your database schema.
Verified Method:

public String getRoomTypeByBookID(int bookID) {
    String roomType = null; // Default to null
    String sql = "SELECT h.Tipo " +
                 "FROM Reservas r " +
                 "JOIN Habitaciones h ON r.ID_Habitacion = h.ID " +
                 "WHERE r.ID = ?";

    try (Connection conn = database.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        pstmt.setInt(1, bookID);
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            roomType = rs.getString("Tipo"); // Retrieve room type
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return roomType;
}

4. Testing the Code

After applying these fixes:

    Verify the database schema by running the PRAGMA table_info query for each table (Clientes, Habitaciones, and Reservas) to confirm column names.
    Test the methods with valid bookID values that exist in the Reservas table.

For example:

DatabaseGenerator dbGen = new DatabaseGenerator();
dbGen.crearTabla();

DatabaseQuery dbQuery = new DatabaseQuery(dbGen);

int bookID = 1; // Replace with a valid Reserva ID
String roomType = dbQuery.getRoomTypeByBookID(bookID);
double roomPrice = dbQuery.getRoomPriceByBookID(bookID);

System.out.println("Room Type: " + roomType);
System.out.println("Room Price: " + roomPrice);

Expected Output

For a valid bookID:

Room Type: Doble
Room Price: 80.0

If errors persist, confirm that:

    Data exists in the Reservas and Habitaciones tables for the provided bookID.
    The database file (motel.db) reflects the updated schema.

Tú dijiste:
row.createCell(5).setCellValue(dataQuery.getRoomTypeByBookID(book.getIdHabitacion()));
                    row.createCell(6).setCellValue(dataQuery.getRoomPriceByBookID(book.getIdHabitacion()));
me los devuelve vacios
ChatGPT Plus
ChatGPT

Si las celdas de tu archivo se están llenando con valores vacíos, podría deberse a problemas en la lógica de tu código o en los datos subyacentes. Vamos a analizar posibles causas y soluciones.
1. Validación de Datos de Entrada

En tu código, usas book.getIdHabitacion() como parámetro para los métodos getRoomTypeByBookID y getRoomPriceByBookID. Esto implica que estás pasando el ID de la habitación directamente, pero las funciones que implementaste esperan el ID de una reserva (bookID), no el ID de una habitación.
Solución:

Reemplaza el uso de book.getIdHabitacion() por book.getIdReserva() si tienes acceso al ID de la reserva correspondiente. Debería lucir así:

row.createCell(5).setCellValue(dataQuery.getRoomTypeByBookID(book.getIdReserva()));
row.createCell(6).setCellValue(dataQuery.getRoomPriceByBookID(book.getIdReserva()));

Asegúrate de que book.getIdReserva() devuelve el ID de la reserva correcto.
2. Validación de Datos en la Base de Datos

Si sigues obteniendo valores vacíos, verifica la información en tu base de datos:

    Consulta directa: Usa un cliente SQL o alguna herramienta como SQLite Browser para ejecutar la consulta directamente:

    SELECT h.Tipo, h.Precio
    FROM Reservas r
    JOIN Habitaciones h ON r.ID_Habitacion = h.ID
    WHERE r.ID = 1; -- Reemplaza 1 por el ID de reserva que estés probando

    Si no obtienes resultados, el problema está en tus datos.

    Confirmar Datos Relacionados:
        Verifica que en la tabla Reservas, el campo ID_Habitacion apunta correctamente a una entrada existente en Habitaciones.
        Asegúrate de que el campo Tipo en Habitaciones contiene valores y no está vacío.

3. Verificación del Código de Consulta
Método getRoomTypeByBookID

Confirma que devuelve valores correctos ejecutando el siguiente código con un ID válido:

System.out.println(dataQuery.getRoomTypeByBookID(1)); // Reemplaza 1 con un ID de reserva válido

Si sigue devolviendo null, confirma que:

    El ID que pasas como argumento (bookID) existe en la tabla Reservas.
    Los datos en Habitaciones asociados a ese ID_Habitacion no son NULL.

Método getRoomPriceByBookID

Prueba de la misma manera:

System.out.println(dataQuery.getRoomPriceByBookID(1)); // Reemplaza 1 con un ID de reserva válido

4. Validación del Proceso de Creación del Excel

Si las consultas funcionan correctamente pero las celdas del archivo Excel siguen vacías:

    Verifica si las celdas están siendo sobrescritas más adelante en el código.
    Asegúrate de que estás escribiendo el archivo Excel correctamente tras agregar los valores.

Ejemplo:

FileOutputStream fileOut = new FileOutputStream("reporte.xlsx");
workbook.write(fileOut);
fileOut.close();
workbook.close();

5. Depuración con Mensajes de Consola

Agrega mensajes para rastrear el flujo de datos:

System.out.println("Reserva ID: " + book.getIdReserva());
System.out.println("Room Type: " + dataQuery.getRoomTypeByBookID(book.getIdReserva()));
System.out.println("Room Price: " + dataQuery.getRoomPriceByBookID(book.getIdReserva()));

Si alguno de estos valores es null o incorrecto, revisa la lógica de generación de datos.

Con estos pasos, deberías poder identificar y resolver el problema. Si el problema persiste, proporciona más detalles sobre los datos y el flujo para seguir ayudándote.
Tú dijiste:
package dataClases;

public class Book {

    private int ID;
    private int idCliente;
    private int idHabitacion;
    private String fechaInicio;
    private String fechaFin;
    private double total;

    public Book(int ID, int idCliente, int idHabitacion, String fechaInicio, String fechaFin, double total) {
        this.ID = ID;
        this.idCliente = idCliente;
        this.idHabitacion = idHabitacion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.total = total;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdHabitacion() {
        return idHabitacion;
    }

    public void setIdHabitacion(int idHabitacion) {
        this.idHabitacion = idHabitacion;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
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
                int idHabitacion = rs.getInt("ID_Habitacion");
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
    
    public String getRoomTypeByBookID(int bookID) {
        String roomType = null; // Default to null
        String sql = "SELECT h.Tipo " +
                     "FROM Reservas r " +
                     "JOIN Habitaciones h ON r.ID_Habitacion = h.ID " +
                     "WHERE r.ID = ?";

        try (Connection conn = database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, bookID);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                roomType = rs.getString("Tipo"); // Retrieve room type
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return roomType;
    }
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
            	    "FOREIGN KEY (ID_Habitacion) REFERENCES Habitaciones(ID));";
                    row.createCell(5).setCellValue(dataQuery.getRoomTypeByBookID(book.getID()));
                    row.createCell(6).setCellValue(dataQuery.getRoomPriceByBookID(book.getID()));
ChatGPT Plus
ChatGPT

Parece que el problema podría estar relacionado con cómo se manejan los datos en tu consulta o en la lógica que conecta las tablas. Vamos a revisar paso a paso para asegurarnos de que la información fluya correctamente desde las tablas hacia las celdas del archivo Excel.
Diagnóstico del Problema

    Validación de la Lógica de los Métodos
        getRoomTypeByBookID y getRoomPriceByBookID reciben un bookID, que corresponde al ID de una reserva (Reservas.ID).
        En las consultas SQL, estás buscando datos en la tabla Reservas para luego unir con la tabla Habitaciones.

    Posibles Problemas:
        El bookID que estás pasando a los métodos podría no coincidir con ningún registro en Reservas.ID.
        Los datos en Habitaciones asociados a Reservas.ID_Habitacion podrían no existir o no estar bien relacionados.

    Validación del Contenido de la Tabla Reservas
        Asegúrate de que ID_Habitacion en la tabla Reservas corresponde a un registro existente en Habitaciones.

    Validación del Método getRoomTypeByBookID
        Si roomType se devuelve como null, probablemente la consulta no encuentra datos. Esto puede suceder si:
            El ID que pasas no existe en Reservas.
            La relación entre Reservas y Habitaciones no es válida.

Pruebas y Soluciones
1. Prueba Directa en Consola

Agrega mensajes de depuración para verificar los datos en tiempo de ejecución. Por ejemplo:

System.out.println("Book ID: " + book.getID());
System.out.println("Room Type: " + dataQuery.getRoomTypeByBookID(book.getID()));
System.out.println("Room Price: " + dataQuery.getRoomPriceByBookID(book.getID()));

Si alguna de estas líneas imprime null o un valor inesperado, verifica los datos en tu base de datos.
2. Consulta SQL Directa

Ejecuta la siguiente consulta directamente en tu herramienta de base de datos para validar:

SELECT h.Tipo, h.Precio
FROM Reservas r
JOIN Habitaciones h ON r.ID_Habitacion = h.ID
WHERE r.ID = ?;

Reemplaza ? con un ID de reserva válido (por ejemplo, 1) y verifica si devuelve resultados.
3. Verificación de las Relaciones en las Tablas

    Tabla Reservas
        Asegúrate de que el campo ID_Habitacion apunta a un ID existente en Habitaciones.
        Ejemplo:

    SELECT * FROM Reservas WHERE ID_Habitacion NOT IN (SELECT ID FROM Habitaciones);

    Si esta consulta devuelve resultados, significa que tienes relaciones inválidas.

Tabla Habitaciones

    Asegúrate de que los campos Tipo y Precio no sean nulos:

        SELECT * FROM Habitaciones WHERE Tipo IS NULL OR Precio IS NULL;

4. Revisar el Código

    Método getRoomTypeByBookID

    public String getRoomTypeByBookID(int bookID) {
        String roomType = null;
        String sql = "SELECT h.Tipo " +
                     "FROM Reservas r " +
                     "JOIN Habitaciones h ON r.ID_Habitacion = h.ID " +
                     "WHERE r.ID = ?";

        try (Connection conn = database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, bookID);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                roomType = rs.getString("Tipo");
            } else {
                System.out.println("No room type found for Book ID: " + bookID);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return roomType;
    }

    Método getRoomPriceByBookID Repite la misma lógica para Precio y agrega un mensaje en caso de que no se encuentren datos.

Código Revisado para Crear el Archivo Excel

for (Book book : books) {
    String roomType = dataQuery.getRoomTypeByBookID(book.getID());
    String roomPrice = dataQuery.getRoomPriceByBookID(book.getID());

    System.out.println("Processing Book ID: " + book.getID());
    System.out.println("Room Type: " + roomType);
    System.out.println("Room Price: " + roomPrice);

    row.createCell(5).setCellValue(roomType != null ? roomType : "N/A");
    row.createCell(6).setCellValue(roomPrice != null ? roomPrice : "N/A");
}

Con estos pasos, deberías identificar si el problema está en los datos, la lógica de las consultas o la relación entre las tablas. Si el problema persiste, comparte más detalles de los datos involucrados para seguir ayudándote.
Tú dijiste:
necesito el tipo de habitacion a partir de una reserva 
ChatGPT Plus
ChatGPT

Para obtener el tipo de habitación a partir de una reserva, puedes hacerlo mediante una consulta SQL que relacione las tablas Reservas y Habitaciones, usando el ID_Habitacion de la tabla Reservas para obtener el Tipo de la tabla Habitaciones.

Dado el escenario que ya estás utilizando, aquí está el paso a paso para obtener el tipo de habitación en base a un bookID (ID de la reserva):
Paso 1: Consulta SQL

El tipo de habitación se obtiene al hacer una consulta JOIN entre la tabla Reservas y la tabla Habitaciones:

SELECT h.Tipo
FROM Reservas r
JOIN Habitaciones h ON r.ID_Habitacion = h.ID
WHERE r.ID = ?;

En esta consulta:

    r.ID es el bookID de la reserva.
    r.ID_Habitacion es la clave foránea que hace referencia a la tabla Habitaciones.
    h.Tipo es el campo que contiene el tipo de habitación.

Paso 2: Código Java

A continuación te dejo el código Java para obtener el tipo de habitación a partir de una reserva (suponiendo que la conexión a la base de datos está correctamente configurada).

public String getRoomTypeByBookID(int bookID) {
    String roomType = null; // Default to null in case no result is found
    String sql = "SELECT h.Tipo " +
                 "FROM Reservas r " +
                 "JOIN Habitaciones h ON r.ID_Habitacion = h.ID " +
                 "WHERE r.ID = ?"; // Use the bookID as the parameter

    try (Connection conn = database.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        pstmt.setInt(1, bookID); // Set the bookID parameter for the query
        ResultSet rs = pstmt.executeQuery();

        // If a result is found, retrieve the room type
        if (rs.next()) {
            roomType = rs.getString("Tipo");
        }

    } catch (Exception e) {
        e.printStackTrace(); // Log any exceptions that occur
    }

    return roomType;
}

Explicación del Código:

    Conexión a la base de datos: Se establece una conexión con la base de datos mediante database.getConnection().
    Consulta SQL: La consulta JOIN se prepara y se ejecuta con el bookID como parámetro.
    Obtención del resultado: Si la consulta devuelve resultados, se obtiene el tipo de habitación con rs.getString("Tipo").

Paso 3: Uso del Método

Cuando necesites obtener el tipo de habitación para una reserva, puedes usar este método pasándole el bookID de la reserva, por ejemplo:

int bookID = 123; // Ejemplo de ID de reserva
String roomType = dataQuery.getRoomTypeByBookID(bookID);
System.out.println("Tipo de habitación: " + roomType);

Paso 4: Verificación de la Base de Datos

Asegúrate de que en tu base de datos, la relación entre Reservas y Habitaciones esté correctamente configurada:

    En la tabla Reservas, el campo ID_Habitacion debe contener el ID de una habitación válida en la tabla Habitaciones.
    En la tabla Habitaciones, el campo Tipo debe contener el tipo de habitación.

Consideración adicional

Si tu consulta devuelve null, significa que:

    El bookID que pasas no existe en la tabla Reservas.
    El ID_Habitacion relacionado en Reservas no tiene una entrada válida en Habitaciones.

Puedes comprobar ambos casos directamente en la base de datos usando consultas SQL como:

    Verifica que la reserva con el bookID existe:

SELECT * FROM Reservas WHERE ID = ?;

Verifica que el ID_Habitacion está presente en Habitaciones:

    SELECT * FROM Habitaciones WHERE ID = ?;

Esto debería darte el tipo de habitación correctamente. Si tienes algún problema adicional, estaré encantado de ayudarte.
1/2
Tú dijiste:
me da todos ceros en precio
ChatGPT Plus
ChatGPT

Si el método para obtener el precio de la habitación (getRoomPriceByBookID) siempre devuelve ceros, es probable que el problema esté en uno de los siguientes aspectos:
1. Validación del Campo en la Base de Datos

    Verifica que el campo Precio en la tabla Habitaciones contiene datos válidos (valores numéricos diferentes de cero) y no está vacío.

Consulta para verificar los precios:

SELECT ID, Precio FROM Habitaciones;

Si el resultado muestra valores cero para todas las habitaciones, el problema es que la base de datos no contiene precios correctos.
2. Revisión del Tipo de Dato en la Base de Datos

    Asegúrate de que el campo Precio en la tabla Habitaciones es de tipo DOUBLE o un tipo adecuado para manejar decimales.

Ejemplo correcto para definir el campo Precio:

CREATE TABLE Habitaciones (
    ID INTEGER PRIMARY KEY AUTOINCREMENT,
    Numero INTEGER NOT NULL,
    Tipo TEXT NOT NULL,
    Precio DOUBLE NOT NULL
);

Si el tipo de dato es incorrecto, puede ocasionar problemas al interpretar o almacenar los valores.
3. Código del Método getRoomPriceByBookID

Revisemos si el método extrae correctamente el valor del campo Precio.
Método Java:

public double getRoomPriceByBookID(int bookID) {
    double roomPrice = 0.0; // Valor por defecto
    String sql = "SELECT h.Precio " +
                 "FROM Reservas r " +
                 "JOIN Habitaciones h ON r.ID_Habitacion = h.ID " +
                 "WHERE r.ID = ?";

    try (Connection conn = database.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        pstmt.setInt(1, bookID); // Establece el parámetro bookID
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            roomPrice = rs.getDouble("Precio"); // Extrae el precio como double
        }

    } catch (Exception e) {
        e.printStackTrace(); // Maneja errores
    }

    return roomPrice; // Devuelve el precio
}

Revisión:

    Asegúrate de que rs.getDouble("Precio") se esté utilizando correctamente.
    Si el campo en la base de datos es null o contiene texto no numérico, este método podría devolver 0.0.
    Prueba agregar un mensaje de depuración para confirmar qué valores está obteniendo:

if (rs.next()) {
    roomPrice = rs.getDouble("Precio");
    System.out.println("Precio obtenido: " + roomPrice); // Depuración
}

4. Datos Incorrectos Relacionados

Verifica que la relación entre las tablas Reservas y Habitaciones es correcta. Esto incluye:
Consulta de Prueba:

SELECT r.ID AS ReservaID, h.ID AS HabitacionID, h.Precio
FROM Reservas r
JOIN Habitaciones h ON r.ID_Habitacion = h.ID
WHERE r.ID = ?;

Si esta consulta devuelve un precio incorrecto o null, puede deberse a:

    El valor de ID_Habitacion en Reservas no coincide con ningún ID en Habitaciones.
    Los datos en la tabla Habitaciones no están completos o son incorrectos.

5. Lógica en el Código que Usa el Método

Si el método devuelve valores correctos al probarlo de manera aislada, pero en el contexto general sigues obteniendo ceros, revisa cómo se llama el método:

Ejemplo de uso:

row.createCell(6).setCellValue(dataQuery.getRoomPriceByBookID(book.getID()));

Solución de Prueba:

    Imprime el valor que devuelve el método:

double precio = dataQuery.getRoomPriceByBookID(book.getID());
System.out.println("Precio para la reserva " + book.getID() + ": " + precio);
row.createCell(6).setCellValue(precio);

Esto te ayudará a verificar si el problema está en el método o en cómo se utiliza el valor devuelto.

Si después de estas verificaciones el problema persiste, comparte más detalles sobre los datos en las tablas para que pueda ayudarte mejor.
Tú dijiste:
hay que hacer esto numerico?
row.createCell(5).setCellValue(dataQuery.getRoomTypeByRoomID(book.getIdHabitacion()));
                    row.createCell(6).setCellValue(dataQuery.getRoomPriceByRoomID(book.getIdHabitacion()));
ChatGPT Plus
ChatGPT

La respuesta depende del tipo de datos que estás manejando:

    RoomType (Tipo de Habitación): Este valor es un texto (String) y no necesita ser numérico. Puedes dejarlo como está:

    row.createCell(5).setCellValue(dataQuery.getRoomTypeByRoomID(book.getIdHabitacion()));

    RoomPrice (Precio de la Habitación): Este valor debería ser numérico (double o float) porque representa un precio. Sin embargo, la forma en que lo estás manejando con setCellValue en una celda de Excel es adecuada, ya que setCellValue(double value) acepta valores numéricos.

Si los precios aún se muestran incorrectamente (por ejemplo, como 0), verifica lo siguiente:
Validaciones Necesarias
A. Verificar que getRoomPriceByRoomID Retorne un double

Asegúrate de que el método getRoomPriceByRoomID devuelve un valor numérico correcto. Ejemplo:

public double getRoomPriceByRoomID(int roomID) {
    double roomPrice = 0.0;
    String sql = "SELECT Precio FROM Habitaciones WHERE ID = ?";

    try (Connection conn = database.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        pstmt.setInt(1, roomID);
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            roomPrice = rs.getDouble("Precio"); // Devuelve el precio como un número
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return roomPrice;
}

B. Validar el Valor Devuelto en el Código

Imprime el valor devuelto por el método getRoomPriceByRoomID antes de asignarlo a la celda:

double price = dataQuery.getRoomPriceByRoomID(book.getIdHabitacion());
System.out.println("Precio obtenido: " + price);
row.createCell(6).setCellValue(price); // Este método acepta valores numéricos

C. Configurar el Estilo de la Celda para Mostrar Números

En Excel, si el precio se muestra como 0, el problema podría ser el formato de la celda. Puedes establecer un estilo numérico en la celda:

CellStyle numericStyle = workbook.createCellStyle();
DataFormat format = workbook.createDataFormat();
numericStyle.setDataFormat(format.getFormat("#,##0.00")); // Formato numérico con 2 decimales
row.createCell(6).setCellValue(price);
row.getCell(6).setCellStyle(numericStyle); // Aplica el estilo a la celda

Conclusión

No necesitas hacer RoomType numérico, pero sí asegurarte de que:

    RoomPrice es un double en tu código.
    El valor devuelto es correcto.
    Las celdas de Excel que muestran el precio tienen un formato adecuado para números.

Si después de estas correcciones el problema persiste, puede haber un error en la consulta SQL o en los datos almacenados en tu base de datos.
Tú dijiste:
databaseInsert.insertRoom(101, "Sencilla", 50.0);
        databaseInsert.insertRoom(102, "Doble", 80.0);
        databaseInsert.insertRoom(105, "Sencilla", 50.0);
        databaseInsert.insertRoom(106, "Doble", 80.0);
Generando archivo Excel...
ERROR StatusLogger Log4j2 could not find a logging implementation. Please add log4j-core to the classpath. Using SimpleLogger to log to the console...
Precio obtenido: 0.0
Precio obtenido: 0.0
Precio obtenido: 0.0
Precio obtenido: 0.0
ChatGPT Plus
ChatGPT

El mensaje de error indica que el precio obtenido es siempre 0.0. Esto sugiere un problema en el flujo de datos, probablemente en:

    Los datos en la base de datos.
    La consulta SQL o el método getRoomPriceByRoomID.
    La conexión entre el ID de la habitación y los datos de precios.

Verificaciones Paso a Paso
1. Revisar los datos en la base de datos

Ejecuta la consulta directamente en la base de datos para verificar si los precios están correctamente almacenados:

SELECT * FROM Habitaciones;

Confirma que:

    Los precios (Precio) están presentes y correctos.
    Los IDs (ID) coinciden con los IDs usados en las reservas.

2. Revisar el Método getRoomPriceByRoomID

Asegúrate de que el método está buscando correctamente los precios. Este debería ser el método completo:

public double getRoomPriceByRoomID(int roomID) {
    double roomPrice = 0.0; // Valor predeterminado
    String sql = "SELECT Precio FROM Habitaciones WHERE ID = ?";

    try (Connection conn = database.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        pstmt.setInt(1, roomID); // Establece el parámetro
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            roomPrice = rs.getDouble("Precio"); // Obtiene el precio como double
        } else {
            System.out.println("No se encontró la habitación con ID: " + roomID);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return roomPrice;
}

3. Revisar la Relación entre Habitaciones y Reservas

Confirma que las habitaciones relacionadas con las reservas tienen un ID válido en la tabla Habitaciones. Usa esta consulta SQL para validar:

SELECT r.ID AS ReservaID, h.ID AS HabitacionID, h.Precio
FROM Reservas r
LEFT JOIN Habitaciones h ON r.ID_Habitacion = h.ID;

    Verifica que la columna HabitacionID no sea NULL.
    Confirma que los precios (Precio) están correctamente asociados.

4. Imprimir el ID de Habitación Antes de Buscar el Precio

Antes de llamar a getRoomPriceByRoomID, imprime el ID de la habitación:

System.out.println("ID de Habitación: " + book.getIdHabitacion());
double price = dataQuery.getRoomPriceByRoomID(book.getIdHabitacion());
System.out.println("Precio obtenido: " + price);

Esto ayuda a confirmar si el ID de habitación pasado al método es correcto.
5. Verificar la Inserción de Datos

El código de inserción también debe ser revisado. Por ejemplo:

public void insertRoom(int numero, String tipo, double precio) {
    String sql = "INSERT INTO Habitaciones (Numero, Tipo, Precio) VALUES (?, ?, ?)";

    try (Connection conn = database.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        pstmt.setInt(1, numero);
        pstmt.setString(2, tipo);
        pstmt.setDouble(3, precio);
        pstmt.executeUpdate();

    } catch (Exception e) {
        e.printStackTrace();
    }
}

Asegúrate de que:

    Este método se ejecuta antes de generar el archivo Excel.
    Los valores de los parámetros (numero, tipo, precio) son correctos.

6. Revisar la Clase de Conexión a la Base de Datos

Si los pasos anteriores no revelan problemas, asegúrate de que la conexión a la base de datos sea la correcta. Si estás utilizando diferentes esquemas o bases, los datos pueden no estar sincronizados.
Conclusión

Siguiendo los pasos anteriores, deberías poder identificar dónde se genera el problema (datos, consultas o lógica del programa). Una vez que lo identifiques, ajusta el código o los datos en consecuencia. Si encuentras que algún paso específico no funciona como se espera, compárteme los resultados para ayudarte a resolverlo.
Tú dijiste:
Precio obtenido: 0.0
No se encontró la habitación con ID: 106
Precio obtenido: 0.0
No se encontró la habitación con ID: 106
Precio obtenido: 0.0
50.0
80.0
50.0
ChatGPT Plus
ChatGPT

El mensaje "No se encontró la habitación con ID: 106" indica que la consulta no está encontrando las habitaciones con ID 106, lo que sugiere que el ID en la tabla Habitaciones podría estar incorrecto o que hay un problema con la consulta SQL.

Aquí hay algunas posibles causas y soluciones:
1. Revisar la Tabla Habitaciones

Ejecuta la siguiente consulta directamente en la base de datos para asegurarte de que las habitaciones con ID 106 (y otros IDs) existen:

SELECT * FROM Habitaciones WHERE ID = 106;

Esto debería devolver la fila correspondiente con el ID 106, y deberías ver el precio y tipo de habitación.
2. Verificar la Inserción de Datos

Asegúrate de que los datos están siendo insertados correctamente. Si la habitación con ID 106 no aparece, entonces el método de inserción podría haber fallado o el valor de ID no está siendo establecido correctamente.

Ejecuta esta consulta para verificar todas las habitaciones insertadas:

SELECT * FROM Habitaciones;

Verifica que el campo ID esté siendo asignado correctamente y que las habitaciones estén insertadas en la tabla.
3. Verificar si el ID en la Reserva es Correcto

Asegúrate de que en la tabla Reservas el ID_Habitacion es el correcto para cada reserva. Ejecuta esta consulta para ver la relación entre reservas y habitaciones:

SELECT r.ID AS ReservaID, r.ID_Habitacion, h.ID, h.Tipo, h.Precio
FROM Reservas r
LEFT JOIN Habitaciones h ON r.ID_Habitacion = h.ID;

Esto te permitirá ver si las habitaciones están correctamente asociadas a las reservas. Si ID_Habitacion en la tabla Reservas no coincide con ID en la tabla Habitaciones, habrá un problema en los datos de las reservas.
4. Imprimir Datos de la Consulta

Imprime todos los resultados obtenidos en la consulta para ver si la base de datos devuelve el valor esperado:

public double getRoomPriceByRoomID(int roomID) {
    double roomPrice = 0.0; // Valor predeterminado
    String sql = "SELECT Precio FROM Habitaciones WHERE ID = ?";

    try (Connection conn = database.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        pstmt.setInt(1, roomID); // Establece el parámetro
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            roomPrice = rs.getDouble("Precio"); // Obtiene el precio como double
            System.out.println("Precio obtenido para habitación ID " + roomID + ": " + roomPrice);
        } else {
            System.out.println("No se encontró la habitación con ID: " + roomID);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return roomPrice;
}

5. Problema con los Datos en la Base de Datos

Si después de revisar todo esto el problema persiste, es posible que haya un problema en cómo se están insertando o actualizando los datos. Intenta insertar una nueva habitación con ID 106 y asegúrate de que la reserva esté correctamente relacionada con esa habitación.

databaseInsert.insertRoom(106, "Doble", 80.0);

Asegúrate de que el ID de habitación en las reservas coincide con el ID de las habitaciones insertadas.
6. Confirmar que el ID es Correcto en el Objeto Book

Cuando pasas el ID_Habitacion al método, asegúrate de que realmente estás pasando el ID correcto. Imprime el ID_Habitacion antes de llamar al método:

System.out.println("ID de Habitación: " + book.getIdHabitacion());
double price = dataQuery.getRoomPriceByRoomID(book.getIdHabitacion());
System.out.println("Precio obtenido: " + price);

Conclusión

El problema radica en que la consulta no está encontrando las habitaciones por el ID que se le pasa. Asegúrate de que:

    Las habitaciones están correctamente insertadas en la base de datos.
    Las reservas están asociadas con los IDs correctos de las habitaciones.
    Los datos de las habitaciones (precio, tipo) están correctamente asociados.

Siguiendo estos pasos y verificaciones, deberías poder resolver el problema de obtener el precio de la habitación correctamente.
Tú dijiste:
lo que necesito es que le paso el id de la habitacion y a partir del numero sacar el numero de la habitacion y con ello sacar el precio y el tipo por separado
ChatGPT Plus
ChatGPT

Entendido, para lograr esto necesitas una lógica que realice lo siguiente:

    Obtener el número de habitación a partir del ID de la habitación.
    Usar ese número de habitación para obtener su tipo y precio.

Aquí está la implementación paso a paso:
Base de Datos

La tabla Habitaciones ya contiene el número, tipo y precio de la habitación. Las consultas que necesitas realizar serían:

    Obtener el número de habitación:

SELECT Numero FROM Habitaciones WHERE ID = ?;

Obtener el tipo de habitación:

SELECT Tipo FROM Habitaciones WHERE Numero = ?;

Obtener el precio de la habitación:

    SELECT Precio FROM Habitaciones WHERE Numero = ?;

Implementación en Java

Aquí tienes los métodos necesarios para realizar estas operaciones:
Obtener el Número de Habitación

public int getRoomNumberByRoomID(int roomID) {
    int roomNumber = -1; // Valor predeterminado
    String sql = "SELECT Numero FROM Habitaciones WHERE ID = ?";

    try (Connection conn = database.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        pstmt.setInt(1, roomID);
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            roomNumber = rs.getInt("Numero");
            System.out.println("Número de habitación obtenido para ID " + roomID + ": " + roomNumber);
        } else {
            System.out.println("No se encontró una habitación con ID: " + roomID);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return roomNumber;
}

Obtener el Tipo de Habitación

public String getRoomTypeByRoomNumber(int roomNumber) {
    String roomType = null; // Valor predeterminado
    String sql = "SELECT Tipo FROM Habitaciones WHERE Numero = ?";

    try (Connection conn = database.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        pstmt.setInt(1, roomNumber);
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            roomType = rs.getString("Tipo");
            System.out.println("Tipo de habitación obtenido para número " + roomNumber + ": " + roomType);
        } else {
            System.out.println("No se encontró una habitación con número: " + roomNumber);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return roomType;
}

Obtener el Precio de Habitación

public double getRoomPriceByRoomNumber(int roomNumber) {
    double roomPrice = 0.0; // Valor predeterminado
    String sql = "SELECT Precio FROM Habitaciones WHERE Numero = ?";

    try (Connection conn = database.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        pstmt.setInt(1, roomNumber);
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            roomPrice = rs.getDouble("Precio");
            System.out.println("Precio de habitación obtenido para número " + roomNumber + ": " + roomPrice);
        } else {
            System.out.println("No se encontró una habitación con número: " + roomNumber);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return roomPrice;
}

Llamada a los Métodos

Usa estos métodos para obtener la información que necesitas:

int roomNumber = dataQuery.getRoomNumberByRoomID(book.getIdHabitacion());
String roomType = dataQuery.getRoomTypeByRoomNumber(roomNumber);
double roomPrice = dataQuery.getRoomPriceByRoomNumber(roomNumber);

row.createCell(5).setCellValue(roomType);
row.createCell(6).setCellValue(roomPrice);

Verificación de Resultados

Asegúrate de imprimir los valores obtenidos para verificar que todo funciona correctamente:

System.out.println("Número de habitación: " + roomNumber);
System.out.println("Tipo de habitación: " + roomType);
System.out.println("Precio de habitación: " + roomPrice);

Conclusión

Esta solución divide la tarea en tres pasos:

    Obtener el número de habitación a partir del ID.
    Usar el número de habitación para obtener el tipo.
    Usar el número de habitación para obtener el precio.

Esto asegura que puedas manejar la información de manera separada y evita errores al trabajar con los datos.
Tú dijiste:
Tipo de habitación obtenido para número 102: Doble
Precio de habitación obtenido para ID 102: 80.0
Precio obtenido: 80.0
Tipo de habitación obtenido para número 102: Doble
Precio de habitación obtenido para ID 102: 80.0
Precio obtenido: 80.0
Tipo de habitación obtenido para número 105: Sencilla
Precio de habitación obtenido para ID 105: 50.0
Precio obtenido: 50.0
Tipo de habitación obtenido para número 105: Sencilla
Precio de habitación obtenido para ID 105: 50.0
Precio obtenido: 50.0
Tipo de habitación obtenido para número 105: Sencilla
Precio de habitación obtenido para ID 105: 50.0
Precio obtenido: 50.0
Tipo de habitación obtenido para número 105: Sencilla
Precio de habitación obtenido para ID 105: 50.0
Precio obtenido: 50.0
Tipo de habitación obtenido para número 105: Sencilla
Precio de habitación obtenido para ID 105: 50.0
Precio obtenido: 50.0
Tipo de habitación obtenido para número 105: Sencilla
Precio de habitación obtenido para ID 105: 50.0
Precio obtenido: 50.0
Tipo de habitación obtenido para número 105: Sencilla
Precio de habitación obtenido para ID 105: 50.0
Precio obtenido: 50.0
Tipo de habitación obtenido para número 105: Sencilla
Precio de habitación obtenido para ID 105: 50.0
Precio obtenido: 50.0
Tipo de habitación obtenido para número 106: Doble
Precio de habitación obtenido para ID 106: 80.0
Precio obtenido: 80.0
Tipo de habitación obtenido para número 106: Doble
Precio de habitación obtenido para ID 106: 80.0
Precio obtenido: 80.0
Tipo de habitación obtenido para número 106: Doble
Precio de habitación obtenido para ID 106: 80.0
Precio obtenido: 80.0
Tipo de habitación obtenido para número 106: Doble
Precio de habitación obtenido para ID 106: 80.0
Precio obtenido: 80.0
Tipo de habitación obtenido para número 106: Doble
Precio de habitación obtenido para ID 106: 80.0
Precio obtenido: 80.0
Tipo de habitación obtenido para número 106: Doble
Precio de habitación obtenido para ID 106: 80.0
Precio obtenido: 80.0
Tipo de habitación obtenido para número 106: Doble
Precio de habitación obtenido para ID 106: 80.0
Precio obtenido: 80.0
Tipo de habitación obtenido para número 106: Doble
Precio de habitación obtenido para ID 106: 80.0
Precio obtenido: 80.0
me da bien los valores pero luego en el excel pone 0 y el tipo esta vacio
 row.createCell(5).setCellValue(dataQuery.getRoomTypeByRoomNumber(book.getIdHabitacion()));
                    double price = dataQuery.getRoomNumberByRoomID(book.getIdHabitacion());
                    System.out.println("Precio obtenido: " + price);
                    row.createCell(6).setCellValue(price);
ChatGPT Plus
ChatGPT

El problema está en cómo los valores obtenidos (tipo y precio) se asignan a las celdas del archivo Excel. A continuación, explico las posibles causas y cómo corregirlas:
1. Asignación Incorrecta del Tipo de Habitación

    Estás utilizando el método getRoomTypeByRoomNumber con book.getIdHabitacion() que devuelve el ID de la habitación, no el número.
    Esto puede ser incorrecto si getRoomTypeByRoomNumber espera un número de habitación, no un ID.

Solución: Asegúrate de obtener primero el número de habitación con getRoomNumberByRoomID y luego usarlo para obtener el tipo:

int roomNumber = dataQuery.getRoomNumberByRoomID(book.getIdHabitacion());
String roomType = dataQuery.getRoomTypeByRoomNumber(roomNumber);
row.createCell(5).setCellValue(roomType);

2. Precio en Cero en el Excel

    El método getRoomNumberByRoomID está devolviendo el número de habitación, pero lo estás asignando directamente como el precio, lo cual es incorrecto.
    Necesitas llamar a getRoomPriceByRoomNumber para obtener el precio real basado en el número de habitación.

Solución: Utiliza el número de habitación para obtener el precio y luego asignarlo a la celda:

double roomPrice = dataQuery.getRoomPriceByRoomNumber(roomNumber);
row.createCell(6).setCellValue(roomPrice);

3. Formato de Datos en Excel

    Asegúrate de que las celdas donde escribes los valores en Excel están configuradas correctamente.
    Los datos numéricos (roomPrice) deben asignarse como double, y los datos de texto (roomType) como cadenas.

Código Corregido

Aquí está el código corregido:

// Obtener el número de habitación basado en el ID
int roomNumber = dataQuery.getRoomNumberByRoomID(book.getIdHabitacion());

// Obtener el tipo de habitación basado en el número
String roomType = dataQuery.getRoomTypeByRoomNumber(roomNumber);

// Escribir el tipo en la celda
row.createCell(5).setCellValue(roomType);

// Obtener el precio de la habitación basado en el número
double roomPrice = dataQuery.getRoomPriceByRoomNumber(roomNumber);

// Escribir el precio en la celda
row.createCell(6).setCellValue(roomPrice);

// Verificar en consola
System.out.println("Tipo de habitación: " + roomType);
System.out.println("Precio de habitación: " + roomPrice);

4. Errores Comunes y Cómo Solucionarlos

    Error: Valores nulos o vacíos en el Excel
    Si las celdas están vacías, verifica:
        Que los métodos getRoomTypeByRoomNumber y getRoomPriceByRoomNumber estén devolviendo valores correctos.
        Que no estés sobreescribiendo las celdas con valores incorrectos.
        Agrega mensajes de depuración como System.out.println para confirmar los valores antes de escribirlos en Excel.

    Error: El precio es 0.0
    Esto sucede si no estás llamando correctamente a getRoomPriceByRoomNumber o si el método está devolviendo un valor predeterminado (como 0.0) al no encontrar datos en la base.

Pruebas

    Imprime los valores de roomType y roomPrice antes de escribir en Excel:

    System.out.println("Tipo de habitación: " + roomType);
    System.out.println("Precio de habitación: " + roomPrice);

    Verifica si el archivo Excel tiene las celdas en el formato correcto:
        Tipo de habitación: Texto.
        Precio de habitación: Número (con formato de moneda si es necesario).

Con estas correcciones, deberías obtener los valores correctos en el archivo Excel.
tengo un problema al crear estos datos en el excel me lo deja vacio y el otro da 0
row.createCell(5).setCellValue(dataQuery.getRoomType(book.getIdHabitacion()));
                    double roomPrice = dataQuery.getRoomPrice(book.getIdHabitacion());
                    row.createCell(6).setCellValue(roomPrice);
public double getRoomPrice(int roomID) {
        double price = -1;
        String sql = "SELECT Precio FROM Habitaciones WHERE Numero = ?";

        try (Connection conn = database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, roomID);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                price = rs.getDouble("precio");
                System.out.println("Precio de habitación obtenido para ID " + roomID + ": " + price);
            } else {
                System.out.println("No se encontró una habitación con ID: " + roomID);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return price;
    }

    
    public String getRoomType(int roomNumber) {
        String roomType = null;
        String sql = "SELECT Tipo FROM Habitaciones WHERE Numero = ?";

        try (Connection conn = database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, roomNumber);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                roomType = rs.getString("Tipo");
                System.out.println("Tipo de habitación obtenido para número " + roomNumber + ": " + roomType);
            } else {
                System.out.println("No se encontró una habitación con número: " + roomNumber);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return roomType;
    }
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
ChatGPT Plus
ChatGPT

El problema de que los datos en Excel se muestren como vacíos o incorrectos (como 0) puede deberse a varios factores. Aquí tienes una guía para resolverlo:
Verificación del Código SQL y Datos de la Base de Datos

    Columna y datos en la base de datos:
        Asegúrate de que las columnas Precio y Tipo en la tabla Habitaciones contienen datos válidos y no están vacías o mal escritas.
        Revisa la inicialización de la base de datos y verifica que los datos de prueba se están insertando correctamente. Por ejemplo:

        INSERT INTO Habitaciones (Numero, Tipo, Precio) VALUES (101, 'Suite', 200.0);

        Asegúrate de que el campo Numero coincide con el valor que estás pasando como roomID.

    Case sensitivity en el nombre de las columnas:
        En el código, usas rs.getDouble("precio") y rs.getString("Tipo"). Asegúrate de que los nombres de las columnas (Precio, Tipo) coincidan exactamente con los nombres definidos en la tabla (Habitaciones).

Depuración del Código

Agrega mensajes de depuración en puntos clave para identificar dónde se produce el fallo.
Método getRoomPrice

if (rs.next()) {
    price = rs.getDouble("Precio"); // Asegúrate de que coincide con la base de datos
    System.out.println("Precio de habitación obtenido para ID " + roomID + ": " + price);
} else {
    System.out.println("No se encontró una habitación con ID: " + roomID);
}

Método getRoomType

if (rs.next()) {
    roomType = rs.getString("Tipo"); // Asegúrate de que coincide con la base de datos
    System.out.println("Tipo de habitación obtenido para número " + roomNumber + ": " + roomType);
} else {
    System.out.println("No se encontró una habitación con número: " + roomNumber);
}

Escritura en Excel

Si los métodos de obtención de datos (getRoomPrice, getRoomType) están funcionando correctamente, pero los valores en Excel no aparecen como esperas:

    Formato del precio:
        Si roomPrice es -1 (valor inicial del método getRoomPrice), indica que no se encontró la habitación en la base de datos.
        Verifica si el método setCellValue está escribiendo correctamente el valor en Excel.

        row.createCell(6).setCellValue(roomPrice);
        System.out.println("Escribiendo precio en Excel: " + roomPrice);

    Depuración de Excel:
        Revisa si la celda está configurada en el formato correcto. Por ejemplo, si es un número, asegúrate de que Excel no lo interpreta como texto.

Resolución Propuesta

    Validación de datos antes de escribir en Excel:

String roomType = dataQuery.getRoomType(book.getIdHabitacion());
if (roomType == null) {
    roomType = "Desconocido";
}
row.createCell(5).setCellValue(roomType);

double roomPrice = dataQuery.getRoomPrice(book.getIdHabitacion());
if (roomPrice == -1) {
    System.out.println("Error: Precio no disponible para la habitación " + book.getIdHabitacion());
    roomPrice = 0.0; // Valor predeterminado
}
row.createCell(6).setCellValue(roomPrice);

Verificación de datos en la tabla:

    Antes de ejecutar el programa, verifica que la tabla tiene los datos correctos:

        SELECT * FROM Habitaciones;

    Prueba de conexión a la base de datos:
        Asegúrate de que database.getConnection() está estableciendo la conexión correctamente y no produce excepciones.

Con estas acciones deberías ser capaz de identificar y solucionar el problema. Si algo aún no funciona, revisa los logs que se generan en la consola para más pistas.
Tú dijiste:
public String getRoomType(int roomNumber) {
        String roomType = null;
        String sql = "SELECT Tipo FROM Habitaciones WHERE Numero = ?";

        try (Connection conn = database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, roomNumber);
            ResultSet rs = pstmt.executeQuery();
            roomType = rs.getString(0);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return roomType;
    }
ChatGPT Plus
ChatGPT

Tu implementación actual de getRoomType tiene un error en esta línea:

roomType = rs.getString(0);

El índice en los métodos ResultSet.getXXX no es basado en cero, sino en uno, o puedes usar el nombre exacto de la columna (rs.getString("Tipo")). Además, necesitas asegurarte de que rs.next() se invoca antes de intentar extraer datos del ResultSet.
Corrección del Método getRoomType

Aquí está el código corregido:

public String getRoomType(int roomNumber) {
    String roomType = null;
    String sql = "SELECT Tipo FROM Habitaciones WHERE Numero = ?";

    try (Connection conn = database.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        pstmt.setInt(1, roomNumber);
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) { // Asegúrate de mover el cursor al primer resultado
            roomType = rs.getString("Tipo"); // Usa el nombre de la columna o el índice basado en 1
        } else {
            System.out.println("No se encontró una habitación con número: " + roomNumber);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return roomType;
}

Explicación de las Correcciones

    Mover el cursor al primer resultado:
        Debes llamar a rs.next() antes de intentar acceder a los valores del ResultSet. Esto mueve el cursor al primer registro.

    Índice basado en uno:
        rs.getString(1) sería válido, pero en este caso, usar el nombre de la columna es más claro: rs.getString("Tipo").

    Validación en caso de datos inexistentes:
        Si no se encuentra un resultado, el método devuelve null, y un mensaje se registra en la consola para facilitar la depuración.

Con este código corregido, el método debería funcionar correctamente siempre que la base de datos contenga datos válidos. Asegúrate de probarlo con varios valores de roomNumber para confirmar.
Tú dijiste:
git esta ignorande el gitignore subiendo ficheros que no quiero
# Compiled source #
###################
*.com
*.class
*.dll
*.exe
*.o
*.so
*.db
*.xlsx
*.pdf

# Packages #
############
# it's better to unpack these files and commit the raw source
# git has its own built in compression methods
*.7z
*.dmg
*.gz
*.iso
*.jar
*.rar
*.tar
*.zip

# Logs and databases #
######################
*.log
*.sql
*.sqlite

# OS generated files #
######################
.DS_Store
.DS_Store?
._*
.Spotlight-V100
.Trashes
ehthumbs.db
Thumbs.db
motel.db
ChatGPT Plus
ChatGPT

Si Git está ignorando tu archivo .gitignore y sigue subiendo archivos que deberían ser excluidos, puede ser por uno de los siguientes problemas comunes:
1. Los archivos ya están siendo rastreados por Git

Si los archivos ya fueron añadidos al repositorio antes de configurar el .gitignore, Git continuará rastreándolos aunque ahora estén incluidos en el .gitignore.
Solución:

Elimina los archivos del control de versiones sin borrarlos del sistema de archivos con el siguiente comando:

git rm --cached <ruta_del_archivo_o_patron>

Por ejemplo, si deseas eliminar todos los archivos .pdf del seguimiento:

git rm --cached *.pdf

Después, confirma los cambios y haz un commit:

git commit -m "Eliminar archivos del seguimiento según .gitignore"

2. Errores en el formato del .gitignore

    Verifica que el archivo .gitignore esté correctamente nombrado y ubicado en la raíz del repositorio.
    Asegúrate de no tener caracteres inesperados en las entradas. Por ejemplo, no debe haber espacios en blanco adicionales o caracteres de escape innecesarios.

Verifica entradas específicas:

Para asegurarte de que una regla específica está funcionando, puedes probar el patrón con:

git check-ignore -v <ruta_del_archivo>

Esto mostrará si el archivo está siendo ignorado y por qué regla.
3. Reglas superpuestas en .gitignore

Si otro .gitignore más específico existe en un subdirectorio, puede estar anulando reglas definidas en el archivo principal. Busca otros archivos .gitignore en tu proyecto.
Solución:

Si encuentras múltiples .gitignore, asegúrate de que no estén contradiciéndose. Combina las reglas en un único archivo si es necesario.
4. Archivos con inclusión explícita

Si algún archivo fue añadido explícitamente con git add -f, permanecerá en el control de versiones incluso si está en el .gitignore.
Solución:

Elimina estos archivos del seguimiento con:

git rm --cached <archivo>

Pasos Finales: Asegurarte de que .gitignore está funcionando

    Actualiza el índice: Refresca el índice de Git para que respete las reglas del .gitignore:

git rm -r --cached .
git add .
git commit -m "Actualizar .gitignore"

Prueba con nuevos archivos: Intenta añadir un archivo que debería ser ignorado según las reglas para confirmar que no se incluye:

    touch prueba.pdf
    git add prueba.pdf

    Si el archivo está siendo ignorado correctamente, Git debería mostrar un mensaje indicando que no se puede añadir.

Confirmación

Si sigues enfrentando problemas, comparte el contenido de tu .gitignore y los nombres exactos de los archivos que Git está rastreando para ayudarte a diagnosticar mejor.