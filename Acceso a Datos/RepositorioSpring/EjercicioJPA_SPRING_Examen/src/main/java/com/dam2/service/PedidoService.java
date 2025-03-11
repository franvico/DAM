package com.dam2.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dam2.model.DetallePedido;
import com.dam2.model.Pedido;
import com.dam2.model.Usuario;
import com.dam2.repository.PedidoRepository;
import com.dam2.repository.ProductoRepository;
import com.dam2.repository.UsuarioRepository;

@Service
public class PedidoService {

	@Autowired
	PedidoRepository pedRepo;
	@Autowired
	UsuarioRepository usuRepo;
	@Autowired
	ProductoRepository prodRepo;
	
	/**
     * Método que inserta un pedido (sin productos)
     * para el usuario cuyo id se indica
     * @param idUsu Id del usuario para el
     * que se inserta pedido nuevo.
     * La fecha del pedido será la actual.
     * NOTA: un objeto LocalDateTime con la fecha
     * actual se puede crear con:
     * 	LocalDateTime.now()
     */
    public void insertaPedido(int idUsu) {
    	
    	Usuario usu = usuRepo.findById(idUsu).get();
    	Pedido p = new Pedido();
    	p.setUsuario(usu);
    	p.setFechaPedido(LocalDateTime.now());
    	pedRepo.save(p);
    }
    
    /**
     * Método que lista los productos de un
     * pedido, tanto su nombre como cantidad.
     * @param idPed Id del pedido del cuál se 
     * listarán los productos.
     */
    @Transactional
    public void listaProductos(int idPed) {
    	
    	Pedido p = pedRepo.findById(idPed).get();
    	for(DetallePedido dp : p.getDetalles())
    		System.out.println(dp.getProducto());
    	
    }
    
    /**
     * Método que lista el pedido cuyos productos suman
     * más cantidad 
     * @return El objeto Pedido más caro
     */
    @Transactional
    public Pedido pedidoMasCaro() {
    	
    	List<Pedido> pedidos = pedRepo.findAll();
    	
    	Pedido pedMax = null;
    	double cantMax = 0;
    	for(int i=0;i<pedidos.size();i++) {
    		double cant = 0;
    		for(DetallePedido dp : pedidos.get(i).getDetalles())
    			cant += dp.getProducto().getPrecio();
    		if(cant > cantMax) {
    			pedMax = pedidos.get(i);
    			cantMax = cant;
    		}
    	}
    	
    	return pedMax;
    }
    
    // Obtener los pedidos de un usuario
    public List<Pedido> obtenerPedidosPorUsuario(int idUsu) {
        Usuario usuario = usuRepo.findById(idUsu).orElse(null);
        return (usuario != null) ? pedRepo.findByUsuario(usuario) : List.of();
    }

    // Obtener pedidos por fecha
    public List<Pedido> obtenerPedidosPorFecha(LocalDateTime fecha) {
        return pedRepo.findByFecha(fecha);
    }

    // Obtener pedidos después de una fecha
    public List<Pedido> obtenerPedidosDespuesDe(LocalDateTime fecha) {
        return pedRepo.findByFechaPosterior(fecha);
    }

    // Obtener pedidos antes de una fecha
    public List<Pedido> obtenerPedidosAntesDe(LocalDateTime fecha) {
        return pedRepo.findByFechaAnterior(fecha);
    }

    // Obtener el pedido más caro
    @Transactional
    public Pedido pedidoMasCaroRepositorio() {
        return pedRepo.findPedidosMasCaros().stream().findFirst().orElse(null);
    }
}
