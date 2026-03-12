package com.automation.Tests;

import com.automation.ReusableMethods.RawToJSON;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import java.io.File;

import static io.restassured.RestAssured.given;

public class JiraBugCreationWithScreenshot {
    public static void main(String[] args) {
        RestAssured.baseURI="https://golisivasankartarun.atlassian.net/";
        String bugCreatedResponse = given().log().all().header("Content-Type","application/json")
                .header("Authorization","Basic Z29saXNpdmFzYW5rYXJ0YXJ1bjIyNUBnbWFpbC5jb206QVRBVFQzeEZmR0YwenNMODBCbWUtaTVUWmN4QmsyVjJ1OEotQ25jeW1Ebm8tcUR0ZGozekxHQkNMMXhLZE1wWG1DSWpubE9lSmVuTnJwZGExZmhuOFktQXZSY1JyUHJrYlRqX3RhaWgtT1c4bDFqT2lMRkZfZWgza0owbWRSQmV3aTRabFNyeWdWNHl5VTFTT2EzY0ZTaXZVYTVYUjNmVWpaYXFYNG0tMDNKOTdSaXByeGw3RllzPTdFNkQyRUJB")
                .body("{\n" +
                        "    \"fields\": {\n" +
                        "       \"project\":\n" +
                        "       {\n" +
                        "          \"key\": \"API\"\n" +
                        "       },\n" +
                        "       \"summary\": \"Links are not displaying\",\n" +
                        "       \"issuetype\": {\n" +
                        "          \"name\": \"Bug\"\n" +
                        "       }\n" +
                        "   }\n" +
                        "}\n")
                .when().post("rest/api/3/issue")
                .then().log().all().assertThat().statusCode(201).extract().response().asString();
        JsonPath path = RawToJSON.getRawDataToJSON(bugCreatedResponse);
        String id = path.getString("id");

        given().header("X-Atlassian-Token","no-check").pathParam("key",id)
                .header("Authorization","Basic Z29saXNpdmFzYW5rYXJ0YXJ1bjIyNUBnbWFpbC5jb206QVRBVFQzeEZmR0YwenNMODBCbWUtaTVUWmN4QmsyVjJ1OEotQ25jeW1Ebm8tcUR0ZGozekxHQkNMMXhLZE1wWG1DSWpubE9lSmVuTnJwZGExZmhuOFktQXZSY1JyUHJrYlRqX3RhaWgtT1c4bDFqT2lMRkZfZWgza0owbWRSQmV3aTRabFNyeWdWNHl5VTFTT2EzY0ZTaXZVYTVYUjNmVWpaYXFYNG0tMDNKOTdSaXByeGw3RllzPTdFNkQyRUJB")
                .multiPart("file",new File("API//Screenshot (1).png"))
                .when().post("rest/api/3/issue/{key}/attachments")
                .then().log().all().assertThat().statusCode(200);

        given().pathParam("key",id)
                .header("Authorization","Basic Z29saXNpdmFzYW5rYXJ0YXJ1bjIyNUBnbWFpbC5jb206QVRBVFQzeEZmR0YwenNMODBCbWUtaTVUWmN4QmsyVjJ1OEotQ25jeW1Ebm8tcUR0ZGozekxHQkNMMXhLZE1wWG1DSWpubE9lSmVuTnJwZGExZmhuOFktQXZSY1JyUHJrYlRqX3RhaWgtT1c4bDFqT2lMRkZfZWgza0owbWRSQmV3aTRabFNyeWdWNHl5VTFTT2EzY0ZTaXZVYTVYUjNmVWpaYXFYNG0tMDNKOTdSaXByeGw3RllzPTdFNkQyRUJB")
                .when().get("rest/api/3/issue/{key}")
                .then().log().all().assertThat().statusCode(200);
    }
}
