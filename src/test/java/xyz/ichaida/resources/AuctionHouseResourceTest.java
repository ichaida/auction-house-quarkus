package xyz.ichaida.resources;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author Ismail Chaida.
 */
@QuarkusTest
@DisplayName(value = "An AuctionHouse Resource Test Class")
@TestHTTPEndpoint(AuctionHouseResource.class)
class AuctionHouseResourceTest {

    @Test
    @DisplayName("Test All AuctionHouse Endpoint")
    void testListAllAuctionHouses() {
        given().contentType(ContentType.JSON)
            .when().get("/all")
            .then()
            .statusCode(200)
            .body(
                containsString("UKAH"),
                containsString("Chorus"),
                containsString("Mia")
            );
    }
}
