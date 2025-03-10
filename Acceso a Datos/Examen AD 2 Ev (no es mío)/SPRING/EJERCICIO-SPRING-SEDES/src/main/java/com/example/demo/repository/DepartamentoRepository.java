package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.modelo.Departamento;

public interface DepartamentoRepository extends JpaRepository<Departamento, Integer>{

}
