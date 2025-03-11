package com.dam2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dam2.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Integer>{

	 // 🔹 Consultas directas de Spring Data JPA
    List<Usuario> findByNombre(String nombre);
    List<Usuario> findByEmailContaining(String email);
    List<Usuario> findByPedidosIsNotEmpty(); // Usuarios con al menos un pedido
    List<Usuario> findByPedidosIsEmpty(); // Usuarios sin pedidos
    List<Usuario> findByNombreStartingWith(String prefix);

    // 🔹 Queries personalizadas con JPQL y SQL nativo
    @Query("SELECT u FROM Usuario u WHERE u.email = :email")
    Usuario findByEmail(@Param("email") String email);

    @Query("SELECT u FROM Usuario u WHERE SIZE(u.pedidos) = (SELECT MAX(SIZE(usu.pedidos)) FROM Usuario usu)")
    Usuario findUsuarioConMasPedidos();

    @Query(value = "SELECT * FROM usuarios ORDER BY id_usuario DESC LIMIT 1", nativeQuery = true)
    Usuario findUltimoUsuarioRegistrado();

    @Query("SELECT u FROM Usuario u WHERE LOWER(u.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))")
    List<Usuario> buscarUsuariosPorNombreSimilar(@Param("nombre") String nombre);

    @Query(value = "SELECT * FROM usuarios WHERE LENGTH(nombre) > :longitud", nativeQuery = true)
    List<Usuario> findUsuariosConNombreLargo(@Param("longitud") int longitud);
	
}
