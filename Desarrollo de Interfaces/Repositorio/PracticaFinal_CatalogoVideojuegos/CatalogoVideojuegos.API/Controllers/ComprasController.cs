using System;
using System.Collections.Generic;
using System.Configuration;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using CatalogoVideojuegos.API.Models;
using Dapper;
using MySql.Data.MySqlClient;
using Newtonsoft.Json;

namespace CatalogoVideojuegos.API.Controllers
{
    public class ComprasController : Controller
    {
        private string connectionString = ConfigurationManager.ConnectionStrings["catalogo_videojuegos_DB"].ConnectionString;

        private static List<int> carrito = new List<int>();

        // POST: Compras/AgregarAlCarrito
        [HttpPost]
        public ActionResult AgregarAlCarrito(int videojuegoId)
        {
            try
            {
                carrito.Add(videojuegoId); // Agregar el videojuego a la lista

                return Json(new { mensaje = "Videojuego agregado al carrito correctamente." });
            }
            catch (Exception ex)
            {
                return Json(new { error = ex.Message });
            }
        }

        // POST: Compras/QuitarDelCarrito
        [HttpPost]
        public ActionResult QuitarDelCarrito(int videojuegoId)
        {
            try
            {
                if (carrito.Contains(videojuegoId))
                {
                    carrito.Remove(videojuegoId);
                    return Json(new { mensaje = "Videojuego eliminado del carrito correctamente." });
                }
                else
                {
                    return Json(new { error = "El videojuego no está en el carrito." });
                }
            }
            catch (Exception ex)
            {
                return Json(new { error = ex.Message });
            }
        }

        // POST: Compras/ObtenerCarrito
        [HttpPost]
        public ActionResult ObtenerCarrito()
        {
            try
            {
                // Si el carrito está vacío, devolvemos una lista vacía
                if (carrito.Count == 0)
                {
                    return Json(new { juegos = new List<Videojuego>() });
                }

                using (MySqlConnection connection = new MySqlConnection(connectionString))
                {
                    connection.Open();
                    string query = "SELECT id, titulo, portada FROM videojuegos WHERE id IN (" + string.Join(",", carrito) + ")";
                    List<Videojuego> listaJuegos = connection.Query<Videojuego>(query).ToList();

                    return Json(new { juegos = listaJuegos });
                }
            }
            catch (Exception ex)
            {
                return Json(new { error = ex.Message });
            }
        }

        [HttpPost]
        public ActionResult ConfirmarCompra(int idUsuario, List<int> idsJuegos)
        {
            try
            {
                using (MySqlConnection connection = new MySqlConnection(connectionString))
                {
                    connection.Open();

                    // Convertimos la lista de IDs de juegos a JSON para almacenarla en la base de datos
                    string juegosJson = JsonConvert.SerializeObject(idsJuegos);

                    string query = "INSERT INTO compras (id_usuario, ids_juegos) VALUES (@idUsuario, @idsJuegos)";
                    MySqlCommand command = new MySqlCommand(query, connection);
                    command.Parameters.AddWithValue("@idUsuario", idUsuario);
                    command.Parameters.AddWithValue("@idsJuegos", juegosJson);

                    int filasAfectadas = command.ExecuteNonQuery();

                    if (filasAfectadas > 0)
                    {
                        return Json(new { mensaje = "Compra confirmada exitosamente." });
                    }
                    else
                    {
                        return Json(new { error = "No se pudo registrar la compra." });
                    }
                }
            }
            catch (Exception ex)
            {
                return Json(new { error = ex.Message });
            }
        }

        // POST: Compras/VaciarCarrito
        [HttpPost]
        public ActionResult VaciarCarrito()
        {
            try
            {
                carrito.Clear();

                return Json(new { mensaje = "Carrito vaciado correctamente." });
            }
            catch (Exception ex)
            {
                return Json(new { error = ex.Message });
            }
        }

        [HttpPost]
        public ActionResult ObtenerJuegosComprados(int idUsuario)
        {
            try
            {
                using (MySqlConnection connection = new MySqlConnection(connectionString))
                {
                    connection.Open();
                    string query = "SELECT ids_juegos FROM compras WHERE id_usuario = @idUsuario";
                    MySqlCommand command = new MySqlCommand(query, connection);
                    command.Parameters.AddWithValue("@idUsuario", idUsuario);

                    using (MySqlDataReader reader = command.ExecuteReader())
                    {
                        List<int> juegosComprados = new List<int>();

                        while (reader.Read())
                        {
                            string juegosString = reader.GetString("ids_juegos");
                            List<int> ids = JsonConvert.DeserializeObject<List<int>>(juegosString);
                            juegosComprados.AddRange(ids);
                        }

                        return Json(juegosComprados);
                    }
                }
            }
            catch (Exception ex)
            {
                return Json(new { error = ex.Message });
            }
        }


    }
}
