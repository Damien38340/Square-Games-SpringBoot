## Utiliser une image de base contenant maven et java 21
#FROM maven:3.9-eclipse-temurin-21
#
## Définir le répertoire de travail
#WORKDIR /Square-Games-SpringBoot
#
## Copier le fichier pom.xml et télécharger les dépendances
#COPY pom.xml .
#COPY settings.xml /root/.m2/
#
### It is necessary to add the settings.xml file to the root of the project to make it work
###
#
#RUN mvn dependency:resolve
#
## Copier le reste des fichiers sources et construire l'application
#COPY src/ ./src/
#
##RUN mvn clean package -DskipTests
##
### Exposer le port
##EXPOSE 8080
#
## Commande pour démarrer l'application Java
#CMD ["mvn", "spring-boot:run"]

# Base image with JDK
FROM eclipse-temurin:21-jdk

# Set working directory
WORKDIR /app

# Copy the JAR file from the build pipeline
COPY target/*.jar app.jar

# Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]
