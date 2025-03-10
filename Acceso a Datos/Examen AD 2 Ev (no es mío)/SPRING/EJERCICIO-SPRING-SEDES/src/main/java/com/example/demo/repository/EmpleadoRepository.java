package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.modelo.Empleado;

public interface EmpleadoRepository extends JpaRepository<Empleado, String>{

}
