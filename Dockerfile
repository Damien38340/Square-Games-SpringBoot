# Utiliser une image de base Ubuntu
FROM ubuntu:24.04

# Mettre à jour les paquets et installer Java 21
RUN apt update && apt install -y openjdk-21-jdk

RUN apt-get -y install maven

# Vérifier l'installation de Java
RUN java -version

# Définir le répertoire de travail
WORKDIR /Square-Games-SpringBoot

# Copier le fichier pom.xml et télécharger les dépendances
COPY pom.xml .
COPY settings.xml /root/.m2/

## It is necessary to add the settings.xml file to the root of the project to make it work
##

RUN mvn dependency:resolve

# Copier le reste des fichiers sources et construire l'application
COPY src/ ./src/

#RUN mvn clean package -DskipTests
#
## Exposer le port
#EXPOSE 8080

# Commande pour démarrer l'application Java
CMD ["mvn", "spring-boot:run"]