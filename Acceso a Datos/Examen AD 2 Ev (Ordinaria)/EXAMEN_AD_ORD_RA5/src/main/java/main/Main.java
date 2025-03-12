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
		
//		mostrarIngredientes("Cocido");
//		mismoTipoCocina("Cocido", "Spaghetti Carbonara");

	}
	
	public static void mostrarIngredientes(String nombre) {
		
		Document criterio = new Document().append("nombre", nombre);
		
		MongoClient cliente = MongoClients.create();
		MongoDatabase db = cliente.getDatabase("platos");
		MongoCollection<Document> plato = db.getCollection("plato");
		
		ArrayList<Document> platosConsulta = plato.find(criterio).into(new ArrayList<Document>());
		
		for(Document p : platosConsulta) {
			
			List<Document> ingredientes = (List<Document>) p.get("ingredientes");
			
			for(Document i : ingredientes) {
				System.out.println(i.getString("nombre"));
			}
		}
		
		
	}
	public static boolean mismoTipoCocina(String nombrePlato1, String nombrePlato2) {
		
		Document criterio1 = new Document().append("nombre", nombrePlato1);
		Document criterio2 = new Document().append("nombre", nombrePlato2);
		
		MongoClient cliente = MongoClients.create();
		MongoDatabase db = cliente.getDatabase("platos");
		MongoCollection<Document> plato = db.getCollection("plato");
		
		ArrayList<Document> platos1 = plato.find(criterio1).into(new ArrayList<Document>());
		ArrayList<Document> platos2 = plato.find(criterio2).into(new ArrayList<Document>());

		String tipoCocinaPlato1 = platos1.get(0).getString("tipoCocina");
		String tipoCocinaPlato2 = platos2.get(0).getString("tipoCocina");

		if(tipoCocinaPlato1.equals(tipoCocinaPlato2)) {
			System.out.println("Son del mismo tipo de cocina");
			return true;
		}
		else {
			System.out.println("No son del mismo tipo de cocina");
			return false;
		}		
		
	}


}
