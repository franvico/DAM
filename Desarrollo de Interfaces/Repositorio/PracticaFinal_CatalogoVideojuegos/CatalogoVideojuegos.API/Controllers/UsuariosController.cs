using System;
using System.Collections.Generic;
using System.Configuration;
using System.IO;
using System.Linq;
using System.Net;
using System.Security.Cryptography;
using System.Text;
using System.Web;
using System.Web.Mvc;
using CatalogoVideojuegos.API.Models;
using Dapper;
using MySql.Data.MySqlClient;
using static Mysqlx.Expect.Open.Types.Condition.Types;

namespace CatalogoVideojuegos.API.Controllers
{
    public class UsuariosController : Controller
    {
        private string connectionString = ConfigurationManager.ConnectionStrings["catalogo_videojuegos_DB"].ConnectionString;

        // Clave y IV para la encriptación (deberían ser constantes y seguras)
        private static readonly string key = "1234567890123456";  // 16 bytes para AES-128
        private static readonly string iv = "1234567890123456";   // 16 bytes

        // GET: Usuarios/GetAll
        public ActionResult GetAll()
        {
            using (var connection = new MySqlConnection(connectionString))
            {
                connection.Open();
                string query = "SELECT * FROM usuarios";
                var usuarios = connection.Query<Usuario>(query).ToList();
                return Json(usuarios, JsonRequestBehavior.AllowGet);
            }
        }

        // POST: Usuarios/Create
        [HttpPost]
        public ActionResult Create(Usuario nuevoUsuario)
        {
            try
            {
                using (var connection = new MySqlConnection(connectionString))
                {
                    connection.Open();

                    string query = "INSERT INTO usuarios (Nombre, Email, Password, Foto) VALUES (@Nombre, @Email, @Password, @Foto)";

                    int filasAfectadas = connection.Execute(query, new
                    {
                        nuevoUsuario.Nombre,
                        nuevoUsuario.Email,
                        nuevoUsuario.Password,
                        nuevoUsuario.Foto
                    });

                    if (filasAfectadas > 0)
                    {
                        return Json(new { mensaje = "Usuario registrado correctamente" });
                    }
                    else
                    {
                        return Json(new { error = "No se pudo registrar el usuario" });
                    }
                }
            }
            catch (Exception ex)
            {
                return Json(new { error = ex.Message });
            }
        }

        // GET: Usuarios/ExisteEmail?email=correo@ejemplo.com
        public ActionResult ExisteEmail(string email)
        {
            using (var connection = new MySqlConnection(connectionString))
            {
                connection.Open();
                string query = "SELECT COUNT(*) FROM usuarios WHERE Email = @Email";
                int count = connection.ExecuteScalar<int>(query, new { Email = email });

                return Json(count > 0, JsonRequestBehavior.AllowGet); // Retorna `true` si el email existe
            }
        }

        // POST: Usuarios/Login
        [HttpPost]
        public ActionResult Login(Usuario usuario)
        {
            if (usuario == null || string.IsNullOrWhiteSpace(usuario.Email) || string.IsNullOrWhiteSpace(usuario.Password))
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest, "Email y contraseña son obligatorios.");
            }

            using (var connection = new MySqlConnection(connectionString))
            {
                connection.Open();
                string query = "SELECT * FROM usuarios WHERE Email = @Email";
                var usuarioDB = connection.QueryFirstOrDefault<Usuario>(query, new { Email = usuario.Email });

                if (usuarioDB == null)
                {
                    return new HttpStatusCodeResult(HttpStatusCode.NotFound, "Usuario no encontrado.");
                }

                // Verificar si el usuario está baneado
                if (usuarioDB.Baneado) // Suponiendo que tienes una columna `Baneado` en la tabla
                {
                    return new HttpStatusCodeResult(HttpStatusCode.Forbidden, "Usuario baneado. No puede iniciar sesión.");
                }

                // Verificar la contraseña (suponiendo que la contraseña se almacena en texto plano)
                if (DesencriptarContraseña(usuarioDB.Password) != usuario.Password)
                {
                    return new HttpStatusCodeResult(HttpStatusCode.Unauthorized, "Contraseña incorrecta.");
                }

                // Devolver los datos del usuario en formato JSON
                return Json(usuarioDB, JsonRequestBehavior.AllowGet);
            }
        }
        // POST: Usuarios/CambiarEstadoBaneado
        [HttpPost]
        public ActionResult CambiarEstadoBaneado(int id, bool baneado)
        {
            try
            {
                using (var connection = new MySqlConnection(connectionString))
                {
                    connection.Open();
                    string query = "UPDATE usuarios SET baneado = @Baneado WHERE id = @ID";

                    int filasAfectadas = connection.Execute(query, new
                    {
                        Baneado = baneado,
                        ID = id
                    });

                    if (filasAfectadas > 0)
                    {
                        return Json(new { mensaje = baneado ? "Usuario baneado correctamente." : "Usuario desbaneado correctamente." });
                    }
                    else
                    {
                        return Json(new { error = "No se pudo actualizar el estado de baneo." });
                    }
                }
            }
            catch (Exception ex)
            {
                return Json(new { error = ex.Message });
            }
        }

        // POST: Usuarios/CambiarEstadoAdmin
        [HttpPut]
        public ActionResult CambiarEstadoAdmin(int id, bool admin)
        {
            try
            {
                using (var connection = new MySqlConnection(connectionString))
                {
                    connection.Open();

                    // Verificar si el usuario existe
                    string checkQuery = "SELECT COUNT(*) FROM usuarios WHERE id = @ID";
                    int userExists = connection.ExecuteScalar<int>(checkQuery, new { ID = id });

                    if (userExists == 0)
                    {
                        return Json(new { error = "Usuario no encontrado." });
                    }

                    // Actualizar estado de admin
                    string query = "UPDATE usuarios SET admin = @Admin WHERE id = @ID";
                    int filasAfectadas = connection.Execute(query, new
                    {
                        Admin = admin,
                        ID = id
                    });

                    if (filasAfectadas > 0)
                    {
                        return Json(new { mensaje = admin ? "Usuario ahora es administrador." : "Usuario ya no es administrador." });
                    }
                    else
                    {
                        return Json(new { error = "No se pudo actualizar el estado de administrador." });
                    }
                }
            }
            catch (Exception ex)
            {
                return Json(new { error = ex.Message });
            }
        }
        private string DesencriptarContraseña(string contraseñaEncriptada)
        {
            using (Aes aesAlg = Aes.Create())
            {
                aesAlg.Key = Encoding.UTF8.GetBytes(key);
                aesAlg.IV = Encoding.UTF8.GetBytes(iv);

                ICryptoTransform decryptor = aesAlg.CreateDecryptor(aesAlg.Key, aesAlg.IV);
                using (MemoryStream msDecrypt = new MemoryStream(Convert.FromBase64String(contraseñaEncriptada)))
                {
                    using (CryptoStream csDecrypt = new CryptoStream(msDecrypt, decryptor, CryptoStreamMode.Read))
                    {
                        using (StreamReader srDecrypt = new StreamReader(csDecrypt))
                        {
                            return srDecrypt.ReadToEnd();
                        }
                    }
                }
            }
        }
    }
}
