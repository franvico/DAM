using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace CatalogoVideojuegos.API.Models
{
    public class Compra
    {
        public int Id { get; set; }
        public int IdUsuario { get; set; }
        public List<int> IdsJuegos { get; set; }
        public DateTime Fecha { get; set; }
    }
}