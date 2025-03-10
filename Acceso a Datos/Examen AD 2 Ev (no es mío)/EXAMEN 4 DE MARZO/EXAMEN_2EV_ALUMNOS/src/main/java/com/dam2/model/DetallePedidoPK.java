package com.dam2.model;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class DetallePedidoPK {
	
	@Column(name = "id_pedido")
    private int id_pedido;
	
	@Column(name = "id_producto")
    private int id_producto;

	public DetallePedidoPK(int id_pedido, int id_producto) {
		super();
		this.id_pedido = id_pedido;
		this.id_producto = id_producto;
	}
	
	public DetallePedidoPK() {
		
	}

	public int getId_pedido() {
		return id_pedido;
	}

	public void setId_pedido(int id_pedido) {
		this.id_pedido = id_pedido;
	}

	public int getId_producto() {
		return id_producto;
	}

	public void setId_producto(int id_producto) {
		this.id_producto = id_producto;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id_pedido, id_producto);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DetallePedidoPK other = (DetallePedidoPK) obj;
		return id_pedido == other.id_pedido && id_producto == other.id_producto;
	}
	
	

}
