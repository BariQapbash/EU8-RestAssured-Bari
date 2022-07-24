package com.cybertek.day03;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.baseURI;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanTestsWithParameters {

    @BeforeAll
    public static void init() {
        //save baseurl inside this variable so that we dont need to type each http method.
        baseURI = "http://44.202.119.26:8000";
    }

        /*
        Given accept type is Json
        And id parameters value is 5
        When users sends a GET  request to /api/spartans/{id}
        Then status code is 200
        And Content type is application/json
        And "Blythe" should be in response payload
     */

    @DisplayName("Get request to /api/spartans/{id} with ID 5")
    @Test
    public void test1() {

        Response response = given().
                accept(ContentType.JSON)
                .and().pathParams("id", 5)
                .when()
                .get("/api/spartans/{id}");

        // verify status code
        assertEquals(200,response.statusCode());

        // verify contentType
        assertEquals("application/json",response.contentType());

        // Verify Blythe in the json payload/body
        assertTrue(response.body().asString().contains("Blythe"));

    }


}
