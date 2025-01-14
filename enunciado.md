# Sistema de Reporting para Gestión de Reservas de Hotel

El objetivo del proyecto es desarrollar una aplicación que permita gestionar las reservas de un hotel y generar reportes detallados en formato PDF y Excel. La aplicación debe cubrir las siguientes funcionalidades:

1. **Gestión de la Base de Datos**  
   - Creación de tablas para clientes, habitaciones y reservas.  
   - Inserción y consulta de datos en la base de datos.

2. **Generación de Reportes**  
   - Reporte PDF: Lista de reservas con detalles del cliente y la habitación. (librería vista en clase 
   - Reporte Excel: Resumen semanal/mensual de ocupación del hotel.

3. **Documentación y Pruebas**  
   - Documentar el diseño de la base de datos y la estructura del código.  
   - Incluir pruebas unitarias y de integración.
   - Diario de uso de chatGPT

## Especificaciones Técnicas

#### 1. Base de Datos

**Tablas requeridas**:
- **Clientes**: `ID`, `Nombre`, `Apellido`, `Teléfono`, `Email`
- **Habitaciones**: `ID`, `Número`, `Tipo`, `Precio por noche`
- **Reservas**: `ID`, `ID_Cliente`, `ID_Habitación`, `Fecha_Inicio`, `Fecha_Fin`, `Total`

**Creación de Tablas** (SQLite) puede ser otro gestor de base de datos.

```sql
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
```

### Requisitos del Proyecto

1. **Entrega de Código**  
   - Código Java completo para la base de datos, PDF y Excel.
   - Comentarios explicativos en el código.

2. **Documentación**  
   - Diagrama ER de la base de datos.  
   - Explicación de cada módulo del sistema.  
   - Manual de usuario.
   -  registrar todas las interacciones con ChatGPT en un "diario de trabajo" donde anoten:
        - Preguntas realizadas.
        - Respuestas obtenidas.
        - Modificaciones realizadas al código sugerido

3. **Pruebas**  
   - Realizar pruebas con datos reales y ficticios.  
   - Incluir capturas de pantalla de los reportes generados.

4. **Evaluación del Proyecto**  
   - Correcta implementación de la base de datos y el código 
   - Reportes bien formateados y funcionales.  
   - Calidad del código y documentación.
   - modificaciones pedidas por mí.
   - Explicaciones en el momento de la parte que te pida del código.
   - Comprensión del problema y capacidad de identificar y corregir errores.
   - Reflexión crítica sobre las respuestas de ChatGPT.

