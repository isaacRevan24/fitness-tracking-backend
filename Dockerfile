# Base docker image
FROM openjdk:17
EXPOSE 8080
LABEL maintainer="isaacRevan24"
ADD target/fitness-tracking-1.0.0.jar fitness-tracker.jar
ENTRYPOINT ["java", "-jar", "/fitness-tracker.jar"]