package model;

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
	
	

	public Pedido() {
	}

	public Pedido(Integer id, model.Usuario usuario, LocalDateTime fechaPedido, List<model.DetallePedido> detalles) {
		this.id = id;
		this.usuario = usuario;
		this.fechaPedido = fechaPedido;
		this.detalles = detalles;
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
