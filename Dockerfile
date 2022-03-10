# Base docker image
FROM openjdk:17
LABEL maintainer="isaacRevan24"
ADD target/fitness-tracking-1.0.0.jar fitness-tracker.jar
ENTRYPOINT ["java", "-jar", "fitness-tracker.jar"]
EXPOSE 8080