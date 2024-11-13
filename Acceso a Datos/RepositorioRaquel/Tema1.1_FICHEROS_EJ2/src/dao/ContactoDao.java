package dao;

import java.io.FileNotFoundException;
import java.io.IOException;

import beans.Contacto;

public interface ContactoDao {

	//métodos para hacer el CRUD
	//del contacto
	//CRUD: Create Reading Update Delete

	void addContacto(Contacto c) throws FileNotFoundException, IOException;
	
	
	Contacto getContacto(String nombre) throws FileNotFoundException, IOException, ClassNotFoundException;
	
	
	void updateContacto(String nombre, Contacto c) throws FileNotFoundException, IOException, ClassNotFoundException;
	
	
	void deleteContacto(String nombre);

}
