# Usa una imagen base de Windows Server Core con .NET Framework 4.8
FROM mcr.microsoft.com/dotnet/framework/aspnet:4.8 AS base

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /inetpub/wwwroot

# Copia los archivos del proyecto (el archivo .csproj y el bin/Release)
COPY bin/Release/ .

# Expon el puerto 80 para que sea accesible
EXPOSE 80

# Define el comando de inicio para IIS (Internet Information Services)
ENTRYPOINT ["C:\\ServiceMonitor.exe", "w3svc"]