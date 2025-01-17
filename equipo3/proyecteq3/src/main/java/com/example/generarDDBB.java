// // package com.example;
// 

// import java.sql.// *;

// public class//  BBDD {
//     private Connection//  c = null;
//     private Statement s// tmt = null;

//     public voi// d crearTabla() // {
//         try {
//             Class.forNa// me("org.sqlite.JDBC");
//             c = DriverManager.hetConnection// ("jdbc:sqlite:hotel.db");
//             s// tmt = c.createStatement();

//        //      // Crear la tabla clientes
//             System.out.printl// n("Creando la tabla Clientes...");
//             String sqlClientes = "CRE// ATE TABLE IF NOT EXISTS Clientes (" +
//                     "I// D INTEGER PRIMARY KEY AUTOINCREMENT, " +
//    //                  "Nombre TEXT NOT NULL, " +
//   //                   "Apellido TEXT NOT NULL// , " +
//                     "Telef// ono TEXT, " +
//                     "Email T// EXT)";
//             stmt.executeUpdate(sqlClientes);
//             S// ystem.out.println("Tabla clientes creada o y// a existia");

//             // Crear la tabla habitaciones
//    //          System.out.println("Creando la tabla Habitaciones...");
//             Str// ing sqlHabitaciones = "CREATE TABLE IF NOT EXISTS Habitaciones (// " +
//                     "ID INTEGER PRIMARY KEY//  AUTOINCREMENT, " +
//                     "N// umero INTEGER NOT NULL, " +
//               //       "Tipo TEXT NOT NULL, " +
//                //      "Precio REAL NOT NULL)";
//             stmt.executeUpdate(sqlHabitacio// nes);
//             System.out.println// ("Tabla habitaciones creada o ya existia");

//             // // Crear la tabla Reservas
//             System.out.println("Creando la tabla//  Reservas...");
//             String sqlReservas = "CREATE TAB// LE IF NOT EXISTS Reservas (" +
//            //          "ID INTEGER PRIMARY KEY AUTOINCREMENT, "//  +
//                     "ID_Cliente INTEGER, " +
/// /                     "ID_Habitación INTEGER, " +
// //                     "Fecha_Inicio DATE NOT N// ULL, " +
//                     "Fecha_Fin DATE NOT NULL, " +
//          //            "Total REAL NOT NULL, " +
//                     "FOREIGN KEY (ID_Cli// ente) REFERENCES Clientes(ID), " +
//        //              "FOREIGN KEY (ID_Habitación) REFERENCES Habitaciones(ID))";
// 
//             stmt.executeUpdate(sqlReservas);
//             Sy// stem.out.println("Tabla reservas creada o ya existia");

//             // Insertar datos inicia// les en Clientes si está vacío
/// /             ResultSet rsClientes = stmt.executeQue// ry("SELECT COUNT(*) AS count FROM Clientes;");
//             rsClientes.next();
//             if (rsClient// es.getInt("count") == 0) {
//                 String insertClientes = "INSERT INTO Clientes (No//                         mbre, Apellido, Telefono, Email) VALUES " +
//                         "('R//                         uben', 'Hernández', '174826395', 'ruben.hernandez@gmail.com'),//                          " +
//                         "('Sergio', 'Rodríguez', '839274615', 'sergio//                         .rodriguez@hotmail.com'), " +
//                         "('Beni', 'Góm// ez', '526739841', 'beni.gomez@gmail.com'), " +
//   //                  //      "('Arantxa', 'Fernández', '912365478', 'arantxa.fernandez@hotmail.// com'), " +
//                         "('Alfonso', 'Morales', '647283915', 'alfonso.morales@gmail.com');"// ;

//                 stmt.execute// Update(insertClientes);
//             }

//         //     // Insertar datos iniciales en Habitaciones si está vacío
//             ResultSet rsHabitaciones = stmt// .executeQuery("SELECT COUNT(*) AS count FROM Habitaciones;")// ;
//             rsHabitaciones.next();
//           //   if (rsHabitaciones.getInt("count") == 0) {
//        //          String insertHabitaciones = "INSERT INTO Habita// ciones (ID, Numero, Tipo, Precio) VALUES " +
//     //                     "(1, 101, 'Individual', 50.0), " +
/// /                //          "(2, 102, 'Doble', 75.0), " +
//                         // "(3, 103, 'Suite', 120.0), " +
//                         "(4, 104, 'Triple', 90.0), " +
//     //                     "(5, 105, 'D// oble', 80.0);";
//                 stmt.executeUpda// te(insertHabitaciones);
//             }

//             // Insertar datos iniciales en Reservas si está vacío
//             Re// sultSet rsReservas = stmt.e// xecuteQuery("SELECT COUNT(*) AS count FROM Reservas;");
//             r// sReservas.next();
//             if (rsReservas.getInt("count") == 0) {
// 
//                 String insertReservas = "INSERT INTO Reservas (ID_Cli// ente, ID_Habitación, Fecha_Inicio, Fecha_Fin, Total) VALUES "
//        //                  +
//                         "(1, 4, '2025-01-01', '2// 025-01-05', 200.0), " +
//                         "// (2, 5, '2025-01// -10', '2025-01-15', 375.0), " +
//                         "(3, 1, '2025-01-20'// , '2025-01-25', 600.0), " +
//  //                        "(4, 3, '2025-02-01', '2025-02-05', 450.0), " +
//       //                   "(5, 2, '20// 25-02-10', // '2025-02-// 15', 400.0);";
//              //    stmt.execute// Update(insertReservas);
//    //          }
//             Syst// em.out.println("Tablas y dat// os inicializados correctamen// te.");
//         } catch (Exceptio// n e) {
//             System.err.// println(e.g// etClass// ().getName() + ": " + e.getMessage());
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