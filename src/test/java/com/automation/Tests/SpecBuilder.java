package com.automation.Tests;

import com.automation.Pojo.Serialization.GetLocation;
import com.automation.Pojo.Serialization.Location;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class SpecBuilder {

    @Test
    public static void RequestAndResponseSpecBuilderTest() {
        GetLocation location = new GetLocation();
        Location location1 = new Location();
        location1.setLat("-38.383494");
        location1.setLng("33.427362");

        location.setLocation(location1);
        location.setAccuracy("50");
        location.setName("Siva");
        location.setPhone_number("837662388");
        location.setAddress("1st street, Kerala");

        location.setTypes(List.of("Mouse", "Keyboard", "Wire"));

        location.setWebsite("https://google.com");
        location.setLanguage("Tamil");

        RequestSpecification requestSpecification = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
                .addQueryParam("key", "qaclick123")
                .setContentType(ContentType.JSON).build();

        ResponseSpecification responseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON).build();

        Response response = given().spec(requestSpecification).body(location)
                .when().post("maps/api/place/add/json")
                .then().spec(responseSpecification).extract().response();

        System.out.println(response.asString());
    }
}
