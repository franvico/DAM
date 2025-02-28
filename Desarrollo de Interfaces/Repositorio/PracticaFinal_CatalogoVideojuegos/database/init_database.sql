-- CREAR BASE DE DATOS
DROP DATABASE IF EXISTS catalogo_videojuegos;

CREATE DATABASE catalogo_videojuegos
CHARACTER SET utf8
COLLATE utf8_general_ci;

-- GENERAR TABLAS

CREATE TABLE usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    foto VARCHAR(500) DEFAULT 'default.png',
    baneado BOOLEAN DEFAULT FALSE,
    admin BOOLEAN DEFAULT FALSE
);

CREATE TABLE videojuegos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    precio DECIMAL(10,2) NULL,
    portada VARCHAR(500) DEFAULT 'default.png'
);

CREATE TABLE compras (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT NOT NULL,
    ids_juegos TEXT NOT NULL,
    fecha DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id) ON DELETE CASCADE
);

-- VALORES INICIALES (USUARIO ADMIN)
INSERT INTO usuarios (nombre, email, password, baneado, admin)  
VALUES ('admin', 'admin@catalogo_videojuegos.es', 'admin', FALSE, TRUE);