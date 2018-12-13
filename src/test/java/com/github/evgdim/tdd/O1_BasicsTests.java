package com.github.evgdim.tdd;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class O1_BasicsTests {
	@Rule
	public MyCustomRule myRule = new MyCustomRule();
	
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

@Slf4j
class MyCustomRule implements TestRule {

	@Override
	public Statement apply(Statement base, Description description) {
		return new Statement() {
			
			@Override
			public void evaluate() throws Throwable {
				log.info("               @MyCustomRule before {}", description);
				base.evaluate();
				log.info("               @MyCustomRule after {}", description);
			}
		};
	}
	
}