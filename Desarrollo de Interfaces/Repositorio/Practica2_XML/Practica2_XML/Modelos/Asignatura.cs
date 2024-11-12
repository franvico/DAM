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

        public Asignatura() {
            alumnos = new List<Alumno>();
        }

        public void setNombre(string nombre)
        {
            this.nombre = nombre;
        }
        public void setAlumnos(List<Alumno> alumnos)
        {
            this.alumnos = alumnos;
        }

        public List<Alumno> getAlumnos()
        {
            return alumnos;
        }
    }
}
