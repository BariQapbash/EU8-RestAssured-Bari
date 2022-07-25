package com.cybertek.day05;

import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;

public class HandCrestMatchersIntro {

    @Test
    public void test1(){

        assertThat(5+5, Matchers.is(10));
    }


}
