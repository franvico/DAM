using Practica2_XML.Modelos;
using System;
using System.Collections.Generic;
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
                            //GetAsignaturaMasCara();
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
                            // añade los nuevos datos al fichero
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
            
            XmlDocument xmlDoc = cargarFicheroXML(xmlPath);

            // creo un nuevo elemento a partir del objeto asignatura
            XmlElement asignaturaElement = xmlDoc.CreateElement("asignatura");
            asignaturaElement.SetAttribute("nombre", asignatura.nombre);


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
            XmlDocument xmlDoc = cargarFicheroXML(xmlPath);

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

        public static XmlDocument cargarFicheroXML(string filePath)
        {
            // crear objeto XmlDocument
            XmlDocument xmlDoc = new XmlDocument();

            // cargo el contenido de mi xml en el objeto XmlDoc
            xmlDoc.Load(filePath);

            return xmlDoc;
        }
    }
}
