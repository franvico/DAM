CREATE DATABASE recetas;
USE recetas;

CREATE TABLE cocina (
    id_cocina INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL
);


CREATE TABLE ingrediente (
    id_ingrediente INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL 
);


CREATE TABLE receta (
    id_receta INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL ,
    id_cocina INT,
    FOREIGN KEY (id_cocina) REFERENCES cocina(id_cocina) 
);


CREATE TABLE receta_ingrediente (
    id_receta INT,
    id_ingrediente INT,
    PRIMARY KEY (id_receta, id_ingrediente),
    FOREIGN KEY (id_receta) REFERENCES receta(id_receta),
    FOREIGN KEY (id_ingrediente) REFERENCES ingrediente(id_ingrediente)
);