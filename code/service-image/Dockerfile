#
# Build stage
#
FROM maven:3.6.0-jdk-11-slim AS build
COPY /libraries/ /home/app/libraries
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

#
# Package stage
#
FROM openjdk:11-jre-slim
COPY --from=build /home/app/target/*.jar /usr/local/lib/app.jar
RUN mkdir -p /usr/local/lib/src/main/resources/static/profile
EXPOSE 9004
ENTRYPOINT ["java","-jar","/usr/local/lib/app.jar"]
