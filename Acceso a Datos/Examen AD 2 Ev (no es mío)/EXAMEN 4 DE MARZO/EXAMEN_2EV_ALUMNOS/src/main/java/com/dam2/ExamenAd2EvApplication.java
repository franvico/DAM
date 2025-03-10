package com.dam2;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.dam2.model.Pedido;
import com.dam2.service.PedidoService;


@SpringBootApplication
public class ExamenAd2EvApplication {

		
	public static void main(String[] args) {
		
		ApplicationContext context = SpringApplication.run(ExamenAd2EvApplication.class, args);
		
		PedidoService ps = new PedidoService();
		
		//AQUÍ LAS LLAMADAS A LOS MÉTODOS DEL SERVICIO
		ps.insertaPedido(1);
		
		ps.listaProductos(1);
		
	}
	
	

}
