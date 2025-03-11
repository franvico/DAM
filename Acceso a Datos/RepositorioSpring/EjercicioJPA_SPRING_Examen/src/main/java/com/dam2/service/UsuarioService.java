package com.dam2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dam2.model.Usuario;
import com.dam2.repository.PedidoRepository;
import com.dam2.repository.ProductoRepository;
import com.dam2.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	PedidoRepository pedRepo;
	@Autowired
	UsuarioRepository usuRepo;
	@Autowired
	ProductoRepository prodRepo;
	
	// 1️⃣ Buscar usuarios por nombre
    @Transactional
    public List<Usuario> buscarPorNombre(String nombre) {
        return usuRepo.findByNombre(nombre);
    }

    // 2️⃣ Buscar usuarios cuyo email contenga una cadena
    public List<Usuario> buscarPorEmailContiene(String email) {
        return usuRepo.findByEmailContaining(email);
    }

    // 3️⃣ Obtener usuarios que han realizado al menos un pedido
    public List<Usuario> obtenerUsuariosConPedidos() {
        return usuRepo.findByPedidosIsNotEmpty();
    }

    // 4️⃣ Obtener usuarios que no han realizado ningún pedido
    public List<Usuario> obtenerUsuariosSinPedidos() {
        return usuRepo.findByPedidosIsEmpty();
    }

    // 5️⃣ Obtener usuarios cuyo nombre comienza con cierto prefijo
    public List<Usuario> obtenerUsuariosPorPrefijo(String prefijo) {
        return usuRepo.findByNombreStartingWith(prefijo);
    }

    // 🔹 Métodos con queries personalizadas

    // 6️⃣ Buscar usuario por email
    public Usuario buscarPorEmail(String email) {
        return usuRepo.findByEmail(email);
    }

    // 7️⃣ Obtener el usuario con más pedidos
    public Usuario usuarioConMasPedidos() {
        return usuRepo.findUsuarioConMasPedidos();
    }

    // 8️⃣ Obtener el último usuario registrado en la base de datos
    public Usuario obtenerUltimoUsuarioRegistrado() {
        return usuRepo.findUltimoUsuarioRegistrado();
    }

    // 9️⃣ Buscar usuarios cuyo nombre sea similar a una cadena
    public List<Usuario> buscarUsuariosPorNombreSimilar(String nombre) {
        return usuRepo.buscarUsuariosPorNombreSimilar(nombre);
    }

    // 🔟 Obtener usuarios con nombres más largos que cierta cantidad de caracteres
    public List<Usuario> obtenerUsuariosConNombreLargo(int longitud) {
        return usuRepo.findUsuariosConNombreLargo(longitud);
    }
}
