package com.automation.Tests;

import com.automation.Pojo.Deserialization.GetCourses;
import com.automation.Pojo.Deserialization.WebAutomation;
import com.automation.ReusableMethods.RawToJSON;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class Deserialization {
    @Test
    public void PojoClassTest() {
        RestAssured.baseURI = "https://rahulshettyacademy.com/";
        String AuthorizationServerResponse = given()
                .formParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
                .formParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
                .formParam("grant_type", "client_credentials")
                .formParam("scope", "trust")
                .when().post("oauthapi/oauth2/resourceOwner/token")
                .then().assertThat().statusCode(200)
                .extract().response().asString();

        JsonPath js = RawToJSON.getRawDataToJSON(AuthorizationServerResponse);
        String access_token = js.getString("access_token");

        //Deserialization
        GetCourses response = given().queryParam("access_token", access_token)
                .when().get("oauthapi/getCourseDetails").as(GetCourses.class);

        //print the all course names in WebAutomation
        response.getCourses().getWebAutomation().forEach(s -> System.out.println(s.getCourseTitle()));

        //print the Instructor,LinkedIn,Expertise
        System.out.println(response.getInstructor() + " " + response.getLinkedIn() + " " + response.getExpertise());

        //print the soapUi course price
        response.getCourses().getApi().stream().filter(
                        s -> s.getCourseTitle().contains("SoapUI")).
                forEach(s -> System.out.println(s.getPrice()));

        // Assertion for expected course titles with actual titles using list
        List<String> actualList = response.getCourses()
                .getWebAutomation()
                .stream()
                .map(WebAutomation::getCourseTitle)
                .toList();
        List<String> expectedList = List.of("Selenium Webdriver Java", "Cypress", "Protractor");
        Assert.assertEquals(expectedList, actualList, "Course titles do not match!");
    }
}
