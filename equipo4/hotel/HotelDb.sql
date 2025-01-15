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

-- Insertar datos en la tabla Clientes
INSERT INTO Clientes (Nombre, Apellido, Telefono, Email) VALUES 
('Juan', 'Pérez', '123456789', 'juan.perez@example.com'),
('María', 'González', '987654321', 'maria.gonzalez@example.com'),
('Carlos', 'López', '456123789', 'carlos.lopez@example.com'),
('Ana', 'Martínez', '321654987', 'ana.martinez@example.com'),
('Sofía', 'Hernández', '789456123', 'sofia.hernandez@example.com');

-- Insertar datos en la tabla Habitaciones
INSERT INTO Habitaciones (Numero, Tipo, Precio) VALUES 
(101, 'Individual', 50.00),
(102, 'Doble', 75.00),
(103, 'Suite', 150.00),
(104, 'Doble', 80.00),
(105, 'Individual', 55.00);

-- Insertar datos en la tabla Reservas
INSERT INTO Reservas (ID_Cliente, ID_Habitación, Fecha_Inicio, Fecha_Fin, Total) VALUES 
(1, 101, '2025-01-10', '2025-01-12', 100.00),  -- Juan reserva la habitación 101
(2, 103, '2025-01-15', '2025-01-18', 450.00),  -- María reserva la habitación 103
(3, 102, '2025-02-01', '2025-02-05', 300.00),  -- Carlos reserva la habitación 102
(4, 104, '2025-02-10', '2025-02-14', 320.00),  -- Ana reserva la habitación 104
(5, 105, '2025-03-01', '2025-03-02', 55.00);   -- Sofía reserva la habitación 105
