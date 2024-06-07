FROM gradle:8.5-jdk17

WORKDIR /opt/app

COPY ./build/libs/backend-0.0.1-SNAPSHOT.jar ./

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "backend-0.0.1-SNAPSHOT.jar"]