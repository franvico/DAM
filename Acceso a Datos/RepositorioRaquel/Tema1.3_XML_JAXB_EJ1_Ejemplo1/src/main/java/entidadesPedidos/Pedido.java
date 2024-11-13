package entidadesPedidos;

import java.util.LinkedList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlTransient;

public class Pedido {
	
	private int id;
	private Cliente cliente;
	private List<Producto> listaProductos = new LinkedList<>();
	
	public Pedido(Cliente cliente, List<Producto> listaProductos) {
		super();
		this.cliente = cliente;
		this.listaProductos = listaProductos;
	}

	public Pedido() {
		super();
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@XmlElementWrapper(name = "listaProductos")
	@XmlElement(name = "producto")
	public List<Producto> getListaProductos() {
		return listaProductos;
	}

	public void setListaProductos(List<Producto> listaProductos) {
		this.listaProductos = listaProductos;
	}

	@XmlTransient
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
