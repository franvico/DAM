package entidadesPedidos;


import java.util.LinkedList;
import java.util.List;
import java.util.Objects;



public class Pedido {
	
	private int id; // para tener una clave primaria a pesar de que no está en el XML, por ello usamos la etiqueta @XmlTransient 

	private Cliente cliente;
	private List<Producto> productos = new LinkedList<>(); // productos no se corresponde con el nombre del XML que es producto, así que tengo que mapearlo con el XMLgetElement
	
	// CONSTRUCTOR
	public Pedido(Cliente cliente, List<Producto> productos) {
		super();
		this.cliente = cliente;
		this.productos = productos;	
	}
	
	public Pedido() {
		super();
	}




	// GETTERS Y SETTERS
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}


	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	@Override
	public int hashCode() {
		return Objects.hash(id);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pedido other = (Pedido) obj;
		return id == other.id;
	}
	
	

}
