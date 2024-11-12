using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Practica2_XML.Modelos
{
    internal class Alumno
    {

        public string nombre;
        public int edad;
        public int grado;

        public Alumno(string nombre, int edad, int grado)
        {
            this.nombre = nombre;
            this.edad = edad;
            this.grado = grado;
        }


    }
}
