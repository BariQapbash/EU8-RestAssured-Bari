package com.cybertek.day05;

import com.cybertek.utilities.SpartanTestBase;
import io.restassured.response.Response;
import org.hamcrest.Matcher;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class JsonToJavaTest extends SpartanTestBase{


    @DisplayName("GET one spartan and deserialize to Map")
    @Test
    public void oneSpartanToMap(){

        Response response = given().pathParams("id",15)
                .when().get("/api/spartans/{id}")
                .then().statusCode(200).extract().response();

        // get the json and convert it to Map
        Map<String,Object> jsonMap = response.as(Map.class);

        System.out.println(jsonMap.toString());

        //after we got the map, we can use hamcrest or junit assertions to do assertion
        String actualName = (String) jsonMap.get("name");
        assertThat(actualName,is("Meta"));
    }

    @DisplayName("GET all spartans to JAVA data Structure")
    @Test
    public void getAllSpartans(){

        Response response = get("/api/spartans/").then().statusCode(200).extract().response();

        // we need to convert json to Java which is deserialize

        List<Map<String,Object>> jsonList = response.as(List.class);

        System.out.println("jsonList.get(1).get(\"name\") = " + jsonList.get(1).get("name"));

        Map<String,Object> spartans3 = jsonList.get(2);
        System.out.println("spartans3 = " + spartans3);

    }




}





