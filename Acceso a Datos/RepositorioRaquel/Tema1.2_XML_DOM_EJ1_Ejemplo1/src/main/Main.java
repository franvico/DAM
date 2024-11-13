package main;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;

import org.xml.sax.SAXException;

import logica.Funcionalidades;

public class Main {

	public static void main(String[] args) throws FileNotFoundException, ParserConfigurationException, SAXException, IOException, TransformerFactoryConfigurationError, TransformerException {
		
		//probamos:
		System.out.println(Funcionalidades.getNota("Javier"));
		
//		Funcionalidades.modificaNota("Eva",5);
		
//		Funcionalidades.insertarAlumno("Luis",8);

	}

}
