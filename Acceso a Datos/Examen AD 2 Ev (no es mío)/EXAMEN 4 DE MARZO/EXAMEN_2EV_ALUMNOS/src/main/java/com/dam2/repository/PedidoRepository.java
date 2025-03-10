package com.dam2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dam2.model.Pedido;
import com.dam2.model.Usuario;

public interface PedidoRepository 
		extends JpaRepository<Pedido,Integer>{

}
