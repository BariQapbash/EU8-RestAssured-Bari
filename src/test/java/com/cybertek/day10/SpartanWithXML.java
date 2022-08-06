package com.cybertek.day10;

import com.cybertek.utilities.SpartanAuthTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class SpartanWithXML extends SpartanAuthTestBase {


    @DisplayName("GET request to / api/spartans ans verify xml")
    @Test
    public void getSpartanXml(){
        // we will ask for xml response
        // assert status code us 200
        // assert content type is xml(we get xml response)
        // verify first data name is Meade

        given()
                .accept(ContentType.XML)
                .and()
                .auth().basic("admin","admin")
                .when()
                .get("/api/spartans")
                .then()
                .statusCode(200)
                .contentType("application/xml;charset=UTF-8")
                .body("list.item[0].name",is("Meade"))
                .body("list.item[0].gender",is("Male"))
                .log().all();

    }


    @DisplayName("GET request /api/spartans with xmlPath")
    @Test
    public void testXmlPath(){

        Response response = given()
                .accept(ContentType.XML)
                .and()
                .auth().basic("admin","admin")
                .when()
                .get("/api/spartans");


        // get response xml body.payload and save inside the xmlPath Object
        XmlPath xmlPath = response.xmlPath();

        String name = xmlPath.getString("List.item[0].name");
        System.out.println("name = " + name);

        int id = xmlPath.getInt("List.item[2].id");
        System.out.println("id = " + id);

        // how we get all names and save into list of string

        List<String> names = xmlPath.getList("list.item.name");
        System.out.println("names = " + names);

    }



}
