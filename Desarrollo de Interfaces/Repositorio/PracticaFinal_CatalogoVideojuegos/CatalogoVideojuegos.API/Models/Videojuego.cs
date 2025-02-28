using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace CatalogoVideojuegos.API.Models
{
    public class Videojuego
    {
        public int Id { get; set; }
        public string Titulo { get; set; }
        public decimal? Precio { get; set; }  // Puede ser nulo
        public string Portada { get; set; }
    }
}