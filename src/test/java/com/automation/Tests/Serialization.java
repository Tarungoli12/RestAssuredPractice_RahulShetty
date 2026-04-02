package com.automation.Tests;

import com.automation.Pojo.Serialization.GetLocation;
import com.automation.Pojo.Serialization.Location;
import io.restassured.RestAssured;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Serialization {

    @Test
    public static void MapTest() {
        GetLocation location = new GetLocation();
        Location location1 = new Location();

        location1.setLat("-38.383494");
        location1.setLng("33.427362");

        location.setLocation(location1);
        location.setAccuracy("50");
        location.setName("Tarun");
        location.setPhone_number("8325628329");
        location.setAddress("1st street, Andhra");

        location.setTypes(List.of("Laptop", "Bike", "Car"));

        location.setWebsite("https://rahulshettyacademy.com");
        location.setLanguage("Telugu");

        RestAssured.baseURI = "https://rahulshettyacademy.com";
        given().log().all().queryParam("key", "qaclick123")
                .header("Content-Type", "application/json")
                .body(location).when().post("maps/api/place/add/json")
                .then().log().all().assertThat().statusCode(200)
                .body("scope", equalTo("APP")).header("Server", "Apache/2.4.52 (Ubuntu)");
    }
}
