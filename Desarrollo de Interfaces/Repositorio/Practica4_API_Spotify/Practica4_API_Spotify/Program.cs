using System.Net;

var builder = WebApplication.CreateBuilder(args);

// Add services to the container.
builder.Services.AddRazorPages();

var app = builder.Build();

// Configure the HTTP request pipeline.
if (!app.Environment.IsDevelopment())
{
    app.UseExceptionHandler("/Error");
    // The default HSTS value is 30 days. You may want to change this for production scenarios, see https://aka.ms/aspnetcore-hsts.
    app.UseHsts();
}

app.UseHttpsRedirection();
app.UseStaticFiles();

app.UseRouting();

app.UseAuthorization();

app.MapRazorPages();

app.MapGet("/spotify/auth", () =>
{
    string clientId = "ca7ce4312917427290255a8754e672fd";
    string redirectUri = "https://localhost:7244/";
    string scope = "user-read-private user-read-email playlist-read-private";

    // Construir la URL de autorización
    string authUrl = $"https://accounts.spotify.com/authorize" +
                     $"?client_id={clientId}" +
                     $"&response_type=code" +
                     $"&redirect_uri={WebUtility.UrlEncode(redirectUri)}" +
                     $"&scope={WebUtility.UrlEncode(scope)}";

    Console.WriteLine("Por favor, abre esta URL en tu navegador para autorizar la aplicación:");
    Console.WriteLine(authUrl);
    return Results.Text($"Por favor, abre esta URL en tu navegador para autorizar la aplicación:\n\n{authUrl}");
});

// Endpoint para manejar el callback de Spotify
app.MapGet("/callback", (HttpContext context) =>
{
    var query = context.Request.Query;

    if (query.ContainsKey("code"))
    {
        string code = query["code"];
        return Results.Text($"Código de autorización recibido: {code}");
    }
    else
    {
        return Results.Text("No se recibió ningún código de autorización.");
    }
});

app.Run();
