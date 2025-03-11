package main;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;

public class Main {

	public static void main(String[] args) {
		
		//listaLibros("Cervantes");
		eliminaLibros(5);
	}
	
	private static void eliminaLibros(int precio) {
		
		MongoClient cliente = MongoClients.create();
		
		MongoCollection col = cliente.getDatabase("libros").getCollection("libro");
		
		Document doc = new Document();
		
		doc.append("precio", new Document().append("$gt", precio));
		
		col.deleteMany(doc);
		
		cliente.close();
		
	}
	
	private static void listaLibros(String nombre) {
		
		MongoClient cliente = MongoClients.create();
		
		MongoCollection col = cliente.getDatabase("libros").getCollection("libro");
		
		Document doc = new Document().append("autor", nombre);
		
		List<Document> libros 
			= (List<Document>) col.find(doc).into(new ArrayList<>());
		
		for(Document d : libros)
			System.out.println(d.getString("nombre"));
		
		cliente.close();
	}

}
