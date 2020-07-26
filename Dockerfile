FROM maven:3-jdk-11

ADD . /javaProject
WORKDIR /javaProject

RUN mvn clean install -DskipTests

FROM openjdk:11-jdk
ARG JAR_FILE=/javaProject/target/*.jar
COPY --from=0 ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","/app.jar"]