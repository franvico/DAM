# Catalogo de videojuegos

## Introducción
Este es el proyecto final de la asignatura de Desarrollo de Interfaces del CFGS DAM2. <br>
En él se pretende mostrar de manera práctica los conocimientos adquiridos en la asginatura a lo largo del curso.

## Definición
El proyecto consta de cuatro parte clave:<br>
1. **CatalogoVideojuegos.CLIENT**: proyecto que recoge la **interfaz gráfica**, compuesta por controles y forms que ayudarán a los usuarios a navegar e interactuar con la aplicación. También lleva a cabo la comunicación con el backend (proyecto API).<br><br>
2. **CatalogoVideojuegos.API**: proyecto en el que se define una **API Rest** que hará de intermediario en la comunicación entre la interfaz gráfica y la lógica de negocio. A su vez, la parte lógica se encargará de comunicarse con la base de datos.<br><br>
3. **Base de datos**: pequeña base de datos mySql en la que se recogen las tablas necesarias para hacer persistente la información creada durante la ejecución de la apliación.<br><br>
4. **Documentación**: se facilitan los **manuales de usuario y administrador** para el uso correcto de la aplicación. Se acompaña la explicación de su uso con capturas de pantalla. También se facilitan las **intrucciones de ejecución** de la aplicación, recogidas en este README.<br><br>

## Instrucciones de ejecución de la aplicación.
### 1. Creación de la base de datos.
Lanzar el script /database/init_database.sql
### 2. Inciar API
Iniciar proyecto CatalogoVideojuegos.API que correrá por defecto en (http://localhost:54072/)
### 3. Iniciar CLIENT
Iniciar proyecto CatalogoVideojuegos.CLIENT para acceder a la parte gráfica de la aplicación.