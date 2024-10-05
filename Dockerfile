# Usa una imagen base de Java
FROM openjdk:17-slim

#Manteiner info
LABEL maintainer="Enrique <ebgvdeveloper@gmail.com>"

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /portfolio-web

# Copia el archivo JAR de tu proyecto al directorio /app en el contenedor
COPY target/portfolio-0.0.1-SNAPSHOT.jar /portfolio-web/portfolio-api.jar

# Expone el puerto
EXPOSE 8080

#execute the application
ENTRYPOINT ["java","-jar","portfolio-api.jar"]