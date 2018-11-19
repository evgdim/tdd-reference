package com.github.evgdim.tdd.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.github.evgdim.tdd.entity.Person;
import com.github.evgdim.tdd.repository.PersonRepository;

@Service
public class PersonServiceImpl implements PersonService {
	private final PersonRepository personRepository;
	private final AccountClient accountClient;
		
	public PersonServiceImpl(PersonRepository personRepository, AccountClient accountClient) {
		this.personRepository = personRepository;
		this.accountClient = accountClient;
	}

	public Person checkPerson(Long id) {
		Optional<Person> optionalPerson = this.personRepository.findById(id);
		return optionalPerson
				.filter(p -> p.getAge() > 18)
				.map(p -> setAccounts(p))
				.orElseThrow(() -> new PersonNotOldEnoughException());
	}

	private Person setAccounts(Person p) {
		p.setAccounts(accountClient.findAccounts(p.getId())); 
		return p;
	}

}
