package com.dam2.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dam2.main.modelo.Departamento;


@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento, Integer> {
}
