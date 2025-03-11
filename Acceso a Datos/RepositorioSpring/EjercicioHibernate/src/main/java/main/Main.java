package main;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.Transaction;

import model.DetallePedido;
import model.Pedido;
import model.Producto;
import model.Usuario;

public class Main {

	public static void main(String[] args) {

		// Llamadas a los métodos de prueba

      // Prueba para insertar un usuario
      System.out.println("Insertar Usuario:");
      insertaUsuario();
      
//      // Prueba para obtener un usuario por ID
//      System.out.println("\nObtener Usuario por ID:");
//      obtenerUsuarioPorId();
//
//      // Prueba para eliminar un usuario
//      System.out.println("\nEliminar Usuario:");
//      eliminarUsuario();
//
//      // Prueba para insertar un pedido
//      System.out.println("\nInsertar Pedido:");
//      insertaPedido();
//
//      // Prueba para obtener los pedidos de un usuario
//      System.out.println("\nObtener Pedidos de un Usuario:");
//      obtenerPedidosDeUsuario();
//
//      // Prueba para insertar un producto
//      System.out.println("\nInsertar Producto:");
//      insertaProducto();
//
//      // Prueba para insertar un detalle de pedido
//      System.out.println("\nInsertar Detalle Pedido:");
//      insertaDetallePedido();
//
//      // Prueba para obtener los productos de un pedido
//      System.out.println("\nObtener Productos de un Pedido:");
//      obtenerProductosDePedido();
//
//      // Prueba para asignar un producto a un pedido
//      System.out.println("\nAsignar Producto a un Pedido:");
//      asignarProductoAPedido();
//
//      // Prueba para listar todos los productos
//      System.out.println("\nListar Productos:");
//      listarProductos();
//
//      // Prueba para obtener el pedido más caro
//      System.out.println("\nPedido Más Caro:");
//      pedidoMasCaro();
//
//      // Prueba para listar productos de un pedido específico
//      System.out.println("\nListar Productos de un Pedido Específico:");
//      listarProductosDePedido();
//
//      // Prueba para obtener pedidos de un usuario
//      System.out.println("\nObtener Pedidos de un Usuario Específico:");
//      obtenerPedidosDeUsuario(1);  // Suponiendo que el usuario con ID 1 existe
//
//      // Prueba para añadir un producto a un pedido
//      System.out.println("\nAñadir Producto a un Pedido:");
//      addProduct(1, 1, 3);  // Suponiendo que el producto con ID 1 y el pedido con ID 1 existen
//
//      // Prueba para mostrar el email de un usuario por su pedido
//      System.out.println("\nMostrar Email del Usuario por Pedido:");
//      mostrarEmail(1);  // Suponiendo que el pedido con ID 1 existe
//
//      // Prueba para obtener el usuario que más pedidos ha realizado
//     System.out.println("\nUsuario con Más Pedidos:");
//      usuarioMasPedidos();

	}
	//METODOS PARA USUARIO:
	private static void insertaUsuario() {
	    Scanner scan = new Scanner(System.in);
	    
	    System.out.println("Dame email:");
	    String email = scan.nextLine();
	    System.out.println("Dame nombre:");
	    String nombre = scan.nextLine();
	    
	    Usuario usuario = new Usuario();
	    usuario.setEmail(email);
	    usuario.setNombre(nombre);
	    
	    Session sesion = HibernateUtil.getSessionFactory().openSession();
	    Transaction tx = sesion.beginTransaction();
	    
	    sesion.persist(usuario);
	    
	    tx.commit();
	    sesion.close();
	}
	
	private static void obtenerUsuarioPorId() {
	    Scanner scan = new Scanner(System.in);
	    
	    System.out.println("Dame id del usuario:");
	    int id = scan.nextInt();
	    
	    Session sesion = HibernateUtil.getSessionFactory().openSession();
	    Usuario usuario = sesion.get(Usuario.class, id);
	    
	    System.out.println("Usuario encontrado: " + usuario);
	    
	    sesion.close();
	}
	private static void eliminarUsuario() {
	    Scanner scan = new Scanner(System.in);
	    
	    System.out.println("Dame id del usuario a eliminar:");
	    int id = scan.nextInt();
	    
	    Session sesion = HibernateUtil.getSessionFactory().openSession();
	    Transaction tx = sesion.beginTransaction();
	    
	    Usuario usuario = sesion.get(Usuario.class, id);
	    if (usuario != null) {
	        sesion.remove(usuario);
	    }
	    
	    tx.commit();
	    sesion.close();
	}
	//METODOS PARA PEDIDO:
	private static void insertaPedido() {
	    Scanner scan = new Scanner(System.in);
	    
	    System.out.println("Dame id del usuario para el pedido:");
	    int idUsuario = scan.nextInt();
	    scan.nextLine(); // Consuming newline left by nextInt
	    System.out.println("Dame fecha del pedido (formato YYYY-MM-DD HH:MM):");
	    String fecha = scan.nextLine();
	    
	    // Convertir fecha en LocalDateTime
	    LocalDateTime fechaPedido = LocalDateTime.parse(fecha.replace(" ", "T"));
	    
	    // Obtener el Usuario asociado
	    Session sesion = HibernateUtil.getSessionFactory().openSession();
	    Usuario usuario = sesion.get(Usuario.class, idUsuario);
	    
	    Pedido pedido = new Pedido();
	    pedido.setUsuario(usuario);
	    pedido.setFechaPedido(fechaPedido);
	    
	    Transaction tx = sesion.beginTransaction();
	    
	    sesion.persist(pedido);
	    
	    tx.commit();
	    sesion.close();
	}
	private static void obtenerPedidosDeUsuario() {
	    Scanner scan = new Scanner(System.in);
	    
	    System.out.println("Dame id del usuario para obtener sus pedidos:");
	    int idUsuario = scan.nextInt();
	    
	    Session sesion = HibernateUtil.getSessionFactory().openSession();
	    Usuario usuario = sesion.get(Usuario.class, idUsuario);
	    
	    if (usuario != null) {
	        System.out.println("Pedidos del usuario: ");
	        for (Pedido pedido : usuario.getPedidos()) {
	            System.out.println(pedido);
	        }
	    }
	    
	    sesion.close();
	}

	//METODOS PARA PRODUCTO:
	private static void insertaProducto() {
	    Scanner scan = new Scanner(System.in);
	    
	    System.out.println("Dame nombre del producto:");
	    String nombre = scan.nextLine();
	    System.out.println("Dame precio del producto:");
	    Double precio = scan.nextDouble();
	    
	    Producto producto = new Producto();
	    producto.setNombre(nombre);
	    producto.setPrecio(precio);
	    
	    Session sesion = HibernateUtil.getSessionFactory().openSession();
	    Transaction tx = sesion.beginTransaction();
	    
	    sesion.persist(producto);
	    
	    tx.commit();
	    sesion.close();
	}

	//METODOS PARA DETALLE_PRODUCTO:
	private static void insertaDetallePedido() {
	    Scanner scan = new Scanner(System.in);
	    
	    System.out.println("Dame id del pedido:");
	    int idPedido = scan.nextInt();
	    System.out.println("Dame id del producto:");
	    int idProducto = scan.nextInt();
	    System.out.println("Dame cantidad del producto:");
	    int cantidad = scan.nextInt();
	    
	    Session sesion = HibernateUtil.getSessionFactory().openSession();
	    Pedido pedido = sesion.get(Pedido.class, idPedido);
	    Producto producto = sesion.get(Producto.class, idProducto);
	    
	    DetallePedido detallePedido = new DetallePedido();
	    detallePedido.setPedido(pedido);
	    detallePedido.setProducto(producto);
	    detallePedido.setCantidad(cantidad);
	    
	    Transaction tx = sesion.beginTransaction();
	    
	    sesion.persist(detallePedido);
	    
	    tx.commit();
	    sesion.close();
	}

	//CONSULTAS ADICIONALES
	
	private static void obtenerProductosDePedido() {
	    Scanner scan = new Scanner(System.in);
	    
	    System.out.println("Dame id del pedido:");
	    int idPedido = scan.nextInt();
	    
	    Session sesion = HibernateUtil.getSessionFactory().openSession();
	    Pedido pedido = sesion.get(Pedido.class, idPedido);
	    
	    if (pedido != null) {
	        System.out.println("Productos del pedido: ");
	        for (DetallePedido detalle : pedido.getDetalles()) {
	            System.out.println("Producto: " + detalle.getProducto() + ", Cantidad: " + detalle.getCantidad());
	        }
	    }
	    
	    sesion.close();
	}

	private static void asignarProductoAPedido() {
	    Scanner scan = new Scanner(System.in);
	    
	    System.out.println("Dame id del pedido:");
	    int idPedido = scan.nextInt();
	    System.out.println("Dame id del producto:");
	    int idProducto = scan.nextInt();
	    System.out.println("Dame cantidad del producto:");
	    int cantidad = scan.nextInt();
	    
	    Session sesion = HibernateUtil.getSessionFactory().openSession();
	    Pedido pedido = sesion.get(Pedido.class, idPedido);
	    Producto producto = sesion.get(Producto.class, idProducto);
	    
	    DetallePedido detalle = new DetallePedido(pedido, producto, cantidad);
	    
	    Transaction tx = sesion.beginTransaction();
	    
	    sesion.persist(detalle);
	    
	    tx.commit();
	    sesion.close();
	}

	private static void listarProductos() {
	    Session sesion = HibernateUtil.getSessionFactory().openSession();
	    
	    // Hacer la consulta para obtener todos los productos
	    List<Producto> productos = sesion.createQuery("from Producto", Producto.class).list();
	    
	    for (Producto producto : productos) {
	        System.out.println(producto);
	    }
	    
	    sesion.close();
	}

	private static void pedidoMasCaro() {
	    Session sesion = HibernateUtil.getSessionFactory().openSession();
	    
	    // Consulta para obtener todos los pedidos con sus detalles
	    List<Pedido> pedidos = sesion.createQuery("from Pedido p join fetch p.detalles", Pedido.class).list();
	    
	    Pedido pedidoMasCaro = null;
	    double precioMaximo = 0;
	    
	    for (Pedido pedido : pedidos) {
	        double precioTotal = 0;
	        for (DetallePedido detalle : pedido.getDetalles()) {
	            precioTotal += detalle.getProducto().getPrecio() * detalle.getCantidad();
	        }
	        
	        if (precioTotal > precioMaximo) {
	            precioMaximo = precioTotal;
	            pedidoMasCaro = pedido;
	        }
	    }
	    
	    if (pedidoMasCaro != null) {
	        System.out.println("El pedido más caro es: " + pedidoMasCaro);
	    } else {
	        System.out.println("No se encontraron pedidos.");
	    }
	    
	    sesion.close();
	}


	private static void listarProductosDePedido() {
	    Scanner scan = new Scanner(System.in);
	    
	    System.out.println("Dame id del pedido:");
	    int idPedido = scan.nextInt();
	    
	    Session sesion = HibernateUtil.getSessionFactory().openSession();
	    Pedido pedido = sesion.get(Pedido.class, idPedido);
	    
	    if (pedido != null) {
	        System.out.println("Productos del pedido:");
	        for (DetallePedido detalle : pedido.getDetalles()) {
	            Producto producto = detalle.getProducto();
	            System.out.println("Producto: " + producto.getNombre() + ", Cantidad: " + detalle.getCantidad() + ", Precio: " + producto.getPrecio());
	        }
	    } else {
	        System.out.println("Pedido no encontrado.");
	    }
	    
	    sesion.close();
	}

	private static void obtenerPedidosDeUsuario(int idUsuario) {
	    	    
	    Session sesion = HibernateUtil.getSessionFactory().openSession();
	    Usuario usuario = sesion.get(Usuario.class, idUsuario);
	    
	    if (usuario != null) {
	        System.out.println("Pedidos del usuario " + usuario.getNombre() + ":");
	        for (Pedido pedido : usuario.getPedidos()) {
	            System.out.println("Pedido ID: " + pedido.getId() + ", Fecha: " + pedido.getFechaPedido());
	        }
	    } else {
	        System.out.println("Usuario no encontrado.");
	    }
	    
	    sesion.close();
	}

	private static void addProduct(int idProd, int idPed, int cantidad) {
	    Session session = HibernateUtil.getSessionFactory().openSession();
	    Transaction tx = session.beginTransaction();

	    try {
	        // Buscar el pedido
	        Pedido pedido = session.get(Pedido.class, idPed);
	        if (pedido == null) {
	            System.out.println("No existe pedido");
	            session.close();
	            return;
	        }

	        // Buscar el producto
	        Producto producto = session.get(Producto.class, idProd);
	        if (producto == null) {
	            System.out.println("No existe producto");
	            session.close();
	            return;
	        }

	        // Crear un nuevo DetallePedido y asociarlo con el Pedido y Producto
	        DetallePedido detallePedido = new DetallePedido();
	        detallePedido.setPedido(pedido);
	        detallePedido.setProducto(producto);
	        detallePedido.setCantidad(cantidad);

	        // Guardar el detalle en la base de datos
	        session.persist(detallePedido);

	        tx.commit();
	        System.out.println("Producto añadido al pedido");
	    } catch (Exception e) {
	        if (tx != null) tx.rollback();
	        e.printStackTrace();
	    } finally {
	        session.close();
	    }
	}

	private static void mostrarEmail(int idPed) {
	    Session session = HibernateUtil.getSessionFactory().openSession();

	    try {
	        // Buscar el pedido
	        Pedido pedido = session.get(Pedido.class, idPed);
	        if (pedido == null) {
	            System.out.println("No existe el pedido");
	            session.close();
	            return;
	        }

	        // Obtener el usuario asociado al pedido
	        Usuario usuario = pedido.getUsuario();
	        if (usuario != null) {
	            System.out.println("El email del usuario es: " + usuario.getEmail());
	        } else {
	            System.out.println("No se encontró el usuario para el pedido");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        session.close();
	    }
	}

	private static void usuarioMasPedidos() {
	    Session session = HibernateUtil.getSessionFactory().openSession();

	    try {
	        // Obtener todos los usuarios
	        List<Usuario> usuarios = session.createQuery("from Usuario", Usuario.class).list();
	        
	        Usuario usuarioMaxPedidos = null;
	        int maxPedidos = 0;

	        // Buscar el usuario con más pedidos
	        for (Usuario usuario : usuarios) {
	            int cantidadPedidos = usuario.getPedidos().size();
	            
	            if (cantidadPedidos > maxPedidos) {
	                maxPedidos = cantidadPedidos;
	                usuarioMaxPedidos = usuario;
	            }
	        }

	        if (usuarioMaxPedidos != null) {
	            System.out.println("Usuario con más pedidos: " + usuarioMaxPedidos.getNombre() + ", con " + maxPedidos + " pedidos.");
	        } else {
	            System.out.println("No se encontraron usuarios.");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        session.close();
	    }
	}
}
