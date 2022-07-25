package com.cybertek.day03;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class ORDSApiTestsWithParameters {


    @BeforeAll
    public static void init(){
        //save baseurl inside this variable so that we dont need to type each http method.
        baseURI = "http://44.202.119.26:1000/ords/hr";
    }

    /*
        Given accept type is Json
        And parameters: q = {"region_id":2}
        When users sends a GET request to "/countries"
        Then status code is 200
        And Content type is application/json
        And Payload should contain "United States of America"
     */

    @DisplayName("GET request to /countries with Query Param")
    @Test
    public void test1(){

        Response response =  given().accept(ContentType.JSON)
                .and().queryParam("q","{\"region_id\":2}")
                .log().all()
                .when()
                .get("/countries");


        assertEquals(200,response.statusCode());
        assertEquals("application/json",response.header("Content-Type"));
        assertTrue(response.body().asString().contains("United States of America"));

        response.prettyPrint();


    }



    /*
        Send a GET request to employees and get only employees who works as a IT_PROG
     */

    @DisplayName("GET request to /employees with Query Param")
    @Test
    public void test2(){
        Response response= given().accept(ContentType.JSON)
                .and().queryParam("q","{\"job_id\": \"IT_PROG\"}")
                .when().get("/employees");

        assertEquals(200,response.statusCode());
        assertEquals("application/json",response.header("Content-Type"));
        assertTrue(response.body().asString().contains("IT_PROG"));

        // response.prettyPrint();

        // make sure we have only IT_PROG as a job_id
        List<String> allJobID = response.path("items.job_id");

        for (String eachJobID : allJobID) {
            System.out.println("eachJobID = " + eachJobID);
            assertEquals("IT_PROG",eachJobID);
        }

        // print name of each IT_PROG

        List<String> allProgsName = response.path("items.findAll{it.job_id==\"IT_PROG\"} .first_name");

        for (String eachProg : allProgsName) {
            System.out.println("eachProg = " + eachProg);
        }


    }


}
