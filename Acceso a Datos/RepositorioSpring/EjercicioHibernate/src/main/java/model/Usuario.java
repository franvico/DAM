package model;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario")
	private Integer id;	
	private String email;
	private String nombre;
	
	@OneToMany(mappedBy = "usuario")
	private List<Pedido> pedidos;
	
	

	public Usuario() {
	}

	public Usuario(Integer id, String email, String nombre, List<model.Pedido> pedidos) {
		this.id = id;
		this.email = email;
		this.nombre = nombre;
		this.pedidos = pedidos;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	@Override
	public String toString() {
		return "Usuario [email=" + email + ", nombre=" + nombre + "]";
	}

}
