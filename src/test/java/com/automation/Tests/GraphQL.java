package com.automation.Tests;

import com.automation.Data.JsonData;
import com.automation.ReusableMethods.RawToJSON;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GraphQL {

    @Test
    public void graphQLTest() {
        //Mutation
        String locationName = "Australia";
        String characterName = "Superman";
        String episodeName = "Superman episode 1";

        String mutationResponse = given().log().all()
                .header("Content-Type", "application/json")
                .body(JsonData.addMutationJsonData(locationName, characterName, episodeName))
                .when().post("https://rahulshettyacademy.com/gq/graphql")
                .then().assertThat().statusCode(200).extract().response().asString();
        JsonPath mutation = RawToJSON.getRawDataToJSON(mutationResponse);

        int locationId = mutation.getInt("data.createLocation.id");
        int characterId = mutation.getInt("data.createCharacter.id");
        int episodeId = mutation.getInt("data.createEpisode.id");
        String characterType = "Positive";

        //Query
        String queryResponse = given().log().all()
                .header("Content-Type", "application/json")
                .body(JsonData.getQueryJsonData(locationId, characterId, episodeId, characterType))
                .when().post("https://rahulshettyacademy.com/gq/graphql")
                .then().assertThat().statusCode(200).log().all().extract().response().asString();

        JsonPath query = RawToJSON.getRawDataToJSON(queryResponse);
        Assert.assertEquals(query.getString("data.character.name"), characterName);
        Assert.assertEquals(query.getString("data.episode.name"), episodeName);
    }
}
