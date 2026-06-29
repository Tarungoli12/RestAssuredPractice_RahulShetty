package com.automation.Data;

import java.util.HashMap;
import java.util.Map;

public class JsonData {

    public static String addPlaceJsonData() {
        return "{\n" +
                "  \"location\": {\n" +
                "    \"lat\": -38.383494,\n" +
                "    \"lng\": 33.427362\n" +
                "  },\n" +
                "  \"accuracy\": 50,\n" +
                "  \"name\": \"Tarun Hero\",\n" +
                "  \"phone_number\": \"(+91) 983 893 3937\",\n" +
                "  \"address\": \"29, side layout, cohen 09\",\n" +
                "  \"types\": [\n" +
                "    \"shoe park\",\n" +
                "    \"shop\"\n" +
                "  ],\n" +
                "  \"website\": \"https://rahulshettyacademy.com\",\n" +
                "  \"language\": \"French-IN\"\n" +
                "}";
    }

    public static String getCoursesJsonData() {
        return "{\n" +
                "  \"dashboard\": {\n" +
                "    \"purchaseAmount\": 910,\n" +
                "    \"website\": \"rahulshettyacademy.com\"\n" +
                "  },\n" +
                "  \"courses\": [\n" +
                "    {\n" +
                "      \"title\": \"Selenium Python\",\n" +
                "      \"price\": 50,\n" +
                "      \"copies\": 6\n" +
                "    },\n" +
                "    {\n" +
                "      \"title\": \"Cypress\",\n" +
                "      \"price\": 40,\n" +
                "      \"copies\": 4\n" +
                "    },\n" +
                "    {\n" +
                "      \"title\": \"RPA\",\n" +
                "      \"price\": 45,\n" +
                "      \"copies\": 10\n" +
                "    }\n" +
                "  ]\n" +
                "}";
    }

    public static String addBookJsonData(String isbn, String aisle) {
        return "{\n" +
                "  \"name\": \"Learn Appium Automation with Java\",\n" +
                "  \"isbn\": \"" + isbn + "\",\n" +
                "  \"aisle\": \"" + aisle + "\",\n" +
                "  \"author\": \"John foe\"\n" +
                "}";
    }

    public static String getCourseJsonData() {
        return "{\n" +
                "    \"instructor\": \"RahulShetty\",\n" +
                "    \"url\": \"rahulshettycademy.com\",\n" +
                "    \"services\": \"projectSupport\",\n" +
                "    \"expertise\": \"Automation\",\n" +
                "    \"courses\": {\n" +
                "        \"webAutomation\": [\n" +
                "            {\n" +
                "                \"courseTitle\": \"Selenium Webdriver Java\",\n" +
                "                \"price\": \"50\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"courseTitle\": \"Cypress\",\n" +
                "                \"price\": \"40\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"courseTitle\": \"Protractor\",\n" +
                "                \"price\": \"40\"\n" +
                "            }\n" +
                "        ],\n" +
                "        \"api\": [\n" +
                "            {\n" +
                "                \"courseTitle\": \"Rest Assured Automation using Java\",\n" +
                "                \"price\": \"50\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"courseTitle\": \"SoapUI Webservices testing\",\n" +
                "                \"price\": \"40\"\n" +
                "            }\n" +
                "        ],\n" +
                "        \"mobile\": [\n" +
                "            {\n" +
                "                \"courseTitle\": \"Appium-Mobile Automation using Java\",\n" +
                "                \"price\": \"50\"\n" +
                "            }\n" +
                "        ]\n" +
                "    },\n" +
                "    \"linkedIn\": \"https://www.linkedin.com/in/rahul-shetty-trainer/\"\n" +
                "}";
    }

    public static String addMutationJsonData(String locationName, String characterName, String episodeName) {
        return "{\"query\":\"mutation($locationName:String!,$characterName:String!,$episodeName:String!)\\n{\\n  createLocation(location:{name:$locationName,type:\\\"SouthZone\\\",dimension:\\\"111\\\"})\\n  {\\n    id\\n  }\\n  createCharacter(character:{name:$characterName,type:\\\"Positive\\\",status:\\\"Alive\\\",species:\\\"SuperHuman\\\",gender:\\\"Male\\\",image:\\\"Hero.img\\\",originId:33362,locationId:33362}){\\n    id\\n  }\\n  createEpisode(episode:{name:$episodeName,air_date:\\\"June 2026\\\",episode:\\\"01\\\"}){\\n    id\\n  }\\n}\",\"variables\":{\"locationName\":\"" + locationName + "\",\"characterName\":\"" + characterName + "\",\"episodeName\":\"" + episodeName + "\"}}";
    }

    public static String getQueryJsonData(int locationId, int characterId, int episodeId, String characterType) {
        return "{\"query\":\"query($locationId:Int!,$characterId:Int!,$episodeId:Int!,$charcterType:String!)\\n{\\n  location(locationId: $locationId)\\n  {\\n    name\\n    dimension\\n  }\\n  character(characterId:$characterId){\\n    id\\n    name\\n    type\\n    status\\n    species\\n    gender\\n    image\\n    location\\n    {\\n      id\\n      name\\n      type\\n      dimension\\n    }\\n  }\\n  episode(episodeId:$episodeId){\\n    id\\n    name\\n    air_date\\n    episode\\n    characters{\\n      id\\n      name\\n      type\\n      status\\n      species\\n      gender\\n      image\\n    }\\n    created\\n  }\\n  \\n  characters(filters:{type:$charcterType}){\\n    info{\\n      count\\n      pages\\n      next\\n      prev\\n    }\\n    result{\\n      id\\n      name\\n      type\\n      status\\n      species\\n      gender\\n      image\\n    }\\n  }\\n}\\n\",\"variables\":{\"locationId\":" + locationId + ",\"characterId\":" + characterId + ",\"episodeId\":" + episodeId + ",\"charcterType\":\"" + characterType + "\"}}";
    }

    public static Map<String, Object> getDataUsingHashMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("name","Learn Appium Automation with Java");
        map.put("isbn","API");
        map.put("aisle","1111");
        map.put("author","Chirag Khimani");
        return map;
    }
}
