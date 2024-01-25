package reqres_own_training;

import io.restassured.http.ContentType;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class TestClass2 {
    private final static String URL = "https://reqres.in/";
    @Test
    public void getDataFromUser() {
        Specification.initSpecification(Specification.responseSpecification(200), api.Specification.requestSpecification(URL));

        String email = given()
                .when()
                .contentType(ContentType.JSON)
                .get("api/users?page=2")
                .then().extract().jsonPath().getJsonObject("data[0].email");


        System.out.println(email);

    }
}
