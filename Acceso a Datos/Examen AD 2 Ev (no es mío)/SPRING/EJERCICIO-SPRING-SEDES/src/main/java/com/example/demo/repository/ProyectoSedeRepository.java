package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.modelo.ProyectoSede;
import com.example.demo.modelo.ProyectoSedePK;

public interface ProyectoSedeRepository extends JpaRepository<ProyectoSede, ProyectoSedePK>{

}
