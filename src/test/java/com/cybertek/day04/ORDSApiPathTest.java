package com.cybertek.day04;

import com.cybertek.utilities.HRTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class ORDSApiPathTest extends HRTestBase {

    @DisplayName("GET request to countries with path method")
    @Test
    public void test1(){

        Response response= given().accept(ContentType.JSON)
                .queryParam("q","{\"region_id\":2}")
                .when()
                .get("/countries");

        assertEquals(200,response.statusCode());

        // print limit result
        System.out.println("response.path(\"limit\") = " + response.path("limit"));

        // print has more
        System.out.println("response.path(\"hasMore\") = " + response.path("hasMore"));

        // print first country
        String firstCountryID = response.path("items[0].country_id");
        System.out.println("firstCountryID = " + firstCountryID);

        // print second country name
        String secondCountryName = response.path("items[1].country_name");
        System.out.println("secondCountryName = " + secondCountryName);

        // print "http://44.202.119.26:1000/ords/hr/countries/CA"
        String thirdHref = response.path("items[2].links[0].href");
        System.out.println("thirdHref = " + thirdHref);
        assertEquals(thirdHref,"http://44.202.119.26:1000/ords/hr/countries/CA");

        // get all countries name
        List<String> countryNames = response.path("items.country_name");
        System.out.println("countryNames = " + countryNames);

        //assert that all regions ids are equal to 2
        List<Integer> regionsID = response.path("items.region_id");

        for (Integer eachRegionId : regionsID) {
            System.out.println("eachRegionId = " + eachRegionId);
            assertEquals(2,eachRegionId);
        }



    }

}
