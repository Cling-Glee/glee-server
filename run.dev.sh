# Build the Spring Boot project using Gradle
./gradlew clean build

# Check if the build was successful
if [ $? -eq 0 ]; then
    # Build and run the Docker containers using docker-compose
    docker-compose -f docker-compose-dev.yml up --build
else
    echo "Build failed. Docker containers will not be started."
fi