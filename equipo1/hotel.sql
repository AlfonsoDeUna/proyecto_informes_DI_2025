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