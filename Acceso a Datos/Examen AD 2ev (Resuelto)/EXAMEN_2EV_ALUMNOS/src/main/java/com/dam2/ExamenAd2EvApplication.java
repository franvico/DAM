package com.dam2;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.dam2.model.Pedido;
import com.dam2.service.PedidoService;


@SpringBootApplication
public class ExamenAd2EvApplication {

		
	public static void main(String[] args) {
		ApplicationContext context = 
				SpringApplication.run(ExamenAd2EvApplication.class, args);
		
		
		//AQUÍ LAS LLAMADAS A LOS MÉTODOS DEL SERVICIO
		PedidoService pedServicio = context.getBean(PedidoService.class);
		
		//pedServicio.insertaPedido(2);
		//pedServicio.listaProductos(1);
		System.out.println(pedServicio.pedidoMasCaro());
	}
	
	

}
