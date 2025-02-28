using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace CatalogoVideojuegos.API.Models
{
    public class Usuario
    {
        public int Id { get; set; }
        public string Nombre { get; set; }
        public string Email { get; set; }
        public string Password { get; set; }
        public string Foto { get; set; }
        public bool Baneado { get; set; }
        public bool Admin { get; set; }
    }
}