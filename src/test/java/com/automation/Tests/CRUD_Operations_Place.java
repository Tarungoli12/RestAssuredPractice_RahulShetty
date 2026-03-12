package com.automation.Tests;

import com.automation.Data.JsonData;
import com.automation.ReusableMethods.RawToJSON;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CRUD_Operations_Place {
    public static void main(String[] args) {
        RestAssured.baseURI = "https://rahulshettyacademy.com";
        String addPlaceResponse = given().log().all().queryParam("key", "qaclick123")
                .header("Content-Type", "application/json")
                .body(JsonData.addPlaceJsonData())
                .when().post("maps/api/place/add/json")
                .then().log().all().assertThat().statusCode(200)
                .body("scope", equalTo("APP"))
                .header("Server", "Apache/2.4.52 (Ubuntu)")
                .extract().response().asString();

        JsonPath js = RawToJSON.getRawDataToJSON(addPlaceResponse);
        String placeId = js.getString("place_id");
        System.out.println(placeId);

        String newAddress = "Kerala";

        given().queryParam("key", "qaclick123")
                .header("Content-Type", "application/json")
                .body("{\n" +
                        "\"place_id\":\"" + placeId + "\",\n" +
                        "\"address\":\"" + newAddress + "\",\n" +
                        "\"key\":\"qaclick123\"\n" +
                        "}")
                .when().put("maps/api/place/update/json")
                .then().log().all().assertThat()
                .body("msg", equalTo("Address successfully updated"));

        String getPlaceResponse = given().queryParam("key", "qaclick123")
                .queryParam("place_id", placeId)
                .when().get("maps/api/place/get/json")
                .then().log().all().assertThat().statusCode(200)
                .extract().response().asString();

        JsonPath js1 = RawToJSON.getRawDataToJSON(getPlaceResponse);
        String actualAddress = js1.getString("address");
        Assert.assertEquals(actualAddress, newAddress);

        given().queryParam("key", "qaclick123")
                .header("Content-Type", "application/json")
                .body("{\n" +
                        "    \"place_id\":\""+placeId+"\"\n" +
                        "}")
                .when().delete("maps/api/place/delete/json")
                .then().log().all().assertThat().
                statusCode(200).body("status",equalTo("OK"));
    }
}
