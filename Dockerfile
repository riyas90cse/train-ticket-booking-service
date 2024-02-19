# Stage 1: Build the application
#FROM openjdk:17-alpine AS build

FROM gradle:8-jdk21-alpine AS build

WORKDIR /app

COPY build.gradle settings.gradle /app/
COPY gradlew /app/
RUN chmod +x /app/gradlew

COPY . /app/

# Build the application
RUN /app/gradlew --no-daemon clean build -x test

# Stage 2: Create the final image
#FROM openjdk:17-alpine
FROM gradle:8-jdk21-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the built JAR file from the build stage
COPY --from=build /app/build/libs/train-ticket-booking-service-0.0.1.jar /app/app.jar

# Expose the port that your application will run on
EXPOSE 8090

# Specify the command to run your application
CMD ["java", "-jar", "app.jar"]