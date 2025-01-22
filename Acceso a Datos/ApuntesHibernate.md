# HIBERNATE

### (Opcional) Cambiar versión del jdk y del nivel de compilación del proyecto
- Clic derecho en JRE System Library -> Properties -> Workspace default JRE (jdk-22)
- Clic derecho en el proyecto -> Properties -> Java Compiler -> Compiler Compliance Level (22)

## 1. Añadir la dependencia de Hibernate (se apoya en JDBC así que hay que añadir su driver)

<!-- https://mvnrepository.com/artifact/org.hibernate.orm/hibernate-core -->
<dependency>
    <groupId>org.hibernate.orm</groupId>
    <artifactId>hibernate-core</artifactId>
    <version>6.6.4.Final</version>
</dependency>


## 2. Añadir dependencia de mysql connector
<!-- https://mvnrepository.com/artifact/com.mysql/mysql-connector-j -->
<dependency>
    <groupId>com.mysql</groupId>
    <artifactId>mysql-connector-j</artifactId>
    <version>8.0.33</version>
</dependency>

## Instalar Pluging Maven Tool (hacer en mi portátil también)
NOTA: Si no aparece nada en el marketplace: actualizar Eclipse (Help -> Check for updates)
- Ir a marketplace (help -> Eclipse Marketplace)
- Buscar JBoos Tools (Sólo marcar "Hibernate Tools" se marcarán automáticamente)

## Perspectiva de Hibernate (más fácil de visualizar)
 - Window -> Open Perspective -> Hibernate
 - Cargar esta perspectiva para generar el fichero de configuración de Hibernate de la misma forma que antes por si la anterior da error o no se genera correctamente

## Generar clases nuevas
- Clic derecho en el proyecto -> New -> Other -> Hibernate -> Hibernate Configuration
     Es un fichero XML que se puede hacer a mano pero se puede hacer más fácil con el plugin de hibernate.
- Datos para el fichero:
    - Version:
    - Database: mysql
    - Driver: com.mysql.jcbc.Driver
    - Conexión URL: jdbcmysql:/<hostname>/<database>
    - Default Schema: proyectos
    - Default catalog: proyectos
    - Username:
    - Password
    - No marcar la última casilla
Puedo ver la generación del código del fichero en la pestaña "source"

## Crear hibernate console
Permite verificar si la conexión a BBDD es correcta. Sirve como referencia para que el plugin genere las clases desde la BBDD

## Generar las clases de la BBDD
Clic Derecho -> new -> reverse blablabla
<br>
Consola asociada al proyecto -> incluir tablas de las que quiero generar la clase<br>
Me crea un fichero de configuración<br>
Boton de start (verde) -> generar nuevas clases -> output directory (la carpeta java del proyecto)
reveng.xml (el fichero que me ha creado antes)