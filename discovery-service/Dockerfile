FROM openjdk:17-oracle
# un volume mais ici ça ne sert à rien
VOLUME /tmp
# copie et rénome le fichier discovery-service-0.0.1-SNAPSHOT.jar en app.jar
COPY target/*.jar app.jar
# on exécute notre application
ENTRYPOINT ["java", "-jar", "app.jar"]
