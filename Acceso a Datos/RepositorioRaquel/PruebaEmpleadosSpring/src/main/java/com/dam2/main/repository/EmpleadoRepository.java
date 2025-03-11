package com.dam2.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dam2.main.modelo.Empleado;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Integer> {
	
	List<Empleado> findByNombre(String nombre);
	
	@Query("FROM Empleado WHERE departamento.nombre = :nombre")
	List<Empleado> findByNombreDepto(@Param("nombre") String nombre);
	
	@Query(value = "SELECT nombre FROM empleado WHERE id_depto = ?1", nativeQuery = true)
	List<String> findNombreByIdDepto(int id);
}
