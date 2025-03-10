package main;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

public class Main {

	public static void main(String[] args) {

		eliminarLibros(9);
		
		listaLibros("autor1");

	}
	
	public static void eliminarLibros(int precio) {
		
		boolean esta = false;
		
		Document libro = new Document();
		
		MongoClient cliente = MongoClients.create();
		MongoDatabase db = cliente.getDatabase("libros");
		MongoCollection<Document> libros = db.getCollection("libro");
		
		ArrayList<Document> librosConsulta = libros.find(libro).into(new ArrayList<>());
		
		for(Document d : librosConsulta) {
			List<Document> listaLibro = d.getList("libro", Document.class);
			for(Document doc : listaLibro) {
				if(doc.getInteger("precio")>precio) {
					libros.deleteMany(d);
					esta = true;
				}
			}
		}
		
		if(esta==true) {
			System.out.println("Libros elimnados");
		}else {
			System.out.println("No hay libros superiores a ese precio");
		}
		cliente.close();
	}
	
	public static void listaLibros(String nombre) {
		
		Document libro = new Document();
		
		MongoClient cliente = MongoClients.create();
		MongoDatabase db = cliente.getDatabase("libros");
		MongoCollection<Document> libros = db.getCollection("libro");
		
		ArrayList<Document> librosConsulta = libros.find(libro).into(new ArrayList<>());
		List<String> nombreLibros = new LinkedList<>();
		
		for(Document d : librosConsulta) {
			List<Document> listaLibro = d.getList("libro", Document.class);
			for(Document doc : listaLibro) {
				if(doc.getString("autor").equals(nombre)) {
					nombreLibros.add(doc.getString("nombre"));
				}
			}
		}
		
		if(nombreLibros.isEmpty()) {
			System.out.println("No hay libros de ese autor");
		}else {
			System.out.println("Libros:");
			for(String s :nombreLibros) {
				System.out.print(s);
			}
		}
		
		cliente.close();
	}

}
