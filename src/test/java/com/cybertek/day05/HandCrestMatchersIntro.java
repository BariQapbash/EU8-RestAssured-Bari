package com.cybertek.day05;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;


public class HandCrestMatchersIntro {

    @Test
    public void test1(){

        // actual 5+5
        assertThat(5+5, is(10));
        assertThat(5+5,equalTo(10));
        //matchers has 2 overloaded version
        //first that accept actual value
        //second taht accept another matchers
        //below examples is method is accepting another matchers equal to make it readable
        assertThat(5+5,is(equalTo(10)));

        assertThat(5+5,not(9));
        assertThat(5+5,is(not(9)));
        assertThat(5+5,is(not(equalTo(9))));

        //number comparison
        //greaterThan()
        //greaterThanOrEqualTo()
        //lessThan()
        //lessThanOrEqualTo()
        assertThat(5+5,is(greaterThan(9)));
        assertThat(5+5,is(lessThan(12)));


    }



    @DisplayName("Assertion with String")
    @Test
    public void stringHamcrest() {

        String text = "EU8 is learning Hamcrest";

        // checking for equality is same as number
        assertThat(text, is("EU8 is learning Hamcrest"));
        assertThat(text,equalTo("EU8 is learning Hamcrest"));
        assertThat(text,is(equalTo("EU8 is learning Hamcrest")));

        // check if this text is starts with EU8
        assertThat(text,startsWith("EU8"));

        // now do it in case insensitive manner
        assertThat(text,startsWithIgnoringCase("eu8"));

        // endWith
        assertThat(text,endsWith("est"));
        assertThat(text,endsWithIgnoringCase("Est"));

        // check if text contains String learning
        assertThat(text,containsString("learning"));
        assertThat(text,containsStringIgnoringCase("LEARNING"));


        String str = " ";
        // check if above str is blank
        assertThat(str, blankString());

        // check if trimmed str is empty string
        // assertThat(str,emptyString());
        assertThat(str.trim(),emptyString());

    }



    @DisplayName("Hamcrest for Collection")
    @Test
    public void testCollection() {

        List<Integer> listOfNumbers = Arrays.asList(1, 4, 5, 6, 32, 54, 66, 77, 45, 23);

        // check size of the list
        assertThat(listOfNumbers,hasSize(10));

        // check if this list has item 77
        assertThat(listOfNumbers,hasItem(77));

        // check if this list has items 77,54,23
        assertThat(listOfNumbers,hasItems(77,54,23));

        // check all items are grater than 0
        assertThat(listOfNumbers,everyItem(is(greaterThan(0))));

    }

}
