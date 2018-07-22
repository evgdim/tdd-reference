package com.github.evgdim.tdd.controller;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.github.evgdim.tdd.entity.Person;

@RestController
@RequestMapping("external")
public class ExternalSystemController {
	private final RestTemplate rt;
	public ExternalSystemController() {
		this.rt = new RestTemplateBuilder().rootUri("http://localhost:8888").build();
	}
	@GetMapping
	public Person call() {
		ResponseEntity<Person> entity = rt.getForEntity("/people", Person.class);
		return entity.getBody();
	}
}
