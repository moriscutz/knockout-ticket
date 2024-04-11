FROM gradle:8.5-jdk17

WORKDIR /opt/app

COPY ./build/libs/backend-0.0.1-SNAPSHOT.jar ./

ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar backend-0.0.1-SNAPSHOT.jar"]