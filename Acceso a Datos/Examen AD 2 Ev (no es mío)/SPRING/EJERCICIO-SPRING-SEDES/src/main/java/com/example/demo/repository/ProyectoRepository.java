package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.modelo.Proyecto;

//@Repository
public interface ProyectoRepository extends JpaRepository<Proyecto, Integer>{

}
