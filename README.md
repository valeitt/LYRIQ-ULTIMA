LYRIQ

Descripción

LYRIQ es una aplicación desarrollada con Spring Boot utilizando una arquitectura de microservicios. El proyecto consiste en una plataforma de música donde es posible administrar artistas, canciones, géneros, playlists, favoritos, historial de reproducciones, recomendaciones y notificaciones.

Cada microservicio tiene su propia base de datos y funciona de forma independiente. Para la comunicación entre ellos se utilizó WebClient y todas las peticiones pasan por un API Gateway.

Tecnologías utilizadas

Backend

* Java 21
* Spring Boot
* Spring Data JPA
* Spring Validation
* Spring Cloud Gateway
* Spring HATEOAS
* Spring WebClient

Base de datos

* MySQL 8

Persistencia

* Hibernate
* Liquibase

Documentación

* Swagger / OpenAPI

Contenedores

* Docker
* Docker Compose

Pruebas

* JUnit 5
* Mockito
* JaCoCo

Control de versiones

* Git
* GitHub

Microservicios

| Microservicio          | Puerto |
| ---------------------- | ------ |
| API Gateway            | 7070   |
| Catalog Service        | 7071   |
| Auth Service           | 7072   |
| Swipe Service          | 7073   |
| Favorites Service      | 7074   |
| History Service        | 7075   |
| Playlist Service       | 7076   |
| Playlist Song Service  | 7077   |
| Recommendation Service | 7078   |
| Artist Service         | 7079   |
| Notification Service   | 7080   |

Organización del proyecto

Todos los microservicios siguen una estructura similar:

* Controller
* Service
* Repository
* Model
* DTO
* Config
* Exception
* Assembler

Funcionalidades

* Administración de artistas.
* Administración de canciones y géneros.
* Gestión de playlists.
* Asociación de canciones a playlists.
* Gestión de favoritos.
* Historial de reproducciones.
* Sistema de recomendaciones.
* Registro de swipes.
* Gestión de notificaciones.
* Documentación mediante Swagger.
* Navegación utilizando HATEOAS.

Comunicación entre microservicios

La comunicación entre servicios se implementó utilizando Spring WebClient.

Los principales servicios que se comunican son:

* Playlist Service → Catalog Service
* Playlist Song Service → Playlist Service
* Playlist Song Service → Catalog Service
* Favorites Service → Catalog Service
* Recommendation Service → Catalog Service
* Recommendation Service → History Service
* Notification Service → Auth Service
* Artist Service → Catalog Service

Mejoras implementadas

* API Gateway para centralizar las peticiones.
* Docker Compose para ejecutar todos los servicios junto con MySQL.
* Perfiles de configuración para ejecución local y Docker.
* HATEOAS en los principales recursos.
* Swagger para documentar la API.
* Liquibase para crear automáticamente las tablas de cada servicio.
* Manejo global de excepciones.
* Validaciones utilizando Bean Validation.
* Logging con SLF4J.
* Pruebas unitarias con JUnit y Mockito.

Estructura del proyecto

```text
LYRIQ
│
├── api-gateway
├── artist-service
├── auth-service
├── catalog-service
├── favorites-service
├── history-service
├── notification-service
├── playlist-service
├── playlist-song-service
├── recommendation-service
├── swipe-service
├── docker-compose.yml
├── build-all.bat
└── README.md
```

Ejecución

Clonar el repositorio:

```bash
git clone https://github.com/valeitt/LYRIQ-ULTIMA.git
```

Compilar los microservicios:

```bash
build-all.bat
```

Levantar el proyecto:

```bash
docker compose up --build
```

Swagger

```
http://localhost:7070/swagger-ui/index.html
```

Integrantes

* Valentina Ignacia Osses Aravena
* Raihen Farías

Repositorio

https://github.com/valeitt/LYRIQ-ULTIMA
