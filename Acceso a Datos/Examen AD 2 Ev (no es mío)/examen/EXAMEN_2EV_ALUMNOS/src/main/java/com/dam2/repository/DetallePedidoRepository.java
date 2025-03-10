package com.dam2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dam2.model.DetallePedido;
import com.dam2.model.DetallePedidoPK;

public interface DetallePedidoRepository extends JpaRepository<DetallePedido, DetallePedidoPK>{

}
