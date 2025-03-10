package com.dam2.service;


import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dam2.model.DetallePedido;
import com.dam2.model.DetallePedidoPK;
import com.dam2.model.Pedido;
import com.dam2.model.Producto;
import com.dam2.model.Usuario;
import com.dam2.repository.DetallePedidoRepository;
import com.dam2.repository.PedidoRepository;
import com.dam2.repository.ProductoRepository;
import com.dam2.repository.UsuarioRepository;
import org.springframework.transaction.annotation.Transactional;


@Service
public class PedidoService {
	
	//public static Scanner sc = new Scanner(System.in);
	
	@Autowired
    private PedidoRepository pedRepo;
	
	@Autowired
    private ProductoRepository prodRepo;
	
	@Autowired
    private UsuarioRepository usuRepo;
	
	@Autowired
    private DetallePedidoRepository detPedRepo;
	      
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
	@Transactional
    public void insertaPedido(int idUsu) {
    	
    	Usuario usuario = usuRepo.findById(idUsu).get();
    	Pedido p = new Pedido();
    	
    	if(usuario!=null) {
    		p.setUsuario(usuario);
    		p.setFechaPedido(LocalDateTime.now());
    		
    		pedRepo.save(p);
    		pedRepo.flush();
    		
    		System.out.println("Pedido añadido");
    	}else {
    		System.out.println("El usuario no existe");
    	}
    }
    
    /**
     * Método que lista los productos de un
     * pedido, tanto su nombre como cantidad.
     * @param idPed Id del pedido del cuál se 
     * listarán los productos.
     */
	@Transactional
    public void listaProductos(int idPed) {
    	
		List<DetallePedido> dp = detPedRepo.findAll();
		List<Producto> productos = prodRepo.findAll();
    	List<String> lista = new LinkedList<>();
		
    	if(dp==null) {
    		System.out.println("No hay detalles de pedidos");
    	}else {
	    	for(DetallePedido dt : dp) {
	    		if(dt.getId().getId_pedido()==idPed) {
	    			int idProd = dt.getId().getId_producto();
	       			int cantidad = dt.getCantidad();
	       			
	       			for(Producto p : productos) {
	       				if(p.getId()==idProd) {
	       					lista.add(idProd+": "+cantidad);
	       				}
	       			}
	    		}
	    	}
	    	
	    	System.out.println("Productos");
	    	for(String s : lista) {
	    		System.out.println(s);
	    	}
    	}
    }
    
    /**
     * Método que lista el pedido cuyos productos suman
     * más cantidad 
     * @return El objeto Pedido más caro
     */
	@Transactional
    public Pedido pedidoMasCaro() {
    	
		int i = 0;
		int max = 0;
		
		List<DetallePedido> dp = detPedRepo.findAll();
		List<Producto> productos = prodRepo.findAll();
		Map<Integer, Integer> map = new HashMap<>();
		
		
		
    	return null;
    }

}
