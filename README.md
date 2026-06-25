--LYRIQ--

LYRIQ es una plataforma de música desarrollada con arquitectura de microservicios utilizando Spring Boot. El sistema permite administrar artistas, canciones, playlists, historial, favoritos, recomendaciones y notificaciones, además de autenticación mediante JWT.

Tecnologías utilizadas

- Java 17
- Spring Boot
- Spring Data JPA
- Spring Security (JWT)
- Spring Cloud Gateway
- MySQL
- Maven
- HATEOAS
- Swagger/OpenAPI
- Git y GitHub
- Docker

Arquitectura

El proyecto está compuesto por los siguientes microservicios:

- API Gateway
- Auth Service
- Artist Service
- Catalog Service
- Favorites Service
- History Service
- Notification Service
- Playlist Service
- Playlist Song Service
- Recommendation Service
- Swipe Service

Funcionalidades

- Registro e inicio de sesión con JWT.
- Gestión de artistas.
- Gestión de canciones y géneros.
- Gestión de playlists.
- Gestión de favoritos.
- Historial de reproducciones.
- Recomendaciones de canciones.
- Notificaciones.
- Sistema de Swipe.
- API Gateway para centralizar las peticiones.

Características implementadas

- Arquitectura de microservicios.
- DTOs y validaciones.
- Manejo de excepciones.
- Logging.
- HATEOAS.
- Documentación con Swagger/OpenAPI.
- Seguridad mediante JWT.

Ejecución

1. Clonar el repositorio.

```bash
git clone https://github.com/valeitt/LYRIQ-ULTIMA.git
```

2. Configurar la base de datos MySQL.

3. Ejecutar cada microservicio.

4. Iniciar el API Gateway.

Integrantes

- Valentina Osses
-Raihen Farias

Repositorio

https://github.com/valeitt/LYRIQ-ULTIMA
