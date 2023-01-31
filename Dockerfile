FROM openjdk:17-jdk-slim

LABEL NAME="DIANA MEDIAVILLA"
LABEL MATERIA="DISTRIBUIDA"

# copiar archivo jar y librerías
COPY ./build/install/app-web/lib/app-web-1.0-SNAPSHOT.jar ./
COPY ./build/install/app-web/lib ./lib

CMD ["java", "-cp", "app-web-1.0-SNAPSHOT.jar:./lib/*", "com.distribuida.Main"]

EXPOSE 4567
