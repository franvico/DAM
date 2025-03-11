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
		
//		insertarLibro(); // insertar libro (datos harcodeados)
//		eliminarLibrosConPrecioMenorA(20);
//		eliminarLibrosConPrecioMayorA(45);
//		listarLibrosPorAutor("Harper Lee");
//		actualizarPrecioPorTitulo("1984", 29.99);
//		listarLibrosPorIdioma("Inglés");
//		eliminarLibrosPorAutor("George Orwell");
//		contarLibrosPorAutor("Harper Lee");
//		agregarIdiomaAUnLibro("Matar a un ruiseñor", "Italiano", "IT");
//		obtenerLibroConPrecioMasBajo();

	}
	
	public static void insertarLibro() {
		
		Document libro = new Document();
		libro.append("titulo", "Comerse a un ruiseñor");
		libro.append("autor", "Harper Lee");
		libro.append("precio", 15.99);		
		
		List<Document> idiomas = new ArrayList<>();
		idiomas.add(new Document()
				.append("nombre", "Inglés")
				.append("abreviatura", "EN"));
		idiomas.add(new Document()
				.append("nombre", "Español")
				.append("abreviatura", "ES"));
		idiomas.add(new Document()
				.append("nombre", "Francés")
				.append("abreviatura", "FR"));
		
		libro.append("idiomas", idiomas);
		
		MongoClient cliente = MongoClients.create();
		MongoDatabase db = cliente.getDatabase("libros");
		MongoCollection<Document> libros = db.getCollection("libros");
		libros.insertOne(libro);
		cliente.close();
	}
	
	
	public static void eliminarLibrosConPrecioMenorA(int precio) {
	
		Document criterio = new Document();
		Document temp = new Document().append("$lt", precio);
		criterio.append("precio", temp);
		
		MongoClient cliente = MongoClients.create();
		MongoDatabase db = cliente.getDatabase("libros");
		MongoCollection<Document> libros = db.getCollection("libros");
		
		libros.deleteMany(criterio);
		
		cliente.close();
		
	}
	
	public static void eliminarLibrosConPrecioMayorA(int precio) {
		
		Document criterio = new Document();
		Document temp = new Document().append("$gt", precio);
		criterio.append("precio", temp);
		
		MongoClient cliente = MongoClients.create();
		MongoDatabase db = cliente.getDatabase("libros");
		MongoCollection<Document> libros = db.getCollection("libros");
		
		libros.deleteMany(criterio);
		
		cliente.close();
		
	}
	
	public static void listarLibrosPorAutor(String autor) {
		
		Document criterio = new Document().append("autor", autor);
		
		MongoClient cliente = MongoClients.create();
		MongoDatabase db = cliente.getDatabase("libros");
		MongoCollection<Document> libros = db.getCollection("libros");
		
		ArrayList<Document> librosConsulta = libros.find(criterio).into(new ArrayList<Document>());
		
		for(Document libro : librosConsulta) {
			System.out.println(libro.getString("titulo"));
			System.out.println(libro.getDouble("precio").toString());
			
			List<Document> idiomas = (List<Document>) libro.get("idiomas");
			
			for(Document idioma : idiomas) {
				System.out.println(idioma.getString("nombre"));
				System.out.println(idioma.getString("abreviatura"));
			}
			
		}
		
	}
	
	public static void actualizarPrecioPorTitulo(String titulo, double nuevoPrecio) {
	    Document filtro = new Document().append("titulo", titulo);
	    Document actualizacion = new Document().append("$set", new Document().append("precio", nuevoPrecio));
	    
	    MongoClient cliente = MongoClients.create();
	    MongoDatabase db = cliente.getDatabase("libros");
	    MongoCollection<Document> libros = db.getCollection("libros");
	    
	    libros.updateOne(filtro, actualizacion);
	    
	    cliente.close();
	}
	
	public static void listarLibrosPorIdioma(String idioma) {
	    Document criterio = new Document("idiomas.nombre", idioma);
	    
	    MongoClient cliente = MongoClients.create();
	    MongoDatabase db = cliente.getDatabase("libros");
	    MongoCollection<Document> libros = db.getCollection("libros");
	    
	    ArrayList<Document> librosConsulta = libros.find(criterio).into(new ArrayList<Document>());
	    
	    for(Document libro : librosConsulta) {
	        System.out.println(libro.getString("titulo"));
	        System.out.println(libro.getDouble("precio").toString());
	        
	        List<Document> idiomas = (List<Document>) libro.get("idiomas");
	        for(Document idiomaDoc : idiomas) {
	            System.out.println(idiomaDoc.getString("nombre"));
	            System.out.println(idiomaDoc.getString("abreviatura"));
	        }
	    }
	    
	    cliente.close();
	}
	
	public static void eliminarLibrosPorAutor(String autor) {
	    Document criterio = new Document().append("autor", autor);
	    
	    MongoClient cliente = MongoClients.create();
	    MongoDatabase db = cliente.getDatabase("libros");
	    MongoCollection<Document> libros = db.getCollection("libros");
	    
	    libros.deleteMany(criterio);
	    
	    cliente.close();
	}
	
	public static void contarLibrosPorAutor(String autor) {
	    Document criterio = new Document().append("autor", autor);
	    
	    MongoClient cliente = MongoClients.create();
	    MongoDatabase db = cliente.getDatabase("libros");
	    MongoCollection<Document> libros = db.getCollection("libros");
	    
	    long count = libros.countDocuments(criterio);
	    System.out.println("El autor " + autor + " tiene " + count + " libros en la base de datos.");
	    
	    cliente.close();
	}
	
	public static void agregarIdiomaAUnLibro(String titulo, String idioma, String abreviatura) {
	    Document filtro = new Document().append("titulo", titulo);
	    
	    Document nuevoIdioma = new Document()
	        .append("nombre", idioma)
	        .append("abreviatura", abreviatura);
	    
	    Document actualizacion = new Document().append("$push", new Document().append("idiomas", nuevoIdioma));
	    
	    MongoClient cliente = MongoClients.create();
	    MongoDatabase db = cliente.getDatabase("libros");
	    MongoCollection<Document> libros = db.getCollection("libros");
	    
	    libros.updateOne(filtro, actualizacion);
	    
	    cliente.close();
	}
	
	public static void obtenerLibroConPrecioMasBajo() {
	    MongoClient cliente = MongoClients.create();
	    MongoDatabase db = cliente.getDatabase("libros");
	    MongoCollection<Document> libros = db.getCollection("libros");
	    
	    Document sort = new Document("precio", 1); // Ordenar por precio ascendente
	    Document libroMasBarato = libros.find().sort(sort).limit(1).first();
	    
	    if (libroMasBarato != null) {
	        System.out.println("El libro más barato es: " + libroMasBarato.getString("titulo"));
	        System.out.println("Precio: " + libroMasBarato.getDouble("precio").toString());
	    } else {
	        System.out.println("No hay libros en la base de datos.");
	    }
	    
	    cliente.close();
	}






	
	
}
