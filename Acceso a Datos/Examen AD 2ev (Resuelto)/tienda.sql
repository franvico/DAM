create database tienda;

use tienda;

CREATE TABLE `usuarios` (
  `id_usuario` int NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_usuario`)
);

CREATE TABLE `pedidos` (
  `id_pedido` int NOT NULL AUTO_INCREMENT,
  `id_usuario` int DEFAULT NULL,
  `fecha_pedido` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id_pedido`),
  KEY `FK4a0lfwlpmytywxpwjfa1a3ar2` (`id_usuario`),
  CONSTRAINT `FK4a0lfwlpmytywxpwjfa1a3ar2` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`)
);

CREATE TABLE `productos` (
  `id_producto` int NOT NULL AUTO_INCREMENT,
  `precio` double DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_producto`)
);

CREATE TABLE `detalles_pedido` (
  `cantidad` int DEFAULT NULL,
  `id_pedido` int NOT NULL,
  `id_producto` int NOT NULL,
  PRIMARY KEY (`id_pedido`,`id_producto`),
  KEY `FKpswk4x0p0wk0myw3f7penop0q` (`id_producto`),
  CONSTRAINT `FKpswk4x0p0wk0myw3f7penop0q` FOREIGN KEY (`id_producto`) REFERENCES `productos` (`id_producto`),
  CONSTRAINT `FKrvkloxugyfhcls33cvc1no8rm` FOREIGN KEY (`id_pedido`) REFERENCES `pedidos` (`id_pedido`)
);



-- INSERTAR VALORES

INSERT INTO `usuarios` (`email`, `nombre`) VALUES
('juan.perez@example.com', 'Juan Pérez'),
('maria.gomez@example.com', 'María Gómez'),
('luis.sanchez@example.com', 'Luis Sánchez');

INSERT INTO `productos` (`precio`, `nombre`) VALUES
(25.99, 'Camiseta Roja'),
(39.50, 'Pantalón Azul'),
(15.75, 'Zapatos Deportivos'),
(12.99, 'Sombrero Verde'),
(9.99, 'Bufanda Gris');

INSERT INTO `pedidos` (`id_usuario`, `fecha_pedido`) VALUES
(1, '2025-03-11 10:30:00'),
(2, '2025-03-11 11:45:00'),
(3, '2025-03-11 12:00:00');

INSERT INTO `detalles_pedido` (`cantidad`, `id_pedido`, `id_producto`) VALUES
(2, 1, 1), -- 2 Camisetas para el pedido 1
(1, 1, 2), -- 1 Pantalón para el pedido 1
(3, 2, 3), -- 3 Zapatos para el pedido 2
(1, 3, 5), -- 1 Bufanda para el pedido 3
(2, 3, 4); -- 2 Sombreros para el pedido 3
