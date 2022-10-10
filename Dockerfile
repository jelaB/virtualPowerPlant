FROM openjdk:11

ENV TZ="Europe/Belgrade"
COPY target/virtualPowerPlant-0.0.1-SNAPSHOT.jar virtualPowerPlant.jar
ENTRYPOINT ["java","-jar","virtualPowerPlant.jar"]
