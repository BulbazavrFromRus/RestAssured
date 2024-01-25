package reqres_own_training;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

public class TestClass {
    private final static String URL = "https://reqres.in/";


    @Test
    public void getAllUsers() {
        Specification.initSpecification(Specification.responseSpecification(200), Specification.requestSpecification(URL));

        List<User> userList = given()
                .when()
                .get("/api/users?page=2")
                .then().log().all()
                .extract().body().jsonPath().getList("data", User.class);

        userList.stream().forEach(x->Assert.assertTrue(x.getAvatar().contains(x.getId().toString())));

       // userList.stream().forEach(x->Assert.assertTrue(x.getEmail().endsWith("@reqres.in")));
       Assert.assertTrue(userList.stream().allMatch(x->x.getEmail().endsWith("@reqres.in")));


       //Try to pull all avatars from List userList
        List<String> avatars = userList.stream().map(User::getAvatar).collect(Collectors.toList());
        for(String a:avatars){
            System.out.println(a);
        }
    }

    @Test
    public void successRegister() {
        Specification.initSpecification(Specification.responseSpecification(200), Specification.requestSpecification(URL));

        Integer id  = 4;
        String token = "QpwL5tke4Pnpja7X4";

        UserRegistrationData userRegistrationData = new UserRegistrationData("eve.holt@reqres.in", "pistol");

        SuccessReg successReg = given()
                .body(userRegistrationData)
                .when()
                .post("api/register")
                .then().log().all()
                .extract().as(SuccessReg.class);


        Assert.assertEquals(id, successReg.getId());
        Assert.assertEquals(token, successReg.getToken());
    }

      @Test
      public void sortedYearsTest(){
        Specification.initSpecification(Specification.responseSpecification(200), Specification.requestSpecification(URL));

        List<ColorsData> userList = given()
                .when()
                .get("api/unknown")
                .then().log().all()

                //we  extract data of our body to json to list
                //"data" means - where are we from extract our datas
                .extract().body().jsonPath().getList("data", ColorsData.class);


        List<Integer> yearsList = userList.stream().map(ColorsData::getYear).collect(Collectors.toList());

        List<Integer> sortedYears = yearsList.stream().sorted().collect(Collectors.toList());

        Assert.assertEquals(sortedYears, yearsList);

          System.out.println(yearsList);
          System.out.println(sortedYears);
      }

      @Test
    public void deleteRequest() {
          Specification.initSpecification(Specification.responseSpecification(204), Specification.requestSpecification(URL));

          given()
                  .when()
                  .delete("api/users/2")
                  .then().log().all();
      }


      //In this case i just got dates about one person
      @Test
    public void testForGetInfoAboutSingleUser() {
        Specification.initSpecification(Specification.responseSpecification(200), Specification.requestSpecification(URL));

        UserSupport userSupport = given()
                .when()
                .get("api/users/2")
                .then().log().all()
                .extract().body().jsonPath().getObject("support", UserSupport.class);

        User user = given()
                .when()
                .get("api/users/2")
                        .then().log().all()
                        .extract().body().jsonPath().getObject("data", User.class);



          System.out.println("Test:  " + userSupport.getText());
          System.out.println("Url:   "+userSupport.getUrl());
          System.out.println("*******************************");

          System.out.println("Email: "+ user.getEmail());
      }


      //either one user wasn't found
      @Test
    public void singleUserNotFound() {
        Specification.initSpecification(Specification.responseSpecification(404), Specification.requestSpecification(URL));

          given()
                  .when()
                  .get("/api/users/23")
                  .then();
      }
}
