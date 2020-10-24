# Auction House project

This project uses Quarkus, the Supersonic Subatomic Java Framework.


## Extensions

You can add extensions (i.e OpenAPI, Hibernate Validator) via the Gradle wrapper using:

```shell script
# Add OpenAPI, Swagger UI
./gradlew addExtension --extensions="quarkus-smallrye-openapi"

# Add Hibernate Validator, PostgreSQL JDBC 
./gradlew addExtension --extensions="hibernate-validator, quarkus-jdbc-postgresql"
```

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```shell script
./gradlew quarkusDev
```

### OpenAPI

You can inspect the static content of the generated schema at the default /openapi endpoint:

```shell script
curl http://localhost:8080/openapi
```

## Database 

Login as a Postgres user then:

```postgresql
CREATE DATABASE auction;
CREATE USER auction WITH PASSWORD 'password';
GRANT ALL PRIVILEGES ON DATABASE "auction" to auction;
```

## Packaging and running the application

The application can be packaged using:

```shell script
./gradlew quarkusBuild
```

It produces the `auction-house-1.0-SNAPSHOT-runner.jar` file in the `/build` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `build/lib` directory.

If you want to build an _über-jar_, execute the following command:

```shell script
./gradlew quarkusBuild --uber-jar
```

The application is now runnable using `java -jar build/auction-house-1.0-SNAPSHOT-runner.jar`.

## Creating a native executable

You can create a native executable using: 

```shell script
./gradlew build -Dquarkus.package.type=native
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: 

```shell script
./gradlew build -Dquarkus.package.type=native -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./build/auction-house-1.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/gradle-tooling.

# RESTEasy JAX-RS

Guide: https://quarkus.io/guides/rest-json
