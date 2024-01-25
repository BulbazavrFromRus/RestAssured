package new_method_test_rest_assured;

import io.restassured.http.ContentType;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class RestTest {

    @Test
    public void getUsers() {
        given()
                .baseUri("https://reqres.in/")
                .basePath("users")
                .contentType(ContentType.JSON)
                .when()
                .get()
                .then().statusCode(200);
    }

    @Test
    public void createUser() {

    }
}
