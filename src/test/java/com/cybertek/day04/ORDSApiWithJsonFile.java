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


    }
}
