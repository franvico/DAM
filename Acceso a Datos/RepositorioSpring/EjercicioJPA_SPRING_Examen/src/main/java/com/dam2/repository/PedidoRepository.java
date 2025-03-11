package com.dam2.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dam2.model.Pedido;
import com.dam2.model.Usuario;

public interface PedidoRepository extends JpaRepository<Pedido,Integer> {
	
    // Buscar pedidos por usuario (JPA Query Method)
    List<Pedido> findByUsuario(Usuario usuario);

    // Buscar pedidos por fecha exacta (JPQL)
    @Query("FROM Pedido WHERE fechaPedido = :fecha")
    List<Pedido> findByFecha(@Param("fecha") LocalDateTime fecha);

    // Buscar pedidos realizados después de una fecha
    @Query("FROM Pedido WHERE fechaPedido > :fecha")
    List<Pedido> findByFechaPosterior(@Param("fecha") LocalDateTime fecha);

    // Buscar pedidos realizados antes de una fecha
    @Query("FROM Pedido WHERE fechaPedido < :fecha")
    List<Pedido> findByFechaAnterior(@Param("fecha") LocalDateTime fecha);

    // Buscar los pedidos más caros (ordenando por suma de productos)
    @Query("SELECT p FROM Pedido p JOIN p.detalles d GROUP BY p ORDER BY SUM(d.producto.precio * d.cantidad) DESC")
    List<Pedido> findPedidosMasCaros();

}
