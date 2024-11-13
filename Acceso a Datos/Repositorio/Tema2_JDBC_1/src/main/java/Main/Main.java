package Main;

import java.sql.Connection;

public class Main {

	public static void main(String[] args) {

		
		// HE TENIDO QUE IMPORTAR LAS DEPENDENCIAS DE MAVEN REPOSITORY PARA MYSQL EN EL FICHERO pom.xml
		Connection con = new conexion.Conexion().getConex();

	}

}
