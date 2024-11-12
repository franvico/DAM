using Practica2_XML.Modelos;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Security.Policy;
using System.Text;
using System.Threading.Tasks;
using System.Xml;

namespace Practica2_XML
{
    internal class Program
    {

        static string xmlPath = "universidad.xml";
        static string xmlPathMod = "universidadMod.xml";
        static string xsdPath = "universidad.xsd";

        static void Main(string[] args)
        {
            bool exit = false;

            while (!exit)
            {
                // Mostrar menú de opciones
                Console.WriteLine("SELECCIONE UNA OPCIÓN:\n");
                Console.WriteLine("1. Consultar todas las asignaturas");
                Console.WriteLine("2. Añadir nueva asignatura");
                Console.WriteLine("3. Buscar alumnos por asignatura específica");
                Console.WriteLine("4. Validar archivo xml con xsd");
                Console.WriteLine("0. Salir\n");

                // Leer la opción seleccionada
                int opcion;
                if (int.TryParse(Console.ReadLine(), out opcion))
                {
                    // Manejo de las opciones con un switch
                    switch (opcion)
                    {
                        case 1:
                            consultarAsignaturas();
                            break;
                        case 2:
                            crearAsignatura();
                            break;
                        case 3:
                            consultarAlumnosPorAsignatura();
                            break;
                        case 4:
                            validarXML();
                            break;
                        case 0:
                            Console.WriteLine("Adiós");
                            exit = true;
                            break;
                        default:
                            Console.WriteLine("\nSeleccione una opción válida\n");
                            break;
                    }
                }
                else
                {
                    Console.WriteLine("\nOpción inválida. Por favor ingrese un número.\n");
                }
            }

        }

        public static void validarXML()
        {
            XmlReaderSettings settings = new XmlReaderSettings();
            settings.Schemas.Add(null, xsdPath);
            settings.ValidationType = ValidationType.Schema;

            XmlReader reader = XmlReader.Create(xmlPath, settings);

            try
            {
                while (reader.Read())
                {

                }
                Console.WriteLine("El archivo XML es válido.");
            }
            catch(Exception e)
            {
                Console.WriteLine("Error de XML: " + e.Message);
            }
        }

        public static void consultarAlumnosPorAsignatura()
        {
            Dictionary<string, XmlNode> asignaturas = consultarAsignaturasExistentes();

            Console.WriteLine("\nESCRIBE EL NOMBRE DE LA ASIGNATURA A CONSULTAR:\n");
            foreach(string nombreAsignatura in asignaturas.Keys)
            {
                Console.WriteLine("- " + nombreAsignatura);
            }

            mostrarAlumnos(Console.ReadLine(), asignaturas);

        }

        public static void mostrarAlumnos(string nombreAsignatura, Dictionary<string, XmlNode> asignaturas)
        {
            if (!asignaturas.Keys.Contains(nombreAsignatura))
            {
                Console.WriteLine("\nLa asignatura solicitada no existe");
                consultarAlumnosPorAsignatura();
            }
            else
            {
                Console.WriteLine("\nAlumnos matriculados en " + asignaturas[nombreAsignatura].Attributes["nombre"].Value + "\n");

                XmlNodeList nodosAlumno = asignaturas[nombreAsignatura].SelectNodes("alumno");
                foreach(XmlNode nodoAlumno in nodosAlumno)
                {
                    Console.WriteLine("\tNombre: " + nodoAlumno["nombre"].InnerText);
                    Console.WriteLine("\tEdad: " + nodoAlumno["edad"].InnerText);
                    Console.WriteLine("\tGrado: " + nodoAlumno["grado"].InnerText + "\n");
                }
            }
        }

        public static Dictionary<string, XmlNode> consultarAsignaturasExistentes()
        {
            Dictionary<string, XmlNode> nombresAsignaturas = new Dictionary<string, XmlNode>();

            // cargo el fichero
            XmlDocument xmlDoc = cargarFicheroXML();

            // saco una lista de nodos asignatura
            XmlNodeList nodosAsignatura = xmlDoc.SelectNodes("universidad/asignatura");

            // recorro todos y guardo su atributo "nombre"
            foreach(XmlNode nodoAsignatura in nodosAsignatura)
            {
                nombresAsignaturas.Add(nodoAsignatura.Attributes["nombre"]?.Value, nodoAsignatura);
            }

            return nombresAsignaturas;
        }

        public static void crearAsignatura()
        {
            Asignatura asignatura = new Asignatura();
            List<Alumno> listaAlumnos = new List<Alumno>();

            Console.WriteLine("\nIntroduce el de la asignatura");
            asignatura.setNombre(Console.ReadLine());

            bool exit = false;

            while (!exit)
            {
                Console.WriteLine("SELECCIONE UNA OPCIÓN:\n");
                Console.WriteLine("1. Añadir alumno a la asignatura");
                Console.WriteLine("2. Salir");

                int opcion;
                if (int.TryParse(Console.ReadLine(), out opcion))
                {
                    // Manejo de las opciones con un switch
                    switch (opcion)
                    {
                        case 1:
                            listaAlumnos.Add(crearAlumno());
                            break;
                        case 2:
                            asignatura.setAlumnos(listaAlumnos);                            
                            modificarFicheroXML(asignatura);
                            exit = true;
                            break;
                        default:
                            Console.WriteLine("\nSeleccione una opción válida\n");
                            break;
                    }
                }
                else
                {
                    Console.WriteLine("\nOpción inválida. Por favor ingrese un número.\n");
                }
            }

        }

        public static void modificarFicheroXML(Asignatura asignatura)
        {
            XmlDocument xmlDoc = cargarFicheroXML();

            // creo un nuevo elemento <asignatura>
            XmlElement elementoAsignatura = xmlDoc.CreateElement("asignatura");
            elementoAsignatura.SetAttribute("nombre", asignatura.nombre); // añado su atributo con los datos del objeto Asignatura

            foreach(Alumno alumno in asignatura.getAlumnos())
            {
                // creo un nuevo elemento <alumno>
                XmlElement elementoAlumno = xmlDoc.CreateElement("alumno");

                // creo los elementos <nombre> <edad> y <grado>
                XmlElement elementoNombre = xmlDoc.CreateElement("nombre");
                XmlElement elementoEdad = xmlDoc.CreateElement("edad");
                XmlElement elementoGrado = xmlDoc.CreateElement("grado");

                // asigno el valor de cada uno a partir de los datos del objeto Alumno
                elementoNombre.InnerText = alumno.nombre;
                elementoEdad.InnerText = alumno.edad.ToString();
                elementoGrado.InnerText = alumno.grado.ToString();

                // añado estos elementos al elemento <alumno>
                elementoAlumno.AppendChild(elementoNombre);
                elementoAlumno.AppendChild(elementoEdad);
                elementoAlumno.AppendChild(elementoGrado);

                // añado el elemento <alumno> ya seteado al elemento <asignatura>
                elementoAsignatura.AppendChild(elementoAlumno);
            }

            // selecciono el nodo raíz (universidad)
            XmlNode nodoUniversidad = xmlDoc.SelectSingleNode("universidad");

            // añado el elemento <asignatura> al nodo <universidad>
            nodoUniversidad.AppendChild(elementoAsignatura);

            // guardo el documento en una nueva ruta
            xmlDoc.Save(xmlPathMod);


        }
        public static Alumno crearAlumno()
        {
            Alumno alumno = new Alumno();

            Console.WriteLine("Introduce el nombre del alumno");
            alumno.setNombre(Console.ReadLine());
            Console.WriteLine("Introduce la edad del alumno");
            alumno.setEdad(int.Parse(Console.ReadLine()));
            Console.WriteLine("Introduce el grado del alumno");
            alumno.setGrado(int.Parse(Console.ReadLine()));

            return alumno;
        }

        public static void consultarAsignaturas()
        {
            XmlDocument xmlDoc = cargarFicheroXML();

            // recojo todos los nodos "asignatura" dentro del nodo raíz
            XmlNodeList asignaturas = xmlDoc.SelectNodes("universidad/asignatura");

            Console.WriteLine("Lista de Asignaturas\n");

            foreach (XmlNode asignatura in asignaturas)
            {
                XmlNodeList alumnos = asignatura.SelectNodes("alumno");

                Console.WriteLine(asignatura.Attributes["nombre"]?.Value + "\n");

                foreach (XmlNode alumno in alumnos)
                {
                    Console.WriteLine("\tNombre: " + alumno["nombre"]?.InnerText);
                    Console.WriteLine("\tEdad: " + alumno["edad"]?.InnerText);
                    Console.WriteLine("\tGrado: " + alumno["grado"]?.InnerText + "\n");
                }                
            }            
        }

        public static XmlDocument cargarFicheroXML()
        {
            // crear objeto XmlDocument
            XmlDocument xmlDoc = new XmlDocument();

            // compruebo si ya tengo un fichero universidadMod.xml (no quiero sobreescribir el original de momento)
            string path = File.Exists(xmlPathMod) ? xmlPathMod: xmlPath;
            // cargo el fichero
            xmlDoc.Load(path);

            return xmlDoc;
        }
    }
}
