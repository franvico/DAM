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