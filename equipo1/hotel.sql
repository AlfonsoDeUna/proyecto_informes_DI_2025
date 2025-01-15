CREATE TABLE Clientes (
    ID INTEGER PRIMARY KEY auto_increment,
    Nombre TEXT NOT NULL,
    Apellido TEXT NOT NULL,
    Telefono TEXT,
    Email TEXT
);

CREATE TABLE Habitaciones (
    ID INTEGER PRIMARY KEY auto_increment,
    Numero INTEGER NOT NULL,
    Tipo TEXT NOT NULL,
    Precio REAL NOT NULL
);

CREATE TABLE Reservas (
    ID INTEGER PRIMARY KEY auto_increment,
    ID_Cliente INTEGER,
    ID_Habitación INTEGER,
    Fecha_Inicio DATE NOT NULL,
    Fecha_Fin DATE NOT NULL,
    Total REAL NOT NULL,
    FOREIGN KEY (ID_Cliente) REFERENCES Clientes(ID),
    FOREIGN KEY (ID_Habitación) REFERENCES Habitaciones(ID)
);

INSERT INTO Clientes (Nombre, Apellido, Telefono, Email)
VALUES
('Juan', 'Pérez', '123456789', 'juan.perez@example.com'),
('María', 'Gómez', '987654321', 'maria.gomez@example.com'),
('Carlos', 'López', '456123789', 'carlos.lopez@example.com');

INSERT INTO Habitaciones (Numero, Tipo, Precio)
VALUES
(101, 'Individual', 50.00),
(102, 'Doble', 75.00),
(103, 'Suite', 120.00),
(104, 'Doble', 80.00);

INSERT INTO Reservas (ID_Cliente, ID_Habitación, Fecha_Inicio, Fecha_Fin, Total)
VALUES
(1, 101, '2025-01-20', '2025-01-22', 100.00),
(2, 103, '2025-01-25', '2025-01-28', 360.00),
(3, 102, '2025-02-01', '2025-02-03', 150.00);
