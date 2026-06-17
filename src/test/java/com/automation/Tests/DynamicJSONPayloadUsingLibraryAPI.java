package com.automation.Tests;

import com.automation.Data.JsonData;
import com.automation.ReusableMethods.RawToJSON;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class DynamicJSONPayloadUsingLibraryAPI {

    @Test(dataProvider = "getBooksData")
    public void addBookUsingDynamicJson(String isbn, String aisle) {
        RestAssured.baseURI = "http://216.10.245.166";
        String addBookResponse = given().header("Content-Type", "application/json")
                .body(JsonData.addBookJsonData(isbn, aisle))
                .when().post("Library/Addbook.php")
                .then().assertThat().log().all().statusCode(200)
                .header("Content-Type", "application/json;charset=UTF-8")
                .body("Msg", equalTo("Book Already Exists"))
                .extract().response().asString();
        JsonPath jsonPath = RawToJSON.getRawDataToJSON(addBookResponse);
        String id = jsonPath.getString("ID");
        System.out.println(id);
    }

    @DataProvider(name = "getBooksData")
    public Object[][] getData() {
        return new Object[][]{{"hu", "3835"}, {"de", "4553"}, {"dej", "9832"}};
    }
}
