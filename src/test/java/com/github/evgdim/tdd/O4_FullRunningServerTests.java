package com.github.evgdim.tdd;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockserver.junit.MockServerRule;
import org.mockserver.matchers.MatchType;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;
import org.mockserver.model.JsonBody;
import org.mockserver.model.StringBody;
import org.mockserver.verify.VerificationTimes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.evgdim.tdd.entity.Person;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, properties = {
		"tddref.account.url=http://localhost:8888"
})
public class O4_FullRunningServerTests {
	@Rule
	public MockServerRule mockServer = new MockServerRule(this, 8888);
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	public void health_shoudlReturn200() {
		ResponseEntity<String> entity = this.restTemplate.getForEntity("/health", String.class);
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
	}
	
	@Test
	public void externalSystemPeople_shoudlReturn200AndAPerson() throws IOException {
		try(InputStream personFileStream = new ClassPathResource("__files/person.json").getInputStream()) {
			String personData = IOUtils.toString(personFileStream, StandardCharsets.UTF_8);
			mockServer.getClient().when(HttpRequest.request()
													.withMethod("GET")
													.withPath("/people"))
			                      .respond(HttpResponse.response()
			                    		  				.withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
			                    		  				.withBody(personData));
		            
			ResponseEntity<Person> entity = this.restTemplate.getForEntity("/external", Person.class);
			assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
			assertThat(entity.getBody().getAge()).isEqualTo(29);
			assertThat(entity.getBody().getName()).isEqualTo("Evgeni");
		}
	}
	
	@Test
	public void externalSystemPeople_shoudlBeCalled() throws IOException {
		try(InputStream personFileStream = new ClassPathResource("__files/person.json").getInputStream()) {
			String personData = IOUtils.toString(personFileStream, StandardCharsets.UTF_8);
			
			mockServer.getClient().when(HttpRequest.request()
													.withMethod("GET")
													.withPath("/people"))
									  .respond(HttpResponse.response()
											  				.withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
											  				.withBody(personData));
			
			this.restTemplate.getForEntity("/external", Person.class);
			
			mockServer.getClient().verify(
					HttpRequest.request().withMethod("GET").withPath("/people"), 
					VerificationTimes.exactly(1)
			);
		}
	            
	}
}
