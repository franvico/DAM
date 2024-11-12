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

        static string filePath = "universidad.xml";
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
                            añadirAsignatura();
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

        public static void añadirAsignatura()
        {


            Console.WriteLine("Nombre de la asignatura");
        }

        public static void consultarAsignaturas()
        {
            // crear objeto XmlDocument
            XmlDocument xmlDoc = new XmlDocument();

            // cargo el contenido de mi xml en el objeto XmlDoc
            xmlDoc.Load(filePath);

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
    }
}
