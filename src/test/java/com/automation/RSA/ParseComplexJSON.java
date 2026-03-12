package com.automation.RSA;

import com.automation.Data.JsonData;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

public class ParseComplexJSON {
    public static void main(String[] args) {

        //we didn't have the api, so that's why we took sample response
        // in "JsonData.getCoursesJsonData()" to perform for complex json parsing
        JsonPath jsonPath = new JsonPath(JsonData.getCoursesJsonData());

        //print no of courses
        int courseSize = jsonPath.getInt("courses.size()");
        System.out.println("no of Courses : " + courseSize);

        //print purchase amount
        int purchaseAmount = jsonPath.getInt("dashboard.purchaseAmount");
        System.out.println("purchase amount : " + purchaseAmount);

        //print the first course title
        String firstCourseTitle = jsonPath.get("courses[0].title");
        System.out.println(firstCourseTitle);

        //print the course title and prices of all courses
        for(int i = 0; i< courseSize; i++){
            System.out.println(jsonPath.get("courses["+i+"].title").toString()
              + jsonPath.get("courses["+i+"].price").toString());
        }

        //print the no of copies sold for a RPA course
        for(int i = 0; i< courseSize; i++){
            String courseName = jsonPath.get("courses["+i+"].title");
            if(courseName.equalsIgnoreCase("RPA")){
                System.out.println(jsonPath.get("courses["+i+"].copies").toString());
                break;
            }
        }

        //verify if sum of all course prices matches with purchase amount
        int totalAmount = 0;
        for(int i = 0; i< courseSize; i++){
            int price = jsonPath.getInt("courses["+i+"].price");
            int copies = jsonPath.getInt("courses["+i+"].copies");
            totalAmount += price*copies;
        }
        System.out.println(totalAmount);
        Assert.assertEquals(purchaseAmount,totalAmount);
    }
}
