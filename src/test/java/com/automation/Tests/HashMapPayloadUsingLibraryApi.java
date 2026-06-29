package com.automation.Tests;

import com.automation.Data.JsonData;
import com.automation.ReusableMethods.RawToJSON;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class HashMapPayloadUsingLibraryApi {

    @Test
    public void AddBookTest() {
        RestAssured.baseURI = "http://216.10.245.166";
        String response = given().header("Content-Type", "application/json")
                .body(JsonData.getDataUsingHashMap())
                .when().post("Library/Addbook.php")
                .then().assertThat().statusCode(200).extract().asString();
        JsonPath rawDataToJSON = RawToJSON.getRawDataToJSON(response);
        String ID = rawDataToJSON.getString("ID");
        System.out.println(ID);
        System.out.println(rawDataToJSON.getString("Msg"));

        Map<String, Object> map = new HashMap<>();
        map.put("ID", ID);

        given().body(map).when().delete("Library/DeleteBook.php")
                .then().log().all()
                .assertThat().statusCode(200)
                .body("msg", equalTo("book is successfully deleted"));
    }
}
