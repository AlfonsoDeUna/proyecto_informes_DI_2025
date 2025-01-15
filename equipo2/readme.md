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