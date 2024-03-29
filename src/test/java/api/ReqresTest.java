package api;

import io.restassured.http.ContentType;
import org.junit.Assert;
import org.junit.Test;
import org.seleniumhq.jetty9.server.Authentication;

import java.time.Clock;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

public class ReqresTest {

    private final static String URL = "https://reqres.in/";

    @Test
    public void checkAvatarAndIdTest() {
     Specification.installSpecification(Specification.requestSpecification(URL), Specification.responseSpecification200());
       //given() - method from Rest Assured

       //when() - we write this method to mention all following methods
       //all requests starts from this method

       //contentType(ContentType.JSON) - here we indicate, which format will be have our request
       //in our case this is JSON format that why we indicate JSON

       //get() - in this method we indicate link(url) to this request
       //btw, instead get request there are post/delete/read..

       //then() - this method closes all types which we use(after when())

       //log().all() - after method then() we have to write what we want to display

       //extract() - in order to extract respond from server we have to extract it

       //body() - because of we extract body we indicate body() method

       //jsonPath() - this is just json path

       //getList("data", UserData.class) - because of we want to get list we write getList()
       //in the getList() method we indicate path - "data" it means row in JSON must to start from "data"
        //and second parameter is class, it means to which class we extract our data from json row
       List<UserData> userDataList = given()
               .when()
               //.contentType(ContentType.JSON)
               .get("api/users?page=2")
               .then().log().all()
               .extract().body().jsonPath().getList("data", UserData.class);



    //x - this is just element (счётчик экземпляров класса)


    userDataList.stream().forEach(x-> Assert.assertTrue(x.getAvatar().contains(x.getId().toString())));

    //if true then test pass successufully

    //allMatch() - it means that we will address to all elements in this list
    Assert.assertTrue(userDataList.stream().allMatch(x->x.getEmail().endsWith("@reqres.in")));



    //map() - we want to take out only one element that why we wrote map()
    //map(UserData::getAvatar) - it means we want to  get only avatars

    //collect(Collectors.toList()) - using collect method we can collect everything and push all to one list
    List<String> avatars = userDataList.stream().map(UserData::getAvatar).collect(Collectors.toList());

    //List<String> ids = userDataList.stream().map(UserData::getId).collect(Collectors.toList());
        //initially we write like in the example above where map(UserData::getId)
        //but we can't do this, because method map can take only string

   //that why we write this way: map(x->x.getId().toString())
   //we created x - lambda, x - this is just element from list
   //than we get method getId() and call toString() method
        List<String> ids = userDataList.stream().map(x->x.getId().toString()).collect(Collectors.toList());
        for(int i =0; i<avatars.size(); ++i) {

           Assert.assertTrue(avatars.get(i).contains(ids.get(i)));

        }


    }

    @Test
    public void successfulRegTest() {
       Specification.installSpecification(Specification.requestSpecification(URL), Specification.responseSpecification200());
       Integer id = 4;
       String token = "QpwL5tke4Pnpja7X4";

       Registr user = new Registr("eve.holt@reqres.in", "pistol");
       SuccessReg successReg = given()
               .body(user)
               .when()
               .post("api/register")
               .then().log().all()
               .extract().as(SuccessReg.class);

       Assert.assertNotNull(successReg.getId());
       Assert.assertNotNull(successReg.getToken());


       Assert.assertEquals(id, successReg.getId());
       Assert.assertEquals(token, successReg.getToken());

    }

    @Test
    public void unSuccessfulTest() {
        Specification.installSpecification(Specification.requestSpecification(URL), Specification.responseSpecification400());

        Registr user  = new Registr("sydney@fife", "");

        UnsuccessReg unsuccessReg = given()
                .when()
                .body(user)
                .post("api/register")
                .then().log().all()
                .extract().as(UnsuccessReg.class);

        Assert.assertEquals("Missing password", unsuccessReg.getError());
    }

    @Test
    public void sortedYearsTest() {
        Specification.installSpecification(Specification.requestSpecification(URL), Specification.responseSpecification200());

        //in get()  method we mention from what address we have to get data

        List<ColorsData> colorsDataList = given()
                .when()
                .get("api/unknown")
                .then().log().all()
                .extract().body().jsonPath().getList("data", ColorsData.class);

        List<Integer> years = colorsDataList.stream().map(ColorsData::getYear).collect(Collectors.toList());
        List<Integer> sortedYears = years.stream().sorted().collect(Collectors.toList());

        Assert.assertEquals(sortedYears, years);

        System.out.println(years);
        System.out.println(sortedYears);
    }

    @Test
    public void deleteUserTest() {
        Specification.installSpecification(Specification.requestSpecification(URL), Specification.responseSpecUnique(204));

        given()
                .when()
                .delete("/api/users/2")
                .then().log().all();
    }

    @Test
    public void timeTest() {
        Specification.installSpecification(Specification.requestSpecification(URL), Specification.responseSpecUnique(200));

        UserTime user = new UserTime("morpheus", "zion resident");
        UserTimeResponse response = given()
                .body(user)
                .when()
                .put("/api/users/2")
                .then().log().all()
                .extract().as(UserTimeResponse.class);

        String regex = "(.{5})$";
        String currentTime = Clock.systemUTC().instant().toString().replaceAll(regex,"");
        Assert.assertEquals(currentTime, response.getUpdatedAt().replaceAll(regex, ""));
    }


}
