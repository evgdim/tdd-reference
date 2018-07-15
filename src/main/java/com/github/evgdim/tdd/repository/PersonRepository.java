package com.github.evgdim.tdd.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.evgdim.tdd.entity.Person;

public interface PersonRepository extends JpaRepository<Person, Long>{
	public Optional<Person> findByName(String name);
}
