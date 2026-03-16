package com.automation.Tests;

import com.automation.ReusableMethods.RawToJSON;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import static io.restassured.RestAssured.*;

public class StaticJSONPayloadUsingPlaceAPI {

    @Test
    public void addPlaceUsingStaticJson() throws IOException {
        RestAssured.baseURI="https://rahulshettyacademy.com";
        String addBookResponse = given().log().all()
                .queryParam("key", "qaclick123")
                .header("Content-Type","application/json")
                .body(new String(Files.readAllBytes(Paths.get("API//staticData.json"))))
                .when().post("maps/api/place/add/json")
                .then().assertThat().log().all().statusCode(200)
                .extract().response().asString();
        JsonPath jsonPath = RawToJSON.getRawDataToJSON(addBookResponse);
        String id = jsonPath.getString("place_id");
        System.out.println(id);
    }
}
