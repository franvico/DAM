package logica;

import java.io.File;
import java.util.Scanner;

import clasesJAXB.Pedidos;
import clasesJAXB.Pedidos.Pedido.Cliente;
import clasesJAXB.Pedidos.Pedido.ListaProductos.Producto;
import entidadesPedidos.Pedido;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

public class Main4 {
	
	static Pedidos pedidos;
	
	public static void main(String[] args) throws JAXBException {
		// TODO Auto-generated method stub
		
		Scanner scan = new Scanner(System.in);
			
		pedidos = leerFicheroPedidos(new File("pedidos2.xml"));
		for(clasesJAXB.Pedidos.Pedido p : pedidos.getPedido())
			System.out.println((p.getCliente().getNombre()));
				
		boolean exit = false;
		while(!exit) {
			
			System.out.println("\nSELECCIONE UNA OPCIÓN\n");
			System.out.println("1. Insertar producto a un cliente");
			System.out.println("2. Consultar el cliente que ha gastado más");
			System.out.println("0. Salir de la aplicación");
			
			int opcion = scan.nextInt();
			
			switch(opcion) {
				case 1 : introducirDatosPedido();
					break;
				case 2 : clienteMayorFactura();
					break;
				case 0 : exit = true;
					// crear un nuevo fichero XML con pedidos actualizado , borrar el anterior y cambiar nombre al nuevo
						System.out.println("\nHasta pronto\n");
					break;
				default : System.out.println("Opción incorrecta\n");			
			}
		}
	}
	
	public static void introducirDatosPedido() throws JAXBException {
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("\nIntroduce el NOMBRE del CLIENTE");
		String nombre = scan.nextLine();
		System.out.println("Introduce el NIF del CLIENTE");
		String nif = scan.nextLine();
		System.out.println("Introduce la DESCRIPCIÓN del PRODUCTO");
		String descripcion = scan.nextLine();
		System.out.println("Introduce el PRECIO del PRODUCTO");
		double precio = scan.nextDouble();
		System.out.println("Introduce la CANTIDAD del PRODUCTO");
		int cantidad = scan.nextInt();
		
		insertarProducto(nif, nombre, descripcion, precio, cantidad);
		
		
	}
	
	public static void insertarProducto(String nif, String nombre, String descripcion, double precio, int cantidad) throws JAXBException {
		
		// miro si ese cliente existe y si existe me devuelve su pedido
		clasesJAXB.Pedidos.Pedido p = existeCliente(nif);
		
		if(p != null) {
			// setear producto a la lista de productos del pedido del cliente
			Producto producto = new Producto();
			producto.setDescripcion(descripcion);
			producto.setPrecio(precio);
			producto.setCantidad(cantidad);
			
			p.getListaProductos().getProducto().add(producto);
			
		}
		else {
			
			// crear cliente
			// setear producto a la lista de productos del pedido del cliente
			
			System.out.println("NO EXISTE");
		}
		
	}
	
	public static clasesJAXB.Pedidos.Pedido existeCliente(String nif) {
		for(clasesJAXB.Pedidos.Pedido p : pedidos.getPedido()) {
			if(p.getCliente().getNif().equals(nif)) {
				return p;
			}
		}
		return null;
	}
	
	static String clienteMayorFactura() {
		return null;
	}
	
	public static Pedidos leerFicheroPedidos(File f) throws JAXBException {
		
		JAXBContext jaxbContext = JAXBContext.newInstance(Pedidos.class);
		Unmarshaller u = jaxbContext.createUnmarshaller();
		
		Pedidos pedidos = (Pedidos) u.unmarshal(f);
				
		return pedidos;
	}

}
