package com.github.evgdim.tdd;

import static org.assertj.core.api.Assertions.assertThat;
import static com.github.tomakehurst.wiremock.client.WireMock.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.evgdim.tdd.entity.Person;
import com.github.tomakehurst.wiremock.junit.WireMockRule;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class O4_FullRunningServerTests {
	@Rule
	public WireMockRule wireMockRule = new WireMockRule(8888, 8889);
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	public void health_shoudlReturn200() {
		ResponseEntity<String> entity = this.restTemplate.getForEntity("/health", String.class);
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
	}
	
	@Test
	public void externalSystemPeople_shoudlReturn200AndAPerson() {
		stubFor(get(urlEqualTo("/people"))
	            .willReturn(aResponse()
	                .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
	                .withBodyFile("person.json")));
	            
		ResponseEntity<Person> entity = this.restTemplate.getForEntity("/external", Person.class);
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(entity.getBody().getAge()).isEqualTo(29);
		assertThat(entity.getBody().getName()).isEqualTo("Evgeni");
	}
}
