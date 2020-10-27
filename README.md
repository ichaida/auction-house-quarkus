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

and Swagger UI

```
http://0.0.0.0:8080/swagger-ui/
```

> *PS:* If you're Swagger UI for feeding the data, please make sure to use the next format for the Dates `dd/MM/yyyy HH:mm:ss` (i.e '2/10/2020 10:10:00') 

## Use cases 

### Step 1 - Auction house

Create an auction house with a given name. 

```shell script
curl -X POST "http://0.0.0.0:8080/api/houses/add/name/new%20Auction%20House" -H  "accept: */*" -d ""
```

List all auction houses created.

```shell script
curl -X GET "http://0.0.0.0:8080/api/houses/all" -H  "accept: application/json"

# Output>
[
  {
    "id": 1,
    "name": "new Auction House"
  }
]
```


Delete a specific house.

```shell script
# Delete by Id
curl -X DELETE "http://0.0.0.0:8080/api/houses/delete/id/10" -H  "accept: */*"

# Or, delete by Name
curl -X DELETE "http://0.0.0.0:8080/api/houses/delete/name/new%20Auction%20House" -H  "accept: */*"
```

### Step 2 - Auction

Create an auction for a given auction house *(The Auction Body Id is not mandatory)*

```shell script
# Auction House Id = 2 
curl -X POST "http://0.0.0.0:8080/api/auctions/add/house/id/2" -H  "accept: application/json" -H  "Content-Type: application/json" -d "{\"id\":10,\"currentPrice\":200,\"endTime\":\"30/10/2020 08:15:00\",\"name\":\"iPad\",\"startTime\":\"25/10/2020 08:15:00\",\"startingPrice\":199,\"status\":\"NOT_STARTED\"}"
``` 

List all auctions

```shell script
curl -X GET "http://0.0.0.0:8080/api/auctions/all" -H  "accept: application/json"
```

List all auctions for a given auction house

```shell script
# Auctions of the House Id 1
curl -X GET "http://0.0.0.0:8080/api/auctions/find/house/id/1" -H  "accept: application/json"
```

```shell script
# Auctions of the House named UKAH
curl -X GET "http://0.0.0.0:8080/api/auctions/find/house/name/UKAH" -H  "accept: application/json"
```

List all Auctions by Status

```shell script
curl -X GET "http://0.0.0.0:8080/api/auctions/find/status/RUNNING" -H  "accept: application/json"
```

### Step 3 - Bidders

Create a new user

```shell script
curl -X POST "http://0.0.0.0:8080/api/users/add/user" -H  "accept: */*" -H  "Content-Type: application/json" -d "{\"id\":0,\"firstName\":\"john\",\"lastName\":\"doe\",\"username\":\"jdoe\"}"
```

List all bidders

```shell script
curl -X GET "http://0.0.0.0:8080/api/users/all" -H  "accept: application/json"
```

List all bidding (with the username) happening thus far

```shell script
# Username 'ichaida'
curl -X GET "http://0.0.0.0:8080/api/users/all/bids/ichaida" -H  "accept: application/json"
```



## Database 

### PostgreSQL (Local)

Login as a Postgres user, then:

```postgresql
CREATE DATABASE auction;
CREATE USER auction WITH PASSWORD 'password';
GRANT ALL PRIVILEGES ON DATABASE "auction" to auction;
```

### PostgreSQL (Docker)

Use the shell script under `postgres-10.sh` the `src/main/docker` to spin up your PostgreSQL instance ready for use.


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

## TODO

- Replace `input.sql` with Flyway migration
- PanacheRepository Mocks
- Add testing outside `RestAssured`
