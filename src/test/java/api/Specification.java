package api;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.runner.Request;

public class Specification {

    //create static method where we point our specification for request and respond


    //setBaseUri() - in this method we mention which link will be main
    public static RequestSpecification requestSpecification(String url) {
        return new RequestSpecBuilder()
                .setBaseUri(url)
                .setContentType(ContentType.JSON)
                .build();
    }

    //we need this method to show code status 200 we will see
    public static ResponseSpecification responseSpecification200() {
          return new ResponseSpecBuilder()
                  .expectStatusCode(200)
                  .build();
    }

    public static ResponseSpecification responseSpecification400() {
        return new ResponseSpecBuilder()
                .expectStatusCode(400)
                .build();
    }


    public static void installSpecification(RequestSpecification request, ResponseSpecification response) {
        RestAssured.responseSpecification = response;
        RestAssured.requestSpecification = request;

    }


}
