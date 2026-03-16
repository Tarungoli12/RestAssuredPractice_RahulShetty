package com.automation.Tests;

import com.automation.ReusableMethods.RawToJSON;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class OAuth2 {

    @Test
    public void OAuth2AuthorizationTest() {
        RestAssured.baseURI = "https://rahulshettyacademy.com/";
        String AuthorizationServerResponse = given()
                .formParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
                .formParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
                .formParam("grant_type", "client_credentials")
                .formParam("scope", "trust")
                .when().post("oauthapi/oauth2/resourceOwner/token")
                .then().log().all().assertThat().statusCode(200)
                .extract().response().asString();
        JsonPath js = RawToJSON.getRawDataToJSON(AuthorizationServerResponse);
        String access_token = js.getString("access_token");
        given().queryParam("access_token", access_token)
                .when().get("oauthapi/getCourseDetails")
                .then().log().all();
    }
}
