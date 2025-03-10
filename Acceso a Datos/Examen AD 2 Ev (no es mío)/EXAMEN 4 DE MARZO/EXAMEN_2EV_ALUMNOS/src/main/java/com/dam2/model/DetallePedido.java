package com.dam2.model;

import jakarta.persistence.*;

@Entity
@Table(name = "detalles_pedido")
public class DetallePedido {

	@EmbeddedId
	private DetallePedidoPK id;
	
	@ManyToOne
	@JoinColumn(name = "id_pedido", insertable = false, updatable = false)
    private Pedido pedido;

	@ManyToOne
	@JoinColumn(name = "id_producto", insertable = false, updatable = false)
    private Producto producto;

    @Column(name = "cantidad")
    private Integer cantidad;

	public DetallePedido(DetallePedidoPK id, Pedido pedido, Producto producto, Integer cantidad) {
		super();
		this.id = id;
		this.pedido = pedido;
		this.producto = producto;
		this.cantidad = cantidad;
	}


	public DetallePedido() {
		
	}


	public DetallePedidoPK getId() {
		return id;
	}


	public void setId(DetallePedidoPK id) {
		this.id = id;
	}


	public Pedido getPedido() {
		return pedido;
	}


	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}


	public Producto getProducto() {
		return producto;
	}


	public void setProducto(Producto producto) {
		this.producto = producto;
	}


	public Integer getCantidad() {
		return cantidad;
	}


	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}


	@Override
	public String toString() {
		return "DetallePedido [id=" + id + ", pedido=" + pedido + ", producto=" + producto + ", cantidad=" + cantidad
				+ "]";
	}
	
	

    
}
