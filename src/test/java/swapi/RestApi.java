package swapi;

import io.restassured.http.ContentType;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class RestApi {

    @Test
    public void getUrl(){

        given()
                .baseUri("https://swapi.dev/api/people/")
                .basePath("/1/")
                .contentType(ContentType.JSON)
                .when()
                .get()
                .then()
                .statusCode(200)
                .body("birth_year", equalTo("19BBY"));
    }

    //try to get some fields from request
    @Test
    public void test1(){

      ParamHuman paramHuman = given()
                .contentType(ContentType.JSON)
                .when()
                .get("https://swapi.dev/api/people/1/")
                .then().log().all()
                .extract().body().jsonPath().getObject("data", ParamHuman.class);

      System.out.println(paramHuman.toString());
    }
}
