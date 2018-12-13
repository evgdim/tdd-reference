package com.github.evgdim.tdd;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.evgdim.tdd.configuration.TddProperties;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest(
		properties = {"tddref.random100=my-test-property", "tddref.random1=my-test1-property"} //adds/overrides property
)
@TestPropertySource(properties= {"tddref.random3=my-test-property"}) //same as @SpringBootTest::properties, but also check the java doc for the default behavior
@RunWith(SpringRunner.class)
@Slf4j
public class O6_PropertiesTests {
	@Autowired
	private Environment env;
	@Autowired
	private TddProperties props;
	@Test
	public void printAllProperties() {
		log.info("[properties] tddref.random100: {}", env.getProperty("tddref.random100"));
		log.info("[properties] tddref.random1: {}", env.getProperty("tddref.random1"));
		log.info("[properties] tddref.random2: {}", env.getProperty("tddref.random2"));
		log.info("[properties] tddref.random3: {}", env.getProperty("tddref.random3"));
		log.info("[properties] {}", props);
	}
}
