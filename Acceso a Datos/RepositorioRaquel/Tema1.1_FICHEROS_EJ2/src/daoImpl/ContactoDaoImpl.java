package daoImpl;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import io.ObjectOutputStreamNoHeader;

import beans.Contacto;
import dao.ContactoDao;

public class ContactoDaoImpl implements ContactoDao{

	File ficheroAgenda;
	String nombreFichero;
	
	public ContactoDaoImpl(File f) {
		ficheroAgenda = f;
	}
	
	public ContactoDaoImpl(String nombreFichero) {
		this.nombreFichero = nombreFichero;
	}
	
	@Override
	public void addContacto(Contacto c) throws FileNotFoundException, IOException {
		
		//File ficheroAgenda = new File(nombreFichero);
		
		ObjectOutputStream oos = null;
		if(ficheroAgenda.exists())
			oos = new ObjectOutputStreamNoHeader(new FileOutputStream(ficheroAgenda,true));
		else
			oos = new ObjectOutputStream(new FileOutputStream(ficheroAgenda));
		
		oos.writeObject(c);
		oos.close();
	}

	@Override
	public Contacto getContacto(String nombre) throws FileNotFoundException, IOException, ClassNotFoundException {
		
		//File ficheroAgenda = new File(nombreFichero);
		ObjectInputStream ois = 
				new ObjectInputStream(new FileInputStream(ficheroAgenda));
		
		Contacto c = null;
		boolean fin = false;
		while(!fin) {
			try {
				c = (Contacto) ois.readObject();
				if(c.getNombre().equals(nombre))
					fin = true;
			}catch(EOFException e) {
				fin = true;
			}
		}
		ois.close();
		return c;
	}

	@Override
	public void updateContacto(String nombre, Contacto c) throws FileNotFoundException, IOException, ClassNotFoundException {
		
		//File ficheroAgenda = new File(nombreFichero);
		ObjectOutputStream oos = 
				new ObjectOutputStream(new FileOutputStream(new File("temp")));
		
		ObjectInputStream ois = 
				new ObjectInputStream(new FileInputStream(ficheroAgenda));
		
		boolean fin = false;
		while(!fin) {
			try {
				Contacto cLeido = (Contacto) ois.readObject();
				if(cLeido.getNombre().equals(nombre))
					oos.writeObject(c);
				else
					oos.writeObject(cLeido);
			}catch(EOFException e) {
				fin = true;
			}
		}
		ois.close();
		oos.close();
		
		System.out.println(ficheroAgenda.delete());
		new File("temp").renameTo(ficheroAgenda);
	}

	@Override
	public void deleteContacto(String nombre) {
		// TODO Auto-generated method stub
		
	}

}
