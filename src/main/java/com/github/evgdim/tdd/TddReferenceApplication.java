package com.github.evgdim.tdd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.github.evgdim.tdd.entity.Person;
import com.github.evgdim.tdd.repository.PersonRepository;

@SpringBootApplication
public class TddReferenceApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(TddReferenceApplication.class, args);
		PersonRepository personRepository = ctx.getBean(PersonRepository.class);
		personRepository.save(new Person(null, "Evgeni", 29));
	}
}
