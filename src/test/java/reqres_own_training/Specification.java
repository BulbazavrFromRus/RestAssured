package reqres_own_training;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Specification {


   public static RequestSpecification requestSpecification(String url) {
       return new RequestSpecBuilder()
               .setBaseUri(url)
               .setContentType(ContentType.JSON)
               .build();
   }

   public static ResponseSpecification responseSpecification(int statusCode){
      return new ResponseSpecBuilder()
               .expectStatusCode(statusCode)
               .build();
   }

   public static void initSpecification(ResponseSpecification response, RequestSpecification request){
       RestAssured.requestSpecification = request;
       RestAssured.responseSpecification = response;
   }
}
