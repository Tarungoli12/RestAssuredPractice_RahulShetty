package com.automation.ReusableMethods;

import io.restassured.path.json.JsonPath;

public class RawToJSON {
    public static JsonPath getRawDataToJSON(String response){
        return new JsonPath(response);
    }
}
