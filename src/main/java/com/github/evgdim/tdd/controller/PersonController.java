package com.github.evgdim.tdd.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.evgdim.tdd.entity.Person;
import com.github.evgdim.tdd.service.PersonServiceImpl;

@RestController
@RequestMapping("/people")
public class PersonController {
	private final PersonServiceImpl businessService;
	
	public PersonController(PersonServiceImpl businessService) {
		this.businessService = businessService;
	}

	@GetMapping("/{id}")
	public Person get(@PathVariable Long id) {
		return this.businessService.checkPerson(id);
	}
}
