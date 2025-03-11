package com.dam2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dam2.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto,Integer> {

}
