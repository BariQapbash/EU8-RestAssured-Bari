package com.cybertek.day12;

import com.cybertek.utilities.SpartanNewBase;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class OldRestAssuredTest extends SpartanNewBase {

    @Test
    public void getAllSpartans(){

        given()
                .accept(ContentType.JSON)
                .and()
                .auth().basic("admin","admin")
                .log().all()
                .when()
                .get("/spartans")
                .then().statusCode(200)
                .contentType(ContentType.JSON)
                .body("id[0]", is(10))
                .body("id[1]",is(199))
                .log().all();

    }

    @Test
    public void test2(){


        /*
            in previous version of RestAssured, the given when then style
            was actually written in given expect when format.
            it will assert all the result and give one answer and does not fail whole thing
            if first one fail unlike new structure.
         */
        given().accept(ContentType.JSON)
                .and()
                .auth().basic("admin","admin")
                .log().all()
                .expect()
                .statusCode(200)
                .and()
                .contentType(ContentType.JSON)
                .body("id[0]",is(10))
                .body("id[2]",is(199))
                .logDetail(LogDetail.ALL) // log way using with expected
                .when()
                .get("/spartans");
    }


}
