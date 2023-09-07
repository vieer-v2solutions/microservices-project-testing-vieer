# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-alpine

# Set the working directory to /app
WORKDIR /app

# Copy the executable jar file from the target directory to the container's /app directory
COPY target/health-check-demo-0.0.1-SNAPSHOT.jar app.jar

# Expose port 8080
EXPOSE 8080

# Run the jar file when the container starts
CMD ["java", "-jar", "app.jar"]