package com.testtask.api.tests;

import com.testtask.api.clients.BookingClient;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import io.qameta.allure.restassured.AllureRestAssured;
import java.util.HashMap;
import java.util.Map;

public abstract class BaseApiTest {
    static {
        System.setProperty("jsse.enableSNIExtension", "false");
    }
    protected static final String BASE_URL = "https://restful-booker.herokuapp.com";
    protected RequestSpecification requestSpec;
    protected String authToken;
    protected BookingClient bookingClient;

    @Parameters({ "api.username", "api.password" })
    @BeforeClass(groups = { "api" }, alwaysRun = true)
    public void setUp() {
        String username = System.getProperty("api.username", "admin");
        String password = System.getProperty("api.password", "password123");
        RestAssured.useRelaxedHTTPSValidation();
        RestAssured.baseURI = BASE_URL;

        requestSpec = new RequestSpecBuilder()
                .addFilter(new AllureRestAssured())
                .setContentType("application/json")
                .setAccept("application/json")
                .build();

        authToken = obtainAuthToken(username, password);

        bookingClient = new BookingClient(requestSpec);
    }

    private String obtainAuthToken(String username, String password) {
        Map<String, String> authBody = new HashMap<>();
        authBody.put("username", username);
        authBody.put("password", password);

        return RestAssured.given()
                .relaxedHTTPSValidation()
                .contentType(ContentType.JSON)
                .body(authBody)
                .when()
                .post("/auth")
                .then()
                .statusCode(200)
                .extract()
                .path("token");
    }
}