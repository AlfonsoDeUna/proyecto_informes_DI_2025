# Proyecto
Sistema de Reporting para Gestión de Reservas de Hotel
## Intro
Desarrollo de aplicación que gestiona las reservas de un hotel y genera reportes detallados en formato PDF y Excel. 
## Nombres del equipo
Angel García Ojeda, Mario Serradilla Penido y Daniel Jiménez Zapata
## Documentación del proyecto
### Clase Cliente
El código define una clase llamada Cliente que pertenece al paquete com.proyecto. Esta clase modela un cliente con cinco atributos privados: id (un número entero), nombre, apellido, telefono, y email (todos de tipo String). Estos atributos representan información básica de un cliente.

El constructor de la clase permite inicializar todos los atributos al momento de crear un objeto Cliente. La encapsulación de los atributos (declarados como private) sugiere que no se pueden acceder directamente desde fuera de la clase, sino que se requerirán métodos adicionales, como getters y setters, para interactuar con ellos si se necesitan más adelante.

En resumen, esta clase es una base para almacenar y manejar información de clientes en un proyecto.

### Clase DataBaseConnection
El código define una clase llamada DataBaseConnection que pertenece al paquete com.proyecto. Su objetivo es manejar la conexión a una base de datos SQLite llamada hotel.db.

Esta clase tiene un atributo constante y estático (URL), que almacena la dirección de la base de datos SQLite. El método público y estático getConnection utiliza esta URL para establecer y devolver una conexión a la base de datos mediante DriverManager. Al declarar el método como estático, se puede invocar directamente sin necesidad de crear una instancia de la clase.

En resumen, esta clase actúa como un punto central para obtener conexiones a la base de datos, simplificando la interacción con SQLite y asegurando que el manejo de la conexión esté centralizado.

### Clase DatabaseInitializer
El código define una clase llamada DatabaseInitializer dentro del paquete com.proyecto. Su propósito es inicializar las tablas necesarias para un sistema de gestión, asegurándose de que las tablas existan en una base de datos SQLite.

El método principal de esta clase, initializeDatabase, es estático y se encarga de lo siguiente:

Establece una conexión con la base de datos utilizando la clase DataBaseConnection.
Crea un objeto Statement para ejecutar comandos SQL.
Define tres tablas mediante sentencias SQL:
Clientes: Para almacenar información de clientes con atributos como ID, Nombre, Apellido, Telefono y Email.
Habitaciones: Para registrar habitaciones del hotel con atributos como ID, Numero, Tipo y Precio.
Reservas: Para manejar las reservas, con atributos como ID, claves foráneas a las tablas Clientes y Habitaciones, fechas de inicio y fin de la reserva, y un total.
Ejecuta los comandos SQL para crear las tablas solo si no existen previamente.
Imprime un mensaje indicando que la base de datos se inicializó correctamente o, en caso de error, imprime la traza del error.
En resumen, esta clase centraliza la lógica para configurar la estructura inicial de la base de datos, facilitando el despliegue del sistema.

### Clase ExportarTablaCSV
La clase ExportarTablaACSV dentro del paquete com.proyecto tiene como propósito generar un archivo CSV con información simulada. Este archivo puede ser utilizado para reportes o exportación de datos.

El método principal de la clase, reportar, realiza lo siguiente:

Define el nombre del archivo CSV: El archivo generado se llamará TablaExportada.csv.
Simula datos: Se crea una matriz datos que contiene filas y columnas representando información ficticia de clientes, habitaciones y fechas de reserva.
Genera el archivo CSV:
Utiliza un objeto FileWriter para escribir en el archivo.
Itera por cada fila de la matriz y escribe sus elementos en el archivo, separándolos por punto y coma (;).
Asegura que cada fila se termine con un salto de línea (\n).
Manejo de errores: Si ocurre algún problema durante la escritura, como permisos de archivo o problemas de almacenamiento, se captura la excepción y se imprime la traza del error.
Notificación: Al terminar con éxito, imprime un mensaje indicando que el archivo CSV fue generado correctamente.
En resumen, esta clase es una utilidad sencilla para exportar datos en formato CSV, utilizando datos simulados en este caso. Es útil para crear reportes en un formato ampliamente compatible.

### Clase PDFReportGenerator
La clase PdfReportGenerator, ubicada en el paquete com.proyecto, se encarga de generar un archivo PDF que contiene un reporte de reservas de hotel. Utiliza la biblioteca iText para manejar la creación y formato del documento PDF.

El método principal, generateReport, realiza las siguientes acciones:

Inicialización del PDF:

Crea un objeto PdfWriter que define el archivo de destino.
Usa PdfDocument y Document para gestionar el contenido del PDF.
Configuración de estilos:

Define una fuente en negrita (Helvetica-Bold) para darle estilo al texto del documento.
Configura un título con un tamaño de fuente mayor (18) y color azul (DeviceRgb(0, 102, 204)).
Creación del contenido:

Añade un título al documento, "Reporte de Reservas de Hotel".
Genera una tabla con tres columnas para mostrar datos ficticios de clientes, habitaciones y fechas de reserva.
Agrega datos de ejemplo en la tabla, como "Juan Pérez", "101", y "2025-01-10".
Finalización:

Agrega todos los elementos al documento y lo cierra, asegurándose de guardar correctamente el archivo.
Muestra un mensaje en la consola indicando que el PDF se generó exitosamente.
Manejo de errores:

Captura cualquier excepción que pueda ocurrir durante el proceso, como problemas al escribir el archivo, y muestra la traza del error.
En resumen, esta clase genera un PDF bien estructurado con un título destacado y una tabla de datos ficticios, siendo útil para la creación de reportes profesionales en el contexto de reservas de hotel.

### Clase Main
La clase Main, ubicada en el paquete com.proyecto, actúa como el punto de entrada principal para la ejecución del programa. Su objetivo es coordinar las tareas principales relacionadas con la inicialización de la base de datos y la generación de reportes en formatos PDF y CSV.

El método main realiza las siguientes acciones:

Inicialización de la base de datos:

Llama al método initializeDatabase de la clase DatabaseInitializer, que asegura que las tablas necesarias están configuradas en la base de datos SQLite.
Generación de un reporte PDF:

Define una ruta de destino para el archivo PDF, "ReporteHotelEquipo1.pdf".
Utiliza el método generateReport de la clase PdfReportGenerator para crear un archivo PDF con datos simulados de reservas de hotel.
Exportación de datos a un archivo CSV:

Define una ruta de destino para el archivo CSV, "ReporteHotelEquipo1Excel.csv".
Llama al método reportar de la clase ExportarTablaACSV, que crea un archivo CSV con información simulada.
En resumen, la clase Main orquesta el flujo general del programa, asegurando que:

Se prepare la base de datos.
Se generen reportes en formatos PDF y CSV. Esto la convierte en el núcleo funcional del sistema, integrando todas las funcionalidades desarrolladas en las demás clases.

### Manual de usuario
 [MANUAL DE USUARIO](<./equipo1/ManualDeUsuario.pdf>)

modelo entidad relacion:
![image](https://github.com/user-attachments/assets/633bced4-b0a0-473a-8f9f-a79cbb88513c)


