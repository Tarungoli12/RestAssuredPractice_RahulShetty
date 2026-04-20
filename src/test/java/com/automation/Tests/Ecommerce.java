package com.automation.Tests;

import com.automation.Pojo.Ecommerce.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class Ecommerce {

    @Test
    public void EcommerceTest() {

        //Login and get token and userId
        RequestSpecification loginRequestSpecification = new RequestSpecBuilder().
                setBaseUri("https://rahulshettyacademy.com/").
                setContentType(ContentType.JSON).build();

        RequestLogin requestLogin = new RequestLogin();
        requestLogin.setUserEmail("demo845@gmail.com");
        requestLogin.setUserPassword("Demo@845");

        RequestSpecification loginRequest = given().spec(loginRequestSpecification).body(requestLogin);
        ResponseLogin loginResponse = loginRequest.when().post("api/ecom/auth/login").
                then().extract().as(ResponseLogin.class);

        String token = loginResponse.getToken();
        String userId = loginResponse.getUserId();


        //add product and get productId
        RequestSpecification addProductRequestSpecification = new RequestSpecBuilder().
                setBaseUri("https://rahulshettyacademy.com/").
                addHeader("Authorization", token).build();

        RequestSpecification addProductRequest = given().spec(addProductRequestSpecification).
                formParam("productName", "lets shop").
                formParam("productAddedBy", userId).
                formParam("productCategory", "Photos").
                formParam("productSubCategory", "photo").
                formParam("productPrice", "50000").
                formParam("productDescription", "This is a photo").
                formParam("productFor", "All").
                multiPart("productImage", new File("C:\\Users\\280713\\OneDrive - UST\\Pictures\\Screenshots\\Screenshot 2026-04-08 165322.png"));

        ResponseAddProduct responseAddProduct = addProductRequest.when().post("api/ecom/product/add-product").
                then().log().all().extract().as(ResponseAddProduct.class);


        //place order and get orderId
        RequestSpecification placeOrderRequestSpecification = new RequestSpecBuilder().
                setBaseUri("https://rahulshettyacademy.com/").
                addHeader("Authorization", token).setContentType(ContentType.JSON).build();

        orders order = new orders();
        order.setCountry("India");
        order.setProductOrderedId(responseAddProduct.getProductId());

        List<orders> orderList = new ArrayList<>();
        orderList.add(order);

        RequestPlaceOrder placeOrder = new RequestPlaceOrder();
        placeOrder.setOrders(orderList);

        RequestSpecification placeOrderRequest = given().spec(placeOrderRequestSpecification).
                body(placeOrder);

        ResponsePlaceOrder responsePlaceOrder = placeOrderRequest.when().post("api/ecom/order/create-order").
                then().log().all().extract().as(ResponsePlaceOrder.class);

        String productId = responsePlaceOrder.getProductOrderId().get(0);
        String orderId = responsePlaceOrder.getOrders().get(0);

        //get order details
        RequestSpecification getOrderDetailsRequestSpecification = new RequestSpecBuilder().
                setBaseUri("https://rahulshettyacademy.com/").
                addHeader("Authorization", token).build();

        RequestSpecification getOrderRequest = given().spec(getOrderDetailsRequestSpecification).
                queryParam("id", orderId);

        ResponseGetOrderDetails responseGetOrderDetails = getOrderRequest.when().get("api/ecom/order/get-orders-details").
                then().log().all().extract().as(ResponseGetOrderDetails.class);
        System.out.println(responseGetOrderDetails.getData().getProductName());


        //delete product
        RequestSpecification deleteProductRequestSpecification = new RequestSpecBuilder().
                setBaseUri("https://rahulshettyacademy.com/").
                addHeader("Authorization", token).build();

        String deleteProductResponse = given().spec(deleteProductRequestSpecification).
                pathParam("productId", productId).
                delete("https://rahulshettyacademy.com/api/ecom/product/delete-product/{productId}").
                then().log().all().extract().asString();
        System.out.println(deleteProductResponse);

    }
}
