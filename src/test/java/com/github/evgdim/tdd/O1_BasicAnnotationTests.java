package com.github.evgdim.tdd;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class O1_BasicAnnotationTests {

    @BeforeClass
    public static void runOnceBeforeClass() {
        log.info("@BeforeClass - runOnceBeforeClass");
    }

    @AfterClass
    public static void runOnceAfterClass() {
    	log.info("@AfterClass - runOnceAfterClass");
    }

    @Before
    public void runBeforeTestMethod() {
    	log.info("    @Before - runBeforeTestMethod");
    }

    @After
    public void runAfterTestMethod() {
    	log.info("    @After - runAfterTestMethod");
    }

    @Test
    public void test_method_1() {
    	log.info("        @Test - test_method_1 :: " + this);
    }

    @Test
    public void test_method_2() {
    	log.info("        @Test - test_method_2 :: " + this);
    }

}