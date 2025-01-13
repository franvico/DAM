# JDBC
0. Crear proyecto
    - File -> new -> Dynamic Web Project
    - Clic derecho en el proyecto -> Configure -> Convert to Maven Project
1. Acceso a BBDD desde Java -> API JDBC -> java.sql<br>
    Driver depende del gestor de BBDD (oracle, mysql...) -> se incorporan las clases de ese driver al proyecto añadiendo el .jar de ese driver

2. Eclipse web developer. (Es un ejecutable, está en la carpeta).<br>
    Aplicaciones Web java para desplegada en un servidor Tomcat
    El servidor a la escucha ejecuta la clase java servlet. La petición al servidor llega a través de una petición http

3. Servidor Web Apache Tomcat
    - Descargar Apache Tomcat 10.1
    - Configurar variable de entorno JAVA_HOME para que apunte a jdk (la usa Tomcat para ejecutarse.)

### Ejercicio 2
1. Crear base de datos empleado-proyecto
    
    ```bash
    create database proyectos;

    use proyectos;

    create table empleado(
    dni char(9) not null,
    nom_emp varchar(40) not null,
    primary key(dni)
    );



    create table proyecto (
    id_proy integer auto_increment not null,
    nom_proy varchar(32) not null,
    dni_jefe_proy char(9) not null,
    primary key(id_proy),
    foreign key fk_pry_jefe(dni_jefe_proy) references empleado(dni)
    );

    create table asig_proyecto (
    dni_emp char(9),
    id_proy integer not null,
    primary key(dni_emp, id_proy),
    foreign key fk_asig_emp(dni_emp) references empleado(dni),
    foreign key fk_asig_proy(id_proy) references proyecto(id_proy)
    );
    ```

2. Proyecto Web en eclipse Web<br>
    2.1 Añadir el servidor Tomcat al proyecto (previamente descargado, se encuentra en su carpeta)

        2.1.1 Window -> Preferences -> Server -> Runtime Enviroment -> Add -> Añadir la carpeta donde este el apache tomcat

        2.1.2 Paso 2 de la duda 1


    2.2 Explicación de los recursos de la aplicación

    
    - Contenido Web
        - La raíz del la aplicación en webapp
            - De aquí cuelgan los ficheros .html (ficheros que lee el navegador)
            - Al darle a un botón que haya dentro de un .html me lleva a un servlet del contenido java
        - web.xml -> define el arranque de la página en una de sus etiquetas
    - Ficheros de configuración
    - Contenido java (servidor)
        - com.pancetas -> 5 servlets (1 para cada funcionalidad)
    
    2.3 Lanzar aplicación
    - Clic derecho en la aplicación -> Run As -> Run on server
    - Puedo elegir en qué navegador me lanzará la aplicación en: Window -> Web Browser

3. Subir a servidor de pre-producción

    3.1 Generar un WAR
    - Clic derecho en el proyecto -> Export -> WAR File (no marcar Export source files) -> Exportar donde queramos

    3.2 Lanzar Tomcat
    - Con el ejecutable que está en /bin en al carpeta donde está instalado el Tomcat (desde la terminal) -> estará escuchando en localhost:8080 (o en el puerto especificado en server.xml)

    3.3 Desplegar el WAR
    - Opción 1: En la parte de Desplegar WAR en la interfaz de Tomcat (selecciono el WAR generado)
    - Opción 2: Copiar el WAR en la carpeta /webapp y reiniciar Tomcat (desde la terminal)

    3.4 Ver que está bien desplegado
    - En la carpeta webapp se ha generado la carpeta de la aplicación a partir del WAR

    3.5 Ejecutar la apliacion
    - Desde el navegador llamar a localhost:8080/nombre_de_la_app


4. Funcionalidades concretas de la aplicación

    4.1 Cómo subir archivos
    - En el ejercicio UploadFileServer (en el aula virtual)
    
    4.1 Cómo descargar ficheros

### DUDAS
1. Dónde se asocia que la aplicación al servidor Tomcat? (o viceversa)
    1. Añadir el servidor Tomcat a mi lista de servers en la pestaña "Server" abajo. Si he hecho el paso 2.1 debería aparecerme ya.
    2. Clic derecho en la aplicación -> Build Path -> Pestaña "Libraries" -> Clico en "Modulepath" -> Add Library -> Server Runtime -> Elijo el Apache Tomcat
    3. También puedo hacer clic derecho en el servidor Tomcat (pestaña "servers" abajo) -> Add -> añado la aplicación al server
2. Dónde hago la conexión con la BBDD? Dónde la configuro en la app?
3. Mirar bien cómo subir archivos al server (añadir empleados con un xml) y cómo generarlos y guardarlos en el ordenador desde la app (descargar un json)
