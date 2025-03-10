package main;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

public class Main {
	
	/*CRITERIOS
	 * 	POR CAMPO:
		{ “nombreCampo” : “valor” }
		
		AND DE VARIOS CAMPOS:
		{ “nombreCampo1” : “valor1” , “nombreCampo2” : “valor2” }
		
		OR DE VARIOS CAMPOS:
		{ “$or” : [ { “nombreCampo1” : “valor1”},{ “nombreCampo2” : “valor2” } ]  }
		
		POR CARACTERÍSTICAS CONCRETAS DE UNA PROPIEDAD (EJEMPLO MAYOR QUE):
		{ “nombreCampo”  : { “$gt” : 0 } }   //me devuelve todos los documentos de campo mayor que cero
	 */

	public static void main(String[] args) {
		
		List<String> restaurantes = new ArrayList<>();
		
		restaurantes.add("res3");
		
		List<Document> platos = new ArrayList<>();
		
		//eliminarMenu(2);
		
		platos.add(new Document("nombre", "Lasaña").append("precio", 15));
		
		//insertarMenu(2, "menu2", restaurantes, platos);
		
		recuperarPlatosDelMenu(1);
		
		mostrarMenusDelPlato("Lasaña");
		
		mostrarRestaurantesConMenuMasCaro();
		
		añadirPlatoAlMennu(2, "Tortilla", 20.0);

	}
	
	public static void insertarMenu(int codigo, String nombre, List<String> listaRstaurantes, List<Document> listaPlatos) {
		
		Document menu = new Document();
		
		MongoClient cliente = MongoClients.create();
		MongoDatabase db = cliente.getDatabase("restaurante");
		MongoCollection<Document> menus = db.getCollection("menu");
		
		ArrayList<Document> menusConsulta = menus.find(menu).into(new ArrayList<>());
		
		for(Document m : menusConsulta) {
			if(m.getInteger("codigo")==codigo) {
				System.out.println("El codigo del menu ya existe");
				return;
			}
		}
		
		menu.append("codigo", codigo);
		menu.append("nombre", nombre);
		menu.append("restaurantes", listaRstaurantes);
		menu.append("platos", listaPlatos);
		
		menus.insertOne(menu);
		
		System.out.println("Menu nuevo añadido");
		
		cliente.close();
	}
	
	public static void eliminarMenu(int codigo) {
		
		//boolean esta = false;
		
		//Document menu = new Document();
		
		MongoClient cliente = MongoClients.create();
		MongoDatabase db = cliente.getDatabase("restaurante");
		MongoCollection<Document> menus = db.getCollection("menu");
		
		/*ArrayList<Document> menusConsulta = menus.find(menu).into(new ArrayList<>());
		
		for(Document d : menusConsulta) {
			if(d.getInteger("codigo")==codigo) {
				menus.deleteOne(d);
				esta = true;
			}
		}
		
		if(esta==true) {
			System.out.println("Menu eliminado");
		}else {
			System.out.println("Menu no encontrado");
		}*/
		
		Document filtro = new Document("codigo", codigo);

		if (menus.deleteOne(filtro).getDeletedCount() > 0) {
		    System.out.println("Menú eliminado.");
		} else {
		    System.out.println("Menú no encontrado.");
		}

		
		cliente.close();
	}
	
	public static void recuperarPlatosDelMenu(int codigo) {
		
		boolean esta = false;
		
		Document menu = new Document();
		
		MongoClient cliente = MongoClients.create();
		MongoDatabase db = cliente.getDatabase("restaurante");
		MongoCollection<Document> menus = db.getCollection("menu");
		
		ArrayList<Document> menusConsulta = menus.find(menu).into(new ArrayList<>());
		
		for(Document m : menusConsulta) {
			if(m.getInteger("codigo")==codigo) {
				esta = true;
				
				List<Document> platos = m.getList("platos", Document.class);
				
				for(Document d : platos) {
					String nombre = d.getString("nombre");
					double precio = d.getDouble("precio");
					
					System.out.println("Plato: "+nombre+", precio: "+precio);
				}
			}
		}
		
		if(esta!=true) {
			System.out.println("El codigo del menu no existe");
		}
		
		cliente.close();
	}
	
	public static void mostrarMenusDelPlato(String plato) {
		
		List<Document> listaMenus = new ArrayList<>();
		
		Document menu = new Document();
		
		MongoClient cliente = MongoClients.create();
		MongoDatabase db = cliente.getDatabase("restaurante");
		MongoCollection<Document> menus = db.getCollection("menu");
		
		ArrayList<Document> menusConsulta = menus.find(menu).into(new ArrayList<>());
		
		for(Document m : menusConsulta) {
			List<Document> platos = m.getList("platos", Document.class);
			
			for(Document p : platos) {
				if(p.getString("nombre").equals(plato)) {
					listaMenus.add(m);
				}
			}
		}
		
		if(listaMenus.isEmpty()) {
			System.out.println("No se ha encontrado este plato en ningun menu");
		}else {
			for(Document d : listaMenus) {
				System.out.println("Menu: "+d.getString("nombre"));
			}
		}
		
		cliente.close();
	}
	
	public static void mostrarPecioDelMenu(String codigo) {
		
		double total = 0;
		
		List<Document> listaMenus = new ArrayList<>();
		
		Document menu = new Document();
		
		MongoClient cliente = MongoClients.create();
		MongoDatabase db = cliente.getDatabase("restaurante");
		MongoCollection<Document> menus = db.getCollection("menu");
		
		ArrayList<Document> menusConsulta = menus.find(menu).into(new ArrayList<>());
		
		for(Document d : menusConsulta) {
			if(d.getString("codigo").equals(codigo)) {
				List<Document> platos = d.getList("platos", Document.class);
				
				for(Document plato : platos) {
					total += plato.getDouble("precio");
				}
			}
		}
		
		System.out.println("Precio total del menu: "+total+"€");
		
		cliente.close();
	}
	
	public static void mostrarRestaurantesConMenuMasCaro() {
		
		double precioMax = 0;
		
		List<String> restaurantes = new ArrayList<>();
		
		Document menu = new Document();
		
		MongoClient cliente = MongoClients.create();
		MongoDatabase db = cliente.getDatabase("restaurante");
		MongoCollection<Document> menus = db.getCollection("menu");
		
		ArrayList<Document> menusConsulta = menus.find(menu).into(new ArrayList<>());
		
		//for(Document d : menus.find()) {
		for(Document d : menusConsulta) {
			List<Document> platos = d.getList("platos", Document.class);
			
			double total = 0;
			for(Document plato : platos) {
				total += plato.getDouble("precio");
			}
			
			if(total>precioMax) {
				precioMax = total;
				restaurantes.clear();
	            restaurantes.addAll(d.getList("restaurantes", String.class));
			}else if(total==precioMax) {
				restaurantes.addAll(d.getList("restaurantes", String.class));
			}
		}
		
		if(restaurantes.isEmpty()) {
			System.out.println("No hay restaurantes");
		}else {
			System.out.println("Restaurantes mas caros:");
			for(String s : restaurantes) {
				System.out.println("- "+s);
			}
		}
		
		cliente.close();
	}
	
	public static void añadirPlatoAlMennu(int codigo, String nombre, double precio) {
		
		Document menu = new Document();
		
		MongoClient cliente = MongoClients.create();
		MongoDatabase db = cliente.getDatabase("restaurante");
		MongoCollection<Document> menus = db.getCollection("menu");
		
		Document menuExistente = menus.find(Filters.eq("codigo", codigo)).first();
		
		if (menuExistente == null) {
	        System.out.println("Menú no encontrado");
	    } else {
	    	List<Document> platos = menuExistente.getList("platos", Document.class);

	        for (Document plato : platos) {
	            if (plato.getString("nombre").equals(nombre)) {
	                System.out.println("El plato ya existe en el menú.");
	                cliente.close();
	                return;
	            }
	        }
	    	
			Document nuevoPlato = new Document("nombre", nombre).append("precio", precio);
			
			menus.updateOne(Filters.eq("codigo", codigo), Updates.push("platos", nuevoPlato));
			
			System.out.println("Plato añadido al menu");
	    }
		
		cliente.close();
	}

}
