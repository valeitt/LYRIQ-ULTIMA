@echo off

echo =====================================
echo LIMPIANDO CONTENEDORES E IMAGENES
echo =====================================

docker compose down -v

FOR /f %%i IN ('docker ps -aq') DO docker rm -f %%i
FOR /f %%i IN ('docker images -aq') DO docker rmi -f %%i
FOR /f %%i IN ('docker volume ls -q') DO docker volume rm %%i
FOR /f %%i IN ('docker network ls --filter "type=custom" -q') DO docker network rm %%i

echo.
echo =====================================
echo COMPILANDO MICROSERVICIOS
echo =====================================

cd api-gateway
call .\mvnw clean package -DskipTests

cd ..\artist-service
call .\mvnw clean package -DskipTests

cd ..\auth-service
call .\mvnw clean package -DskipTests

cd ..\catalog-service
call .\mvnw clean package -DskipTests

cd ..\favorites-service
call .\mvnw clean package -DskipTests

cd ..\history-service
call .\mvnw clean package -DskipTests

cd ..\notification-service
call .\mvnw clean package -DskipTests

cd ..\playlist-service
call .\mvnw clean package -DskipTests

cd ..\playlist-song-service
call .\mvnw clean package -DskipTests

cd ..\recommendation-service
call .\mvnw clean package -DskipTests

cd ..\swipe-service
call .\mvnw clean package -DskipTests

cd ..

echo.
echo =====================================
echo CREANDO IMAGENES DOCKER
echo =====================================

docker compose build --no-cache

echo.
echo =====================================
echo LEVANTANDO CONTENEDORES
echo =====================================

docker compose up -d

echo.
echo =====================================
echo PROYECTO INICIADO
echo =====================================

echo.
echo API Gateway:
echo http://localhost:7070
echo.

pause