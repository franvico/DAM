package com.dam2.model;

import java.time.LocalDateTime;
import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name = "pedidos")
public class Pedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_pedido")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;
	
	@Column(name = "fecha_pedido")
	private LocalDateTime fechaPedido;
	
	@OneToMany(mappedBy = "pedido")
	private List<DetallePedido> detalles;

	
	public Pedido(Integer id, Usuario usuario, LocalDateTime fechaPedido, List<DetallePedido> detalles) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.fechaPedido = fechaPedido;
		this.detalles = detalles;
	}
	
	public Pedido() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public LocalDateTime getFechaPedido() {
		return fechaPedido;
	}

	public void setFechaPedido(LocalDateTime fechaPedido) {
		this.fechaPedido = fechaPedido;
	}

	public List<DetallePedido> getDetalles() {
		return detalles;
	}

	public void setDetalles(List<DetallePedido> detalles) {
		this.detalles = detalles;
	}

	@Override
	public String toString() {
		return "Pedido [usuario=" + usuario + ", fechaPedido=" + fechaPedido + "]";
	}
}


