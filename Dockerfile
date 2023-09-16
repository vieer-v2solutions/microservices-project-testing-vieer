# Use an official OpenJDK runtime as a parent image
FROM  openjdk:20-ea-4-jdk

# Set the working directory to /app
WORKDIR /app

# Copy the executable jar file from the target directory to the container's /app directory
COPY target/hotelMicroservice-0.0.1-SNAPSHOT.jar app.jar

# Expose port 8080
EXPOSE 8082

# Run the jar file when the container starts
CMD ["java", "-jar", "app.jar"]
