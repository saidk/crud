## Prerequisites for development
- [Docker](https://www.docker.com/)
- [Java 11](https://jdk.java.net/11/)
 
## Running locally
- Start DB in Docker `docker-compose up -d` or opening it in Intellij IDEA and click arrow buttons
- Run Spring Boot app, via IntelliJ IDEA by running CoreApplication.kt main or via Gradle `bootRun` task, or in terminal type `./gradlew bootRun`
- Start frontend by installing dependencies using `yarn` command and after running `yarn start` command in `src/main/webapp` path

Frontend opens at `localhost:3000` and backend opens at `localhost:8080`

## Code verification

### Tests
- Run backend tests using Intellij
