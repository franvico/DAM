using CatalogoVideojuegos.API.Models;
using MySql.Data.MySqlClient;
using System;
using System.Collections.Generic;
using System;
using System.Collections.Generic;
using System.Configuration;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using CatalogoVideojuegos.API.Models;
using Dapper;
using MySql.Data.MySqlClient;

namespace CatalogoVideojuegos.API.Controllers
{
    public class VideojuegosController : Controller
    {
        private string connectionString = ConfigurationManager.ConnectionStrings["catalogo_videojuegos_DB"].ConnectionString;

        // GET: Videojuegos/GetAll
        public ActionResult GetAll()
        {
            using (var connection = new MySqlConnection(connectionString))
            {
                connection.Open();
                string query = "SELECT * FROM videojuegos";
                var videojuegos = connection.Query<Videojuego>(query).ToList();
                return Json(videojuegos, JsonRequestBehavior.AllowGet);
            }
        }

        // GET: Videojuegos/Details/5
        public ActionResult Details(int id)
        {
            return View();
        }

        // POST: Videojuegos/Create
        [HttpPost]
        public ActionResult Create(Videojuego nuevoVideojuego)
        {
            try
            {
                using (var connection = new MySqlConnection(connectionString))
                {
                    connection.Open();

                    string query = "INSERT INTO videojuegos (titulo, portada) VALUES (@Titulo, @Portada)";

                    int filasAfectadas = connection.Execute(query, new
                    {
                        nuevoVideojuego.Titulo,
                        nuevoVideojuego.Portada
                    });

                    if (filasAfectadas > 0)
                    {
                        return Json(new { mensaje = "Videojuego registrado correctamente" });
                    }
                    else
                    {
                        return Json(new { error = "No se pudo registrar el videojuego" });
                    }
                }
            }
            catch (Exception ex)
            {
                return Json(new { error = ex.Message });
            }
        }

        // GET: Videojuegos/Edit/5
        public ActionResult Edit(int id)
        {
            return View();
        }

        [HttpPost]
        public ActionResult ObtenerJuegosPorIds(List<int> ids)
        {
            try
            {
                using (MySqlConnection connection = new MySqlConnection(connectionString))
                {
                    connection.Open();

                    string query = "SELECT * FROM videojuegos WHERE id IN (" + string.Join(",", ids.Select((s, i) => "@id" + i)) + ")";

                    MySqlCommand command = new MySqlCommand(query, connection);

                    for (int i = 0; i < ids.Count; i++)
                    {
                        command.Parameters.AddWithValue("@id" + i, ids[i]);
                    }


                    using (MySqlDataReader reader = command.ExecuteReader())
                    {
                        List<Videojuego> juegos = new List<Videojuego>();

                        while (reader.Read())
                        {
                            Videojuego juego = new Videojuego
                            {
                                Id = reader.GetInt32("id"),
                                Titulo = reader.GetString("titulo"),
                                Portada = reader.GetString("portada")
                            };
                            juegos.Add(juego);
                        }

                        return Json(juegos);
                    }
                }
            }
            catch (Exception ex)
            {
                return Json(new { error = ex.Message });
            }
        }



        // GET: Videojuegos/Delete/5
        public ActionResult Delete(int id)
        {
            using (var connection = new MySqlConnection(connectionString))
            {
                connection.Open();
                string query = "DELETE FROM videojuegos WHERE id = @Id";
                int rowsAffected = connection.Execute(query, new { Id = id });

                if (rowsAffected > 0)
                {
                    return Json(new { success = true, message = "Videojuego eliminado correctamente." }, JsonRequestBehavior.AllowGet);
                }
                else
                {
                    return Json(new { success = false, message = "No se encontró el videojuego." }, JsonRequestBehavior.AllowGet);
                }
            }
        }

        // POST: Videojuegos/Delete/5
        [HttpPost]
        public ActionResult Delete(int id, FormCollection collection)
        {
            try
            {
                // TODO: Add delete logic here

                return RedirectToAction("Index");
            }
            catch
            {
                return View();
            }
        }
    }
}
