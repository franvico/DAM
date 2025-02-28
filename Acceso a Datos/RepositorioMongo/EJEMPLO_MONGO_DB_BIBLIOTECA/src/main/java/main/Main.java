package main;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class Main {

	public static void main(String[] args) {
//		insertarLibro();
//		findByTitulo("La mosca");
		deleteByGreaterPrecio(200);
	}
	
	private static void insertarLibro() {
		
		// un nuevo registro para libro en mongo db
		Document libro = new Document();
		// agrego el titulo al libro
		libro.append("título", "La araña");
		
		// creo la lista de autores de ese libro
		List<Document> autores = new ArrayList<>();
		autores.add(new Document()
				.append("nombre", "Anita")
				.append("sueldo", 50));
		autores.add(new Document()
				.append("nombre", "Valu")
				.append("sueldo", 500));
		// agrego los autores al libro
		libro.append("autores", autores);		
		
		// conexión con mongoDB (dejo el create() vacío para que me pille el local y el puerto por defecto)
		MongoClient cliente = MongoClients.create();
		// conexión con la db en concreto
		MongoDatabase db = cliente.getDatabase("biblioteca");
		// selecciono la tabla de la db que quiero (libros)
		MongoCollection<Document> libros = db.getCollection("libros");
		// inserto el registro a la tabla
		libros.insertOne(libro);
		// cierro la conexión
		cliente.close();
	}
	
	private static void findByTitulo(String titulo) {
		
		// el criterio de búsqueda será otro document (como poner 'where titulo = titulo')
		Document criterio = new Document().append("titulo", titulo);
		
		MongoClient cliente = MongoClients.create();
		MongoDatabase db = cliente.getDatabase("biblioteca");
		MongoCollection<Document> libros = db.getCollection("libros");
		
		ArrayList<Document> librosConsulta = libros.find(criterio).into(new ArrayList<Document>());
		
		for(Document libro : librosConsulta) {
			System.out.println(libro.getString("titulo"));
			
			List<Document> autores = (List<Document>)libro.get("autores");
			
			for(Document autor : autores) {
				System.out.println(autor.getString("nombre"));
				System.out.println(autor.getInteger("sueldo"));
			}
		}
		
		cliente.close();
	}
	
	private static void deleteByGreaterPrecio(int precio) {
		
		Document criterio = new Document();		
		Document temp = new Document().append("$gt", precio);
		criterio.append("precio", temp);
		
		MongoClient cliente = MongoClients.create();
		MongoDatabase db = cliente.getDatabase("biblioteca");
		MongoCollection<Document> libros = db.getCollection("libros");
		
		libros.deleteMany(criterio);
		
		cliente.close();
	}
}
