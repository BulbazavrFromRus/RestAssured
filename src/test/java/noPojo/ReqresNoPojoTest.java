package noPojo;

import api.Specification;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.devtools.v85.fetch.model.AuthChallengeResponse;
import org.openqa.selenium.json.Json;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

public class ReqresNoPojoTest {

    private final static String URL = "https://reqres.in/";

    @Test
    public void checkAvatarsNoPojoTest() {
        Specification.installSpecification(Specification.requestSpecification(URL), Specification.responseSpecUnique(200));

        Response response = given()
                .when()
                .get("api/users?page=2")
                .then().log().all()
                .body("page",equalTo(2))
                .body("data.id", notNullValue())
                .body("data.email", notNullValue())
                .body("data.first_name", notNullValue())
                .body("data.last_name", notNullValue())
                .body("data.avatar", notNullValue())
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        List<String> emails = jsonPath.get("data.email");
        List<Integer> ids = jsonPath.get("data.id");
        List<String> avatars = jsonPath.get("data.avatar");

        for(int i = 0; i< avatars.size(); ++i) {
            Assert.assertTrue(avatars.get(i).contains(ids.get(i).toString()));
        }

        //allMatch() - this method which checks all elements
        //if one of the elements will not end with "@reqres.in", this methid throw error
        Assert.assertTrue(emails.stream().allMatch(x->x.endsWith("@reqres.in")));
    }

    @Test
    public void successUserRegNoPojoTest() {
        Specification.installSpecification(Specification.requestSpecification(URL), Specification.responseSpecUnique(200));

        //map will include email and password
        Map<String, String> user  = new HashMap<>();
        user.put("email", "eve.holt@reqres.in");
        user.put("password", "pistol");

        Response response = given()
                .body(user)
                .when()
                .post("api/register")
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        int id = jsonPath.get("id");
        String token = jsonPath.get("token");
        Assert.assertEquals(4, id);
        Assert.assertEquals("QpwL5tke4Pnpja7X4", token);
    }

    @Test
    public void unSuccessfulUserRegNoPojoTest() {
        Specification.installSpecification(Specification.requestSpecification(URL), Specification.responseSpecUnique(400));

        Map<String, String> user =new  HashMap<>();

        user.put("email", "sydney@fife");

        Response response = given()
                .body(user)
                .when()
                .post("api/register")
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        String error = jsonPath.get("error");

        Assert.assertEquals("Missing password", error);

    }
}
