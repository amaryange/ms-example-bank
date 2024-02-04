FROM openjdk:17-oracle
# stocke nos données sur le serveur directement et non sur l'image
VOLUME /tmp
# copie et rénome le fichier gateway-service-0.0.1-SNAPSHOT.jar en app.jar
COPY target/*.jar app.jar
# on exécute notre application
ENTRYPOINT ["java", "-jar", "app.jar"]
