package com.automation.Tests;

import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;

public class OAuth2_GrantType_AuthorizationCode {
    public static void main(String[] args) {

//        WebDriver driver = new ChromeDriver();
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
//        driver.get("https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&Auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php&State=verifyAuthCodeRequest");
//
//        WebElement email = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[aria-label='Email or phone']")));
//        email.sendKeys("golisivasankartarun225@gmail.com");
//
//        driver.findElement(By.cssSelector("div[id=identifierNext] button")).click();
//
//        WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[aria-label='Enter your password']")));
//        password.sendKeys("itsmeTarun@6263");
//
//        driver.findElement(By.cssSelector("div[id=identifierNext] button")).click();
//
//        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("h1[id='headingText']")));
//
//        String url = driver.getCurrentUrl();

        String url = "https://rahulshettyacademy.com/getCourse.php?iss=https%3A%2F%2Faccounts.google.com&code=4%2F0AdkVLPxqVtJFSVhjVPKemsqBSShqCAp6s4SfdfS9rCL67nA8e0TbnVcf0mlKD8NMaJusRg&scope=email+openid+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email&authuser=0&prompt=none";

        String code = url.split("code=")[1].split("&")[0];

        String accessTokenResponse = given().urlEncodingEnabled(false).log().all().queryParam("code", code)
                .queryParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
                .queryParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
                .queryParam("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
                .queryParam("grant_type", "authorization_code")
                .when().post("https://www.googleapis.com/oauth2/v4/token")
                .then().log().all().assertThat().statusCode(200).extract().asString();

        JsonPath js = new JsonPath(accessTokenResponse);
        String access_token = js.getString("access_token");

        String courseDetails = given().log().all().queryParam("access_token", access_token)
                .when().get("https://rahulshettyacademy.com/getCourse.php")
                .then().log().all().assertThat().statusCode(200).extract().asString();
        System.out.println(courseDetails);
    }
}
