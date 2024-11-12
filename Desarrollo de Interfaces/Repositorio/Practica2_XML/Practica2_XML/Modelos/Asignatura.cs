using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Practica2_XML.Modelos
{
    internal class Asignatura
    {
        public string nombre;
        public List<Alumno> alumnos = new List<Alumno>();

        public Asignatura(string nombre, List<Alumno> alumnos)
        {
            this.nombre = nombre;
            this.alumnos = alumnos;
        }
    }
}
