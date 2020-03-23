package fr.arindam.k8s;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class MainOperatorResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/check")
          .then()
             .statusCode(200)
             .body(is("hello"));
    }

}