package com.github.evgdim.tdd.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.github.evgdim.tdd.entity.Person;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class PerformanceTestController {
	
	@GetMapping("/hello")
	public Person hello() throws InterruptedException {
		Thread.sleep((long)(2000 * Math.random()));
		log.info("[hello]");
		return new Person(1L, "Test", 5); 
	}
	
	@GetMapping("/next/{id}")
	public Person next(@PathVariable("id") String id) throws InterruptedException {
		Thread.sleep((long)(2000 * Math.random()));
		log.info("[next] {}", id);
		return new Person(1L, "Test", 5); 
	}
}

