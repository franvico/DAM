package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.modelo.EmpleadoDatosProf;
import com.example.demo.modelo.EmpleadoDatosProfPK;

public interface EmpleadoDatosProfRepository extends JpaRepository<EmpleadoDatosProf, EmpleadoDatosProfPK>{

}
