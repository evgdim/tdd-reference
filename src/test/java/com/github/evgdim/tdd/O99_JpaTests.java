package com.github.evgdim.tdd;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import javax.persistence.PersistenceException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.evgdim.tdd.entity.Person;
import com.github.evgdim.tdd.repository.PersonRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class O99_JpaTests {
	@Autowired
	private TestEntityManager em;
	@Autowired
	private PersonRepository personRepo;
	
	@Test
	public void testPersonMapping() {
		Person person = new Person(null, "Evgeni", 29);
		Person persistedPerson = em.persistFlushFind(person );
		assertThat(persistedPerson.getId()).isNotNull();
	}
	
	@Test
	public void findByName_shouldReturnTheEntity() {
		Person person = new Person(null, "Evgeni", 29);
		em.persistAndFlush(person);
		Optional<Person> findByName = this.personRepo.findByName(person.getName());
		assertThat(findByName).isNotNull();
		assertThat(findByName.map(p -> p.getName()).get()).isEqualTo(person.getName());
	}
	
	@Test(expected=PersistenceException.class)
	public void saveWithSameName_shouldThrowException() {
		Person person = new Person(null, "Evgeni", 29);
		em.persistAndFlush(person);
		Person person2 = new Person(null, "Evgeni", 30);
		em.persistAndFlush(person2);
	}
}
