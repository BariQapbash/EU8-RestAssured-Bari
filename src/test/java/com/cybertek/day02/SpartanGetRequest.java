package com.cybertek.day02;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SpartanGetRequest {

    String baseUrl = "http://44.204.1.124:8000";

    //    Given Accept type application/json
    //    When user send GET request to api/spartans end point
    //    Then status code must 200
    //    And response Content Type must be application/
    //    And response body should include spartan result


    @Test
    public void test1(){

        Response response = RestAssured.given().accept(ContentType.JSON)
                .when()
                .get(baseUrl+"/api/spartans");

        // printing status code from response object
        System.out.println("response.statusCode() = " + response.statusCode());

        // printing response contentType from response object
        System.out.println("response.contentType() = " + response.contentType());

        // print whole result body
        response.prettyPrint();

        // how to do API resting then
        // verify status code is 200
        Assertions.assertEquals(response.statusCode(),200);

        // verify content type is application/json
        Assertions.assertEquals(response.contentType(),"application/json");

    }

    /*
        Given accept header is application/json
        When users sends a get request to /api/spartans/3
        Then status code should be 200
        And content type should be application/json
        and json body should contain Fidole
     */

    @DisplayName("Get one spartan /api/spartan/3 and verify")
    @Test
    public void test2(){
        Response response = RestAssured.given().accept(ContentType.JSON).
                when().get(baseUrl+"/api/spartans/3");

        // verify status code 200
        Assertions.assertEquals(response.statusCode(),200);

        // verify content type
        Assertions.assertEquals(response.contentType(),"application/json");

        // verify json body contains Fidole
        Assertions.assertTrue(response.body().asString().contains("Fidole"));

    }

     /*
        Given no headers provided
        When Users sends GET request to /api/hello
        Then response status code should be 200
        And Content type header should be “text/plain;charset=UTF-8”
        And header should contain date
        And Content-Length should be 17
        And body should be “Hello from Sparta"
        */

    @DisplayName("GET request to /api/hello")
    @Test
    public void test3(){

        // send request and save response inside the response object
        Response response = RestAssured.when().get(baseUrl+"/api/hello");


        // verify status code 200
        Assertions.assertEquals(response.statusCode(),200);

        // verify content type
        Assertions.assertEquals("text/plain;charset=UTF-8",response.contentType());


        // verify we have headers named date
        // we use hasHeaderWithName method to get any header value
        Assertions.assertTrue( response.headers().hasHeaderWithName("Date"));

        // how to get any header from response using header key?
        System.out.println("response.header(\"Content-length\") = " + response.header("Content-length"));
        System.out.println(response.header("Date"));


        // verify content length is 17
        Assertions.assertEquals("17",response.header("Content-length"));

        // verify body is "Hello from Sparta"
        Assertions.assertEquals("Hello from Sparta",response.body().asString());

    }





}
