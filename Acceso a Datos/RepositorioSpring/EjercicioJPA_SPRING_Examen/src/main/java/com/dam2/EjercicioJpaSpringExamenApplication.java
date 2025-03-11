package com.dam2;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.dam2.model.Pedido;
import com.dam2.service.PedidoService;
import com.dam2.service.UsuarioService;

@SpringBootApplication
public class EjercicioJpaSpringExamenApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(EjercicioJpaSpringExamenApplication.class, args);
		
		
		// LLAMADAS A LOS .SERVICES
		PedidoService pedServicio = context.getBean(PedidoService.class);
        UsuarioService usuarioServicio = context.getBean(UsuarioService.class);
		
		// LLAMADAS A LOS MÉTODOS DE PEDIDOS
	
		// 1. Insertar un pedido para un usuario existente
        int idUsuario = 2;
//        pedServicio.insertaPedido(idUsuario);
//        System.out.println("✅ Pedido insertado para el usuario con ID " + idUsuario);

//        // 2. Listar productos de un pedido (asumiendo que el pedido 1 existe)
//        int idPedido = 1;
//        System.out.println("📦 Productos del pedido con ID " + idPedido + ":");
//        pedServicio.listaProductos(idPedido);

//        // 3. Obtener el pedido más caro
//        Pedido pedidoCaro = pedServicio.pedidoMasCaro();
//        System.out.println("💰 Pedido más caro: " + (pedidoCaro != null ? pedidoCaro : "No hay pedidos disponibles"));
//
//        // 4. Obtener pedidos de un usuario
//        List<Pedido> pedidosUsuario = pedServicio.obtenerPedidosPorUsuario(idUsuario);
//        System.out.println("📋 Pedidos del usuario con ID " + idUsuario + ": " + pedidosUsuario);
//
//        // 5. Obtener pedidos por fecha específica
//        LocalDateTime fechaPedido = LocalDateTime.of(2025, 3, 11, 12, 0);
//        List<Pedido> pedidosPorFecha = pedServicio.obtenerPedidosPorFecha(fechaPedido);
//        System.out.println("📆 Pedidos en la fecha " + fechaPedido + ": " + pedidosPorFecha);
//
//        // 6. Obtener pedidos después de una fecha
//        LocalDateTime fechaDesde = LocalDateTime.of(2025, 3, 10, 0, 0);
//        List<Pedido> pedidosDespues = pedServicio.obtenerPedidosDespuesDe(fechaDesde);
//        System.out.println("⏩ Pedidos después de " + fechaDesde + ": " + pedidosDespues);
//
//        // 7. Obtener pedidos antes de una fecha
//        LocalDateTime fechaHasta = LocalDateTime.of(2025, 3, 12, 0, 0);
//        List<Pedido> pedidosAntes = pedServicio.obtenerPedidosAntesDe(fechaHasta);
//        System.out.println("⏪ Pedidos antes de " + fechaHasta + ": " + pedidosAntes);
//
//        // 8. Obtener el pedido más caro desde el repositorio
//        Pedido pedidoCaroRepo = pedServicio.pedidoMasCaroRepositorio();
//        System.out.println("💎 Pedido más caro desde el repositorio: " + (pedidoCaroRepo != null ? pedidoCaroRepo : "No hay pedidos disponibles"));
        


        // LLAMADAS A LOS MÉTODOS DE USUARIOS
        
        // 🔹 Consultas directas con Spring Data JPA
//        System.out.println("📌 Usuarios llamados 'Juan': " + usuarioServicio.buscarPorNombre("Juan Pérez"));
//        System.out.println("📧 Usuarios con email que contiene 'gmail': " + usuarioServicio.buscarPorEmailContiene("gmail"));
//        System.out.println("📦 Usuarios con pedidos: " + usuarioServicio.obtenerUsuariosConPedidos());
//        System.out.println("🚫 Usuarios sin pedidos: " + usuarioServicio.obtenerUsuariosSinPedidos());
//        System.out.println("🔤 Usuarios cuyo nombre empieza con 'A': " + usuarioServicio.obtenerUsuariosPorPrefijo("J"));
//
//        // 🔹 Queries personalizadas
//        System.out.println("📩 Buscar usuario por email: " + usuarioServicio.buscarPorEmail("juan@gmail.com"));
//        System.out.println("🏆 Usuario con más pedidos: " + usuarioServicio.usuarioConMasPedidos());
//        System.out.println("🆕 Último usuario registrado: " + usuarioServicio.obtenerUltimoUsuarioRegistrado());
//        System.out.println("🔍 Usuarios con nombre similar a 'Ju': " + usuarioServicio.buscarUsuariosPorNombreSimilar("Ju"));
//        System.out.println("📏 Usuarios con nombre largo (>7 caracteres): " + usuarioServicio.obtenerUsuariosConNombreLargo(7));
		
	}

}
