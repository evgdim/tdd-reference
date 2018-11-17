package com.github.evgdim.tdd.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.github.evgdim.tdd.entity.Person;
import com.github.evgdim.tdd.repository.PersonRepository;

@Service
public class PersonServiceImpl implements BusinessService {
	private final PersonRepository personRepository;
		
	public PersonServiceImpl(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}

	public Person checkPerson(Long id) {
		Optional<Person> optionalPerson = this.personRepository.findById(id);
		return optionalPerson
				.filter(p -> p.getAge() > 18)
				.orElseThrow(() -> new PersonNotOldEnoughException());
	}

}
