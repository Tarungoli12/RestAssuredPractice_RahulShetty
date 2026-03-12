package com.automation.RSA;

import com.automation.Data.JsonData;
import com.automation.ReusableMethods.RawToJSON;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DynamicJSONPayloadUsingLibraryAPI {

    @Test(dataProvider = "getBooksData")
    public void addBook(String isbn,String aisle){
        RestAssured.baseURI="http://216.10.245.166";
        String addBookResponse = given().header("Content-Type","application/json")
                .body(JsonData.addBookJsonData(isbn,aisle))
                .when().post("Library/Addbook.php")
                .then().assertThat().log().all().statusCode(200)
                .extract().response().asString();
        JsonPath jsonPath = RawToJSON.getRawDataToJSON(addBookResponse);
        String id = jsonPath.getString("ID");
        System.out.println(id);
    }

    @DataProvider(name = "getBooksData")
    public Object[][] getData(){
        return new Object[][]{{"jdhu","3835"},{"dee","4553"},{"dekj","9832"}};
    }
}
