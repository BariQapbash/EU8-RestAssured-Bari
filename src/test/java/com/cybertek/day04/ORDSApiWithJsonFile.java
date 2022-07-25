package com.cybertek.day04;

import com.cybertek.utilities.HRTestBase;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class ORDSApiWithJsonFile extends HRTestBase {

    @DisplayName("GET request to Countries")
    @Test
    public void test1() {


        Response response = get("/countries");

        // het the second country name with jsonPath

        // to use jsonPath we assign response to jsonpath
        JsonPath jsonPath = response.jsonPath();

        String secondCountryName = jsonPath.get("items[1].country_name");
        System.out.println("secondCountryName = " + secondCountryName);

        // get all country id
        List<String> allCountryId = jsonPath.getList("items.country_id");
        System.out.println("allCountryId = " + allCountryId);

        // get all country names where their region id is equal to 2
        List<Integer> countryNameWithId2 = jsonPath.getList("items.findAll {it.region_id==2}.country_name");
        System.out.println("countryNameWithId2 = " + countryNameWithId2);


    }


    @DisplayName("GET requesto /employees with query param")
    @Test
    public void test2() {
        //we added limit query param to get 107 employees
        Response response = given().queryParam("limit", 107)
                .when().get("/employees");

        JsonPath jsonPath = response.jsonPath();


        // get email of employees who is working as IT_PROG
        List <String> employeeItProgs = jsonPath.getList("items.findAll {it.job_id==\"IT_PROG\"}.email");
        System.out.println("employeeItProgs = " + employeeItProgs);

        // get me firstname of employees who is making more than 10000
        List<String> employeesEarnMoreThan10000 = jsonPath.getList("items.findAll {it.salary>10000}.first_name");
        System.out.println("employeesEarnMoreThan10000 = " + employeesEarnMoreThan10000);


    }





}
