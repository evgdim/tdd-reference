package com.github.evgdim.tdd;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.github.evgdim.tdd.entity.Person;
import com.github.evgdim.tdd.repository.PersonRepository;
import com.github.evgdim.tdd.service.PersonNotOldEnoughException;
import com.github.evgdim.tdd.service.PersonServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class O3_MockitoTests {
	@Mock private PersonRepository personRepository;
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void checkPerson_shouldReturnIt_whenItsOver18() {
		//given
		when(personRepository.findById(1L)).thenReturn(Optional.of(kiroAge20()));
		
		//when
		PersonServiceImpl personServ= new PersonServiceImpl(personRepository);
		Person person = personServ.checkPerson(1L);
		
		//should
		assertThat(person.getName()).isNotBlank();
	}
	
	@Test
	public void checkPerson_shouldThrowPersonNotOldEnoughException_whenItsUnder18() {
		//given
		when(personRepository.findById(1L)).thenReturn(Optional.of(atanasAge15()));
		
		//should
		this.thrown.expect(PersonNotOldEnoughException.class);
		
		//when
		PersonServiceImpl personServ= new PersonServiceImpl(personRepository);
		personServ.checkPerson(1L);
		
	}

	private Person kiroAge20() {
		return new Person(1L, "Kiro", 20);
	}
	
	private Person atanasAge15() {
		return new Person(1L, "Atanas", 15);
	}
}
