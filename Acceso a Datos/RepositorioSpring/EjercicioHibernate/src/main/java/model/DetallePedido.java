package model;

import jakarta.persistence.*;

@Entity
@Table(name = "detalles_pedido")
public class DetallePedido {
	
	@Id
	@ManyToOne()
	@JoinColumn(name = "id_pedido")
	private Pedido pedido;
	
	@Id
	@ManyToOne()
	@JoinColumn(name = "id_producto")
	private Producto producto;
	
	private Integer cantidad;

	public DetallePedido(model.Pedido pedido, model.Producto producto, Integer cantidad) {
		this.pedido = pedido;
		this.producto = producto;
		this.cantidad = cantidad;
	}

	public DetallePedido() {
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
		return "DetallePedido [pedido=" + pedido + ", cantidad=" + cantidad + "]";
	}

}
